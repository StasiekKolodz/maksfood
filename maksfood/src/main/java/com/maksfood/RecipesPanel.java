package com.maksfood;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicScrollBarUI;

import org.json.JSONArray;

import com.maksfood.Ingredient.Unit;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.List;
import java.awt.event.ActionEvent;

public class RecipesPanel extends RoundedPanel implements ActionListener {
    
    public MainWindow window;
    DefaultListModel<String> model = new DefaultListModel<>();
    DefaultListModel<String> recipesModel = new DefaultListModel<>();
    JList<String> productList = new JList<>( model );
    JList<String> favRecipes = new JList<>( recipesModel );
    public RecipesGenerator rg = new RecipesGenerator();
    public ListModel<String> d;
    public JTextField mealName = new JTextField();
    List<Recipe> fav_recipes_list = new ArrayList<Recipe>();
    public int[] setSelectedIndices;
    
    
    public RecipesPanel(LayoutManager layout, int r, MainWindow w) {
        super(layout, r);
        window = w;
        
        // creating label
        DefaultLabel recipesLabel = new DefaultLabel("Your products",25);
        DefaultLabel favRecipesLabel = new DefaultLabel("Favourite recipes",25);
        
        // creating  buttons
        ColorButton returnButton = new ColorButton("Return");
        window.setButton(returnButton, this);
        
        ColorButton selectButton = new ColorButton("Find recipes");
        window.setButton(selectButton, this);
        
        ColorButton showDetailsButton = new ColorButton("Show details");
        window.setButton(showDetailsButton, this);
        
        ColorButton removeFromFavButton = new ColorButton("Remove recipe");
        window.setButton(removeFromFavButton, this);
        
        ColorButton findByNameButton = new ColorButton("Find by name");
        window.setButton(findByNameButton, this);


        // creating button and label panels
        JPanel recipesLabelPanel = new JPanel(new GridLayout());
        recipesLabelPanel.setOpaque(false);
        JPanel returnButtonPanel = new JPanel(new GridLayout());
        recipesLabelPanel.add(recipesLabel);
        returnButtonPanel.add(returnButton);
        recipesLabelPanel.setBounds(getVisibleRect());
        JPanel textPanel = new JPanel(new GridLayout());
        textPanel.add(mealName);
        
        
        // configuring visual sides of panel
        setOpaque(false);
        setBackground(new Color(255, 238, 219, 200));
        

        // creating scrollable lists
        productList.setSelectionMode(
            ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            productList.setBackground(new Color(165, 56, 96));
            productList.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 20));
            productList.setForeground(Color.WHITE);
            productList.setSelectionBackground(Color.WHITE);
            productList.setFixedCellHeight(30);
            productList.setFixedCellWidth(300);
        productList.setSelectionForeground(new Color(165, 56, 96));
        DefaultListCellRenderer renderer =  (DefaultListCellRenderer)productList.getCellRenderer();  

        renderer.setHorizontalAlignment(JLabel.CENTER);  
        renderer.setVerticalAlignment(JLabel.CENTER); 

        JScrollPane scrollPane = new JScrollPane(productList);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setMinimumSize(new Dimension(300,400));
        scrollPane.setMaximumSize(new Dimension(300, 500));
        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(76, 59, 77);
            }
        });

        favRecipes.setSelectionMode(
            ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        favRecipes.setBackground(new Color(165, 56, 96));
        favRecipes.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 20));
        favRecipes.setForeground(Color.WHITE);
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
        
        
        // creating text field
        mealName.setBackground(new Color(255, 238, 219));
        mealName.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 15));
        mealName.setForeground(new Color(76, 59, 77));
        mealName.setMargin(new Insets(5, 10, 5, 10));
        mealName.setPreferredSize(new Dimension(200, 35));
        JPanel findByNamePanel = new JPanel(new GridLayout());
        findByNamePanel.add(findByNameButton);
        
        
        // adding elements to RecipesPanel
        GridBagConstraints e = new GridBagConstraints();
        e.insets = new Insets(10, 10, 10, 10);
        e.gridx = 0;
        e.gridy = 0;
        this.add(recipesLabelPanel, e);
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


        // updating product list
        update_products();
    }

    public void update_products(){
        DataBase fridgeDB = window.fridgePanel.fridgeDB;
        model.clear();
        fridgeDB.sqlSelect("select * from maksfood.fridge");
        Vector<String> fridgeElements = fridgeDB.getElements(2);
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
        recipeDB.sqlSelect("select name from maksfood.favourite_recipe ORDER BY name");
        Vector<String> recipesNameElements = recipeDB.getElements(1);
        recipeDB.sqlSelect("select ingredients from maksfood.favourite_recipe");
        Vector<String> ingredientsNameElements = recipeDB.getElements(1);
        recipeDB.sqlSelect("select photo_link from maksfood.favourite_recipe");
        Vector<String> photo_links = recipeDB.getElements(1);
        recipeDB.sqlSelect("select recipe_link from maksfood.favourite_recipe");
        Vector<String> url_to_recipes = recipeDB.getElements(1);
        for(int i = 0; i < recipesNameElements.size(); i++){
            Recipe recipe = new Recipe();
            recipe.recipe_text = recipesNameElements.get(i);
            String formatted_string = ingredientsNameElements.get(i).replace("[[", "[");
            String second_format_string = formatted_string.replace("]]", "]");
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
        String name = favRecipes.getSelectedValue();
        window.fridgePanel.fridgeDB.sqlUpdate("DELETE FROM maksfood.favourite_recipe WHERE name='" + name + "'");
        // window.fridgePanel.fridgeDB.fixFavouriteRecipesIds(index+1);
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
    if (e.getActionCommand().equals("Find recipes")){
        window.currentPanel.setVisible(false);
        window.currentPanel = window.recipesListPanel;
        window.getContentPane().add(window.recipesListPanel);

        window.currentPanel.setVisible(true);

        java.util.List<String> s = new ArrayList<String>();
        s = productList.getSelectedValuesList();
        rg.clear_ingredients();
        rg.clear_recipes();
        for (String string : s) {
            Ingredient i = new Ingredient(string, Ingredient.Unit.amount, 5);
            rg.add_ingredient(i);
        }
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