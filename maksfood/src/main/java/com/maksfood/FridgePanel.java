package com.maksfood;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*; 
import java.util.Vector;

public class FridgePanel extends RoundedPanel implements ActionListener{


    public MainWindow window;
    public JList<String> fridgeList;
    static Vector<String> v = new Vector<String>();
    static String current;
    //TODO recipes panel

    public FridgePanel(LayoutManager layout, int r, MainWindow w){
        super(layout, r);
        window = w;
        
        //creating label
        JLabel fridgeLabel = new JLabel("My fridge");
        fridgeLabel.setForeground(new Color(165, 56, 96));
        fridgeLabel.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 30));
        
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
        createDbList();
        e.gridx = 0;
        e.gridy = 2;
        this.add(fridgeList, e);
        
        e.gridx = 0;
        e.gridy = 3;
        this.add(returnButtonPanel, e);
    }
    public void createDbList(){

        // set example array
        String example[] = {"List1", "List2", "List3"};
        // create list
        ReadDB();
        fridgeList = new JList<String>(v);
        fridgeList.setSelectedIndex(0);
    }
    public void ReadDB(){
        try{  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/new_schema","root","mysql");  
            //here sonoo is database name, root is username and password  
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from new_schema.new_table");  
            while(rs.next()){ 
            current = rs.getString(2);
            System.out.println("Res1");
            System.out.println(current);  
            System.out.println("Res2");  
            v.add(current);
            }
            con.close();  
            }
            catch(Exception e){ 
                System.out.println(e);
                System.out.println("FAILED");}
    }
    @Override
    public void actionPerformed(ActionEvent e) {


        window.currentPanel.setVisible(false);

        window.currentPanel = window.menuPanel;

        window.currentPanel.setVisible(true);
    }


}
