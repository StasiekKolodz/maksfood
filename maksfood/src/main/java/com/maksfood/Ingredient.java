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
        //TODO: exception when kg or liter unit
        this.name = name;
        this.unit = unit;
        this.integer_amount = amount;
        this.float_amount = 0;;
    }

    public Ingredient(String name, Unit unit, float amount) {
        //TODO: exception whem amount unit
        this.name = name;
        this.unit = unit;
        this.integer_amount = 0;
        this.float_amount = amount;
    }

    public void increaseAmount(int amount){
        //TODO: exception when kg or liter unit
        this.integer_amount += amount;
    }

    public void increaseAmount(float amount){
        //TODO: exception whem amount unit
        this.float_amount += amount;
    }
    
    public void decreaseAmount(int amount){
        //TODO: exception when kg or liter unit
        this.integer_amount -= amount;
    }

    public void decreaseAmount(float amount){
        //TODO: exception whem amount unit
        this.float_amount -= amount;
    }

    public void changeAmount(int new_amount){
        //TODO: exception when kg or liter unit
        this.integer_amount = new_amount;
    }

    public void changeAmount(float new_amount){
        //TODO: exception whem amount unit
        this.float_amount = new_amount;
    }

    public void changeUnit(Unit new_unit){
        this.unit = new_unit;
    }

    public String get_name(){
        return this.name;
    }
}