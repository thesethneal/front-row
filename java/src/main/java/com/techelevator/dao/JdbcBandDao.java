package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Band;
import com.techelevator.model.BandGenreDto;
import com.techelevator.model.Genre;
import com.techelevator.model.RoleDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcBandDao implements BandDao {

    private JdbcTemplate template;

    public JdbcBandDao(DataSource ds) {
        template = new JdbcTemplate(ds);
    }

    @Override
    public List<BandGenreDto> getSubscribedBands(Principal principal) {
        List<BandGenreDto> bands = new ArrayList<>();

        String sql = "SELECT * FROM bands\n" +
                "\tJOIN user_band ON bands.band_id = user_band.band_id\n" +
                "\tWHERE user_band.user_id = ?\n" +
                "\tORDER BY REGEXP_REPLACE(band_name, '^(The |An |A )', '', 'i');";

        long principalId = getUserIdByUsername(principal.getName());

        try {
            SqlRowSet results = template.queryForRowSet(sql, principalId);
            while (results.next()) {
                bands.add(mapRowToBandGenreDto(results));
            }
            for (BandGenreDto band : bands) {
                String bandName = band.getBand().getBandName();
                band.setGenreNames(getGenresByBandName(bandName));
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }

        return bands;
    }

    @Override
    public Band getBandByBandId(long bandId) {

        Band band = new Band();
        String sql = "SELECT * FROM bands WHERE band_id = ?;";

        try {
            SqlRowSet results = template.queryForRowSet(sql, bandId);
            if (results.next()) {
                band = mapRowToBand(results);
            } else {
                throw new DaoException("Band not found by given ID.");
            }

        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }

        return band;
    }

    @Override
    public BandGenreDto getBandByBandName(String bandName) {
        BandGenreDto band = new BandGenreDto();

        String sql = "SELECT * FROM bands WHERE band_name = ?;";

        try {
            SqlRowSet results = template.queryForRowSet(sql, bandName);
            if (results.next()) {
                band = mapRowToBandGenreDto(results);
                band.setGenreNames(getGenresByBandName(bandName));

            } else {
                throw new DaoException("Band not found by given name.");
            }

        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }

        return band;
    }

    @Override
    public List<BandGenreDto> getAllBands() {
        List<BandGenreDto> bands = new ArrayList<>();
        String sql = "SELECT * FROM bands;";

        try {
            SqlRowSet results = template.queryForRowSet(sql);
            while (results.next()) {
                bands.add(mapRowToBandGenreDto(results));
            }
            for (BandGenreDto band : bands) {
                String bandName = band.getBand().getBandName();
                band.setGenreNames(getGenresByBandName(bandName));
            }

        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems" + e.getMessage());
        }

        return bands;
    }
// TODO
    @Override
    public void updateBand(BandGenreDto updatedBand) {

        String sql = "UPDATE bands " +
                "SET band_name = ?, band_description = ?, band_hero_image = ?\n" +
                "\tWHERE band_id = ?;";

        try {
            int rowsAffected = template.update(sql,
                    updatedBand.getBand().getBandName(),
                    updatedBand.getBand().getBandDescription(),
                    updatedBand.getBand().getBandHeroImage(),
                    updatedBand.getBand().getBandId());
            if (rowsAffected == 0) {
                throw new DaoException("No bands affected. Expected at least one.");
            }
            unlinkGenres(updatedBand.getBand().getBandId());
            linkGenres(updatedBand.getGenreNames(), updatedBand.getBand().getBandId());

        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }

    }

    @Override
    public void createBand(BandGenreDto newBand, Principal principal) {

        long principalId = getUserIdByUsername(principal.getName());

        long newBandId = addBand(newBand, principalId);
        linkGenres(newBand.getGenreNames(), newBandId);

    }

    @Override
    public List<BandGenreDto> searchBandGenre(String searchTerm) {

        List<BandGenreDto> bandGenreDtos = new ArrayList<>();

        String sql = "SELECT * FROM bands " +
                "WHERE band_name ILIKE ? " +
                "ORDER BY REGEXP_REPLACE(band_name, '^(The |An |A )', '', 'i');";

        searchTerm = "%" + searchTerm + "%";

        try {
            SqlRowSet results = template.queryForRowSet(sql, searchTerm);
            while (results.next()) {
                bandGenreDtos.add(mapRowToBandGenreDto(results));
            }
            for (BandGenreDto band : bandGenreDtos) {
                String bandName = band.getBand().getBandName();
                band.setGenreNames(getGenresByBandName(bandName));
            }

        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems" + e.getMessage());
        }

        return bandGenreDtos;
    }

    @Override
    public void subscribe(long bandId, Principal principal) {

        String checkSql = "SELECT COUNT(*) FROM user_band " +
                "WHERE user_id = ? AND band_id = ?;";

        String sql = "INSERT INTO user_band (user_id, band_id) " +
                "VALUES (?,?);";

        String sqlMessages = "INSERT INTO message_user (message_id, user_id, is_read) " +
                "SELECT m.message_id, ?, FALSE " +
                "FROM messages m " +
                "LEFT JOIN message_user mu ON m.message_id = mu.message_id AND mu.user_id = ? " +
                "WHERE m.message_sender = ? AND mu.message_id IS NULL;";

        long principalId = getUserIdByUsername(principal.getName());

        try {
            int count = template.queryForObject(checkSql, Integer.class, principalId, bandId);
            if (count > 0) {
                return;
            } else {
                template.update(sql, principalId, bandId);
                template.update(sqlMessages, principalId, principalId, bandId);
            }

        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }
    }

    public void unsubscribe(long bandId, Principal principal) {
        String sql = "DELETE FROM user_band " +
                "WHERE user_id = ? " +
                "AND band_id = ?;";

        long principalId = getUserIdByUsername(principal.getName());

        try {
            template.update(sql, principalId, bandId);
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }

    }

    public boolean isFollowing(long bandId, Principal principal) {
        String sql = "SELECT COUNT(*) FROM user_band " +
                "WHERE user_id = ? AND band_id =?;";

        long principalId = getUserIdByUsername(principal.getName());
        boolean following = false;

        try {
            long count = template.queryForObject(sql, Integer.class, principalId, bandId);
            if (count > 0) {
                following = true;
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }
        return following;
    }

    @Override
    public RoleDto getRoleAndManagedBands(Principal principal) {

        String role = getRoleByUsername(principal.getName());
        RoleDto principalInfo = new RoleDto(role);

        if (principalInfo.getRole().equals("ROLE_BAND")) {
            long principalId = getUserIdByUsername(principal.getName());

            List<Band> bands = getBandsManagedByUserId(principalId);
            if (bands.size() == 0){
                updateUser(principal, "ROLE_USER");
                principalInfo.setRole("ROLE_USER");
                return principalInfo;
            }
            principalInfo.setManagedBands(bands);
        }

        return principalInfo;
    }

    @Override
    public void updateUser(Principal principal, String role) {
        String sql = "UPDATE users\n" +
                "\tSET role = ?\n" +
                "\tWHERE user_id = ?;";

        role = role.toUpperCase();
        long principalId = getUserIdByUsername(principal.getName());

        try {
            int rowsAffected = template.update(sql, role, principalId);
            if (rowsAffected == 0) {
                throw new DaoException("No users affected. Expected at least one.");
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }
    }


    public List<BandGenreDto> getManagedBands(Principal principal) {
        List<BandGenreDto> bands = new ArrayList<>();
        String sql = "SELECT * FROM bands WHERE band_manager_id = ?;";
        long principalId = getUserIdByUsername(principal.getName());

        try {
            SqlRowSet results = template.queryForRowSet(sql, principalId);
            while (results.next()) {
                bands.add(mapRowToBandGenreDto(results));
            }
            for (BandGenreDto band : bands) {
                String bandName = band.getBand().getBandName();
                band.setGenreNames(getGenresByBandName(bandName));
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }

        return bands;
    }


    // Private methods below
    private long getUserIdByUsername(String username) {
        String sql = "SELECT user_id FROM users WHERE username = ?;";
        long userId = -1;

        try {
            userId = template.queryForObject(sql, new Object[]{username}, Long.class);
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems 2");
        }
        return userId;
    }

    private long getBandByOwnerId(long id) {
        String sql = "SELECT band_id FROM bands WHERE band_manager_id = ?;";
        long userId = -1;

        try {
            SqlRowSet results = template.queryForRowSet(sql, id);
            if (results.next()) {
                userId = results.getLong("band_manager_id");
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }

        return userId;
    }

    private Band mapRowToBand(SqlRowSet rowSet) {
        Band band = new Band();

        band.setBandId(rowSet.getLong("band_id"));
        band.setBandName(rowSet.getString("band_name"));
        band.setBandDescription(rowSet.getString("band_description"));
        band.setBandHeroImage(rowSet.getString("band_hero_image"));
        band.setBandManagerId(rowSet.getLong("band_manager_id"));

        return band;

    }

    private BandGenreDto mapRowToBandGenreDto(SqlRowSet rowSet) {
        BandGenreDto bandGenreDto = new BandGenreDto();
        bandGenreDto.setBand(mapRowToBand(rowSet));
        return bandGenreDto;
    }

    private List<String> getGenresByBandName(String bandName) {
        List<String> genres = new ArrayList<>();

        String sql = "SELECT genre_name FROM genres\n" +
                "\tJOIN band_genre ON genres.genre_id = band_genre.genre_id\n" +
                "\tJOIN bands ON band_genre.band_id = bands.band_id\n" +
                "\tWHERE band_name = ? " +
                "ORDER BY REGEXP_REPLACE(band_name, '^(The |An |A )', '', 'i');";

        try {
            SqlRowSet results = template.queryForRowSet(sql, bandName);
            while(results.next()) {
                genres.add(results.getString("genre_name"));
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }

        return genres;
    }

    private String getRoleByUsername(String username) {
        String sql = "SELECT role FROM users WHERE username = ?;";
        String userRole = "";

        try {
            SqlRowSet result = template.queryForRowSet(sql, username);
            if (result.next()) {
                userRole = result.getString("role");
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }

        return userRole;
    }

    private List<Band> getBandsManagedByUserId(long userId) {
        List<Band> bands = new ArrayList<>();
        String sql = "SELECT * FROM bands WHERE band_manager_id = ?;";

        try {
            SqlRowSet results = template.queryForRowSet(sql, userId);
            while (results.next()) {
                bands.add(mapRowToBand(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }

        return bands;
    }

    private void linkGenres(List<String> genreNames, long bandId) {
        String sql = "INSERT INTO band_genre (band_id, genre_id)\n" +
                "\tVALUES (?, ?);";

        try {
            for (String genre : genreNames) {
                long genreId = getGenreIdByName(genre);
                template.update(sql, bandId, genreId);
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems" + e.getMessage());
        }
    }

    private void unlinkGenres(long bandId) {
        String sql = "DELETE FROM band_genre\n" +
                "\tWHERE band_id = ?;";

        try {
            template.update(sql, bandId);
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems" + e.getMessage());
        }
    }

    private long getGenreIdByName(String genreName) {

        long genreId = -1;
        String sql = "SELECT genre_id FROM genres WHERE genre_name = ?;";

        try {
            SqlRowSet results = template.queryForRowSet(sql, genreName);
            if (results.next()) {
                genreId = results.getLong("genre_id");
            } else {
                throw new DaoException("Could not find genre.");
            }

        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }


        return genreId;
    }

    private long addBand(BandGenreDto newBand, long managerId) {
        long newBandId = -1;

        String sql = "INSERT INTO bands (band_name,band_description," +
                "band_manager_id,band_hero_image) VALUES (?,?,?,?) " +
                "RETURNING band_id;";

        try {
            newBandId = template.queryForObject(sql, new Object[] {
                    newBand.getBand().getBandName(),
                    newBand.getBand().getBandDescription(),
                    managerId,
                    newBand.getBand().getBandHeroImage()}, Integer.class);
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems" + e.getMessage());
        }

        return newBandId;
    }

}