package com.example.nationinformation.model;

public class Nation {
    private String name;
    private String ensignURL;
    private int population;
    private int acreage;

    public Nation(String name, String ensignURL, int population, int acreage) {
        this.name = name;
        this.ensignURL = ensignURL;
        this.population = population;
        this.acreage = acreage;
    }

    public String getName() {
        return name;
    }

    public String getEnsignURL() {
        return ensignURL;
    }

    public int getPopulation() {
        return population;
    }

    public int getAcreage() {
        return acreage;
    }
}
