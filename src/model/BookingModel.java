package model;

import database.CRUD;
import database.ConfigDB;
import entity.Booking;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingModel implements CRUD {


    @Override
    public Object insert(Object obj) {
// 1. Open the connection

        Connection objConnection = ConfigDB.openConnection();
        // Convert obj to what arrived from coder

        Booking objBooking = (Booking) obj;
        try{
            String sql = "INSERT INTO booking (id_passenger, id_flight, book_date, seat) VALUES (?, ?, ?, ?);";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            // Assign the value to "?"
            objPrepare.setInt(1, objBooking.getIdPassenger());
            objPrepare.setInt(2, objBooking.getIdFlight());
            objPrepare.setDate(3, objBooking.getBookDate());
            objPrepare.setString(4, objBooking.getSeat());


            // Execute the Query
            objPrepare.execute();

            // Get the result with the id (generated keys)
            ResultSet objRest = objPrepare.getGeneratedKeys();

            // Iterate while there is a register
            while(objRest.next()){
                // We could get the value with index as well
                objBooking.setId(objRest.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "BOOKING SUCCESS");
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "ERROR >>> " + e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return objBooking;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listBooking = new ArrayList<>();
        // Open the connection
        Connection objConnection = ConfigDB.openConnection();

        try {
            // 3. Enter the query of sql
            String sql = "SELECT * FROM booking;";
            // 4. Use the prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            // 5. Execute the query and get the result (ResultSet)
            ResultSet objResult = objPrepare.executeQuery();
            // 6. While there is a next result, do
            while (objResult.next()){
                // 6.1 Create a coder
                Booking objBooking = new Booking();

                objBooking.setBookDate(objResult.getDate("book_date"));
                objBooking.setIdPassenger(objResult.getInt("id_passenger"));
                objBooking.setIdFlight(objResult.getInt("id_flight"));
                objBooking.setSeat(objResult.getString("seat"));
                listBooking.add(objBooking);
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error >>> " + e.getMessage());

        }finally {
            ConfigDB.closeConnection();
        }

        return listBooking;

    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Booking objBooking = (Booking) obj;

        boolean isUpdated = false;
        try {
            String sql = "update booking set seat = ? where id = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setString(1, objBooking.getSeat());
            objPrepared.setInt(2, objBooking.getId());


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
        Booking objCoder = (Booking) obj;
        Connection objConnection = ConfigDB.openConnection();

        boolean isDeleted = false;
        try {
            String sql = "DELETE FROM booking WHERE id = ?";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, objCoder.getId());

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
    public Booking findById(int id){
        // Open the connection

        Connection objConnection = ConfigDB.openConnection();
        Booking objBooking = null;
        try{                                         // ? = query parameter
            String sql = "SELECT * FROM booking WHERE id = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //
            objPrepare.setInt(1, id);

            ResultSet objResult = objPrepare.executeQuery();

            if(objResult.next()){
                objBooking = new Booking();
                objBooking.setIdFlight(objResult.getInt("id_flight"));
                objBooking.setIdPassenger(objResult.getInt("id_passenger"));
                objBooking.setBookDate(objResult.getDate("book_date"));
                objBooking.setSeat(objResult.getString("seat"));
                objBooking.setId(objResult.getInt("id"));

            }
            ConfigDB.closeConnection();

        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "ERROR >>>" + e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return objBooking;
    }


}
