/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.application.Persistence.impl;

import edu.application.model.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.springframework.stereotype.Service;
import edu.application.Exceptions.RoulettePersistenceException;

/**
 *
 * @author Usuario
 */
public class RouletteDB{
    //Direccion del DB de prueba
    static String urlDatabase = "jdbc:postgresql://ec2-23-21-115-109.compute-1.amazonaws.com:5432/dcjlcvlpuum728";
    static String usuarioDb = "unkwzcvdxtrfys";
    static String passwordDb= "03eaed5577b5eadb70f2bbe4de0d68e23a967ab611eff45138dc2b690b0fe052";
    static Connection conn = null;

    //Main provicional para realizar pruebas a la base de datos
    /**public void main(String[] args) throws RoulettePersistenceException{
        Usuario user = new Usuario(768903,"Didio", "Osorio", "dosorasdsaio@sywork.net","contra");
        realizaConexion();
        insertarUsuario(user);
        ConsultarUsuario("dosorasdsaio@sywork.net");
        //createTable();
        //insertarRegistro();
        //selectRegistros();
    }*/
    
    
    public void realizaConexion() throws RoulettePersistenceException{
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(urlDatabase,  usuarioDb, passwordDb);
            System.out.println("La conexion se realizo sin problemas!");
        } catch (ClassNotFoundException e) {
            System.out.println("Ocurrio un error not found: "+e.getMessage());
        } catch (SQLException e) {
            System.out.println("Ocurrio un error : "+e.getMessage());
        }        
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
            System.out.println("La conexion se realizo sin problemas! ");
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
    
    
    public void insertarUsuario(Usuario user){
        //Recupera conexion y crea Statement para el db
        Usuario usr = user;
        Connection c = conn;
        Statement stmt = null;
        
        //Valida si existe una conexion abierta al db y si no trata de abrir una
        if(c == null){
            try {
                c = DriverManager.getConnection(urlDatabase,usuarioDb, passwordDb);
                System.out.println("La conexion se realizo sin problemas!");
            } catch (Exception e) {
                System.out.println("Ocurrio un error : "+e.getMessage());
            }
        }
        try{
            Class.forName("org.postgresql.Driver");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            //SQL de ejemplo mientras se define lo que se va a ingresar a la base de datos
            String sql = "INSERT INTO usuario (nombre,apellido,correo,contraseña,saldo,numerocedula) "
               +"VALUES ('"+usr.getNombre()+"','"+usr.getApellido()+"','"+usr.getCorreo()+"','"+usr.getContra()+"',"+usr.getSaldo()+","+usr.getId()+");";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
            System.out.println("Se ingreso correctamente el usuario");
        }catch(Exception e){
            System.out.println("Ocurrio un error ingresando el usuario : "+e.getMessage());
        }
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
            System.out.println("La conexion se realizo sin problemas! =) ");
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
            System.out.println("Operation done successfully");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        
    }

    
    public void alterarSaldo(Usuario us, float valor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void updateUsuario(Usuario us){
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
            System.out.println("La conexion se realizo sin problemas! =) ");
        }

        try {
            Class.forName("org.postgresql.Driver");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "UPDATE usuario SET nombre='"+ us.getNombre() + "', apellido='" + us.getApellido() +"', correo='"
                    + us.getCorreo() + "', contraseña='" + us.getContra() + "', saldo='" + us.getSaldo() + "', numerocedula='"
                    + us.getId()+"' WHERE correo='" + us.getCorreo() +"';";
            int n = stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    
    public Usuario getUsuario(String user) throws RoulettePersistenceException {
        //Recupera conexion y crea Statement para el db
        System.err.println(user);
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
            //System.out.println("La conexion se realizo sin problemas! =) ");
        }
        try{
            Class.forName("org.postgresql.Driver");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            //SQL de ejemplo mientras se define lo que se va a ingresar a la base de datos
            String sql = "Select * from usuario where correo ='"+user+"';";
            System.out.println(sql);
            String rsl = null;
            ResultSet rs = stmt.executeQuery(sql);
            Usuario u = null;
            while (rs.next()) {
                 rsl = rs.getString("contraseña");
                 System.out.println(rsl);
                 u = new Usuario(rs.getInt("numerocedula"),rs.getString("nombre"), rs.getString("apellido"),
                         rs.getString("correo"), rs.getString("contraseña"), rs.getFloat("saldo"));
            }
            rs.close();
            stmt.close();
            c.close();
            //System.out.println("Operation done successfully");
            //System.out.println(rsl);
            return u;
            
        }catch(Exception e){
            System.out.println("Ocurrio un error ingresando el usuario : "+e.getMessage());
        }
        return null;
    }
}