package business.service;

import business.dto.AirportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.dao.AirportDAO;
import persistence.entities.Airport;
import persistence.entities.City;

@Service
public class AirportService {
    @Autowired
    AirportDAO airportDAO;

    public void insert(AirportDTO airportDTO){
        City city = new City();
        city.setName(airportDTO.getCityDTO().getName());
        Airport airport = new Airport();
        airport.setName(airportDTO.getName());
        airport.setCity(city);
        airportDAO.insert(airport);
    }
}
