package com.techelevator.dao;

import com.techelevator.model.Band;
import com.techelevator.model.BandGenreDto;
import com.techelevator.model.Genre;
import com.techelevator.model.RoleDto;

import java.security.Principal;
import java.util.List;

public interface BandDao {

    List<BandGenreDto> getSubscribedBands(Principal principal);

    Band getBandByBandId(long bandId);
    BandGenreDto getBandByBandName(String bandName);
    // but what if there are
    // multiple bands with the same name???
    List<BandGenreDto> getAllBands();

    void updateBand(BandGenreDto updatedBand);

    void createBand(BandGenreDto newBand, Principal principal);

    List<BandGenreDto> searchBandGenre(String searchTerm);

    void subscribe(long bandId, Principal principal);

    void unsubscribe(long bandId, Principal principal);

    boolean isFollowing(long bandId, Principal principal);

    RoleDto getRoleAndManagedBands(Principal principal);

    void updateUser(Principal principal, String role);
    List<BandGenreDto> getManagedBands(Principal principal);


}
