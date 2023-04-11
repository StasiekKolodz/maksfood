package com.maksfood;

import java.awt.Color;
import java.awt.*;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


import javax.swing.JButton;

public class ColorButton extends JButton {
    private Color pressedColor = new Color(165, 56, 96);
    private Color rolloverColor = new Color(56, 39, 57);
    private Color normalColor = new Color(76, 59, 77);
    private Color textColor = new Color(255, 238, 219);

    public ColorButton (String text) {
        super(text);
        setBorderPainted(false);
        setFocusPainted(false);

        setContentAreaFilled(false);
        setOpaque(true);

        setBackground(normalColor);
        setForeground(textColor);
        setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
        setText(text);

        addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent evt) {
                if (getModel().isPressed()) {
                    setBackground(pressedColor);
                } else if (getModel().isRollover()) {
                    setBackground(rolloverColor);
                } else {
                    setBackground(normalColor);
                }
            }
        });
    }

    public ColorButton (String text, int size) {
        super(text);
        setBorderPainted(false);
        setFocusPainted(false);

        setContentAreaFilled(false);
        setOpaque(true);

        setBackground(normalColor);
        setForeground(textColor);
        setFont(new Font("DejaVu Sans Condensed", Font.BOLD, size));
        setText(text);

        addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent evt) {
                if (getModel().isPressed()) {
                    setBackground(pressedColor);
                } else if (getModel().isRollover()) {
                    setBackground(rolloverColor);
                } else {
                    setBackground(normalColor);
                }
            }
        });
    }
}