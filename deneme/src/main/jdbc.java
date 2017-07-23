/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author MesutKutlu
 */
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class jdbc {
    public Connection connectToDatabaseOrDie()
  {
    Connection conn = null;
    try
    {
      Class.forName("org.postgresql.Driver");
      String url = "jdbc:postgresql://127.0.0.1:5432/jailmanage";
      conn = DriverManager.getConnection(url,"postgres", "12345");
    }
    catch (ClassNotFoundException e)
    {
      e.printStackTrace();
      System.exit(1);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
      System.exit(2);
    }
    return conn;
  }
}
