package com.example.mobile_assignment2.task1.task1b;

public class CatBreed {
    String name;
    String life_span;
    String origin;
    String temperament;
    String description;

    public CatBreed() {

    }

    public CatBreed(String name, String life_span, String origin, String temperament, String description) {
        this.name = name;
        this.life_span = life_span;
        this.origin = origin;
        this.temperament = temperament;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getLife_span() {
        return life_span;
    }

    public String getOrigin() {
        return origin;
    }

    public String getTemperament() {
        return temperament;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLife_span(String life_span) {
        this.life_span = life_span;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CatBreed{" +
                "name='" + name + '\'' +
                ", life_span='" + life_span + '\'' +
                ", origin='" + origin + '\'' +
                ", temperament='" + temperament + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
