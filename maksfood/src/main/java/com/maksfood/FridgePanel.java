package com.maksfood;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
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
    public int jtFlag = 0;
    public Menu menuPanel;

    public FridgePanel(LayoutManager layout, int r, MainWindow w, DataBase dataBase){
        super(layout, r);
        window = w;
        fridgeDB = dataBase;
        
        //creating label
        DefaultLabel fridgeLabel = new DefaultLabel("My fridge");
        menuPanel = window.menuPanel;

        //creating return button
        ColorButton returnButton = new ColorButton("Return");
        ColorButton addButton = new ColorButton("Add");
        ColorButton deleteButton = new ColorButton("Delete");
        window.setButton(returnButton,this);
        window.setButton(deleteButton,this);
        window.setButton(addButton,this);
        
        //creating button and label panels
        JPanel fridgeLabelPanel = new JPanel(new GridLayout());
        fridgeLabelPanel.setOpaque(false);
        JPanel returnButtonPanel = new JPanel(new GridLayout());
        JPanel addButtonPanel = new JPanel(new GridLayout());
        JPanel deleteButtonPanel = new JPanel(new GridLayout());
        fridgeLabelPanel.add(fridgeLabel);
        returnButtonPanel.add(returnButton);
        addButtonPanel.add(addButton);
        deleteButtonPanel.add(deleteButton);
        fridgeLabelPanel.setBounds(getVisibleRect());
        
        setOpaque(false);
        setBackground(new Color(255, 238, 219, 200));

        //adding elements to RecipesPanel
        GridBagConstraints e = new GridBagConstraints();
        e.insets = new Insets(20,5,20,5);
        e.gridx = 0;
        e.gridy = 1;
        e.gridwidth = 2;
        this.add(fridgeLabelPanel, e);
        // createDbList();


        fridgeList = new JList<String>();
        fridgeList.setBackground(new Color(165, 56, 96));
        fridgeList.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 15));
        fridgeList.setForeground(Color.WHITE);
        fridgeList.setFixedCellHeight(30);
        fridgeList.setFixedCellWidth(200);
        fridgeList.setSelectionBackground(Color.WHITE);
        fridgeList.setSelectionForeground(new Color(165, 56, 96));
        DefaultListCellRenderer renderer =  (DefaultListCellRenderer)fridgeList.getCellRenderer();  
        renderer.setHorizontalAlignment(JLabel.CENTER);  
        renderer.setVerticalAlignment(JLabel.CENTER);  
        fridgeList.scrollRectToVisible(getBounds());
        JScrollPane scrollPane = new JScrollPane(fridgeList);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setMinimumSize(new Dimension(300,400));
        scrollPane.setMaximumSize(new Dimension(300, 450));

        ListSelectionModel select= fridgeList.getSelectionModel();  
        select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  
        select.addListSelectionListener(new ListSelectionListener() {  
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()){
                    int index = fridgeList.getSelectedIndex();
                if(index != -1){
                    fridgeDB.sqlSelect("SELECT * FROM maksfood.fridge WHERE id=" + Integer.toString(index+1));
                    productDetails = fridgeDB.getRow(1, 2, 4);
                    try{
                        jtFlag = 1;
                    for(int i=0; i<3; i++){
                        jt.setValueAt(productDetails.elementAt(i), 1,i);
                    }
                        jtFlag = 0;
                    }catch(Exception k){
                        System.out.println("Adding element failed");
                        }
                }
                }
             }});

        e.gridx = 0;
        e.gridy = 2;
        e.gridwidth = 2;
        this.add(scrollPane, e);
        updateList();
        String data[][]={ {"name","amount","exp date"},{"","",""}};    
        String column[]={"product name","amount","expiration date"};         
        jt=new JTable(data,column); 
        jt.setGridColor(new Color(165, 56, 96));
        jt.setSelectionBackground(new Color(165, 56, 96));
        jt.setSize(200, 100);
        jt.getModel().addTableModelListener(new TableModelListener() {
            
            @Override
            public void tableChanged(TableModelEvent e) {
                if(jtFlag == 0){
                int id = fridgeList.getSelectedIndex();
                String name = jt.getValueAt(1,0).toString();
                String amount = jt.getValueAt(1,1).toString();
                String exp_date = jt.getValueAt(1,2).toString();
                fridgeDB.sqlUpdate("UPDATE maksfood.fridge SET name='"+name+"', amount='"+amount+"', exp_date='"+exp_date+
                "' WHERE id="+(id+1) +";");
                updateList();
                }
            }
          });
        e.gridx = 0;
        e.gridy = 3;  
        e.gridwidth = 2;     
        this.add(jt, e);

        e.gridx = 0;
        e.gridy = 6;
        e.gridwidth = 2;
        this.add(returnButtonPanel, e);
        e.gridwidth = 1;
        e.gridx = 0;
        e.gridy = 4;
        this.add(addButtonPanel, e);
        e.gridx = 1;
        e.gridy = 4;
        this.add(deleteButtonPanel, e);
    }

    public void updateList(){

        fridgeDB.sqlSelect("select * from maksfood.fridge");
        fridgeElements = fridgeDB.getElements(2);

        fridgeList.setListData(fridgeElements);
        fridgeList.setSelectedIndex(0);
    }
    public void deleteSelectedElement(){
        int index = fridgeList.getSelectedIndex();
        fridgeDB.sqlUpdate("DELETE FROM maksfood.fridge WHERE id=" + Integer.toString(index+1));
        fridgeDB.fixIds(index+1);
        updateList();
    }

    public void deleteFromExpired(){
        int index = fridgeList.getSelectedIndex();
        fridgeDB.sqlUpdate("DELETE FROM maksfood.expiredProducts WHERE name="+"\""+fridgeList.getModel().getElementAt(index)+"\"");
        fridgeDB.fixIds(index+1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Return")) {
            window.currentPanel.setVisible(false);
            window.currentPanel = window.menuPanel;
            window.expiration.updateAboutToExpire();
            window.menuPanel.dateTimeLabel.setText(window.menuPanel.displayExpData());
            window.getContentPane().add(window.menuPanel);
            window.currentPanel.setVisible(true);

            
        }
        if (e.getActionCommand().equals("Delete")) {
            deleteFromExpired();
            deleteSelectedElement();
        }

        if (e.getActionCommand().equals("Add")) {
            window.currentPanel.setVisible(false);
            window.currentPanel = window.fridgeAddPanel;
            window.getContentPane().add(window.fridgeAddPanel);
            window.currentPanel.setVisible(true);
        }
    }


}

