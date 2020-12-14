package business.service;

import business.dto.AirportDTO;
import business.dto.CityDTO;
import business.dto.FlightDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.dao.AirportDAO;
import persistence.dao.CityDAO;
import persistence.dao.ContinentDAO;
import persistence.dao.CountryDAO;
import persistence.entities.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AirportService {
    @Autowired
    AirportDAO airportDAO;
    @Autowired
    CityDAO cityDAO;
    @Autowired
    CountryDAO countryDAO;
    @Autowired
    ContinentDAO continentDAO;

    public void insert(AirportDTO airportDTO){
        Airport airport = new Airport();
        City foundCity = cityDAO.findCityByName(airportDTO.getCityDTO().getName());
        if (foundCity == null){
            City city = new City();
            Country foundCountry = countryDAO.findCountryByName(airportDTO.getCityDTO().getCountryDTO().getName());
            if (foundCountry == null){
                Country country = new Country();
                Continent foundContinent = continentDAO.findContinentByName(airportDTO.getCityDTO().getCountryDTO().getContinentDTO().getName());
                if (foundContinent == null){
                    Continent continent = new Continent();
                    continent.setName(airportDTO.getCityDTO().getCountryDTO().getContinentDTO().getName());
                    country.setContinent(continent);
                }else{
                    country.setContinent(foundContinent);
                }
                country.setName(airportDTO.getCityDTO().getCountryDTO().getName());
                city.setCountry(country);
            }else{
                city.setCountry(foundCountry);
            }
            city.setName(airportDTO.getCityDTO().getName());
            airport.setCity(city);
        }else{
            airport.setCity(foundCity);
        }
        airport.setName(airportDTO.getName());
        Set<Flight> flightSet = new HashSet<>();
        for (FlightDTO flightDTO : airportDTO.getFlightDTOSet()){
            Flight flight = new Flight();
            flight.setFlightDate(flightDTO.getFlightDate());
            flight.setFlightHour(flightDTO.getFlightHour());
            flight.setFlightTo(flightDTO.getFlightTo());
            flight.setPrice(flightDTO.getPrice());
            flight.setAvailableSeats(flightDTO.getAvailableSeats());
            flight.setAirport(airport);
            flightSet.add(flight);
        }
        airport.setFlightSet(flightSet);
        airportDAO.insert(airport);
    }

    public AirportDTO findAirportByName(String name){
        AirportDTO airportDTO = new AirportDTO();
        Airport airport = airportDAO.findAirportByName(name);
        if (airport == null){
            return null;
        }
        airportDTO.setName(airport.getName());
        CityDTO cityDTO = new CityDTO();
        cityDTO.setName(airport.getCity().getName());
        airportDTO.setCityDTO(cityDTO);
        Set<FlightDTO> flightDTOSet = new HashSet<>();
        for (Flight flight : airport.getFlightSet()){
            FlightDTO flightDTO = new FlightDTO();
            flightDTO.setFlightDate(flight.getFlightDate());
            flightDTO.setFlightHour(flight.getFlightHour());
            flightDTO.setFlightTo(flight.getFlightTo());
            flightDTO.setPrice(flight.getPrice());
            flightDTO.setAvailableSeats(flight.getAvailableSeats());
            flightDTOSet.add(flightDTO);
        }
        airportDTO.setFlightDTOSet(flightDTOSet);
        return airportDTO;
    }

    public List<String> findAirportByCity(String name){
        List<String> airportList = airportDAO.findAirportByCity(name);
        if (airportList.isEmpty()){
            return null;
        }
        return airportList;
    }
}
