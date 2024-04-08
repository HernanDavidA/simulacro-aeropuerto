package model;

import database.CRUD;
import database.ConfigDB;
import entity.Airplane;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;

public class AirplaneModel implements CRUD {

    @Override
    public Object insert(Object obj) {

        Connection objConnection = ConfigDB.openConnection();
        Airplane objAirplane = (Airplane) obj;

        try{
            String sql = "INSERT INTO airplane (model, capacity) VALUES (?, ?)";

            PreparedStatement objPrepared = objConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            objPrepared.setString(1, objAirplane.getModel());
            objPrepared.setInt(2, objAirplane.getCapacity());

            objPrepared.execute();

            ResultSet objResult = objPrepared.getGeneratedKeys();

            while(objResult.next()){
                objAirplane.setId(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "Airplane added successfully! 😃");
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "ERROR >>>> " + e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return objAirplane;
    }
    @Override
    public List<Object> findAll() {

        List<Object> listAirplane = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try{
            String sql = "SELECT * FROM airplane;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepared.executeQuery();
            while(objResult.next()){
                Airplane objAirplane = new Airplane();

                objAirplane.setCapacity(objResult.getInt("capacity"));
                objAirplane.setModel(objResult.getString("model"));
                objAirplane.setId(objResult.getInt("id"));
                listAirplane.add(objAirplane);
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return listAirplane;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Airplane objAirplane = (Airplane) obj;

        boolean isUpdated = false;
        try {
            String sql = "UPDATE airplane SET capacity = ?, model = ?  where id  = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setInt(1, objAirplane.getCapacity());
            objPrepared.setString(2, objAirplane.getModel());
            objPrepared.setInt(3, objAirplane.getId());

            int totalRowAffected = objPrepared.executeUpdate();

            if (totalRowAffected > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Updated successfully");
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error >>>> " + e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {
        Airplane objAirplane = (Airplane) obj;
        Connection objConnection = ConfigDB.openConnection();

        boolean isDeleted = false;
        try {

            String sql = "DELETE FROM airplane WHERE id = ?";
            // Create the prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            // Give value to ?
            objPrepare.setInt(1, objAirplane.getId());
            // 7. Execute the query (executeUpdate) return the quantity of data affected
            int totalAffectedRows = objPrepare.executeUpdate();
            if (totalAffectedRows > 0 ){
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "The update was successfully");
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error >>>" + e.getMessage());
        }
        ConfigDB.closeConnection();
        return isDeleted;
    }
    public static Airplane findById(int id){

        Connection objConnection = ConfigDB.openConnection();
        Airplane objAirplane = null;
        try{                                         // ? = query parameter
            String sql = "SELECT * FROM airplane WHERE id = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, id);
            ResultSet objResult = objPrepare.executeQuery();

            if(objResult.next()){
                objAirplane = new Airplane();
                objAirplane.setCapacity(objResult.getInt("capacity"));
                objAirplane.setModel(objResult.getString("model"));
                objAirplane.setId(objResult.getInt("id"));
            }
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "ERROR >>>" + e.getMessage());
        }
        ConfigDB.closeConnection();
        return objAirplane;
    }



}
