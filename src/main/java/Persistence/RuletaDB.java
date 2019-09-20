/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Usuario
 */
public class RuletaDB {
    //Direccion del DB de prueba
    String urlDatabase =  "jdbc:postgresql://localhost:5432/miRuleta";
    Connection conn = null;


    public void realizaConexion(){
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(urlDatabase,  "usuario", "password");
        } catch (ClassNotFoundException e) {
            System.out.println("Ocurrio un error : "+e.getMessage());
        } catch (SQLException e) {
            System.out.println("Ocurrio un error : "+e.getMessage());
        }
        System.out.println("La conexión se realizo sin problemas! =) ");
    }
    
    public void insertarRegistro(){
        //Recupera conexion y crea Statement para el db
        Connection c = this.conn;
        Statement stmt = null;
        
        //Valida si existe una conexion abierta al db y si no trata de abrir una
        if(c == null){
            try {
                c = DriverManager.getConnection(this.urlDatabase,"manisha", "123");
                c.setAutoCommit(false);
            } catch (Exception e) {
                System.out.println("Ocurrio un error : "+e.getMessage());
            }
            System.out.println("La conexión se realizo sin problemas! =) ");
        }
        
        try {
         Class.forName("org.postgresql.Driver");
         stmt = c.createStatement();
         //SQL de ejemplo mientras se define lo que se va a ingresar a la base de datos
         String sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
            + "VALUES (1, 'Paul', 32, 'California', 20000.00 );";
         stmt.executeUpdate(sql);
         stmt.close();
         c.commit();
         c.close();
      } catch (Exception e) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
      System.out.println("Records created successfully");
         
    }
}