package entity;

import java.sql.Time;
import java.sql.Date;


public class Flight {
    private int id;
    private String destiny;
    private Date exitDate;
    private Time exitTime;
    private int idAirplane;

    public Flight() {
    }

    public Flight(int id, String destiny, Date exitDate, Time exitTime, Airplane id_airplane) {
        this.id = id;
        this.destiny = destiny;
        this.exitDate = exitDate;
        this.exitTime = exitTime;
        this.idAirplane = idAirplane;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestiny() {
        return destiny;
    }

    public void setDestiny(String destiny) {
        this.destiny = destiny;
    }

    public java.sql.Date getExitDate() {
        return exitDate;
    }

    public void setExitDate(Date exitDate) {
        this.exitDate = exitDate;
    }

    public Time getExitTime() {
        return exitTime;
    }

    public void setExitTime(Time exitTime) {
        this.exitTime = exitTime;
    }

    public int getIdAirplane() {
        return idAirplane;
    }

    public void setIdAirplane(int idAirplane) {
        this.idAirplane = idAirplane;
    }

    @Override
    public String toString() {
        return "Flight :" +
                "ID :" + id +
                ", Destiny: '" + destiny + '\'' +
                ", Exit date: " + exitDate +
                ", Exit time: " + exitTime +
                ", id_airplane: " + idAirplane;
    }
}
