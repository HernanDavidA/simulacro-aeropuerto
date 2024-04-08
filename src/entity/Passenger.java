package entity;

public class Passenger {
private int id;
private String name;
private String lastName;

private String identification;

    public Passenger() {
    }

    public Passenger(int id, String name, String lastName, String identification) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.identification = identification;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    @Override
    public String toString() {
        return "Passenger:" +
                "- ID : " + id +
                "- Name : '" + name + '\'' +
                "- Last name='" + lastName + '\'' +
                "- Identification='" + identification + '\'' +
                '}';
    }
}
