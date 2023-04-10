package com.maksfood;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
// import java.awt.event.MouseEvent;
// import java.awt.event.MouseListener;
import java.util.ArrayList;
// import java.util.List;
import java.awt.event.ActionEvent;

public class RecipesPanel extends RoundedPanel implements ActionListener {
    
    public MainWindow window;
    // public JList<String> myList;
    DefaultListModel<String> model = new DefaultListModel<>();
    JList<String> myList = new JList<>( model );
    public RecipesGenerator rg = new RecipesGenerator();
    public String[] d = {"tomato", "cheese"};
    
    DefaultListModel<String> recipe_model = new DefaultListModel<>();
    JList<String> recipeList = new JList<>(recipe_model);

    public RecipesPanel(LayoutManager layout, int r, MainWindow w) {
        super(layout, r);
        window = w;

        // creating label
        JLabel recipesLabel = new JLabel("Recipes");
        recipesLabel.setForeground(new Color(165, 56, 96));
        recipesLabel.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 30));

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
        setBackground(new Color(255, 238, 219));

        // adding elements to RecipesPanel
        GridBagConstraints e = new GridBagConstraints();
        e.insets = new Insets(10, 10, 10, 10);
        e.gridx = 0;
        e.gridy = 0;
        this.add(recipesLabelPanel, e);

        e.gridx = 0;
        e.gridy = 3;
        this.add(returnButtonPanel, e);

        // array.add("tomatos");
        // array.add("cheese");
        
        for ( int i = 0; i < d.length; i++ ){
          model.addElement(d[i]);
        }

        myList.setSelectionMode(
                    ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        myList.setBackground(new Color(165, 56, 96));
        // myList.setSize(200, 100);
        myList.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 20));
        myList.setForeground(Color.WHITE);
        myList.setFixedCellHeight(30);
        myList.setFixedCellWidth(120);
        myList.setSelectionBackground(Color.WHITE);
        myList.setSelectionForeground(new Color(165, 56, 96));
        DefaultListCellRenderer renderer =  (DefaultListCellRenderer)myList.getCellRenderer();  
        renderer.setHorizontalAlignment(JLabel.CENTER);  
        renderer.setVerticalAlignment(JLabel.CENTER);  
        myList.scrollRectToVisible(getBounds());
        ColorButton selectButton = new ColorButton("find recipes");
        window.setButton(selectButton, this);
        e.gridx = 0;
        e.gridy = 1;
        this.add(myList, e);
        e.gridx = 0;
        e.gridy = 2;
        this.add(selectButton, e);
    }
    public void update_recipes(){
        recipe_model.clear();
        for (Recipe r : rg.recipes_list) {
            recipe_model.addElement(r.recipe_text);
        }
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
}
}