package controller;

import entity.Airplane;
import model.AirplaneModel;

import javax.swing.*;

public class AirplaneController {

    public static void create(){
        AirplaneModel objAirplaneModel = new AirplaneModel();

        String model = JOptionPane.showInputDialog("Insert the model of the airplane");
        int capacity = Integer.parseInt(JOptionPane.showInputDialog("Insert capacity of the airplane"));

        Airplane objAirplane = new Airplane();
//          Create an instance of coder
        objAirplane.setModel(model);
        objAirplane.setCapacity(capacity);

        // Call the method of intersection and save the object thar return on coder previously instantiated, we have to cast it
        objAirplane = (Airplane) objAirplaneModel.insert(objAirplane);

        JOptionPane.showMessageDialog(null, objAirplane.toString());
    }

    public static void getAllString(){
        AirplaneModel objAirplaneModel = new AirplaneModel();
        String listAirplane = "\n Airplane LIST \n";
        for (Object ite : objAirplaneModel.findAll()){
            Airplane objPatient = (Airplane) ite;
            listAirplane += objPatient.toString() +"\n";
        }
        JOptionPane.showMessageDialog(null,listAirplane);
    }
    public static void delete(){
        AirplaneModel objAirplaneModel = new AirplaneModel();
        String listAirplane = getAll();
        int idDeleted = Integer.parseInt(JOptionPane.showInputDialog(listAirplane +  "\n  Enter the ID of the airplane to delete"));

        Airplane objAirplane = objAirplaneModel.findById(idDeleted);
        int confirm = 1;
        if(objAirplane == null){
            JOptionPane.showMessageDialog(null, "Airplane not found");
        }else{
            confirm = JOptionPane.showConfirmDialog(null,"Are you sure want to delete the airplane " + objAirplane.getModel() + "?");
            if (confirm == 0){
                objAirplane.setId(idDeleted);
                objAirplaneModel.delete(objAirplane);
            }
        }
    }

    public static String getAll(){
        AirplaneModel objAirplaneModel = new AirplaneModel();
        String listAirplane = "";
        for (Object ite : objAirplaneModel.findAll()){
            Airplane objAirplane = (Airplane) ite;
            listAirplane += objAirplane.toString() +"\n";
        }
        return listAirplane;
    }



}
