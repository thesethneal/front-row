package com.techelevator.controller;

import com.techelevator.dao.GenreDao;
import com.techelevator.model.Genre;
import com.techelevator.model.GenreDto;
import com.techelevator.model.ImageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class GenreController {

    @Autowired
    private GenreDao genreDao;

    @GetMapping("/genres")
    public List<String> getAllGenres() {
        return genreDao.getAllGenreNames();
    }

    @GetMapping("/all-genres")
    public List<Genre> getGenresToManage(){
        return genreDao.getAllGenres();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/add-genre")
    public void addGenre(@RequestBody GenreDto genre){
        genreDao.addGenre(genre);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/update-genres")
    public void updateGenres(@RequestBody List<Genre> genres){
        genreDao.updateGenres(genres);
    }

}
