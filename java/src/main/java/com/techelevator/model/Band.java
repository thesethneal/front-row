package com.techelevator.model;

public class Band {

    private long bandId;
    private String bandName;
    private String bandDescription;
    private long bandManagerId;
    private String bandHeroImage;

    public long getBandId() {
        return bandId;
    }

    public void setBandId(long bandId) {
        this.bandId = bandId;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public String getBandDescription() {
        return bandDescription;
    }

    public void setBandDescription(String bandDescription) {
        this.bandDescription = bandDescription;
    }

    public long getBandManagerId() {
        return bandManagerId;
    }

    public void setBandManagerId(long bandManagerId) {
        this.bandManagerId = bandManagerId;
    }

    public String getBandHeroImage() {
        return bandHeroImage;
    }

    public void setBandHeroImage(String bandHeroImage) {
        this.bandHeroImage = bandHeroImage;
    }
}
