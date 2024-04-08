package model;

import database.CRUD;
import database.ConfigDB;
import entity.Flight;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FlightModel implements CRUD {
    @Override
    public Object insert(Object obj) {

        Connection objConnection = ConfigDB.openConnection();
        Flight objFlight = (Flight) obj;

        try{
            String sql = "INSERT INTO flight (destiny, exit_date, exit_time, id_airplane) VALUES (?, ?, ?, ?);";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);


            objPrepared.setString(1, objFlight.getDestiny());
            objPrepared.setDate(2, objFlight.getExitDate());
            objPrepared.setTime(3, objFlight.getExitTime());
            objPrepared.setInt(4, objFlight.getIdAirplane());

            objPrepared.execute();

            ResultSet objResult = objPrepared.getGeneratedKeys();

            while(objResult.next()){
                objFlight.setId(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "FLIGHT BOOKED SUCCESSFULLY");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "ERROR >>> " + e.getMessage());

        }finally {
            ConfigDB.closeConnection();
        }
        return objFlight;
    }

    @Override
    public List<Object> findAll() {

        List<Object> listFlight = new ArrayList<>();
        // Open the connection
        Connection objConnection = ConfigDB.openConnection();

        try {
            // 3. Enter the query of sql
            String sql = "SELECT * FROM flight;";
            // 4. Use the prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            // 5. Execute the query and get the result (ResultSet)
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){

                Flight objFlight = new Flight();

                objFlight.setDestiny(objResult.getString("destiny"));
                objFlight.setExitDate(objResult.getDate("exit_date"));
                objFlight.setExitTime(objResult.getTime("exit_time"));
                objFlight.setIdAirplane(objResult.getInt("id_airplane"));
                objFlight.setId(objResult.getInt("id"));
                listFlight.add(objFlight);
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error >>> " + e.getMessage());

        }finally {
            ConfigDB.closeConnection();
        }

        return listFlight;

    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Flight objFlight = (Flight) obj;

        boolean isUpdated = false;
        try {
            String sql = "UPDATE flight SET exit_date = ? WHERE id = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);


            objPrepared.setDate(1, objFlight.getExitDate());
            objPrepared.setInt(2, objFlight.getId());

            int totalRowAffected = objPrepared.executeUpdate();

            if (totalRowAffected >0 ){
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

        Flight objFlight = (Flight) obj;

        Connection objConnection = ConfigDB.openConnection();

        boolean isDeleted = false;
        try {

            String sql = "DELETE FROM flight WHERE id = ?";
            // Create the prepareStatement
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            // Give value to ?
            objPrepared.setInt(1, objFlight.getId());
            // 7. Execute the query (executeUpdate) return the quantity of data affected
            int totalAffectedRows = objPrepared.executeUpdate();
            System.out.println(obj);
            if (totalAffectedRows > 0 ){
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "The update was successfully");
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error >>> " + e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return isDeleted;
    }

    public Flight findById(int id){
        // Open the connection

        Connection objConnection = ConfigDB.openConnection();
        Flight objFlight = null;
        try{                                         // ? = query parameter
            String sql = "SELECT * FROM flight WHERE id = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //
            objPrepare.setInt(1, id);

            ResultSet objResult = objPrepare.executeQuery();

            if(objResult.next()){

                objFlight = new Flight();
                objFlight.setDestiny(objResult.getString("destiny"));
                objFlight.setExitDate(objResult.getDate("exit_date"));
                objFlight.setExitTime(objResult.getTime("exit_time"));
                objFlight.setIdAirplane(objResult.getInt("id_airplane"));
            }

        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "ERROR >>>" + e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return objFlight;
    }
    public List<Flight> findByDestiny(String destiny){

        List<Flight> objFlightList = new ArrayList<>();
        // Open connection with the DB
        Connection objConnection = ConfigDB.openConnection();

        try{

            String sql = "SELECT * FROM flight WHERE destiny LIKE ? ;";

            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setString(1, "%" + destiny + "%");

            ResultSet objResult = objPrepared.executeQuery();
            while(objResult.next()){
                Flight objFlight = new Flight();
                objFlight.setDestiny(objResult.getString("destiny"));
                objFlight.setExitDate(objResult.getDate("exit_date"));
                objFlight.setExitTime(objResult.getTime("exit_time"));
                objFlight.setIdAirplane(objResult.getInt("id_airplane"));

                objFlightList.add(objFlight);
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "ERROR >>> " + e.getMessage());
        }finally {
            ConfigDB.closeConnection();

        }

        return objFlightList;
    }


}

