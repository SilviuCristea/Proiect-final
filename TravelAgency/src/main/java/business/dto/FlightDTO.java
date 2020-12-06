package business.dto;

import java.sql.Time;
import java.util.Date;

public class FlightDTO {
    private Date flightDate;
    private Time flightHour;
    private String flightTo;
    private int price;
    private int availableSeats;
    private AirportDTO airportDTO;

    public FlightDTO(Date flightDate, Time flightHour, String flightTo, int price, int availableSeats, AirportDTO airportDTO) {
        this.flightDate = flightDate;
        this.flightHour = flightHour;
        this.flightTo = flightTo;
        this.price = price;
        this.availableSeats = availableSeats;
        this.airportDTO = airportDTO;
    }

    public FlightDTO(Date flightDate, Time flightHour, String flightTo, int price, int availableSeats) {
        this.flightDate = flightDate;
        this.flightHour = flightHour;
        this.flightTo = flightTo;
        this.price = price;
        this.availableSeats = availableSeats;
    }

    public FlightDTO() {
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

    public AirportDTO getAirportDTO() {
        return airportDTO;
    }

    public void setAirportDTO(AirportDTO airportDTO) {
        this.airportDTO = airportDTO;
    }

    @Override
    public String toString() {
        return "FlightDTO{" +
                "flightDate=" + flightDate +
                ", flightHour=" + flightHour +
                ", flightTo='" + flightTo + '\'' +
                ", price=" + price +
                ", availableSeats=" + availableSeats +
                '}';
    }
}
