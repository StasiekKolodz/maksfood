package com.maksfood;

import javax.swing.*;
import java.awt.*;

public class FridgePanel extends JPanel{

    //TODO fridge panel

    public FridgePanel(){
    JLabel fridgeLabel = new JLabel("My Fridge");
    fridgeLabel.setFont(new Font("Arial", Font.BOLD, 36));
    this.add(fridgeLabel);
    }
}
