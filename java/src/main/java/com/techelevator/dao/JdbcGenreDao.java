package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Genre;
import com.techelevator.model.GenreDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class  JdbcGenreDao implements GenreDao {

    private JdbcTemplate template;

    public JdbcGenreDao(DataSource ds) {
        template = new JdbcTemplate(ds);
    }

    @Override
    public List<Genre> getAllGenres() {

        List<Genre> genres = new ArrayList<>();
        String sql = "SELECT * FROM genres";

        try {
            SqlRowSet results = template.queryForRowSet(sql);
            while (results.next()) {
                genres.add(mapRowToGenre(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }
        return genres;
    }

    @Override
    public List<String> getAllGenreNames() {

        List<String> genres = new ArrayList<>();
        String sql = "SELECT genre_name FROM genres";

        try {
            SqlRowSet results = template.queryForRowSet(sql);
            while (results.next()) {
                genres.add(results.getString("genre_name"));
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }

        return genres;

    }



    @Override
    public Genre getGenreByName(String genreName) {

        Genre genre = new Genre();
        String sql = "SELECT * FROM genres WHERE genre_name = ?;";

        try {
            SqlRowSet results = template.queryForRowSet(sql, genreName);
            if (results.next()) {
                genre = mapRowToGenre(results);
            } else {
                throw new DaoException("Could not find genre.");
            }

        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }


        return genre;
    }

    @Override
    public void addGenre(GenreDto genre) {
        String sql = "INSERT INTO genres (genre_name, visible) VALUES (?, true)";
        String newGenreName = genre.getName();

        try {
            System.out.println(newGenreName);
            template.update(sql, newGenreName);
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }
    }

    @Override
    public void updateGenres(List<Genre> genres){
        String sql = "UPDATE genres SET visible = ? WHERE genre_id = ?;";

        try {
            for (Genre genre : genres) {
                template.update(sql, genre.visible, genre.getGenreId());
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }

    }

    private Genre mapRowToGenre(SqlRowSet rowSet) {
        Genre genre = new Genre();
        genre.setGenreId(rowSet.getLong("genre_id"));
        genre.setGenreName(rowSet.getString("genre_name"));
        genre.setVisible(rowSet.getBoolean("visible"));
        return genre;
    }

}
