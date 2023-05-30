package com.maksfood;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

import org.json.JSONArray;

import com.maksfood.Ingredient.Unit;
import com.mysql.cj.xdevapi.JsonArray;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
// import java.awt.event.MouseEvent;
// import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;
import java.util.List;
// import java.util.List;
import java.awt.event.ActionEvent;

public class RecipesPanel extends RoundedPanel implements ActionListener {
    
    public MainWindow window;
    // public JList<String> myList;
    DefaultListModel<String> model = new DefaultListModel<>();
    DefaultListModel<String> recipesModel = new DefaultListModel<>();
    JList<String> myList = new JList<>( model );
    JList<String> favRecipes = new JList<>( recipesModel );
    public RecipesGenerator rg = new RecipesGenerator();
    public ListModel<String> d;
    public JTextField mealName = new JTextField();
    List<Recipe> fav_recipes_list = new ArrayList<Recipe>();


    public RecipesPanel(LayoutManager layout, int r, MainWindow w) {
        super(layout, r);
        window = w;

        // creating label
        DefaultLabel recipesLabel = new DefaultLabel("Your products",25);
        DefaultLabel favRecipesLabel = new DefaultLabel("Favourite recipes",25);

        // creating return button
        ColorButton returnButton = new ColorButton("Return");
        window.setButton(returnButton, this);

        // creating button and label panels
        JPanel recipesLabelPanel = new JPanel(new GridLayout());
        recipesLabelPanel.setOpaque(false);
        JPanel returnButtonPanel = new JPanel(new GridLayout());
        recipesLabelPanel.add(recipesLabel);
        returnButtonPanel.add(returnButton);
        recipesLabelPanel.setBounds(getVisibleRect());
        
        setOpaque(false);
        setBackground(new Color(255, 238, 219, 200));

        // adding elements to RecipesPanel
        GridBagConstraints e = new GridBagConstraints();
        e.insets = new Insets(10, 10, 10, 10);
        e.gridx = 0;
        e.gridy = 0;
        this.add(recipesLabelPanel, e);



        update_products();

        myList.setSelectionMode(
                    ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        myList.setBackground(new Color(165, 56, 96));
        // myList.setSize(200, 100);
        myList.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 20));
        myList.setForeground(Color.WHITE);
        // myList.setFixedCellHeight(30);
        // myList.setFixedCellWidth(120);
        myList.setSelectionBackground(Color.WHITE);
        myList.setSelectionForeground(new Color(165, 56, 96));
        DefaultListCellRenderer renderer =  (DefaultListCellRenderer)myList.getCellRenderer();  
        renderer.setHorizontalAlignment(JLabel.CENTER);  
        renderer.setVerticalAlignment(JLabel.CENTER);  
        // renderer.set
        ColorButton selectButton = new ColorButton("find recipes");
        JScrollPane scrollPane = new JScrollPane(myList);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setMinimumSize(new Dimension(300,400));
        scrollPane.setMaximumSize(new Dimension(300, 500));
        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(76, 59, 77);
            }
        });

        
        mealName.setBackground(new Color(255, 238, 219));
        // mealName.setMinimumSize(new Dimension(200, 200));
        mealName.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 15));
        mealName.setForeground(new Color(76, 59, 77));
        mealName.setMargin(new Insets(5, 10, 5, 10));
        mealName.setPreferredSize(new Dimension(200, 35));
        // mealName.setBounds(100, 100, 100, 100);
        JPanel textPanel = new JPanel(new GridLayout());
        // textPanel.setSize(200, 200);
        textPanel.add(mealName);
        ColorButton findByNameButton = new ColorButton("Find by name");
        window.setButton(findByNameButton, this);
        JPanel findByNamePanel = new JPanel(new GridLayout());
        findByNamePanel.add(findByNameButton);

        ColorButton showDetailsButton = new ColorButton("Show details");
        window.setButton(showDetailsButton, this);
        
        ColorButton removeFromFavButton = new ColorButton("Remove recipe");
        window.setButton(removeFromFavButton, this);

        favRecipes.setSelectionMode(
            ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            favRecipes.setBackground(new Color(165, 56, 96));
            // myList.setSize(200, 100);
        favRecipes.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 20));
        favRecipes.setForeground(Color.WHITE);
        // myList.setFixedCellHeight(30);
        // myList.setFixedCellWidth(120);
        favRecipes.setSelectionBackground(Color.WHITE);
        favRecipes.setSelectionForeground(new Color(165, 56, 96));
        favRecipes.setFixedCellHeight(30);
        favRecipes.setFixedCellWidth(300);
        
        JScrollPane recipeScrollPane = new JScrollPane(favRecipes);
        recipeScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        recipeScrollPane.setMinimumSize(new Dimension(300,400));
        recipeScrollPane.setMaximumSize(new Dimension(300, 500));
        recipeScrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(76, 59, 77);
            }
        });
        // scrollPane.setVerticalScrollBar(scrollBar);
        myList.setFixedCellHeight(30);
        myList.setFixedCellWidth(300);
        window.setButton(selectButton, this);
        
        e.gridx = 0;
        e.gridy = 1;
        this.add(scrollPane, e);
        e.gridx = 0;
        e.gridy = 2;
        this.add(selectButton, e);
        e.gridx = 1;
        e.gridy = 1;
        this.add(recipeScrollPane, e);
        e.gridx = 0;
        e.gridy = 3;
        this.add(textPanel,e);
        e.gridx = 0;
        e.gridy = 4;
        this.add(findByNamePanel,e);
        e.gridx = 1;
        e.gridy = 0;
        this.add(favRecipesLabel,e);
        e.gridx = 1;
        e.gridy = 2;
        this.add(showDetailsButton,e);
        e.gridx = 1;
        e.gridy = 3;
        this.add(removeFromFavButton,e);
        e.gridx = 1;
        e.gridy = 4;
        this.add(returnButtonPanel, e);
    }
    public void update_products(){
        DataBase fridgeDB = window.fridgePanel.fridgeDB;
        model.clear();
        fridgeDB.sqlSelect("select * from maksfood.fridge");
        Vector<String> fridgeElements = fridgeDB.getElements(100, 2);
        // forfridgeElements.size()
        for (String elem : fridgeElements) {
            model.addElement(elem);
        }
    }

    public void update_recipes(){
        window.recipesListPanel.recipe_model.clear();
        for (Recipe r : rg.recipes_list) {
            window.recipesListPanel.recipe_model.addElement(r.recipe_text);
        }
    }

    public void update_fav_recipes_from_db(){
        fav_recipes_list.clear();
        DataBase recipeDB = window.fridgePanel.fridgeDB;
        recipeDB.sqlSelect("select name from maksfood.favourite_recipe");
        Vector<String> recipesNameElements = recipeDB.getElements(1);
        System.out.println(recipesNameElements.toString());
        recipeDB.sqlSelect("select ingredients from maksfood.favourite_recipe");
        Vector<String> ingredientsNameElements = recipeDB.getElements(1);
        System.out.println(ingredientsNameElements.toString());
        recipeDB.sqlSelect("select photo_link from maksfood.favourite_recipe");
        Vector<String> photo_links = recipeDB.getElements(1);
        System.out.println(photo_links.toString());
        recipeDB.sqlSelect("select recipe_link from maksfood.favourite_recipe");
        Vector<String> url_to_recipes = recipeDB.getElements(1);
        System.out.println(url_to_recipes.toString());
        for(int i = 0; i < recipesNameElements.size(); i++){
            Recipe recipe = new Recipe();
            recipe.recipe_text = recipesNameElements.get(i);
            // String[] splitted_ingredient_list = ingredientsNameElements.get(i).split(",");
            String formatted_string = ingredientsNameElements.get(i).replace("[[", "[");
            String second_format_string = formatted_string.replace("]]", "]");
            System.out.println(second_format_string);
            JSONArray ja = new JSONArray("["+second_format_string+"]");
            for(int j=0;j < ja.length(); j++){
                recipe.ingredient_lines.add(ja.getString(j));
            }
            recipe.link_to_photo = photo_links.get(i);
            recipe.link_to_recipe = url_to_recipes.get(i);
            fav_recipes_list.add(recipe) ;
        }
    }

    public void update_favourite_recipes(){
        recipesModel.clear();
        for (Recipe recipe : fav_recipes_list) {
            recipesModel.addElement(recipe.recipe_text);
        }
    }

    public void deleteSelectedElement(){
        int index = favRecipes.getSelectedIndex();
        window.fridgePanel.fridgeDB.sqlUpdate("DELETE FROM maksfood.favourite_recipe WHERE id=" + Integer.toString(index+1));
        window.fridgePanel.fridgeDB.fixFavouriteRecipesIds(index+1);
        update_fav_recipes_from_db();
        update_favourite_recipes();
    }

    // button click perform
    @Override
    public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("Return")) {

        window.currentPanel.setVisible(false);

        window.currentPanel = window.menuPanel;

        window.currentPanel.setVisible(true);
    }
    if (e.getActionCommand().equals("find recipes")){
        window.currentPanel.setVisible(false);
        window.currentPanel = window.recipesListPanel;
        window.getContentPane().add(window.recipesListPanel);

        window.currentPanel.setVisible(true);

        java.util.List<String> s = new ArrayList<String>();
        s = myList.getSelectedValuesList();
        rg.clear_ingredients();
        rg.clear_recipes();
        System.out.print(s);
        for (String string : s) {
            Ingredient i = new Ingredient(string, Ingredient.Unit.amount, 5);
            rg.add_ingredient(i);
        }
        System.out.print(rg.ingredient_list);
        rg.send_request_to_api();
        update_recipes();
    }
    if (e.getActionCommand().equals("Find by name")) {

        window.currentPanel.setVisible(false);
        window.currentPanel = window.recipesListPanel;
        window.getContentPane().add(window.recipesListPanel);

        window.currentPanel.setVisible(true);
        rg.clear_ingredients();
        rg.clear_recipes();
        String s = new String();
        s = mealName.getText();
        Ingredient i = new Ingredient(s,Unit.amount,1);
        rg.add_ingredient(i);
        rg.send_request_to_api();
        update_recipes();

    }
    if (e.getActionCommand().equals("Remove recipe")) {
        deleteSelectedElement();
    }
    if (e.getActionCommand().equals("Show details")) {
        window.currentPanel.setVisible(false);
        window.currentPanel = window.recipeDetalisPanel;
        window.getContentPane().add(window.recipeDetalisPanel);
        window.recipeDetalisPanel.previousPanel = this;
        int i = favRecipes.getSelectedIndex();
        try {
            window.recipeDetalisPanel.update_details(fav_recipes_list.get(i));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        window.currentPanel.setVisible(true);
    }
}
}