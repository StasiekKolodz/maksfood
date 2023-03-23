package com.maksfood;

import javax.swing.*;
import java.awt.*;

public class ShoppingPanel extends JPanel{

    //TODO shopping panel
    
    public ShoppingPanel(){
        JLabel shoppingLabel = new JLabel("My Fridge");
        shoppingLabel.setFont(new Font("Arial", Font.BOLD, 36));
        this.add(shoppingLabel);
        }
}
