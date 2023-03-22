package com.maksfood;

import javax.swing.*;

public class MainWindow extends JFrame {

    public MainWindow() {
        // Set the title of the window
        super("My Application");

        // Create a menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create a menu
        JMenu fileMenu = new JMenu("File");

        // Add menu items to the menu
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem exitItem = new JMenuItem("Exit");

        // Add menu items to the menu
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator(); // Add a separator between the Save and Exit menu items
        fileMenu.add(exitItem);

        // Add the menu to the menu bar
        menuBar.add(fileMenu);

        // Set the menu bar for this frame
        setJMenuBar(menuBar);

        // Set the size of the window
        setSize(400, 300);

        // Set the window to be visible
        setVisible(true);
    }

    public static void main(String[] args) {
        MainWindow app = new MainWindow();
    }

}