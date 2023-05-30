package com.maksfood;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
// import java.awt.event.MouseEvent;
// import java.awt.event.MouseListener;
import java.util.ArrayList;
// import java.util.List;
import java.awt.event.ActionEvent;

public class RecipesListPanel extends RoundedPanel implements ActionListener {
    
    public MainWindow window;
    public DefaultListModel<String> recipe_model = new DefaultListModel<>();
    public JList<String> recipeList = new JList<>(recipe_model);

    public RecipesListPanel(LayoutManager layout, int r, MainWindow w) {
        super(layout, r);
        window = w;

        // creating label
        JLabel recipesLabel = new JLabel("Recipes List");
        recipesLabel.setForeground(new Color(165, 56, 96));
        recipesLabel.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 30));

        // creating return button
        ColorButton returnButton = new ColorButton("Return");
        window.setButton(returnButton, this);

        ColorButton showDetailsButton = new ColorButton("Show Details");
        window.setButton(showDetailsButton, this);

        ColorButton addToFavButton = new ColorButton("Add to favourite");
        window.setButton(addToFavButton, this);

        // creating button and label panels
        JPanel recipesLabelPanel = new JPanel(new GridLayout());
        recipesLabelPanel.setOpaque(false);
        JPanel returnButtonPanel = new JPanel(new GridLayout());
        recipesLabelPanel.add(recipesLabel);
        returnButtonPanel.add(returnButton);
        recipesLabelPanel.setBounds(getVisibleRect());
        
        setOpaque(false);
        setBackground(new Color(255, 238, 219));

        recipeList.setSelectionMode(
            ListSelectionModel.SINGLE_SELECTION);
        recipeList.setBackground(new Color(165, 56, 96));
        // recipeList.setSize(200, 100);
        recipeList.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 17));
        recipeList.setForeground(Color.WHITE);
        recipeList.setFixedCellHeight(30);
        recipeList.setFixedCellWidth(400);
        recipeList.setSelectionBackground(Color.WHITE);
        recipeList.setSelectionForeground(new Color(165, 56, 96));
        DefaultListCellRenderer renderer =  (DefaultListCellRenderer)recipeList.getCellRenderer();  
        renderer.setHorizontalAlignment(JLabel.CENTER);  
        renderer.setVerticalAlignment(JLabel.CENTER); 

        JScrollPane recipeScrollList = new JScrollPane(recipeList);
        recipeScrollList.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(76, 59, 77);
            }
        });

        // adding elements to RecipesPanel
        GridBagConstraints e = new GridBagConstraints();
        e.insets = new Insets(10, 10, 10, 10);
        e.gridwidth = 2;
        e.gridx = 0;
        e.gridy = 0;
        this.add(recipesLabelPanel, e);

        e.gridwidth = 2;
        e.gridx = 0;
        e.gridy = 1;
        this.add(recipeScrollList, e);
        
        e.gridwidth = 1;
        e.gridx = 0;
        e.gridy = 2;
        this.add(showDetailsButton,e);
        
        e.gridx = 1;
        e.gridy = 2;
        this.add(addToFavButton,e);

        e.gridwidth = 2;
        e.gridx = 0;
        e.gridy = 4;
        this.add(returnButtonPanel, e);
    }


    // button click perform
    @Override
    public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("Return")) {

        window.currentPanel.setVisible(false);

        window.currentPanel = window.recipesPanel;

        window.currentPanel.setVisible(true);
    }
    if (e.getActionCommand().equals("Show Details")) {
        window.currentPanel.setVisible(false);

        window.currentPanel = window.recipeDetalisPanel;
        window.recipeDetalisPanel.previousPanel = this;
        window.getContentPane().add(window.recipeDetalisPanel);
        
        int i = recipeList.getSelectedIndex();
        try {
            window.recipeDetalisPanel.update_details(window.recipesPanel.rg.recipes_list.get(i));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        window.currentPanel.setVisible(true);
    }
    if (e.getActionCommand().equals("Add to favourite")) {

        // window.currentPanel.setVisible(false);
        // System.out.println("sthasdas");
        int recipe_index = recipeList.getSelectedIndex();
        Recipe chosen_recipe = window.recipesPanel.rg.recipes_list.get(recipe_index);
        String ingredients = "";
        for (int j = 0; j<chosen_recipe.ingredient_lines.size(); j++) {
            String ing_line = chosen_recipe.ingredient_lines.get(j).replace("\"","\'");
            if(j!=chosen_recipe.ingredient_lines.size()-1){
            ingredients = ingredients + "\""+ ing_line+"\"" +",";
            }
            else{
            ingredients = ingredients + "\"" + ing_line+"\""; 
            }
        }
        String replaced = ingredients.replace("\'", "\\"+"\'");
        // String removing_stars = replaced.replace("*", "\\"+"*");
        System.out.println("xxxxxxxxxxxx"+replaced);
        window.dataBase.sqlUpdate("INSERT INTO maksfood.favourite_recipe VALUES(DEFAULT,'"+
        chosen_recipe.recipe_text.replace("\'", "\\"+"\'") + "','" + replaced + "','" + chosen_recipe.link_to_photo
        + "','" + chosen_recipe.link_to_recipe + "');");
        window.recipesPanel.update_fav_recipes_from_db();
        window.recipesPanel.update_favourite_recipes();
        }
        
    }
    
    
}
