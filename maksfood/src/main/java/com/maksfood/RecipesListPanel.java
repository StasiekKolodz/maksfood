package com.maksfood;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
// import java.awt.event.MouseEvent;
// import java.awt.event.MouseListener;
import java.util.ArrayList;
// import java.util.List;
import java.awt.event.ActionEvent;

public class RecipesListPanel extends RoundedPanel implements ActionListener {
    
    public MainWindow window;
    

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
        e.insets = new Insets(50, 10, 50, 10);
        e.gridx = 0;
        e.gridy = 0;
        this.add(recipesLabelPanel, e);


        e.gridx = 0;
        e.gridy = 1;
        this.add(window.recipesPanel.recipeList, e);

        e.gridx = 0;
        e.gridy = 2;
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
    
    
}
}