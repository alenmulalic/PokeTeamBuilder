package com.example.myapplication.pokemonClass;

public class Top100Pokemon extends Pokemon {

    private int topCount;

    public Top100Pokemon(String name, String type1, String type2, String number, String image, String ability1, String ability2, String hiddenAbility, String hp, String atk, String def, String spA, String spD, String speed, int topCount) {
        super(name, type1, type2, number, image, ability1, ability2, hiddenAbility, hp, atk, def, spA, spD, speed);
        this.topCount = topCount;
    }

    public int getTopCount() {
        return topCount;
    }
}
