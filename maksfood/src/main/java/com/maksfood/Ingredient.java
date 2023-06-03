package com.maksfood;

public class Ingredient {
    public enum Unit {
        kg, amount, l;
    }

    private String name;
    private Unit unit;
    private int integer_amount;
    private float float_amount;

    public Ingredient(String name, Unit unit, int amount) {
        this.name = name;
        this.unit = unit;
        this.integer_amount = amount;
        this.float_amount = 0;;
    }

    public Ingredient(String name, Unit unit, float amount) {
        this.name = name;
        this.unit = unit;
        this.integer_amount = 0;
        this.float_amount = amount;
    }

    public void increaseAmount(int amount){
        this.integer_amount += amount;
    }

    public void increaseAmount(float amount){
        this.float_amount += amount;
    }
    
    public void decreaseAmount(int amount){
        this.integer_amount -= amount;
    }

    public void decreaseAmount(float amount){
        this.float_amount -= amount;
    }

    public void changeAmount(int new_amount){
        this.integer_amount = new_amount;
    }

    public void changeAmount(float new_amount){
        this.float_amount = new_amount;
    }

    public void changeUnit(Unit new_unit){
        this.unit = new_unit;
    }

    public String get_name(){
        return this.name;
    }
}