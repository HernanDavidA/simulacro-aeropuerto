package entity;

import java.awt.*;
import java.sql.Date;

public class Booking {
    private int id;
    private int idPassenger;
    private int idFlight;
    private Date bookDate;
    private String seat;

    public Booking() {
    }

    public Booking(int id, int idPassenger, int idFlight, Date bookDate, String seat) {
        this.id = id;
        this.idPassenger = idPassenger;
        this.idFlight = idFlight;
        this.bookDate = bookDate;
        this.seat = seat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPassenger() {
        return idPassenger;
    }

    public void setIdPassenger(int idPassenger) {
        this.idPassenger = idPassenger;
    }

    public int getIdFlight() {
        return idFlight;
    }

    public void setIdFlight(int idFlight) {
        this.idFlight = idFlight;
    }

    public Date getBookDate() {
        return bookDate;
    }

    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    @Override
    public String toString() {
        return "Booking:" +'\'' +
                "- ID:" + id + '\'' +
                "- ID Passenger:" + idPassenger +'\'' +
                ", ID Flight:" + idFlight +'\'' +
                ", Booking date:" + bookDate +'\'' +
                ", Seat:'" + seat ;
    }
}
