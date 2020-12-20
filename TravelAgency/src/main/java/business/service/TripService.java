package business.service;

import business.dto.FlightDTO;
import business.dto.HotelDTO;
import business.dto.RoomDTO;
import business.dto.TripDTO;
import config.HibernateUtil;
import org.hibernate.Session;
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
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Continent continentAirport = null;
        continentAirport = continentDAO.findContinentByName(tripDTO.getAirportDTO().getCityDTO().getCountryDTO().getContinentDTO().getName(), session);
        if (continentAirport == null){
            continentAirport = new Continent();
            continentAirport.setName(tripDTO.getAirportDTO().getCityDTO().getCountryDTO().getContinentDTO().getName());
        }
        Country countryAirport = null;
        countryAirport = countryDAO.findCountryByName(tripDTO.getAirportDTO().getCityDTO().getCountryDTO().getName(), session);
        if (countryAirport == null){
            countryAirport = new Country();
            countryAirport.setName(tripDTO.getAirportDTO().getCityDTO().getCountryDTO().getName());
            countryAirport.setContinent(continentAirport);
        }
        City cityAirport = null;
        cityAirport = cityDAO.findCityByName(tripDTO.getAirportDTO().getCityDTO().getName(), session);
        if (cityAirport == null){
            cityAirport = new City();
            cityAirport.setName(tripDTO.getAirportDTO().getCityDTO().getName());
            cityAirport.setCountry(countryAirport);
        }
        Airport airport = null;
        airport = airportDAO.findAirportByName(tripDTO.getAirportDTO().getName(), session);
        if (airport == null){
            airport = new Airport();
            airport.setName(tripDTO.getAirportDTO().getName());
            airport.setCity(cityAirport);

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
        }



        Continent continentHotel = null;
        if (!tripDTO.getHotelDTO().getCityDTO().getCountryDTO().getContinentDTO().getName().equals(continentAirport.getName())){
            continentHotel = continentDAO.findContinentByName(tripDTO.getHotelDTO().getCityDTO().getCountryDTO().getContinentDTO().getName(), session);
            if (continentHotel == null) {
                continentHotel = new Continent();
                continentHotel.setName(tripDTO.getHotelDTO().getCityDTO().getCountryDTO().getContinentDTO().getName());
            }
        }
        Country countryHotel = null;
        if ((!tripDTO.getHotelDTO().getCityDTO().getCountryDTO().getName().equals(countryAirport.getName()))){
            countryHotel = countryDAO.findCountryByName(tripDTO.getHotelDTO().getCityDTO().getCountryDTO().getName(), session);
            if (countryHotel == null) {
                countryHotel = new Country();
                countryHotel.setName(tripDTO.getHotelDTO().getCityDTO().getCountryDTO().getName());
                if (continentHotel == null) {
                    countryHotel.setContinent(continentAirport);
                } else {
                    countryHotel.setContinent(continentHotel);
                }
            }
        }

        City cityHotel = null;
        cityHotel = cityDAO.findCityByName(tripDTO.getHotelDTO().getCityDTO().getName(), session);
        if (cityHotel == null){
            cityHotel = new City();
            cityHotel.setName(tripDTO.getHotelDTO().getCityDTO().getName());
            if (countryHotel == null){
                cityHotel.setCountry(countryAirport);
            }else{
                cityHotel.setCountry(countryHotel);
            }
        }
        Hotel hotel = null;
        hotel = hotelDAO.findHotelByNameAndCity(tripDTO.getHotelDTO().getName(), cityHotel.getName(), session);
        if (hotel == null){
            hotel = new Hotel();
            hotel.setName(tripDTO.getHotelDTO().getName());
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
            hotel.setCity(cityHotel);
        }

        Trip trip = new Trip();
        trip.setName(tripDTO.getName());
        trip.setAirport(airport);
        trip.setHotel(hotel);
        trip.setDepartureDate(tripDTO.getDepartureDate());
        trip.setReturnDate(tripDTO.getReturnDate());
        trip.setNumberDays(tripDTO.getNumberDays());
        trip.setTripType(tripDTO.getTripType());
        trip.setAdultPrice(tripDTO.getAdultPrice());
        trip.setKidPrice(tripDTO.getKidPrice());
        trip.setPromoted(tripDTO.isPromoted());
        trip.setAdultBed(tripDTO.getAdultBed());
        trip.setKidsBed(tripDTO.getKidsBed());
        trip.setStock(tripDTO.getStock());


        tripDAO.insert(trip, session);
        session.getTransaction().commit();
        session.close();

    }


    public List<String> findTripByName(String name){
        List<String> tripList = tripDAO.findTripByName(name);
        if (tripList.isEmpty()){
            return null;
        }
        return tripList;
    }
}
