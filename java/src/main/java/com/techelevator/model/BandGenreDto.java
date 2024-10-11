package com.techelevator.model;

import java.util.List;

public class BandGenreDto {

    private Band band;
    private List<String> genreNames;

    public Band getBand() {
        return band;
    }

    public void setBand(Band band) {
        this.band = band;
    }

    public List<String> getGenreNames() {
        return genreNames;
    }

    public void setGenreNames(List<String> genreNames) {
        this.genreNames = genreNames;
    }
}
