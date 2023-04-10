package com.maksfood;

import java.sql.*; 
import java.util.Vector;

public class DataBase {
    // public Vector<String> elements = new Vector<String>();
    String current;
    public Connection con;
    public Statement stmt;
    public ResultSet rs;
    // public static void main(String args[]){
    //     DataBase db = new DataBase();

    //     db.connect("3306", "new_schema", "root", "mysql");
    //     db.sqlSelect("select * from new_schema.new_table");
    //     System.out.println(db.getElements(5, 2));
    // }
    public boolean connect(String port, String db_name, String username, String password){
        try{  
            Class.forName("com.mysql.jdbc.Driver");  
            con = DriverManager.getConnection(  
            "jdbc:mysql://localhost:" + port +"/" + db_name, username, password);  
            stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,  
            ResultSet.CONCUR_UPDATABLE);   
            return true;
        }
        catch(Exception e){ 
            System.out.println(e);
            System.out.println("CONNECTION FAILED");
            return false;}

    }
    public void sqlSelect(String command){

        try{       
        rs= stmt.executeQuery(command); 
        System.out.println("EXECUTING COMMAND");
        System.out.println(command);
        }
        catch(Exception e){ 
            System.out.println(e);
            System.out.println("EXECUTING COMMAND FAILED");}

    }
    public void sqlUpdate(String command){

        try{       
            stmt.executeUpdate(command); 
            System.out.println("EXECUTING COMMAND");
            System.out.println(command);
        }
        catch(Exception e){ 
            System.out.println(e);
            System.out.println("EXECUTING COMMAND FAILED");}

    }
    public Vector<String> getElements(int elements_number, int column){
        int i = 0;
        Vector<String> elements = new Vector<String>();

        // elements.clear();
        
        try{
        while(rs.next() && i++ < elements_number){
            current = rs.getString(column);
            elements.add(current);
        }
        }
        catch(Exception e){ 
            System.out.println(e);
            System.out.println("GETTING DB ELEMENTS FAILED");}
        return elements;
    }

    public Vector<String> getElements(int column){
        Vector<String> elements = new Vector<String>();
        try{
        while(rs.next()){
            current = rs.getString(column);
            elements.add(current);
        }
        }
        catch(Exception e){ 
            System.out.println(e);
            System.out.println("GETTING DB ELEMENTS FAILED");}
        return elements;
    }

    public Vector<String> getRow(int row_number, int from_column, int to_column){

        Vector<String> elements = new Vector<String>();

        // elements.clear();
        
        try{
        rs.absolute(row_number);
        for(int i = from_column; i<=to_column; i++){
            current = rs.getString(i);
            elements.add(current); 
        }       
        }
        catch(Exception e){ 
            System.out.println(e);
            System.out.println("GETTING DB ELEMENTS FAILED");}
        return elements;
    }
    

}                
