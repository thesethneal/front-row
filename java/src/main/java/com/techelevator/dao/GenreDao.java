package com.techelevator.dao;

import com.techelevator.model.Genre;
import com.techelevator.model.GenreDto;

import java.util.List;

public interface GenreDao {

    List<Genre> getAllGenres();

    Genre getGenreByName(String genreName);

    List<String> getAllGenreNames();

    void addGenre(GenreDto genre);

    void updateGenres(List<Genre> genres);

}
