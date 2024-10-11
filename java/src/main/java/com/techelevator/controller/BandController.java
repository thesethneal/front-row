package com.techelevator.controller;

import com.techelevator.dao.BandDao;
import com.techelevator.dao.EventDao;
import com.techelevator.model.Band;
import com.techelevator.model.BandGenreDto;
import com.techelevator.model.EventBandDto;
import com.techelevator.model.RoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.annotation.HttpConstraint;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class BandController {

    @Autowired
    private BandDao bandDao;

    @GetMapping("/bands")
    public List<BandGenreDto> getAllBands() {
        return bandDao.getAllBands();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/search-bands/{searchTerm}")
    public List<BandGenreDto> searchBands(@PathVariable String searchTerm) {
        return bandDao.searchBandGenre(searchTerm);
    }

    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{bandId}/update")
    public void updateBand(@RequestBody @Valid BandGenreDto band, @PathVariable long bandId) {
        if (bandId != band.getBand().getBandId()) {
            throw new IllegalArgumentException("Band name in the path does not match the request body");
        }
        bandDao.updateBand(band);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/my-bands")
    public List<BandGenreDto> getSubscribedBands(Principal principal) {
        return bandDao.getSubscribedBands(principal);
    }

    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create-band")
    public void createBand(@RequestBody @Valid BandGenreDto band, Principal principal) {
        bandDao.createBand(band, principal);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{bandName}")
    public BandGenreDto getBandByName(@PathVariable String bandName) {
        return bandDao.getBandByBandName(bandName);
    }

    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/subscribe/{bandId}")
    public void subscribeToBand(@PathVariable long bandId, Principal principal) {
        bandDao.subscribe(bandId, principal);
    }

    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/unsubscribe/{bandId}")
    public void unsubscribeFromBand(@PathVariable long bandId, Principal principal) {
        bandDao.unsubscribe(bandId, principal);
    }

    @GetMapping("/is-following/{bandId}")
    public boolean isFollowing(@PathVariable long bandId, Principal principal) {
        return bandDao.isFollowing(bandId, principal);

    }

    @GetMapping("/user-role")
    public RoleDto getRoleAndManagedBands(Principal principal) {
        return bandDao.getRoleAndManagedBands(principal);
    }

    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/change-role/{role}")
    public void updateRole(Principal principal, @PathVariable String role) {
        bandDao.updateUser(principal, role);
    }

    @GetMapping("/managed-bands")
    public List<BandGenreDto> getManagedBands(Principal principal) {
        return bandDao.getManagedBands(principal);
    }

}
