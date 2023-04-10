package com.maksfood;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*; 
import java.util.Vector;

public class FridgePanel extends RoundedPanel implements ActionListener{


    public MainWindow window;
    public JList<String> fridgeList;
    public Vector<String> fridgeElements;
    public Vector<String> productDetails;
    public JScrollPane scrollpane;
    public DataBase fridgeDB;
    public JTable jt;
    public JLabel detailsLabel;
    //TODO recipes panel

    public FridgePanel(LayoutManager layout, int r, MainWindow w, DataBase dataBase){
        super(layout, r);
        window = w;
        fridgeDB = dataBase;
        //creating label
        DefaultLabel fridgeLabel = new DefaultLabel("My fridge");

        //creating return button
        ColorButton returnButton = new ColorButton("Return");
        window.setButton(returnButton,this);
        
        //creating button and label panels
        JPanel fridgeLabelPanel = new JPanel(new GridLayout());
        fridgeLabelPanel.setOpaque(false);
        JPanel returnButtonPanel = new JPanel(new GridLayout());
        fridgeLabelPanel.add(fridgeLabel);
        returnButtonPanel.add(returnButton);
        fridgeLabelPanel.setBounds(getVisibleRect());
        
        setOpaque(false);
        setBackground(new Color(255, 238, 219));

        //adding elements to RecipesPanel
        GridBagConstraints e = new GridBagConstraints();
        e.insets = new Insets(50,10,50,10);
        e.gridx = 0;
        e.gridy = 1;
        this.add(fridgeLabelPanel, e);
        // createDbList();
        e.gridx = 0;
        e.gridy = 2;

        fridgeList = new JList<String>();
        
        ListSelectionModel select= fridgeList.getSelectionModel();  
        select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  
        select.addListSelectionListener(new ListSelectionListener() {  
            public void valueChanged(ListSelectionEvent e) { 
                if (!e.getValueIsAdjusting()){
                int index = fridgeList.getSelectedIndex();
                fridgeDB.sqlSelect("SELECT * FROM new_schema.new_table WHERE id= " + Integer.toString(index+1));
                productDetails = fridgeDB.getRow(1, 2, 4);
                try{
                for(int i=0; i<3; i++){
                jt.setValueAt(productDetails.elementAt(i), 1,i);
                }
            }
                catch(Exception k){ 
                    }



                    // productDetails.elementAt(i);

                // System.out.println(created);

            }
             }});


        // to get content pane

        // String example[] = {"List1", "List2", "List3"};
        // fridgeList.setListData(example);
        this.add(fridgeList, e);
        updateList(dataBase);
        String data[][]={ {"name","amount","exp date"},{"","",""}};    
        String column[]={"product name","amount","expiration date"};         
        jt=new JTable(data,column); 

        // jt.setBounds(30,40,200,300);  
        e.gridx = 0;
        e.gridy = 4;       
        this.add(jt, e);

        // detailsLabel = new JLabel("details");


        // e.gridx = 0;
        // e.gridy = 3;
        // this.add(detailsLabel, e);
        e.gridx = 0;
        e.gridy = 5;
        this.add(returnButtonPanel, e);
    }
    public void updateList(DataBase dataBase){

        dataBase.sqlSelect("select * from new_schema.new_table");
        fridgeElements = dataBase.getElements(5, 2);
        // for(int i=0; i<30; i++){
        //     fridgeElements.add("dkk");
        // }
        System.out.println(fridgeElements);
        fridgeList.setListData(fridgeElements);
        fridgeList.setSelectedIndex(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        window.currentPanel.setVisible(false);

        window.currentPanel = window.menuPanel;

        window.currentPanel.setVisible(true);
    }


}
