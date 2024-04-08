package controller;

import entity.Airplane;
import entity.Booking;
import entity.Flight;
import entity.Passenger;
import model.AirplaneModel;
import model.BookingModel;
import model.FlightModel;
import model.PassengerModel;

import javax.swing.*;
import java.sql.Date;
import java.sql.Time;

public class BookingController {
    public static void createBooking() {
        BookingModel objBookingModel = new BookingModel();

        int idPassenger = Integer.parseInt(JOptionPane.showInputDialog(getAllPassenger() + "Insert the id of the owner of the flight"));
        int idFlight = Integer.parseInt(JOptionPane.showInputDialog(getAllFlight() + "Insert the id of the owner of the flight"));
        Date bookDate = Date.valueOf(JOptionPane.showInputDialog("Insert date of the booking"));
        String seat = JOptionPane.showInputDialog("Insert the seat");

        Booking objBooking = new Booking();

        objBooking.setIdPassenger(idPassenger);
        objBooking.setIdFlight(idFlight);
        objBooking.setBookDate(bookDate);
        objBooking.setSeat(seat);

        objBooking = (Booking) objBookingModel.insert(objBooking);

        JOptionPane.showMessageDialog(null, objBooking.toString());
    }
    public static String getAllFlight(){
        FlightModel objFlightModel = new FlightModel();
        String listFlight = "\n FLIGHT LIST \n";
        for (Object ite : objFlightModel.findAll()){
            Flight objFlight = (Flight) ite;
            listFlight += objFlight.toString() +"\n";
        }
        return listFlight;
    }
    public static String getAllPassenger(){
        PassengerModel objPassengerModel = new PassengerModel();
        String listPassenger = "\n PATIENT LIST \n";
        for (Object ite : objPassengerModel.findAll()){

            Passenger objPassenger = (Passenger) ite;
            listPassenger += objPassenger.toString() +"\n";
        }
        return listPassenger;
    }

    public static void delete() {
        BookingModel objFlightModel = new BookingModel();
        String listFlight = getAll(); // Esta función debe ser ajustada para mostrar solo la información relevante de cada vuelo
        int idDeleted = Integer.parseInt(JOptionPane.showInputDialog(listFlight + "\n Enter the ID of the flight to delete"));

        Booking objBooking = objFlightModel.findById(idDeleted);
        int confirm = 1;
        if (objBooking == null) {
            JOptionPane.showMessageDialog(null, "Flight not found");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the flight to \n" + objBooking.toString() + " ?");
            if (confirm == 0) {
                objBooking.setId(idDeleted);
                objFlightModel.delete(objBooking);
            }
        }
    }

    public static void getAllString(){

        JOptionPane.showMessageDialog(null, getAll());
    }
    public static void updateSeat(){
        BookingModel objFlightModel = new BookingModel();
        String listFlight = getAll();
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listFlight + "\n Enter the id of the flight to edit the date"));
        Booking objFlight = objFlightModel.findById(idUpdate);
        if (objFlight == null) {
            JOptionPane.showMessageDialog(null, "Patient not found");
        } else {
            String date = String.valueOf(JOptionPane.showInputDialog(null, "Enter the name : " , objFlight.getSeat()));

            objFlight.setSeat(date);

            objFlight.setId(idUpdate);
            objFlightModel.update(objFlight);
        }
    }
    public static String getAll(){
        BookingModel objBookingModel = new BookingModel();
        String listPassenger = "\n BOOKING LIST \n";
        for (Object ite : objBookingModel.findAll()){

            Passenger objPassenger = (Passenger) ite;
            listPassenger += objPassenger.toString() +"\n";
        }
        return listPassenger;
    }
}
