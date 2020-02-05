package com.example.myapplication.pokemonClass;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.myapplication.R;

import java.io.Serializable;

public class Pokemon implements Serializable {
    private String name;
    private String type1;
    private String type2;
    private String number;
    private String image;
    private String ability1;
    private String ability2;
    private String hiddenAbility;
    private String hp;
    private String atk;
    private String def;
    private String spA;
    private String spD;
    private String speed;


    public Pokemon (String name, String type1, String type2 , String number, String image, String ability1, String ability2, String hiddenAbility, String hp,
                    String atk, String def, String spA, String spD, String speed){
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.number = number;
        this.image = image;
        this.ability1 = ability1;
        this.ability2 = ability2;
        this.hiddenAbility = hiddenAbility;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.spA = spA;
        this.spD = spD;
        this.speed = speed;
    }
    private Boolean isTypeValid(String type){
        String types[] = {"Normal", "Fire", "Water", "Electric", "Grass", "Ice", "Fighting", "Poison", "Ground", "Flying", "Psychic",
                "Bug", "Rock", "Ghost", "Dragon", "Dark", "Steel", "Fairy"
        };
        for(int i = 0; i < types.length; i++){
            if(type.equals(types[i])){
                return true;
            }
        }
        return false;


    }

    public String getName() {
        return name;
    }

    public String getType1() {
        if(isTypeValid(type1)){
            return type1;
        }
        return "invalid";
    }

    public String getType2() {
        if(isTypeValid(type2)){
            return type2;
        }
        return "invalid";
    }

    public String getNumber() { return number; }

    public String getImage() { return image; }

    public String getAbility1() {
        return ability1;
    }

    public String getAbility2() {
        return ability2;
    }

    public String getAtk() {
        return atk;
    }

    public String getDef() {
        return def;
    }

    public String getHiddenAbility() {
        return hiddenAbility;
    }

    public String getHp() {
        return hp;
    }

    public String getSpA() {
        return spA;
    }

    public String getSpD() {
        return spD;
    }

    public String getSpeed() {
        return speed;
    }

    public int getTypeColor(String type, Context con) {

        String types[] = {"normal", "fire", "water", "electric", "grass", "ice", "fighting", "poison", "ground", "flying", "psychic",
                "bug", "rock", "ghost", "dragon", "dark", "steel", "fairy"
        };
        int colors[] = {ContextCompat.getColor(con, R.color.normal), ContextCompat.getColor(con, R.color.fire),
                ContextCompat.getColor(con, R.color.water), ContextCompat.getColor(con, R.color.electric),
                ContextCompat.getColor(con, R.color.grass), ContextCompat.getColor(con, R.color.ice),
                ContextCompat.getColor(con, R.color.fighting), ContextCompat.getColor(con, R.color.poison),
                ContextCompat.getColor(con, R.color.ground), ContextCompat.getColor(con, R.color.flying),
                ContextCompat.getColor(con, R.color.psychic), ContextCompat.getColor(con, R.color.bug),
                ContextCompat.getColor(con, R.color.rock), ContextCompat.getColor(con, R.color.ghost),
                ContextCompat.getColor(con, R.color.dragon), ContextCompat.getColor(con, R.color.dark),
                ContextCompat.getColor(con, R.color.steel), ContextCompat.getColor(con, R.color.fairy)
        };
        for(int i = 0; i < types.length; i++){
            if(type.equals(types[i])){
                return colors[i];
            }
        }

        return 0;
    }
}
