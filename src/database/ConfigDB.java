package database;


import com.mysql.cj.jdbc.Driver;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {

    static Connection objConnection = null;


    public static Connection openConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/airport01";
            String user = "root";
            String password = "";

            objConnection = (Connection) DriverManager.getConnection(url, user, password);

            System.out.println("Connected successfully");

        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null,"ERROR >>>>" + e.getMessage());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"ERROR >>>>" + e.getMessage());

        }
        return objConnection;
    }

    public static void closeConnection(){
        try{
            if(objConnection != null)objConnection.close();
            System.out.println("Disconnected successfully");

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"ERROR >>>>" + e.getMessage());
        }
    }
}
