/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL;

/**
 *
 * @author Joluc
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author Tacho
 */
public class SQL_Conexion {
    private final String url = "jdbc:mysql://localhost/venditor";
    PreparedStatement psPrepararSentencia;
    Connection con = null;
    public SQL_Conexion(String user, String pass) {
     try{
         Class.forName("com.mysql.jdbc.Driver");
         con = DriverManager.getConnection(url,"root","");
         //con = DriverManager.getConnection(url,user,pass);
         if (con!=null){
            System.out.println("Conexión a base de datos Venditor...lista");
         }
        }
         catch(SQLException e){
         System.out.println(e);
         
         }
         catch(ClassNotFoundException e){
             //System.out.println("acá está el error");
          System.out.println(e);
          
         }
     
    }

    public SQL_Conexion() {

    }

    public boolean SQL_Conexion(String user, String pass) {
     try{
         Class.forName("com.mysql.jdbc.Driver");
         con = DriverManager.getConnection(url,user,pass);
         if (con!=null){
            System.out.println("Conexión a base de datos facturacion. listo");
         }
        }
         catch(SQLException e){
         System.out.println(e);
         return false;
         }
         catch(ClassNotFoundException e){
          System.out.println(e);
          return false;
         }
     return true;
    }
    
    public Connection conectado(){
      return con;
    }

    public void desconectar(){
      con = null;
      System.out.println("conexion terminada");
    } 
}