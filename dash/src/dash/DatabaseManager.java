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
 * Some code excerpts taken from URL:
https://www.developer.com/java/data/manipulating-a-database-with-jdbc.html
* @author Navjot
 */
public class DatabaseManager {
    
    /* Database information
    */ 
   static final String DB_URL =  "jdbc:mysql://localhost:3306/test";
   static final String DB_DRV =  "com.mysql.jdbc.Driver";
   static final String DB_USER = "foo";
   static final String DB_PASSWD = "bar";
   static Connection connection = null;
    
    public DatabaseManager(){
    
    }
    public void setCon(){
            try {
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWD);
        } catch (Exception ex) {
            ex.printStackTrace();
        }    
    }
    public static void conClose() {
        try {
            connection.setAutoCommit(true);
            connection.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
    
    public int registerCashier(String firstName, String lastName,String password){
      
      PreparedStatement registerCashier = null;
      int empid = 100; //Starting employee is 100
      
      try{
          //Establish Connection to Dash for Cash database
          connection=DriverManager.getConnection
            (DB_URL,DB_USER,DB_PASSWD);
       
          //Prepare statement to be send to database for query
         registerCashier=connection.prepareStatement("INSERT INTO Employees(firstName,lastName,password,isManager) VALUES(?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
   
         registerCashier.setString(1, firstName);
         registerCashier.setString(2, lastName);
         registerCashier.setString(3, password);
         registerCashier.setInt(4, 0);
         //send query
        empid=  empid+registerCashier.executeUpdate();
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
        
  return empid;   }
    public int authenticate(int empId, String password){
      
      try{
          //Establish Connection to Dash for Cash database
          connection=DriverManager.getConnection
            (DB_URL,DB_USER,DB_PASSWD);
    
          //Excute Query
          String sqlQuery = "SELECT EXISTS (SELECT  *  FROM Employees WHERE (id= ? AND password=?))";
       
          PreparedStatement prepared = connection.prepareStatement(sqlQuery);
          prepared.setInt(1, empId);
          prepared.setString(2, password);
          ResultSet rs = prepared.executeQuery();
          rs.next();
          if(rs.getInt(1)==0){
              rs.getInt(1);
              
              return 0;
          }else{ // Manager check
              String managerQuery = "SELECT  *  FROM Employees WHERE (id= ?)";
              PreparedStatement preparedManager = connection.prepareStatement(managerQuery);
              preparedManager.setInt(1, empId);
              ResultSet newRs = preparedManager.executeQuery();
              newRs.next();
              if(newRs.getInt("isManager")==1){
                    return 2;} else{
              return 1;}
          }
      }catch(SQLException ex){
          System.err.println(ex);
          return -1;
      }finally{
         try {
 
            connection.close();
         } catch (SQLException ex) {
                System.exit(1);
             }
    }
}
    public Order retriveOrder(int saleId){
       
        PreparedStatement registerCashier = null;
        Order getSales = new Order();
      try{
          //Establish Connection to Dash for Cash database
          connection=DriverManager.getConnection
            (DB_URL,DB_USER,DB_PASSWD);
          
          
        
         try{
         //Retrive Order from database
//         getSales = (Order)getOrder.readJavaObject(connection, saleId);
         }catch(Exception e){
             System.exit(0);
         }
      }catch(SQLException sqlEx){
          sqlEx.printStackTrace();
                    System.exit(1);
      }finally{
         try {
             //close all
            registerCashier.close();
            connection.close();
         } catch (SQLException ex) {
                System.exit(1);
             }
      }
      return getSales;
    }
    public Item retriveItem(int id){
    //Create item
    Item product= new Item();
    
    //Prepared connection and preparedstatement
    PreparedStatement preparedStatement = null;
    String sqlQuery = "SELECT * FROM Items WHERE (id=?)";
    
    try{
         connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWD);
         
         preparedStatement = connection.prepareStatement(sqlQuery);
         preparedStatement.setInt(1, id);
         ResultSet rs = preparedStatement.executeQuery();
         while(rs.next()){
             product.setItemName(rs.getString("itemName"));
             product.setItemPrice(rs.getDouble("itemPrice"));
             product.setItemType(rs.getInt("itemType"));
         }
    }catch(SQLException sqlex){   //catch SQL error messeage
        System.err.println(sqlex);
    }  
    return product;
    }
    public int saveOrder(Object order){
       String WRITE_OBJECT_SQL = "INSERT INTO Orders(order_objects) VALUES (?)";
          int i =0;
      try{
          //Establish Connection to Dash for Cash database
          PreparedStatement pstmt = connection.prepareStatement(WRITE_OBJECT_SQL);
         // set input parameters
         pstmt.setObject(1, order);
         pstmt.executeUpdate();
         connection.commit();
      }catch(SQLException Ex){
               Ex.printStackTrace();
                    System.exit(1);
      }finally{
         try {
            connection.close();
         } catch (SQLException ex) {
                System.exit(1);
             }
         }
          return i;
    }
}
