package com.maksfood;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.BorderLayout;
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

        this.listPanel.add(list, BorderLayout.NORTH);
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
