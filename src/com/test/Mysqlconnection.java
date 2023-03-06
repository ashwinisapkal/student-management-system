package com.test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Mysqlconnection {

 static Connection conn =null;

 public static Connection getConnection() throws SQLException, ClassNotFoundException, java.sql.SQLException
 {
     Class.forName("com.mysql.cj.jdbc.Driver");
     conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/student_app_db", "root","");
     return conn;
 }


 public static void closeConnection() throws SQLException
 {
     if(conn!=null)
     {
         try
         {
             conn.close();
         }
         catch (SQLException e)
         {

             System.out.println("Something went wrong!!");
         }
     }
 }
 public static void main(String args[])
 {
	 
 }

}