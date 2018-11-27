/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dash;

/*
Importing MySQL
*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

/**
https://www.developer.com/java/data/manipulating-a-database-with-jdbc.html
* @author navi
 */
public class DatabaseManager {
    
    /* Database information
    */ 
   static final String DB_URL =  "jdbc:mysql://localhost:3306/test";
   static final String DB_DRV =  "com.mysql.jdbc.Driver";
   static final String DB_USER = "foo";
   static final String DB_PASSWD = "bar";
    
    public DatabaseManager(){
    
    }
    
    public void registerCashier(int id, String firstName, String lastName,String password){
      
      Connection connection = null;
      PreparedStatement registerCashier = null;
      
      try{
          //Establish Connection to Dash for Cash database
          connection=DriverManager.getConnection
            (DB_URL,DB_USER,DB_PASSWD);
          
          //Prepare statement to be send to database for query
         registerCashier=connection.prepareStatement("INSERT INTO emp VALUES(?,?,?,?)");
         registerCashier.setInt(1,id);
         registerCashier.setString(2,firstName);
         registerCashier.setString(3,lastName);
         registerCashier.setString(4, password);

         //send query
          registerCashier.execute();
      }catch(SQLException sqlEx){
          sqlEx.printStackTrace();
                    System.exit(1);
      }finally{
         try {
            registerCashier.close();
            connection.close();
         } catch (SQLException ex) {
                System.exit(1);
             }
         }
        
    }
    public int authenticate(int empId, String password){
      Connection connection = null;
      PreparedStatement registerCashier = null;
      int isManager= 0;
      
      try{
          //Establish Connection to Dash for Cash database
          connection=DriverManager.getConnection
            (DB_URL,DB_USER,DB_PASSWD);
          Statement statement = connection.createStatement();
          //Excute Query
          ResultSet rs = statement.executeQuery("SELECT EXITS( SELECT id, password,isManager FROM emp WHERE (id ="+empId+"AND"
                  + "password = "+password);
          if(!rs.next()){
              return 0;
          }else{ // Manager check
              while(rs.next()){
                  isManager = rs.getInt("isManager");
              }if(isManager == 1){
              return 2;} else{
              return 1;}
          }
      }catch(Exception e){
          System.exit(0);
      }finally{
         try {
            registerCashier.close();
            connection.close();
         } catch (SQLException ex) {
                System.exit(1);
             }
    }
 return 0;}
    public SalesTransaction retriveSalesData(int saleId){
        Connection connection = null;
        PreparedStatement registerCashier = null;
        
        total =saleTotal;
        status1=status;
        
        
      try{
          //Establish Connection to Dash for Cash database
          connection=DriverManager.getConnection
            (DB_URL,DB_USER,DB_PASSWD);
          
         Statement statement = connection.createStatement();
          //Excute Query
          ResultSet rs = statement.executeQuery("SELECT * FROM emp WHERE (id ="+saleId);
         //send query
         while(rs.next()){
             
         }
      }catch(SQLException sqlEx){
          sqlEx.printStackTrace();
                    System.exit(1);
      }finally{
         try {
            registerCashier.close();
            connection.close();
         } catch (SQLException ex) {
                System.exit(1);
             }
      }
      
    }
}
