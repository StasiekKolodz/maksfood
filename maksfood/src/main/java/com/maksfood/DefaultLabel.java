package com.maksfood;

import java.awt.*;
import javax.swing.JLabel;

public class DefaultLabel extends JLabel{

    public DefaultLabel(String text){
        super(text);
        this.setForeground(new Color(165, 56, 96));
        this.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 30)); 
    }

    public DefaultLabel(String text, int size){
        super(text);
        this.setForeground(new Color(165, 56, 96));
        this.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, size)); 
    }

    public DefaultLabel(String text, int size, String font){
        super(text);
        this.setForeground(new Color(165, 56, 96));
        this.setFont(new Font(font, Font.BOLD, size)); 
    }

    public DefaultLabel(String text, int size, String font, int[] color){
        super(text);
        this.setForeground(new Color(color[0], color[1], color[2]));
        this.setFont(new Font(font, Font.BOLD, size)); 
    }



}
