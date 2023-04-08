package com.maksfood;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShoppingPanel extends RoundedPanel implements ActionListener{

    public MainWindow window;
    public JPanel shoppingLabelPanel;
    public JPanel returnButtonPanel;
    public JList listsList;

    public ShoppingPanel(LayoutManager layout, int r, MainWindow w){
        super(layout, r);
        window = w;
        
        //creating label
        JLabel shoppingLabel = new JLabel("Shopping");
        shoppingLabel.setForeground(new Color(165, 56, 96));
        shoppingLabel.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 30));
        
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

        formListOfLists();
        
        setOpaque(false);
        setBackground(new Color(255, 238, 219, 200));

        //adding elements to RecipesPanel
        GridBagConstraints e = new GridBagConstraints();
        e.insets = new Insets(50,10,50,10);
        e.gridx = 0;
        e.gridy = 1;
        this.add(shoppingLabelPanel, e);

        e.gridx = 0;
        e.gridy = 2;
        this.add(listsList, e);
        
        e.gridx = 0;
        e.gridy = 3;
        this.add(returnButtonPanel, e);
    }

    public void formListOfLists(){

        // set example array
        String example[] = {"List1", "List2", "List3"};
        // create list
        listsList = new JList(example);
        listsList.setSelectedIndex(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        window.currentPanel.setVisible(false);

        window.currentPanel = window.menuPanel;

        window.currentPanel.setVisible(true);
    }


}
