package com.maksfood;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicScrollBarUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionListener;

public class ListContainer{
    public JList<String> list;
    public JPanel listPanel;
    public JTextField textField;
    public DefaultListModel<String> listModel;
    public ColorButton addButton;
    public ColorButton removeButton;

    public ListContainer(ListSelectionListener listener, ActionListener aListener){
        this.listPanel = new JPanel();
        this.listPanel.setLayout(new BorderLayout());

        this.textField = new JTextField();

        this.listModel = new DefaultListModel<>();
        
        this.list = new JList<>(listModel);
        this.list.addListSelectionListener(listener);
        // this.list = new JList<String>();
        this.list.setSelectionMode(
            ListSelectionModel.SINGLE_SELECTION);
        this.list.setBackground(new Color(165, 56, 96));
        // this.list.setSize(200, 100);
        this.list.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 20));
        this.list.setForeground(Color.WHITE);
        // this.list.setFixedCellHeight(30);
        // this.list.setFixedCellWidth(120);
        this.list.setSelectionBackground(Color.WHITE);
        this.list.setSelectionForeground(new Color(165, 56, 96));
        this.list.setFixedCellWidth(200);
        this.list.setFixedCellHeight(30);
        DefaultListCellRenderer renderer =  (DefaultListCellRenderer)this.list.getCellRenderer();  
        renderer.setHorizontalAlignment(JLabel.CENTER);  
        renderer.setVerticalAlignment(JLabel.CENTER); 

        JScrollPane jsp = new JScrollPane(list);
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jsp.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(76, 59, 77);
            }
        });
        this.listPanel.add(jsp, BorderLayout.NORTH);
        this.listPanel.add(textField, BorderLayout.SOUTH);

        setAddButton(aListener);
        setRemoveButton(aListener);
    }

    public void setAddButton(ActionListener l){
        this.addButton = new ColorButton("Add item", 15);
        this.listPanel.add(addButton, BorderLayout.WEST);
        this.addButton.addActionListener(l);
    }

    public void setRemoveButton(ActionListener l){
        this.removeButton = new ColorButton("Remove item", 15);
        this.listPanel.add(removeButton, BorderLayout.EAST);
        this.removeButton.addActionListener(l);
    }
}
