package persistence.entities;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@NamedQueries({
        @NamedQuery(name = "findFlightByAirport", query = "select flight from Flight flight inner join flight.airport airport where airport.name = :name")
})

@Entity
@Table(name = "flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "flight_date")
    private Date flightDate;
    @Column(name = "flight_hour")
    private Time flightHour;
    @Column(name = "flight_to")
    private String flightTo;
    @Column(name = "price")
    private int price;
    @Column(name = "available_seats")
    private int availableSeats;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "airport_id")
    private Airport airport;

    public Flight(Date flightDate, Time flightHour, String flightTo, int price, int availableSeats, Airport airport) {
        this.flightDate = flightDate;
        this.flightHour = flightHour;
        this.flightTo = flightTo;
        this.price = price;
        this.availableSeats = availableSeats;
        this.airport = airport;
    }

    public Flight() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(Date flightDate) {
        this.flightDate = flightDate;
    }

    public Time getFlightHour() {
        return flightHour;
    }

    public void setFlightHour(Time flightHour) {
        this.flightHour = flightHour;
    }

    public String getFlightTo() {
        return flightTo;
    }

    public void setFlightTo(String flightTo) {
        this.flightTo = flightTo;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightDate=" + flightDate +
                ", flightHour=" + flightHour +
                ", flightTo='" + flightTo + '\'' +
                ", price=" + price +
                ", availableSeats=" + availableSeats +
                ", airport=" + airport +
                '}';
    }
}
