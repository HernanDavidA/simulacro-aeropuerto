package model;

import com.mysql.cj.protocol.PacketSentTimeHolder;
import database.CRUD;
import database.ConfigDB;
import entity.Passenger;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PassengerModel implements CRUD {


    @Override
    public Object insert(Object obj) {

        Connection objConnection = ConfigDB.openConnection();
        Passenger objPassenger = (Passenger) obj;

        try{
            String sql = "INSERT INTO passenger (name, last_name, identification) VALUES (?,?,?);";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepared.setString(1, objPassenger.getName());
            objPrepared.setString(2, objPassenger.getLastName());
            objPrepared.setString(3, objPassenger.getIdentification());

            objPrepared.execute();

            ResultSet objResult = objPrepared.getGeneratedKeys();

            while(objResult.next()){
                objPassenger.setId(objResult.getInt(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR >>>>>>" + e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return objPassenger;
    }

    @Override
    public List<Object> findAll() {


        List<Object> listPassenger = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try{
            String sql = "SELECT * FROM passenger;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepared.executeQuery();

            while(objResult.next()){
                Passenger objPassenger = new Passenger();

                objPassenger.setName(objResult.getString("name"));
                objPassenger.setLastName(objResult.getString("last_name"));
                objPassenger.setId(objResult.getInt("id"));
                objPassenger.setIdentification(objResult.getString("identification"));

                listPassenger.add(objPassenger);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR >>>>>>" + e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return listPassenger;
    }

    @Override
    public boolean update(Object obj) {

        Connection objConnection = ConfigDB.openConnection();

        Passenger objPassenger = (Passenger)obj;

        boolean isUpdated = false;

        try{
            String sql = "UPDATE passenger SET name = ?, last_name = ?, identification = ? WHERE id = ?;";

            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            objPrepared.setString(1, objPassenger.getName());
            objPrepared.setString(2, objPassenger.getLastName());
            objPrepared.setString(3, objPassenger.getIdentification());
            objPrepared.setInt(4, objPassenger.getId());


            int totalRowAffected = objPrepared.executeUpdate();

            if (totalRowAffected>0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Updated successfully");

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR >>>>>>" + e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }


        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {
        Passenger objPassenger = (Passenger) obj;

        Connection objConnection = ConfigDB.openConnection();

        boolean isDeleted = false;

        try{
            String sql = "DELETE FROM passenger WHERE id = ?;";

            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            objPrepared.setInt(1, objPassenger.getId());

            int totalAffectedRows = objPrepared.executeUpdate();

            if(totalAffectedRows > 0 ){
                isDeleted = true;

                JOptionPane.showMessageDialog(null, "Passenger deleted successfully");

            }
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR >>>>>>" + e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return isDeleted;
    }
    public List<Passenger> findByName(String name){

        List<Passenger> objPassengerList = new ArrayList<>();
        // Open connection with the DB
        Connection objConnection = ConfigDB.openConnection();


        try{

            String sql = "SELECT * FROM passenger WHERE name like ? ;";

            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setString(1, "%" + name + "%");

            ResultSet objResult = objPrepared.executeQuery();
            while(objResult.next()){
                Passenger objPassenger = new Passenger();
                objPassenger.setLastName(objResult.getString("last_name"));
                objPassenger.setName(objResult.getString("name"));
                objPassenger.setIdentification(objResult.getString("identification"));
                objPassenger.setId(objResult.getInt("id"));

                objPassengerList.add(objPassenger);
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "ERROR >>> " + e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return objPassengerList;
    }

    public Passenger findById(int id){
        Connection objConnection = ConfigDB.openConnection();

        Passenger objPassenger = null;

        try{
            String sql = "SELECT * FROM passenger WHERE id = ?;";

            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            objPrepared.setInt(1, id);

            ResultSet objResult = objPrepared.executeQuery();


            if (objResult.next()){
                objPassenger = new Passenger();

                objPassenger.setLastName(objResult.getString("last_name"));
                objPassenger.setName(objResult.getString("name"));
                objPassenger.setIdentification(objResult.getString("identification"));
                objPassenger.setId(objResult.getInt("id"));

            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "ERROR >>> " + e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return objPassenger;
    }
}
