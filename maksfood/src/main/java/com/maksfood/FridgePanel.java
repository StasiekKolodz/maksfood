package com.maksfood;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FridgePanel extends RoundedPanel implements ActionListener{


    public MainWindow window;

    //TODO recipes panel

    public FridgePanel(LayoutManager layout, int r, MainWindow w){
        super(layout, r);
        window = w;
        
        //creating label
        JLabel fridgeLabel = new JLabel("My fridge");
        fridgeLabel.setForeground(new Color(165, 56, 96));
        fridgeLabel.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 30));
        
        //creating return button
        ColorButton returnButton = new ColorButton("Return");
        window.setButton(returnButton,this);
        
        //creating button and label panels
        JPanel fridgeLabelPanel = new JPanel(new GridLayout());
        fridgeLabelPanel.setOpaque(false);
        JPanel returnButtonPanel = new JPanel(new GridLayout());
        fridgeLabelPanel.add(fridgeLabel);
        returnButtonPanel.add(returnButton);
        fridgeLabelPanel.setBounds(getVisibleRect());
        
        setOpaque(false);
        setBackground(new Color(255, 238, 219, 200));

        //adding elements to RecipesPanel
        GridBagConstraints e = new GridBagConstraints();
        e.insets = new Insets(50,10,50,10);
        e.gridx = 0;
        e.gridy = 1;
        this.add(fridgeLabelPanel, e);
        
        e.gridx = 0;
        e.gridy = 2;
        this.add(returnButtonPanel, e);
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        window.currentPanel.setVisible(false);

        window.currentPanel = window.menuPanel;

        window.currentPanel.setVisible(true);
    }


}
