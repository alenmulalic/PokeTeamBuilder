package com.example.myapplication.pokemonItemClassAndActivity;

import java.io.Serializable;

public class PokeItem implements Serializable {
    private String id;
    private String  itemName;
    private String itemAbility;
    private String image;


    public PokeItem (String id, String itemName, String itemAbility, String image){
        this.id = id;
        this.itemName = itemName;
        this.itemAbility = itemAbility;
        this.image = image;
    }

    public String getId() {return id;}
    public String getItemName() {return itemName;}
    public String getItemAbility() {return itemAbility;}
    public String getImage() {return image;}
}
