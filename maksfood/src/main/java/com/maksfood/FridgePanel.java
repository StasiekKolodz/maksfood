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
        JLabel recipesLabel = new JLabel("Fridge");
        recipesLabel.setForeground(new Color(165, 56, 96));
        recipesLabel.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 30));
        this.add(recipesLabel);

        ColorButton returnButton = new ColorButton("Return");
        setButton(returnButton);
        this.add(returnButton);

        setOpaque(false);
        setBackground(new Color(255, 238, 219));
        setSize(900, 1000);
    }

    public void setButton(ColorButton buttonName){
        buttonName.setPreferredSize(new Dimension(200, 60));
        buttonName.addActionListener( this );
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        window.currentPanel.setVisible(false);

        window.currentPanel = window.menuPanel;

        window.currentPanel.setVisible(true);
    }


}
