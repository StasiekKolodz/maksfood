package com.maksfood;

import java.sql.*; 
import java.util.Vector;

public class DataBase {
    String current;
    public Connection con;
    public Statement stmt;
    public ResultSet rs;
    public boolean connect(String port, String db_name, String username, String password){
        try{   
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
        System.out.println("EXECUTING COMMAND");
        System.out.println(command);  
        rs= stmt.executeQuery(command); 

        }
        catch(Exception e){ 
            System.out.println(e);
            System.out.println("EXECUTING SELECT COMMAND FAILED");}

    }
    public void sqlUpdate(String command){

        try{ 
            System.out.println("EXECUTING COMMAND");
            System.out.println(command);      
            stmt.executeUpdate(command); 

        }
        catch(Exception e){ 
            System.out.println(e);
            System.out.println("EXECUTING UPDATE COMMAND FAILED");}

    }
    public int getId(){
        int current_ret = 0;
        try{
            rs.next();
            current_ret = rs.getInt(1);
        }
        catch(Exception e){ 
            System.out.println(e);
            System.out.println("GETTING DB ID FAILED");}
        return current_ret;
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

        try{
        rs.absolute(row_number);
        for(int i = from_column; i<=to_column; i++){
            current = rs.getString(i);
            elements.add(current); 
        }       
        }
        catch(Exception e){ 
            System.out.println(e);
            System.out.println("GETTING DB ROW FAILED");
        }
        return elements;
    }
    public void fixIds(int deletedId){
        sqlUpdate("UPDATE maksfood.fridge SET id=id-1 WHERE id> " + Integer.toString(deletedId)+ " AND id!=1");
        sqlUpdate("ALTER TABLE maksfood.fridge AUTO_INCREMENT = 1;");
    }
    public void fixFavouriteRecipesIds(int deletedId){
        sqlUpdate("UPDATE maksfood.favourite_recipe SET id=id-1 WHERE id> " + Integer.toString(deletedId)+ " AND id!=1");
        sqlUpdate("ALTER TABLE maksfood.favourite_recipe AUTO_INCREMENT = 1;");
    }

    public int getRowsCountFridge(){
        //Query to get the number of rows in a table
        String query = "SELECT COUNT(id) FROM maksfood.fridge";
        int count = -1;
        //Executing the query
        try{
            rs = stmt.executeQuery(query);
            //Retrieving the result
            rs.next();
            count = rs.getInt(1);
        }
        catch(Exception e){
            System.out.println(e);
            System.out.println("GETTING DB COUNT FAILED");
        }
        return count;
    }

    public int getRowsCountShoppingList(){
        //Query to get the number of rows in a table
        String query = "SELECT COUNT(id) FROM maksfood.shoppingList";
        int count = -1;
        //Executing the query
        try{
            rs = stmt.executeQuery(query);
            //Retrieving the result
            rs.next();
            count = rs.getInt(1);
        }
        catch(Exception e){
            System.out.println(e);
            System.out.println("GETTING DB COUNT FAILED");
        }
        return count;
    }

    public int CountRS(){
        int size =0;
        try{
            if (rs != null) 
            {
            rs.last();
            size = rs.getRow();
            }
        }catch(Exception e){
            System.out.println(e);
            System.out.println("COUNTING RESULT SET FAILED");
        }
        return size;
    }

    public int getRowsCountExpiredProds(boolean isExpired){
        int count = -1;
        if(isExpired){
            String query = "SELECT COUNT(id) FROM maksfood.expiredProducts WHERE isExpired=1";
         try{
            rs = stmt.executeQuery(query);
            //Retrieving the result
            rs.next();
            count = rs.getInt(1);
        }
        catch(Exception e){
            System.out.println(e);
            System.out.println("DB COUNT FAILED");
        }
        }
        else{
            String query = "SELECT COUNT(id) FROM maksfood.expiredProducts WHERE isExpired=0";
            try{
                rs = stmt.executeQuery(query);
                //Retrieving the result
                rs.next();
                count = rs.getInt(1);
            }
            catch(Exception e){
                System.out.println(e);
                System.out.println("DB COUNT FAILED");
            }
        }
        //Executing the query
       
        return count;
    }

}                
