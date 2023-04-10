package com.maksfood;

import javax.swing.*;
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


        // adding elements to RecipesPanel
        GridBagConstraints e = new GridBagConstraints();
        e.insets = new Insets(10, 10, 10, 10);
        e.gridx = 0;
        e.gridy = 0;
        this.add(recipesLabelPanel, e);

        e.gridx = 0;
        e.gridy = 1;
        this.add(new JScrollPane(recipeList), e);

        e.gridx = 0;
        e.gridy = 2;
        this.add(showDetailsButton,e);

        e.gridx = 0;
        e.gridy = 3;
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
        window.getContentPane().add(window.recipeDetalisPanel);
        
        int i = recipeList.getSelectedIndex();
        try {
            window.recipeDetalisPanel.update_details(i);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        window.currentPanel.setVisible(true);

        
    }
    
    
}
}