package model;

public class Pet {

    private String name;
    private String species;
    private Integer birthYear;
    private String colour;
    private String[]  favFoods;

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

    public String[] getFavFoods() {
        return favFoods;
    }

    public void setFavFoods(String[] favFoods) {
        this.favFoods = favFoods;
    }

}
