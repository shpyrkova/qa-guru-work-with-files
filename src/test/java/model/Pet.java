package model;

import java.util.List;

public class Pet {

    private String name;
    private String species;
    private Integer birthYear;
    private String colour;
    private List<String> favFoods;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public Object[] getFavFoods() {
        return new List[]{favFoods};
    }

    public void setFavFoods(List<String> favFoods) {
        this.favFoods = favFoods;
    }

}
