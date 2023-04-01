package com.maksfood;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShoppingPanel extends RoundedPanel implements ActionListener{

    public MainWindow window;
    public JPanel shoppingLabelPanel;
    public JPanel returnButtonPanel;
    
    public JList<String> listsList;
    public DefaultListModel<String> listsListModel;
    public DefaultListModel<String> shoppingListModel;
    public JList<String> shoppingList;
    

    public ShoppingPanel(LayoutManager layout, int r, MainWindow w){
        super(layout, r);
        window = w;
        
        DefaultLabel shoppingLabel = new DefaultLabel("Shopping");

        //creating return button
        ColorButton returnButton = new ColorButton("Return");
        window.setButton(returnButton, this);

        //creating button and label panels
        shoppingLabelPanel = new JPanel(new GridBagLayout());
        shoppingLabelPanel.setOpaque(false);
        returnButtonPanel = new JPanel(new GridLayout());
        shoppingLabelPanel.add(shoppingLabel);
        returnButtonPanel.add(returnButton);
        shoppingLabelPanel.setBounds(getVisibleRect());
        setOpaque(false);
        setBackground(new Color(255, 238, 219));

        //adding elements to RecipesPanel
        
        formListOfLists();
        setLayout();
        setListAppearance(listsList);
        setListAppearance(shoppingList);


    }

    public void setLayout(){

        // example list
        // String foo[] = {"Eggs", "Milk"};
        // JList<String> shoppingList = new JList<>(foo);
        // shoppingList.setFixedCellHeight(25);
        // shoppingList.setFixedCellWidth(200);


        GridBagConstraints e = new GridBagConstraints();
        e.insets = new Insets(50,10,50,10);
        e.gridx = 1;
        e.gridy = 1;
        this.add(shoppingLabelPanel, e);
        
        e.gridx = 0;
        e.gridy = 2;
        this.add(listsList, e);

        e.gridx = 2;
        e.gridy = 2;
        this.add(shoppingList, e);
        
        e.gridx = 1;
        e.gridy = 3;
        this.add(returnButtonPanel, e);
    }

    public void formListOfLists(){
        listsListModel = new DefaultListModel<>();
        listsListModel.addElement("Tortilla");
        listsListModel.addElement("StudentPack");
        listsListModel.addElement("Harcerz");

        shoppingListModel = new DefaultListModel<>();
        
        listsList = new JList<>(listsListModel);
        shoppingList = new JList<>(shoppingListModel);

        listsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedIndex = listsList.getSelectedIndex();
                    if (selectedIndex != -1) {
                        String selectedValue = listsListModel.get(selectedIndex);
                        // update list 2 with selected list items
                        shoppingListModel.clear();
                        for (String item : getListItems(selectedValue)) {
                            shoppingListModel.addElement(item);
                        }
                    }
                }
            }
        });
        
    }

    public void setListAppearance(JList list){
        list.setForeground(new Color(165, 56, 96));
        list.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 15));
        list.setBackground(new Color(255, 238, 219));

    }
    // returns a list of items for the given list name
    private static String[] getListItems(String listName) {
        if (listName.equals("Tortilla")) {
            return new String[] {"Tortilla", "Lettuce Mix", "Roasted Chicken", "Garlic Sauce", "Sriracha Sauce"};
        } else if (listName.equals("StudentPack")) {
            return new String[] {"Piwo", "Zupka chińska", "Parówki"};
        } else if (listName.equals("Harcerz")) {
            return new String[] {"Szyszki", "Mielonka", "Keczup"};
        } else {
            return new String[] {};
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        window.currentPanel.setVisible(false);

        window.currentPanel = window.menuPanel;

        window.currentPanel.setVisible(true);
    }


}
