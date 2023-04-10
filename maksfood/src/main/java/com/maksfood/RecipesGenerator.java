package com.maksfood;

import java.util.List;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RecipesGenerator {

    String baseURL = "https://edamam-recipe-search.p.rapidapi.com/search?q=";
    List<Ingredient> ingredient_list = new ArrayList<Ingredient>();
    List<Recipe> recipes_list = new ArrayList<Recipe>();
    OkHttpClient client = new OkHttpClient();
    
    RecipesGenerator(){
        
    }

    public void add_ingredient(Ingredient i){
        ingredient_list.add(i);
    }

    public void remove_ingredient(Ingredient i){
        ingredient_list.remove(i);
    }

    public void clear_ingredients(){
        ingredient_list.clear();
    }

    public void clear_recipes(){
        recipes_list.clear();
    }
    
    public void send_request_to_api(){
        String url = baseURL;
        for (int i = 0; i < ingredient_list.size(); i++) {
            if(i == ingredient_list.size()-1){
                url = url + ingredient_list.get(i).get_name();
            }
            else{
                url = url + ingredient_list.get(i).get_name() + "%2C%20";
            }
        }

        Request request = new Request.Builder()
            .url(url)
            .get()
            .addHeader("X-RapidAPI-Key", "4b68897bacmsh1fcb67fadd2486fp1f3220jsn73fece68c19d")
            .addHeader("X-RapidAPI-Host", "edamam-recipe-search.p.rapidapi.com")
            .build();
            try{
        Response response = client.newCall(request).execute();
        JSONObject o = new JSONObject(response.body().string());
        JSONArray hits = new JSONArray(o.get("hits").toString());
        List<JSONObject> hits_recipes = new ArrayList<JSONObject>();
        for (int i = 0; i<hits.length(); i++ ){
            JSONObject h = new JSONObject(hits.get(i).toString());
            JSONObject r = new JSONObject(h.get("recipe").toString());
            hits_recipes.add(r);
            String label = r.get("label").toString();
            JSONArray ingredientLines = new JSONArray(r.get("ingredientLines").toString());
            Recipe rec = new Recipe();
            ArrayList<String> recipe_ingredient_lines = new ArrayList<String>();
            for (int j = 0; j< ingredientLines.length(); j++){
                recipe_ingredient_lines.add(ingredientLines.get(j).toString());
            }
            rec.add_ingredeint_lines(recipe_ingredient_lines);
            rec.recipe_text = label;
            recipes_list.add(rec);
        }
        // System.out.println(o.toString());
        // System.out.println(recipes_list.get(0).recipe_text + ":");
        // for(int i = 0; i < recipes_list.get(0).ingredient_lines.size(); i++){
        //     System.out.println(recipes_list.get(0).ingredient_lines.get(i));
        // }
        // System.out.println(recipes_list.get(0));

            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
    }
    
}

