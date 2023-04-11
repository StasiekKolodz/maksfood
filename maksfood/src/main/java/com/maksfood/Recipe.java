package com.maksfood;

// import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Recipe {
    
    public String recipe_text;
    public List<Ingredient> ingredient_list = new ArrayList<Ingredient>();
    public List<String> ingredient_lines = new ArrayList<String>();
    public String link_to_recipe;
    public String link_to_photo;

    public Recipe(){
    }

    public void add_ingredeint_lines(ArrayList<String> list){
        ingredient_lines = list;
    }
}
