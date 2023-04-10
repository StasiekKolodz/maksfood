package com.maksfood;

import java.util.Vector;
import java.sql.*;  
class MysqlCon{  
static Vector<String> v = new Vector<String>();
static String current;
public static void main(String args[]){  
try{  
Class.forName("com.mysql.jdbc.Driver");  
Connection con=DriverManager.getConnection(  
"jdbc:mysql://localhost:3306/new_schema","root","mysql");  
//here sonoo is database name, root is username and password  
Statement stmt=con.createStatement();  
ResultSet rs=stmt.executeQuery("select * from new_schema.new_table");  
while(rs.next()){ 
current = rs.getString(2);
// System.out.println("Res1");
System.out.println(current);  
// System.out.println("Res2");  
v.add(current);
}
con.close();  
}
catch(Exception e){ 
    System.out.println(e);
    System.out.println("FAILED");}
}  
}