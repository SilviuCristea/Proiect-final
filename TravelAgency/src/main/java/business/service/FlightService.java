package business.service;

import business.dto.FlightDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.dao.FlightDAO;
import persistence.entities.Airport;
import persistence.entities.Flight;

@Service
public class FlightService {
    @Autowired
    FlightDAO flightDAO;

    public void insert(FlightDTO flightDTO){
        Airport airport = new Airport();
        airport.setName(flightDTO.getAirportDTO().getName());
        Flight flight = new Flight();
        flight.setFlightDate(flightDTO.getFlightDate());
        flight.setFlightHour(flightDTO.getFlightHour());
        flight.setFlightTo(flightDTO.getFlightTo());
        flight.setPrice(flightDTO.getPrice());
        flight.setAvailableSeats(flightDTO.getAvailableSeats());
        flight.setAirport(airport);
        flightDAO.insert(flight);
    }
}
