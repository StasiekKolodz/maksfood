package com.maksfood;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RecipesPanel extends JPanel implements ActionListener{

    private JPanel currentPanel;

    //TODO recipes panel

    public RecipesPanel(){
    JLabel recipesLabel = new JLabel("Recipes");
    recipesLabel.setFont(new Font("Arial", Font.BOLD, 36));
    this.add(recipesLabel);

    JButton returnButton = new JButton("Return");
    setButton(returnButton);
    }

    public void setButton(JButton buttonName){
        buttonName.setFont(new Font("Arial", Font.BOLD, 12));
        buttonName.setForeground(Color.WHITE);
        buttonName.setBackground(new Color(80,7,2));
        buttonName.setPreferredSize(new Dimension(200, 60));
        buttonName.addActionListener( this );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Create a new panel depending on which button was pressed
        currentPanel.setVisible(false);
        Menu mainWindow = new Menu();
        currentPanel = mainWindow;
        // getContentPane().add(currentPanel);
        currentPanel.setVisible(true);
    }
    }