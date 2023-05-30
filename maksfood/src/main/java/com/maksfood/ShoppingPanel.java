package com.maksfood;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;


public class ShoppingPanel extends RoundedPanel implements ActionListener, ListSelectionListener{
    public MainWindow window;
    public DataBase shoppingDB;
    public JPanel shoppingLabelPanel;
    public JPanel returnButtonPanel;
    private ColorButton returnButton;
    public Vector<String> listElements;
    public ListContainer listsList;
    public ListContainer shoppingList;
    

    public ShoppingPanel(LayoutManager layout, int r, MainWindow w, DataBase dataBase){
        super(layout, r);
        window = w;
        shoppingDB = dataBase;
        DefaultLabel shoppingLabel = new DefaultLabel("Shopping");

        //creating return button
        returnButton = new ColorButton("Return");
        window.setButton(returnButton, this);

        //creating button and label panels
        shoppingLabelPanel = new JPanel(new GridBagLayout());
        shoppingLabelPanel.setOpaque(false);
        returnButtonPanel = new JPanel(new GridLayout());
        shoppingLabelPanel.add(shoppingLabel);
        returnButtonPanel.add(returnButton);
        shoppingLabelPanel.setBounds(getVisibleRect());
        setOpaque(false);
        setBackground(new Color(255, 238, 219, 200));


        //forming both list of lists and shopping list objects
        listsList =  new ListContainer(this, this);
        shoppingList =  new ListContainer(this, this);

        updateListsList();
        setLayout();

        setListAppearance(listsList.list);
        setListAppearance(shoppingList.list);
    }

    public void setLayout(){

        GridBagConstraints e = new GridBagConstraints();
        e.insets = new Insets(10,10,10,10);
        e.gridx = 0;
        e.gridy = 0;
        e.gridwidth = 2;
        this.add(shoppingLabelPanel, e);
        
        GridBagConstraints s = new GridBagConstraints();
        s.insets = new Insets(25,25,25,25);
        s.gridx = 0;
        s.gridy = 1;
        this.add(listsList.listPanel, s);

        s.gridx = 1;
        s.gridy = 1;
        this.add(shoppingList.listPanel, s);

        
        e.gridx = 0;
        e.gridy = 2;
        e.gridwidth = 2;
        this.add(returnButtonPanel, e);
    }


    public void setListAppearance(JList<String> list){

        // TODO use template
        // list.setForeground(new Color(165, 56, 96));
        // list.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 15));
        // list.setBackground(new Color(255, 238, 219));
    }

    public void updateList(String selectedValue){
        shoppingDB.sqlSelect("SELECT * from maksfood.shoppingList WHERE parent_list='"+selectedValue+"'");
        listElements = shoppingDB.getElements(10, 2);

        shoppingList.list.setListData(listElements);
        shoppingList.list.setSelectedIndex(0);
    }

    public void updateList(){
        int selectedIndex = listsList.list.getSelectedIndex();
        String selectedItem = listsList.listModel.getElementAt(selectedIndex);

        shoppingDB.sqlSelect("SELECT * from maksfood.shoppingList WHERE parent_list='"+selectedItem+"'");
        listElements = shoppingDB.getElements(10, 2);
        shoppingList.list.setListData(listElements);
        shoppingList.list.setSelectedIndex(0);
    }

    public void updateListsList(){

        shoppingDB.sqlSelect("SELECT * from maksfood.listsList");
        listElements = shoppingDB.getElements(30, 2);
        listsList.listModel.clear();
        listsList.listModel.addAll(listElements);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if(src == returnButton){
        window.currentPanel.setVisible(false);
        window.currentPanel = window.menuPanel;
        window.currentPanel.setVisible(true);
        }
        else if(src == listsList.addButton){
            String text = listsList.textField.getText();
                if (!text.isEmpty()) {
                    addListsListElement(text);
                    listsList.textField.setText("");
                }
        }
        else if(src == shoppingList.addButton){
            String text = shoppingList.textField.getText();
                if (!text.isEmpty()) {
                    addShoppingListElement(text);
                    shoppingList.textField.setText("");
                }
        }
        else if(src == listsList.removeButton){
            int selectedIndex = listsList.list.getSelectedIndex();
                if (selectedIndex != -1) {
                    String selectedItem = listsList.listModel.getElementAt(selectedIndex);

                    // Remove the selected item from the first list
                    deleteSelectedList(selectedItem);
        }
    }
        else if(src == shoppingList.removeButton){
            System.out.print("a !");
            int selectedIndexSh = shoppingList.list.getSelectedIndex();
            int selectedIndex = listsList.list.getSelectedIndex();
            System.out.print("b !");
                if (selectedIndexSh != -1) {
                    System.out.print(selectedIndexSh);
                    String selectedItemSh = shoppingList.list.getSelectedValue();
                    System.out.print("x !");
                    String selectedItem = listsList.listModel.getElementAt(selectedIndex);
                    // Remove the selected item from the second list
                    System.out.print("d !");
                    deleteSelectedElement(selectedItemSh, selectedItem);
                    
        }
    }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            Object src = e.getSource();
            if (src == listsList.list) {
                int selectedIndex = listsList.list.getSelectedIndex();
                if (selectedIndex != -1) {
                    String selectedValue = listsList.listModel.get(selectedIndex);
                    // update list 2 with selected list items
    
                    shoppingList.listModel.clear();
                    updateList(selectedValue);
                }
            }
        }
    }
    public void addShoppingListElement(String test){
        int selectedIndex = listsList.list.getSelectedIndex();
        String selectedValue = listsList.listModel.get(selectedIndex);
        shoppingDB.sqlUpdate("INSERT INTO maksfood.shoppingList VALUES(DEFAULT,'"+
        test + "','" + "1" + "','" + selectedValue + "');");
        updateList(selectedValue);

    }
    public void addListsListElement(String test){
        shoppingDB.sqlUpdate("INSERT INTO maksfood.listsList VALUES(DEFAULT,'"+
        test + "');");
        updateListsList();
    }
    public void deleteSelectedElement(String elem, String parent){
        shoppingDB.sqlUpdate("DELETE FROM maksfood.shoppingList WHERE name='" + elem + "' AND parent_list='" +parent + "'");
        updateList();
    }
    public void deleteSelectedList(String list){
        shoppingDB.sqlUpdate("DELETE FROM maksfood.shoppingList WHERE parent_list='" + list + "'");
        shoppingDB.sqlUpdate("DELETE FROM maksfood.listsList WHERE name='" + list + "'");
        updateListsList();
    }
}
