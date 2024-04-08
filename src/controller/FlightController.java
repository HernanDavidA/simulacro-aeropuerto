package controller;

import entity.Airplane;
import entity.Flight;


import model.FlightModel;
import model.AirplaneModel;



import javax.swing.*;
import java.sql.Date;
import java.sql.Time;

public class FlightController {


    public static void create(){

        FlightModel objFlightModel = new FlightModel();

        String destiny = JOptionPane.showInputDialog("Insert the destiny of the flight");
        Date exitDate = Date.valueOf(JOptionPane.showInputDialog("Insert date of the flight"));
        Time exitTime = Time.valueOf(JOptionPane.showInputDialog("Insert time of the flight"));
        int idAirplane = Integer.parseInt(JOptionPane.showInputDialog(getAllAirplane() + "Insert the id of the airplane that makes the flight"));

        Flight objFlight = new Flight();
//          Create an instance of coder
        objFlight.setDestiny(destiny);
        objFlight.setExitTime(exitTime);
        objFlight.setExitDate(exitDate);
        objFlight.setIdAirplane(idAirplane);

        // Call the method of intersection and save the object thar return on coder previously instantiated, we have to cast it
        objFlight = (Flight) objFlightModel.insert(objFlight);

        JOptionPane.showMessageDialog(null, objFlight.toString());
    }
    public static String getAllAirplane(){
        AirplaneModel objAirplaneModel = new AirplaneModel();
        String listAirplane = "AIRPLANE LIST \n";
        for (Object ite : objAirplaneModel.findAll()){
            Airplane objAirplane = (Airplane) ite;
            listAirplane += objAirplane.toString() +"\n";
        }
        return listAirplane;
    }
    public static void findByDestiny(){
        FlightModel objDestinyModel = new FlightModel();
        String destiny = JOptionPane.showInputDialog(null, "Enter the destiny");

        String listDestiny = "\n FLIGHT LIST \n";
        for (Object ite : objDestinyModel.findByDestiny(destiny)){
            // Become the Object to coder
            Flight objFlight = (Flight) ite;
            listDestiny += objFlight.toString() +"\n";
        }
        JOptionPane.showMessageDialog(null, listDestiny);
    }

    public static void delete() {
        FlightModel objFlightModel = new FlightModel();
        String listFlight = getAll(); // Esta función debe ser ajustada para mostrar solo la información relevante de cada vuelo
        int idDeleted = Integer.parseInt(JOptionPane.showInputDialog(listFlight + "\n Enter the ID of the flight to delete"));

        Flight objFlight = objFlightModel.findById(idDeleted);
        int confirm = 1;
        if (objFlight == null) {
            JOptionPane.showMessageDialog(null, "Flight not found");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the flight to \n" + objFlight.getDestiny() + " ?");
            if (confirm == 0) {
                objFlight.setId(idDeleted);
                objFlightModel.delete(objFlight);
            }
        }
    }

    public static String getAll(){
        FlightModel objFlightModel = new FlightModel();
        String listFlight = "\n PATIENT LIST \n";
        for (Object ite : objFlightModel.findAll()){
            Flight objFlight = (Flight) ite;
            listFlight += objFlight.toString() +"\n";
        }
        return listFlight;
    }

    public static void update(){
        FlightModel objFlightModel = new FlightModel();
        String listFlight = getAll();
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listFlight + "\n Enter the id of the flight to edit the date"));
        Flight objFlight = objFlightModel.findById(idUpdate);
        if (objFlight == null) {
            JOptionPane.showMessageDialog(null, "Patient not found");
        } else {
            Date date = Date.valueOf(JOptionPane.showInputDialog(null, "Enter the name : " , objFlight.getExitDate()));

            objFlight.setExitDate(date);

            objFlight.setId(idUpdate);
            objFlightModel.update(objFlight);
        }
    }
}
