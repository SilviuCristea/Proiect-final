package business.service;

import business.dto.AirportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.dao.AirportDAO;
import persistence.entities.Airport;
import persistence.entities.City;

import java.util.ArrayList;
import java.util.List;

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
    public List<AirportDTO> findAirportByname(String name){
        List<Airport> airportList = airportDAO.findAirportByName(name);
        List<AirportDTO> airportDTOList = new ArrayList<AirportDTO>();
        for (Airport airport : airportList){
            AirportDTO airportDTO = new AirportDTO();
            airportDTO.setName(airport.getName());
            airportDTOList.add(airportDTO);
        }
        return airportDTOList;
    }
}
