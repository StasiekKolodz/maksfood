package com.maksfood;

// import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Recipe {
    
    String recipe_text;
    List<Ingredient> ingredient_list = new ArrayList<Ingredient>();
    List<String> ingredient_lines = new ArrayList<String>();

    public Recipe(){
    }

    public void add_ingredeint_lines(ArrayList<String> list){
        ingredient_lines = list;
    }
}
