/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dash;

/**
 *
 * Code taken from 
 * URL: http://www.java2s.com/Code/Java/Database-SQL-JDBC/HowtoserializedeserializeaJavaobjecttotheMySQLdatabase.htm 
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SerializeObject {
  static final String WRITE_OBJECT_SQL = "INSERT INTO Sales(Order) VALUES (?)";
  static final String READ_OBJECT_SQL = "SELECT Order FROM Sales WHERE id = ?";
  public SerializeObject(){
  }

   public int writeJavaObject(Connection conn, Object object) throws Exception {
    String className = object.getClass().getName();
    PreparedStatement pstmt = conn.prepareStatement(WRITE_OBJECT_SQL);

    // set input parameters
    pstmt.setString(1, className);
    pstmt.setObject(2, object);
    pstmt.executeUpdate();

    // get the generated key for the id
    ResultSet rs = pstmt.getGeneratedKeys();
    int id = -1;
    if (rs.next()) {
      id = rs.getInt(1);
    }

    rs.close();
    pstmt.close();
    return id;
  }

  public Object readJavaObject(Connection conn, int id) throws Exception {
    PreparedStatement pstmt = conn.prepareStatement(READ_OBJECT_SQL);
    pstmt.setLong(1, id);
    ResultSet rs = pstmt.executeQuery();
    rs.next();
    Object object = rs.getObject(1);    
    String className = object.getClass().getName();
    rs.close();
    pstmt.close();
    return object;
  }
    
}
