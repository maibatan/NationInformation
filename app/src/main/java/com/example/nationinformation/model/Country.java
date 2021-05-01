package com.example.nationinformation.model;

public class Country {
    private String name;
    private String population;
    private String areaInSqKm;
    private String countryCode;


    
    public Country(String name, String population, String areaInSqKm) {
        this.name = name;

        this.population = population;
        this.areaInSqKm = areaInSqKm;
    }

    public Country(String name, String population, String areaInSqKm, String countryCode) {
        this.name = name;
        this.population = population;
        this.areaInSqKm = areaInSqKm;
        this.countryCode = countryCode;
    }

    public String getName() {
        return name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getPopulation() {
        return population;
    }

    public String getAreaInSqKm() {
        return areaInSqKm;
    }
}
