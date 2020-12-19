package business.service;

import business.dto.FlightDTO;
import business.dto.HotelDTO;
import business.dto.RoomDTO;
import business.dto.TripDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.dao.*;
import persistence.entities.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TripService {
    @Autowired
    TripDAO tripDAO;
    @Autowired
    AirportDAO airportDAO;
    @Autowired
    HotelDAO hotelDAO;
    @Autowired
    CityDAO cityDAO;
    @Autowired
    CountryDAO countryDAO;
    @Autowired
    ContinentDAO continentDAO;

    public void insert(TripDTO tripDTO){
        Trip trip = new Trip();
        trip.setName(tripDTO.getName());
        Airport foundAirport = airportDAO.findAirportByName(tripDTO.getAirportDTO().getName());
        if (foundAirport == null){
            Airport airport = new Airport();
            City foundCity = cityDAO.findCityByName(tripDTO.getAirportDTO().getCityDTO().getName());
            if (foundCity == null){
                City city = new City();
                Country foundCountry = countryDAO.findCountryByName(tripDTO.getAirportDTO().getCityDTO().getCountryDTO().getName());
                if (foundCountry == null){
                    Country country = new Country();
                    Continent foundContinent = continentDAO.findContinentByName(tripDTO.getAirportDTO().getCityDTO().getCountryDTO().getContinentDTO().getName());
                    if (foundContinent == null){
                        Continent continent = new Continent();
                        continent.setName(tripDTO.getAirportDTO().getCityDTO().getCountryDTO().getName());
                        country.setContinent(continent);
                    }else{
                        country.setContinent(foundContinent);
                    }
                    country.setName(tripDTO.getAirportDTO().getCityDTO().getCountryDTO().getContinentDTO().getName());
                    city.setCountry(country);
                }else{
                    city.setCountry(foundCountry);
                }
                city.setName(tripDTO.getAirportDTO().getCityDTO().getName());
                airport.setCity(city);
            }else{
                airport.setCity(foundCity);
            }
            airport.setName(tripDTO.getAirportDTO().getName());
            Set<Flight> flightSet = new HashSet<>();
            for (FlightDTO flightDTO : tripDTO.getAirportDTO().getFlightDTOSet()){
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
            trip.setAirport(airport);
        }else {
            trip.setAirport(foundAirport);
        }
        Hotel foundHotel = (Hotel) hotelDAO.findHotelByCity(tripDTO.getHotelDTO().getName());
        if (foundHotel == null){
            Hotel hotel = new Hotel();
            City foundCity = cityDAO.findCityByName(tripDTO.getHotelDTO().getCityDTO().getName());
            if (foundCity == null){
                City city = new City();
                Country foundCountry = countryDAO.findCountryByName(tripDTO.getHotelDTO().getCityDTO().getCountryDTO().getName());
                if (foundCountry == null){
                    Country country = new Country();
                    Continent foundContinent = continentDAO.findContinentByName(tripDTO.getHotelDTO().getCityDTO().getCountryDTO().getContinentDTO().getName());
                    if (foundContinent == null){
                        Continent continent = new Continent();
                        continent.setName(tripDTO.getHotelDTO().getCityDTO().getCountryDTO().getContinentDTO().getName());
                        country.setContinent(continent);
                    }else{
                        country.setContinent(foundContinent);
                    }
                    country.setName(tripDTO.getHotelDTO().getCityDTO().getCountryDTO().getName());
                    city.setCountry(country);
                }else{
                    city.setCountry(foundCountry);
                }
                city.setName(tripDTO.getHotelDTO().getCityDTO().getName());
                hotel.setCity(city);
            }else{
                hotel.setCity(foundCity);
            }
            hotel.setName(tripDTO.getHotelDTO().getName());
            hotel.setStars(tripDTO.getHotelDTO().getStars());
            hotel.setStars(tripDTO.getHotelDTO().getStars());
            hotel.setDescription(tripDTO.getHotelDTO().getDescription());
            Set<Room> roomSet = new HashSet<>();
            for (RoomDTO roomDTO : tripDTO.getHotelDTO().getRoomDTOSet()){
                Room room = new Room();
                room.setType(roomDTO.getType());
                room.setNumber(roomDTO.getNumber());
                room.setExtraBed(roomDTO.isExtraBed());
                room.setHotel(hotel);
                roomSet.add(room);
            }
            hotel.setRoomSet(roomSet);
            trip.setHotel(hotel);
        }else{
            trip.setHotel(foundHotel);
        }
        trip.setDepartureDate(tripDTO.getDepartureDate());
        trip.setReturnDate(tripDTO.getReturnDate());
        trip.setNumberDays(tripDTO.getNumberDays());
        trip.setAdultPrice(tripDTO.getAdultPrice());
        trip.setKidPrice(tripDTO.getKidPrice());
        trip.setPromoted(tripDTO.isPromoted());
        trip.setAdultBed(tripDTO.getAdultBed());
        trip.setKidsBed(tripDTO.getKidsBed());
        trip.setStock(tripDTO.getStock());
        tripDAO.insert(trip);
    }


}
