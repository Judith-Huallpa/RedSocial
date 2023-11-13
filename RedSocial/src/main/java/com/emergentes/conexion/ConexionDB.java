/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zerlu
 */
public class ConexionDB {
    static String driver="com.mysql.cj.jdbc.Driver";
    static String url="jdbc:mysql://localhost:3306/bd_biblioteca";
    static String usuario="root";
    static String password="4040";
    protected Connection conn=null;
    public ConexionDB(){
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, usuario, password);
            if (conn!= null) {
                System.out.println("Conexion OK: "+conn);
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar "+ e.getMessage());
        }catch(ClassNotFoundException ex){
            System.out.println("Error en driver "+ ex.getMessage());
        }
    }
    public Connection conectar(){
        return conn;
    }
    public void desconectar(){
        System.out.println("Cerrando la BD "+ conn);
        try {
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
