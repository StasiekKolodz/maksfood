package com.maksfood;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame{

    public JPanel currentPanel;
    public Menu menuPanel;
    public RecipesPanel recipesPanel;
    public FridgePanel fridgePanel;
    public ShoppingPanel shoppingPanel;
    public DataBase dataBase;
    public RecipesListPanel recipesListPanel;
    public FridgeAddPanel fridgeAddPanel;
    public MainWindow() {
//      Create and connect to database
        dataBase = new DataBase();
        dataBase.connect("3306", "maksfood", "root", "mysql");
        // Set window properties
        setTitle("MainWindow");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        //creating panels
        menuPanel = new Menu(new GridBagLayout(), 45, this);
        recipesPanel = new RecipesPanel(new GridBagLayout(),45, this);
        fridgePanel = new FridgePanel(new GridBagLayout(), 45, this, dataBase);
        fridgeAddPanel = new FridgeAddPanel(new GridBagLayout(), 45, this, dataBase);
        shoppingPanel = new ShoppingPanel(new GridBagLayout(), 45, this);
        recipesListPanel = new RecipesListPanel(new GridBagLayout(), 45, this);
    
        
        //setting current panel to menu panel
        currentPanel = menuPanel;
        
        // Add current panel to frame
        getContentPane().setBackground(new Color(165, 56, 96));
        setLayout(new GridBagLayout());
        GridBagConstraints e = new GridBagConstraints();
        e.gridheight = 900;
        e.gridwidth = 800;
        e.gridx = 0;
        e.gridy = 2;
        add(currentPanel,e);

        // RecipesGenerator gen = new RecipesGenerator();
        // Ingredient i = new Ingredient("tomato",Ingredient.Unit.amount, 2);
        // Ingredient i2 = new Ingredient("pasta",Ingredient.Unit.amount, 2);
        // // Ingredient i3 = new Ingredient("",Ingredient.Unit.amount, 4);
        // gen.add_ingredient(i);
        // gen.add_ingredient(i2);
        // gen.add_ingredient(i3);

        // gen.send_request_to_api();

        // Set the window to be visible
        setVisible(true);
    }

    // general function to setup buttons
    public void setButton(ColorButton buttonName, ActionListener l){
        buttonName.setPreferredSize(new Dimension(200, 60));
        buttonName.addActionListener(l);
    }
    public void setButton(ColorButton buttonName, ActionListener l, int width, int height){
        buttonName.setPreferredSize(new Dimension(width, height));
        buttonName.addActionListener(l);
    }
    //running App
    public static void main(String[] args) {
        MainWindow app = new MainWindow();
    }

}