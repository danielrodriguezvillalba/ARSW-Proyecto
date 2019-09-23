/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.application.Persistence;

import edu.application.model.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Usuario
 */
public class RuletaDB {
    //Direccion del DB de prueba
    static String urlDatabase = "jdbc:postgresql://ec2-23-21-115-109.compute-1.amazonaws.com:5432/dcjlcvlpuum728";
    static String usuarioDb = "unkwzcvdxtrfys";
    static String passwordDb= "03eaed5577b5eadb70f2bbe4de0d68e23a967ab611eff45138dc2b690b0fe052";
    static Connection conn = null;
    
    /**
     * Main provicional para realizar pruebas a la base de datos
    public static void main(String args[]) {
        Usuario user = new Usuario(768903,"Didio", "Osorio", "dosorio@sywork.net","contra");
        realizaConexion();
        insertarUsuario(user);
        //createTable();
        //insertarRegistro();
        //selectRegistros();
    }**/


    public static void realizaConexion(){
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(urlDatabase,  usuarioDb, passwordDb);
        } catch (ClassNotFoundException e) {
            System.out.println("Ocurrio un error not found: "+e.getMessage());
        } catch (SQLException e) {
            System.out.println("Ocurrio un error : "+e.getMessage());
        }
        System.out.println("La conexi�n se realizo sin problemas!");
    }
    
    public static void createTable(){
        //Recupera conexion y crea Statement para el db
        Connection c = conn;
        Statement stmt = null;
        
        //Valida si existe una conexion abierta al db y si no trata de abrir una
        if(c == null){
            try {
                c = DriverManager.getConnection(urlDatabase,usuarioDb, passwordDb);
                c.setAutoCommit(false);
            } catch (Exception e) {
                System.out.println("Ocurrio un error : "+e.getMessage());
            }
            System.out.println("La conexi�n se realizo sin problemas! =) ");
        }
        try{
            Class.forName("org.postgresql.Driver");
            stmt = c.createStatement();
            String sql = "CREATE TABLE COMPANY " +
               "(ID INT PRIMARY KEY     NOT NULL," +
               " NAME           TEXT    NOT NULL, " +
               " AGE            INT     NOT NULL, " +
               " ADDRESS        CHAR(50), " +
               " SALARY         REAL)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Table created successfully");
  
    }
    
    public static void insertarUsuario(Usuario user){
        //Recupera conexion y crea Statement para el db
        Usuario usr = user;
        Connection c = conn;
        Statement stmt = null;
        
        //Valida si existe una conexion abierta al db y si no trata de abrir una
        if(c == null){
            try {
                c = DriverManager.getConnection(urlDatabase,usuarioDb, passwordDb);
                
            } catch (Exception e) {
                System.out.println("Ocurrio un error : "+e.getMessage());
            }
            System.out.println("La conexi�n se realizo sin problemas! =) ");
        }
        try{
            Class.forName("org.postgresql.Driver");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            //SQL de ejemplo mientras se define lo que se va a ingresar a la base de datos
            String sql = "INSERT INTO usuario (nombre,apellido,correo,contrase�a,saldo,numerocedula) "
               +"VALUES ('"+usr.getNombre()+"','"+usr.getApellido()+"','"+usr.getCorreo()+"','"+usr.getContra()+"',"+usr.getSaldo()+","+usr.getId()+");";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        }catch(Exception e){
            System.out.println("Ocurrio un error ingresando el usuario : "+e.getMessage());
        }
        System.out.println("Se ingreso correctamente el usuario");
    }
    
    
    public static void insertarRegistro(){
        //Recupera conexion y crea Statement para el db
        Connection c = conn;
        Statement stmt = null;
        
        //Valida si existe una conexion abierta al db y si no trata de abrir una
        if(c == null){
            try {
                c = DriverManager.getConnection(urlDatabase,usuarioDb, passwordDb);
                c.setAutoCommit(false);
            } catch (Exception e) {
                System.out.println("Ocurrio un error : "+e.getMessage());
            }
            System.out.println("La conexi�n se realizo sin problemas! =) ");
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
    
    public static void selectRegistros(){
        //Recupera conexion y crea Statement para el db
        Connection c = conn;
        Statement stmt = null;
        
        //Valida si existe una conexion abierta al db y si no trata de abrir una
        if(c == null){
            try {
                c = DriverManager.getConnection(urlDatabase,usuarioDb, passwordDb);
                c.setAutoCommit(false);
            } catch (Exception e) {
                System.out.println("Ocurrio un error : "+e.getMessage());
            }
            System.out.println("La conexi�n se realizo sin problemas! =) ");
        }
        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM COMPANY;" );
            while ( rs.next() ) {
                int id = rs.getInt("id");
                String  name = rs.getString("name");
                int age  = rs.getInt("age");
                String  address = rs.getString("address");
                float salary = rs.getFloat("salary");
                System.out.println( "ID = " + id );
                System.out.println( "NAME = " + name );
                System.out.println( "AGE = " + age );
                System.out.println( "ADDRESS = " + address );
                System.out.println( "SALARY = " + salary );
                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }
}