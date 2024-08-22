/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PMQUANG
 */
public class DBConnect {

    protected Connection connection;
    protected PreparedStatement statement;
    protected ResultSet resultSet;

    public DBConnect(String url, String username, String pass) {
        try {
            //        url: string connection: chứa thông tin server, DB
//        acc:user, pass: acc của server
            //Driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //connection
            connection = DriverManager.getConnection(url, username, pass);
            System.out.println("connected");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public DBConnect() {
        this("jdbc:sqlserver://localhost:1433;databaseName=BookStore", "sa", "123456");
    }

    public ResultSet getData(String sql) {
        Statement state;
        ResultSet rs = null;
        try {
            state =connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = state.executeQuery(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       return rs;
    }

    public static void main(String[] args) {
        new DBConnect();
    }
}
