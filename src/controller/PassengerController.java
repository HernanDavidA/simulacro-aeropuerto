package controller;

import entity.Passenger;
import model.PassengerModel;

import javax.swing.*;

public class PassengerController {
    public static void create(){
        PassengerModel objDoctorModel = new PassengerModel();
        String name = JOptionPane.showInputDialog("Insert name of the passenger");
        String lastName = JOptionPane.showInputDialog("Insert last name of the passenger");
        String identification = JOptionPane.showInputDialog("Enter the identification of the passenger");

        Passenger objPassenger = new Passenger();

        objPassenger.setName(name);
        objPassenger.setLastName(lastName);
        objPassenger.setIdentification(identification);

        objPassenger = (Passenger) objDoctorModel.insert(objPassenger);
        JOptionPane.showMessageDialog(null, objPassenger.toString());

    }
    public static String getAll(){
        PassengerModel objPassengerModel = new PassengerModel();
        String listPassenger = "\n FLIGHT LIST \n";
        for (Object ite : objPassengerModel.findAll()){

            Passenger objPassenger = (Passenger) ite;
            listPassenger += objPassenger.toString() +"\n";
        }
        return listPassenger;
    }
    public static void update(){
        PassengerModel objPassengerModel = new PassengerModel();
        String listPassenger = getAll();
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listPassenger + "\n Enter the id of the passenger to edit"));
        Passenger objPassenger = objPassengerModel.findById(idUpdate);
        if (objPassenger == null) {
            JOptionPane.showMessageDialog(null, "Patient not found");
        } else {
            String name = JOptionPane.showInputDialog(null, "Enter the name : " , objPassenger.getName());
            String lastName = JOptionPane.showInputDialog(null, "Enter the last name: ", objPassenger.getLastName());
            String identification = JOptionPane.showInputDialog(null, "Enter the date", objPassenger.getIdentification());

            objPassenger.setName(name);
            objPassenger.setLastName(lastName);
            objPassenger.setIdentification(identification);

            objPassenger.setId(idUpdate);
            objPassengerModel.update(objPassenger);
        }
    }
    public static void findByName(){
        PassengerModel objPassengerModel = new PassengerModel();
        String name = JOptionPane.showInputDialog(null, "Enter the passenger's name");


        String listPassenger = "\n PASSENGER LIST \n";
        for (Object ite : objPassengerModel.findByName(name)){
            // Become the Object to coder
            Passenger objPassenger = (Passenger) ite;
            listPassenger += objPassenger.toString() +"\n";
        }
        JOptionPane.showMessageDialog(null, listPassenger);
    }
    public static void delete(){
        PassengerModel objPassengerModel = new PassengerModel();

        String listPassenger = getAll();

        int idDeleted = Integer.parseInt(JOptionPane.showInputDialog(listPassenger +  "\n  Enter the ID of the passenger to delete"));

        Passenger objPassenger = objPassengerModel.findById(idDeleted);
        int confirm = 1;
        if(objPassenger == null){
            JOptionPane.showMessageDialog(null, "Passenger not found");
        }else{
            confirm = JOptionPane.showConfirmDialog(null,"Are you sure want to delete the passenger? \n" + objPassenger.getName());
            if (confirm == 0){
                objPassenger.setId(idDeleted);
                objPassengerModel.delete(objPassenger);

            }
        }
    }
    public static void getAllString(){
        PassengerModel objPassengerModel = new PassengerModel();
        String listPassenger = "\n PASSENGER LIST \n";
        for (Object ite : objPassengerModel.findAll()){

            Passenger objDoctor = (Passenger) ite;
            listPassenger += objDoctor.toString() +"\n";
        }
        JOptionPane.showMessageDialog(null, listPassenger);
    }


}
