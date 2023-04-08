package com.maksfood;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MainWindow extends JFrame{

    public JPanel currentPanel;
    public Menu menuPanel;
    public RecipesPanel recipesPanel;
    public FridgePanel fridgePanel;
    public ShoppingPanel shoppingPanel;

    public MainWindow() {

        // Set window properties
        setTitle("MainWindow");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        //creating panels
        menuPanel = new Menu(new GridBagLayout(), 45, this);
        recipesPanel = new RecipesPanel(new GridBagLayout(),45, this);
        fridgePanel = new FridgePanel(new GridBagLayout(), 45, this);
        shoppingPanel = new ShoppingPanel(new GridBagLayout(), 45, this);
        
        //setting current panel to menu panel
        currentPanel = menuPanel;

        // setting background
        try {
           setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("resources/background.jpg")).getScaledInstance((int) dim.getWidth(), (int) dim.getHeight(), Image.SCALE_SMOOTH))));
            }
        catch (IOException e){
                System.out.println("Image doesn't exist");
            }

        // Add current panel to frame
        setLayout(new GridBagLayout());
        GridBagConstraints e = new GridBagConstraints();
        e.gridheight = 900;
        e.gridwidth = 800;
        e.gridx = 0;
        e.gridy = 2;
        add(currentPanel,e);

        // Set the window to be visible
        setVisible(true);
    }

    // general function to setup buttons
    public void setButton(ColorButton buttonName, ActionListener l){
        buttonName.setPreferredSize(new Dimension(200, 60));
        buttonName.addActionListener(l);
    }

    //running App
    public static void main(String[] args) {
        MainWindow app = new MainWindow();
    }

}