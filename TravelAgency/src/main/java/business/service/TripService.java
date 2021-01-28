package business.service;

import business.dto.*;
import config.HibernateUtil;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.dao.*;
import persistence.entities.*;

import java.util.*;

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
        }else{
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


    public List<TripDTO> findAllTrips(){
        List<TripDTO> tripDTOList = new ArrayList<>();
        List<Trip> tripList = tripDAO.findAllTrip();
        for (Trip trip : tripList){
            TripDTO tripDTO = new TripDTO();
            tripDTO.setName(trip.getName());
            AirportDTO airportDTO = new AirportDTO();
            airportDTO.setName(trip.getAirport().getName());
            CityDTO cityDTOFromAirportDTO = new CityDTO();
            cityDTOFromAirportDTO.setName(trip.getAirport().getCity().getName());
            CountryDTO countryDTO = new CountryDTO();
            countryDTO.setName(trip.getAirport().getCity().getCountry().getName());
            ContinentDTO continentDTO = new ContinentDTO();
            continentDTO.setName(trip.getAirport().getCity().getCountry().getContinent().getName());
            countryDTO.setContinentDTO(continentDTO);
            cityDTOFromAirportDTO.setCountryDTO(countryDTO);
            airportDTO.setCityDTO(cityDTOFromAirportDTO);
            Set<FlightDTO> flightDTOSet = new HashSet<>();
            for (Flight flight : trip.getAirport().getFlightSet()){
                FlightDTO flightDTO = new FlightDTO();
                if (flight.getFlightTo().equals(trip.getHotel().getCity().getName())) {
                    flightDTO.setFlightDate(flight.getFlightDate());
                    flightDTO.setFlightHour(flight.getFlightHour());
                    flightDTO.setFlightTo(flight.getFlightTo());
                    flightDTO.setPrice(flight.getPrice());
                    flightDTO.setAvailableSeats(flight.getAvailableSeats());
                    flightDTOSet.add(flightDTO);
                }
            }
            airportDTO.setFlightDTOSet(flightDTOSet);
            tripDTO.setAirportDTO(airportDTO);
            HotelDTO hotelDTO = new HotelDTO();
            hotelDTO.setName(trip.getHotel().getName());
            hotelDTO.setStars(trip.getHotel().getStars());
            hotelDTO.setDescription(trip.getHotel().getDescription());
            CityDTO cityDTOFromHotelDTO = new CityDTO();
            cityDTOFromHotelDTO.setName(trip.getHotel().getCity().getName());
            CountryDTO countryDTOHotel = new CountryDTO();
            countryDTOHotel.setName(trip.getHotel().getCity().getCountry().getName());
            cityDTOFromHotelDTO.setCountryDTO(countryDTOHotel);
            ContinentDTO continentDTOHotel = new ContinentDTO();
            continentDTOHotel.setName(trip.getHotel().getCity().getCountry().getContinent().getName());
            countryDTOHotel.setContinentDTO(continentDTOHotel);
            hotelDTO.setCityDTO(cityDTOFromHotelDTO);
            Set<RoomDTO> roomDTOSet = new HashSet<>();
            for (Room room : trip.getHotel().getRoomSet()){
                RoomDTO roomDTO = new RoomDTO();
                roomDTO.setType(room.getType());
                roomDTO.setNumber(room.getNumber());
                roomDTO.setExtraBed(room.isExtraBed());
                roomDTOSet.add(roomDTO);
            }
            hotelDTO.setRoomDTOSet(roomDTOSet);
            tripDTO.setHotelDTO(hotelDTO);
            tripDTO.setDepartureDate(trip.getDepartureDate());
            tripDTO.setReturnDate(trip.getReturnDate());
            tripDTO.setNumberDays(trip.getNumberDays());
            tripDTO.setTripType(trip.getTripType());
            tripDTO.setAdultPrice(trip.getAdultPrice());
            tripDTO.setKidPrice(trip.getKidPrice());
            tripDTO.setPromoted(trip.isPromoted());
            tripDTO.setAdultBed(trip.getAdultBed());
            tripDTO.setKidsBed(trip.getKidsBed());
            tripDTO.setStock(trip.getStock());
            tripDTOList.add(tripDTO);
        }
        return tripDTOList;
    }

    public List<TripDTO> findPromotedTrips(boolean promoted){
        List<Trip> tripList = tripDAO.findPromotedTrips(promoted);
        if (tripList.isEmpty()){
            return null;
        }
        List<TripDTO> tripDTOList = new ArrayList<>();
        for (Trip trip : tripList){
            TripDTO tripDTO = new TripDTO();
            tripDTO.setName(trip.getName());
            AirportDTO airportDTO = new AirportDTO();
            airportDTO.setName(trip.getAirport().getName());
            CityDTO cityDTOFromAirportDTO = new CityDTO();
            cityDTOFromAirportDTO.setName(trip.getAirport().getCity().getName());
            CountryDTO countryDTO = new CountryDTO();
            countryDTO.setName(trip.getAirport().getCity().getCountry().getName());
            ContinentDTO continentDTO = new ContinentDTO();
            continentDTO.setName(trip.getAirport().getCity().getCountry().getContinent().getName());
            countryDTO.setContinentDTO(continentDTO);
            cityDTOFromAirportDTO.setCountryDTO(countryDTO);
            airportDTO.setCityDTO(cityDTOFromAirportDTO);
            Set<FlightDTO> flightDTOSet = new HashSet<>();
            for (Flight flight : trip.getAirport().getFlightSet()){
                FlightDTO flightDTO = new FlightDTO();
                if (flight.getFlightTo().equals(trip.getHotel().getCity().getName())) {
                    flightDTO.setFlightDate(flight.getFlightDate());
                    flightDTO.setFlightHour(flight.getFlightHour());
                    flightDTO.setFlightTo(flight.getFlightTo());
                    flightDTO.setPrice(flight.getPrice());
                    flightDTO.setAvailableSeats(flight.getAvailableSeats());
                    flightDTOSet.add(flightDTO);
                }
            }
            airportDTO.setFlightDTOSet(flightDTOSet);
            tripDTO.setAirportDTO(airportDTO);
            HotelDTO hotelDTO = new HotelDTO();
            hotelDTO.setName(trip.getHotel().getName());
            hotelDTO.setStars(trip.getHotel().getStars());
            hotelDTO.setDescription(trip.getHotel().getDescription());
            CityDTO cityDTOFromHotelDTO = new CityDTO();
            cityDTOFromHotelDTO.setName(trip.getHotel().getCity().getName());
            CountryDTO countryDTOHotel = new CountryDTO();
            countryDTOHotel.setName(trip.getHotel().getCity().getCountry().getName());
            cityDTOFromHotelDTO.setCountryDTO(countryDTOHotel);
            ContinentDTO continentDTOHotel = new ContinentDTO();
            continentDTOHotel.setName(trip.getHotel().getCity().getCountry().getContinent().getName());
            countryDTOHotel.setContinentDTO(continentDTOHotel);
            hotelDTO.setCityDTO(cityDTOFromHotelDTO);
            Set<RoomDTO> roomDTOSet = new HashSet<>();
            for (Room room : trip.getHotel().getRoomSet()){
                RoomDTO roomDTO = new RoomDTO();
                roomDTO.setType(room.getType());
                roomDTO.setNumber(room.getNumber());
                roomDTO.setExtraBed(room.isExtraBed());
                roomDTOSet.add(roomDTO);
            }
            hotelDTO.setRoomDTOSet(roomDTOSet);
            tripDTO.setHotelDTO(hotelDTO);
            tripDTO.setDepartureDate(trip.getDepartureDate());
            tripDTO.setReturnDate(trip.getReturnDate());
            tripDTO.setNumberDays(trip.getNumberDays());
            tripDTO.setTripType(trip.getTripType());
            tripDTO.setAdultPrice(trip.getAdultPrice());
            tripDTO.setKidPrice(trip.getKidPrice());
            tripDTO.setPromoted(trip.isPromoted());
            tripDTO.setAdultBed(trip.getAdultBed());
            tripDTO.setKidsBed(trip.getKidsBed());
            tripDTO.setStock(trip.getStock());
            tripDTOList.add(tripDTO);
        }
        return tripDTOList;
    }

    public List<TripDTO> findUpcomingTrips(Date departureDate){
        List<Trip> tripList = tripDAO.findUpcomingTrips(departureDate);
        List<TripDTO> tripDTOList = new ArrayList<>();
        for (Trip trip : tripList){
            TripDTO tripDTO = new TripDTO();
            tripDTO.setName(trip.getName());
            AirportDTO airportDTO = new AirportDTO();
            airportDTO.setName(trip.getAirport().getName());
            CityDTO cityDTOFromAirportDTO = new CityDTO();
            cityDTOFromAirportDTO.setName(trip.getAirport().getCity().getName());
            CountryDTO countryDTO = new CountryDTO();
            countryDTO.setName(trip.getAirport().getCity().getCountry().getName());
            ContinentDTO continentDTO = new ContinentDTO();
            continentDTO.setName(trip.getAirport().getCity().getCountry().getContinent().getName());
            countryDTO.setContinentDTO(continentDTO);
            cityDTOFromAirportDTO.setCountryDTO(countryDTO);
            airportDTO.setCityDTO(cityDTOFromAirportDTO);
            Set<FlightDTO> flightDTOSet = new HashSet<>();
            for (Flight flight : trip.getAirport().getFlightSet()){
                FlightDTO flightDTO = new FlightDTO();
                if (flight.getFlightTo().equals(trip.getHotel().getCity().getName())) {
                    flightDTO.setFlightDate(flight.getFlightDate());
                    flightDTO.setFlightHour(flight.getFlightHour());
                    flightDTO.setFlightTo(flight.getFlightTo());
                    flightDTO.setPrice(flight.getPrice());
                    flightDTO.setAvailableSeats(flight.getAvailableSeats());
                    flightDTOSet.add(flightDTO);
                }
            }
            airportDTO.setFlightDTOSet(flightDTOSet);
            tripDTO.setAirportDTO(airportDTO);
            HotelDTO hotelDTO = new HotelDTO();
            hotelDTO.setName(trip.getHotel().getName());
            hotelDTO.setStars(trip.getHotel().getStars());
            hotelDTO.setDescription(trip.getHotel().getDescription());
            CityDTO cityDTOFromHotelDTO = new CityDTO();
            cityDTOFromHotelDTO.setName(trip.getHotel().getCity().getName());
            CountryDTO countryDTOHotel = new CountryDTO();
            countryDTOHotel.setName(trip.getHotel().getCity().getCountry().getName());
            cityDTOFromHotelDTO.setCountryDTO(countryDTOHotel);
            ContinentDTO continentDTOHotel = new ContinentDTO();
            continentDTOHotel.setName(trip.getHotel().getCity().getCountry().getContinent().getName());
            countryDTOHotel.setContinentDTO(continentDTOHotel);
            hotelDTO.setCityDTO(cityDTOFromHotelDTO);
            Set<RoomDTO> roomDTOSet = new HashSet<>();
            for (Room room : trip.getHotel().getRoomSet()){
                RoomDTO roomDTO = new RoomDTO();
                roomDTO.setType(room.getType());
                roomDTO.setNumber(room.getNumber());
                roomDTO.setExtraBed(room.isExtraBed());
                roomDTOSet.add(roomDTO);
            }
            hotelDTO.setRoomDTOSet(roomDTOSet);
            tripDTO.setHotelDTO(hotelDTO);
            tripDTO.setDepartureDate(trip.getDepartureDate());
            tripDTO.setReturnDate(trip.getReturnDate());
            tripDTO.setNumberDays(trip.getNumberDays());
            tripDTO.setTripType(trip.getTripType());
            tripDTO.setAdultPrice(trip.getAdultPrice());
            tripDTO.setKidPrice(trip.getKidPrice());
            tripDTO.setPromoted(trip.isPromoted());
            tripDTO.setAdultBed(trip.getAdultBed());
            tripDTO.setKidsBed(trip.getKidsBed());
            tripDTO.setStock(trip.getStock());
            tripDTOList.add(tripDTO);
        }
        return tripDTOList;
    }

    public List<TripDTO> findUpcomingTripsByContinent(String name, Date departureDate){
        List<Trip> tripList = tripDAO.findUpcomingTripsByContinent(name, departureDate);
        if (tripList.isEmpty()){
            return null;
        }
        List<TripDTO> tripDTOList = new ArrayList<>();
        for (Trip trip : tripList){
            TripDTO tripDTO = new TripDTO();
            tripDTO.setName(trip.getName());
            AirportDTO airportDTO = new AirportDTO();
            airportDTO.setName(trip.getAirport().getName());
            CityDTO cityDTOFromAirportDTO = new CityDTO();
            cityDTOFromAirportDTO.setName(trip.getAirport().getCity().getName());
            CountryDTO countryDTO = new CountryDTO();
            countryDTO.setName(trip.getAirport().getCity().getCountry().getName());
            ContinentDTO continentDTO = new ContinentDTO();
            continentDTO.setName(trip.getAirport().getCity().getCountry().getContinent().getName());
            countryDTO.setContinentDTO(continentDTO);
            cityDTOFromAirportDTO.setCountryDTO(countryDTO);
            airportDTO.setCityDTO(cityDTOFromAirportDTO);
            Set<FlightDTO> flightDTOSet = new HashSet<>();
            for (Flight flight : trip.getAirport().getFlightSet()){
                FlightDTO flightDTO = new FlightDTO();
                if (flight.getFlightTo().equals(trip.getHotel().getCity().getName())) {
                    flightDTO.setFlightDate(flight.getFlightDate());
                    flightDTO.setFlightHour(flight.getFlightHour());
                    flightDTO.setFlightTo(flight.getFlightTo());
                    flightDTO.setPrice(flight.getPrice());
                    flightDTO.setAvailableSeats(flight.getAvailableSeats());
                    flightDTOSet.add(flightDTO);
                }
            }
            airportDTO.setFlightDTOSet(flightDTOSet);
            tripDTO.setAirportDTO(airportDTO);
            HotelDTO hotelDTO = new HotelDTO();
            hotelDTO.setName(trip.getHotel().getName());
            hotelDTO.setStars(trip.getHotel().getStars());
            hotelDTO.setDescription(trip.getHotel().getDescription());
            CityDTO cityDTOFromHotelDTO = new CityDTO();
            cityDTOFromHotelDTO.setName(trip.getHotel().getCity().getName());
            CountryDTO countryDTOHotel = new CountryDTO();
            countryDTOHotel.setName(trip.getHotel().getCity().getCountry().getName());
            cityDTOFromHotelDTO.setCountryDTO(countryDTOHotel);
            ContinentDTO continentDTOHotel = new ContinentDTO();
            continentDTOHotel.setName(trip.getHotel().getCity().getCountry().getContinent().getName());
            countryDTOHotel.setContinentDTO(continentDTOHotel);
            hotelDTO.setCityDTO(cityDTOFromHotelDTO);
            Set<RoomDTO> roomDTOSet = new HashSet<>();
            for (Room room : trip.getHotel().getRoomSet()){
                RoomDTO roomDTO = new RoomDTO();
                roomDTO.setType(room.getType());
                roomDTO.setNumber(room.getNumber());
                roomDTO.setExtraBed(room.isExtraBed());
                roomDTOSet.add(roomDTO);
            }
            hotelDTO.setRoomDTOSet(roomDTOSet);
            tripDTO.setHotelDTO(hotelDTO);
            tripDTO.setDepartureDate(trip.getDepartureDate());
            tripDTO.setReturnDate(trip.getReturnDate());
            tripDTO.setNumberDays(trip.getNumberDays());
            tripDTO.setTripType(trip.getTripType());
            tripDTO.setAdultPrice(trip.getAdultPrice());
            tripDTO.setKidPrice(trip.getKidPrice());
            tripDTO.setPromoted(trip.isPromoted());
            tripDTO.setAdultBed(trip.getAdultBed());
            tripDTO.setKidsBed(trip.getKidsBed());
            tripDTO.setStock(trip.getStock());
            tripDTOList.add(tripDTO);
        }
        return tripDTOList;
    }

    public List<TripDTO> findUpcomingTripsByCountry(String name, Date departureDate){
        List<Trip> tripList = tripDAO.findUpcomingTripsByCountry(name, departureDate);
        if (tripList.isEmpty()){
            return null;
        }
        List<TripDTO> tripDTOList = new ArrayList<>();
        for (Trip trip : tripList){
            TripDTO tripDTO = new TripDTO();
            tripDTO.setName(trip.getName());
            AirportDTO airportDTO = new AirportDTO();
            airportDTO.setName(trip.getAirport().getName());
            CityDTO cityDTOFromAirportDTO = new CityDTO();
            cityDTOFromAirportDTO.setName(trip.getAirport().getCity().getName());
            CountryDTO countryDTO = new CountryDTO();
            countryDTO.setName(trip.getAirport().getCity().getCountry().getName());
            ContinentDTO continentDTO = new ContinentDTO();
            continentDTO.setName(trip.getAirport().getCity().getCountry().getContinent().getName());
            countryDTO.setContinentDTO(continentDTO);
            cityDTOFromAirportDTO.setCountryDTO(countryDTO);
            airportDTO.setCityDTO(cityDTOFromAirportDTO);
            Set<FlightDTO> flightDTOSet = new HashSet<>();
            for (Flight flight : trip.getAirport().getFlightSet()){
                FlightDTO flightDTO = new FlightDTO();
                if (flight.getFlightTo().equals(trip.getHotel().getCity().getName())) {
                    flightDTO.setFlightDate(flight.getFlightDate());
                    flightDTO.setFlightHour(flight.getFlightHour());
                    flightDTO.setFlightTo(flight.getFlightTo());
                    flightDTO.setPrice(flight.getPrice());
                    flightDTO.setAvailableSeats(flight.getAvailableSeats());
                    flightDTOSet.add(flightDTO);
                }
            }
            airportDTO.setFlightDTOSet(flightDTOSet);
            tripDTO.setAirportDTO(airportDTO);
            HotelDTO hotelDTO = new HotelDTO();
            hotelDTO.setName(trip.getHotel().getName());
            hotelDTO.setStars(trip.getHotel().getStars());
            hotelDTO.setDescription(trip.getHotel().getDescription());
            CityDTO cityDTOFromHotelDTO = new CityDTO();
            cityDTOFromHotelDTO.setName(trip.getHotel().getCity().getName());
            CountryDTO countryDTOHotel = new CountryDTO();
            countryDTOHotel.setName(trip.getHotel().getCity().getCountry().getName());
            cityDTOFromHotelDTO.setCountryDTO(countryDTOHotel);
            ContinentDTO continentDTOHotel = new ContinentDTO();
            continentDTOHotel.setName(trip.getHotel().getCity().getCountry().getContinent().getName());
            countryDTOHotel.setContinentDTO(continentDTOHotel);
            hotelDTO.setCityDTO(cityDTOFromHotelDTO);
            Set<RoomDTO> roomDTOSet = new HashSet<>();
            for (Room room : trip.getHotel().getRoomSet()){
                RoomDTO roomDTO = new RoomDTO();
                roomDTO.setType(room.getType());
                roomDTO.setNumber(room.getNumber());
                roomDTO.setExtraBed(room.isExtraBed());
                roomDTOSet.add(roomDTO);
            }
            hotelDTO.setRoomDTOSet(roomDTOSet);
            tripDTO.setHotelDTO(hotelDTO);
            tripDTO.setDepartureDate(trip.getDepartureDate());
            tripDTO.setReturnDate(trip.getReturnDate());
            tripDTO.setNumberDays(trip.getNumberDays());
            tripDTO.setTripType(trip.getTripType());
            tripDTO.setAdultPrice(trip.getAdultPrice());
            tripDTO.setKidPrice(trip.getKidPrice());
            tripDTO.setPromoted(trip.isPromoted());
            tripDTO.setAdultBed(trip.getAdultBed());
            tripDTO.setKidsBed(trip.getKidsBed());
            tripDTO.setStock(trip.getStock());
            tripDTOList.add(tripDTO);
        }
        return tripDTOList;
    }

    public List<TripDTO> findUpcomingTripsByCity(String name, Date departureDate){
        List<Trip> tripList = tripDAO.findUpcomingTripsByCity(name, departureDate);
        if (tripList.isEmpty()){
            return null;
        }
        List<TripDTO> tripDTOList = new ArrayList<>();
        for (Trip trip : tripList){
            TripDTO tripDTO = new TripDTO();
            tripDTO.setName(trip.getName());
            AirportDTO airportDTO = new AirportDTO();
            airportDTO.setName(trip.getAirport().getName());
            CityDTO cityDTOFromAirportDTO = new CityDTO();
            cityDTOFromAirportDTO.setName(trip.getAirport().getCity().getName());
            CountryDTO countryDTO = new CountryDTO();
            countryDTO.setName(trip.getAirport().getCity().getCountry().getName());
            ContinentDTO continentDTO = new ContinentDTO();
            continentDTO.setName(trip.getAirport().getCity().getCountry().getContinent().getName());
            countryDTO.setContinentDTO(continentDTO);
            cityDTOFromAirportDTO.setCountryDTO(countryDTO);
            airportDTO.setCityDTO(cityDTOFromAirportDTO);
            Set<FlightDTO> flightDTOSet = new HashSet<>();
            for (Flight flight : trip.getAirport().getFlightSet()){
                FlightDTO flightDTO = new FlightDTO();
                if (flight.getFlightTo().equals(trip.getHotel().getCity().getName())) {
                    flightDTO.setFlightDate(flight.getFlightDate());
                    flightDTO.setFlightHour(flight.getFlightHour());
                    flightDTO.setFlightTo(flight.getFlightTo());
                    flightDTO.setPrice(flight.getPrice());
                    flightDTO.setAvailableSeats(flight.getAvailableSeats());
                    flightDTOSet.add(flightDTO);
                }
            }
            airportDTO.setFlightDTOSet(flightDTOSet);
            tripDTO.setAirportDTO(airportDTO);
            HotelDTO hotelDTO = new HotelDTO();
            hotelDTO.setName(trip.getHotel().getName());
            hotelDTO.setStars(trip.getHotel().getStars());
            hotelDTO.setDescription(trip.getHotel().getDescription());
            CityDTO cityDTOFromHotelDTO = new CityDTO();
            cityDTOFromHotelDTO.setName(trip.getHotel().getCity().getName());
            CountryDTO countryDTOHotel = new CountryDTO();
            countryDTOHotel.setName(trip.getHotel().getCity().getCountry().getName());
            cityDTOFromHotelDTO.setCountryDTO(countryDTOHotel);
            ContinentDTO continentDTOHotel = new ContinentDTO();
            continentDTOHotel.setName(trip.getHotel().getCity().getCountry().getContinent().getName());
            countryDTOHotel.setContinentDTO(continentDTOHotel);
            hotelDTO.setCityDTO(cityDTOFromHotelDTO);
            Set<RoomDTO> roomDTOSet = new HashSet<>();
            for (Room room : trip.getHotel().getRoomSet()){
                RoomDTO roomDTO = new RoomDTO();
                roomDTO.setType(room.getType());
                roomDTO.setNumber(room.getNumber());
                roomDTO.setExtraBed(room.isExtraBed());
                roomDTOSet.add(roomDTO);
            }
            hotelDTO.setRoomDTOSet(roomDTOSet);
            tripDTO.setHotelDTO(hotelDTO);
            tripDTO.setDepartureDate(trip.getDepartureDate());
            tripDTO.setReturnDate(trip.getReturnDate());
            tripDTO.setNumberDays(trip.getNumberDays());
            tripDTO.setTripType(trip.getTripType());
            tripDTO.setAdultPrice(trip.getAdultPrice());
            tripDTO.setKidPrice(trip.getKidPrice());
            tripDTO.setPromoted(trip.isPromoted());
            tripDTO.setAdultBed(trip.getAdultBed());
            tripDTO.setKidsBed(trip.getKidsBed());
            tripDTO.setStock(trip.getStock());
            tripDTOList.add(tripDTO);
        }
        return tripDTOList;
    }

    public List<TripDTO> findUpcomingTripsByHotel(String name, Date departureDate){
        List<Trip> tripList = tripDAO.findUpcomingTripsByHotel(name, departureDate);
        if (tripList.isEmpty()){
            return null;
        }
        List<TripDTO> tripDTOList = new ArrayList<>();
        for (Trip trip : tripList){
            TripDTO tripDTO = new TripDTO();
            tripDTO.setName(trip.getName());
            AirportDTO airportDTO = new AirportDTO();
            airportDTO.setName(trip.getAirport().getName());
            CityDTO cityDTOFromAirportDTO = new CityDTO();
            cityDTOFromAirportDTO.setName(trip.getAirport().getCity().getName());
            CountryDTO countryDTO = new CountryDTO();
            countryDTO.setName(trip.getAirport().getCity().getCountry().getName());
            ContinentDTO continentDTO = new ContinentDTO();
            continentDTO.setName(trip.getAirport().getCity().getCountry().getContinent().getName());
            countryDTO.setContinentDTO(continentDTO);
            cityDTOFromAirportDTO.setCountryDTO(countryDTO);
            airportDTO.setCityDTO(cityDTOFromAirportDTO);
            Set<FlightDTO> flightDTOSet = new HashSet<>();
            for (Flight flight : trip.getAirport().getFlightSet()){
                FlightDTO flightDTO = new FlightDTO();
                if (flight.getFlightTo().equals(trip.getHotel().getCity().getName())) {
                    flightDTO.setFlightDate(flight.getFlightDate());
                    flightDTO.setFlightHour(flight.getFlightHour());
                    flightDTO.setFlightTo(flight.getFlightTo());
                    flightDTO.setPrice(flight.getPrice());
                    flightDTO.setAvailableSeats(flight.getAvailableSeats());
                    flightDTOSet.add(flightDTO);
                }
            }
            airportDTO.setFlightDTOSet(flightDTOSet);
            tripDTO.setAirportDTO(airportDTO);
            HotelDTO hotelDTO = new HotelDTO();
            hotelDTO.setName(trip.getHotel().getName());
            hotelDTO.setStars(trip.getHotel().getStars());
            hotelDTO.setDescription(trip.getHotel().getDescription());
            CityDTO cityDTOFromHotelDTO = new CityDTO();
            cityDTOFromHotelDTO.setName(trip.getHotel().getCity().getName());
            CountryDTO countryDTOHotel = new CountryDTO();
            countryDTOHotel.setName(trip.getHotel().getCity().getCountry().getName());
            cityDTOFromHotelDTO.setCountryDTO(countryDTOHotel);
            ContinentDTO continentDTOHotel = new ContinentDTO();
            continentDTOHotel.setName(trip.getHotel().getCity().getCountry().getContinent().getName());
            countryDTOHotel.setContinentDTO(continentDTOHotel);
            hotelDTO.setCityDTO(cityDTOFromHotelDTO);
            Set<RoomDTO> roomDTOSet = new HashSet<>();
            for (Room room : trip.getHotel().getRoomSet()){
                RoomDTO roomDTO = new RoomDTO();
                roomDTO.setType(room.getType());
                roomDTO.setNumber(room.getNumber());
                roomDTO.setExtraBed(room.isExtraBed());
                roomDTOSet.add(roomDTO);
            }
            hotelDTO.setRoomDTOSet(roomDTOSet);
            tripDTO.setHotelDTO(hotelDTO);
            tripDTO.setDepartureDate(trip.getDepartureDate());
            tripDTO.setReturnDate(trip.getReturnDate());
            tripDTO.setNumberDays(trip.getNumberDays());
            tripDTO.setTripType(trip.getTripType());
            tripDTO.setAdultPrice(trip.getAdultPrice());
            tripDTO.setKidPrice(trip.getKidPrice());
            tripDTO.setPromoted(trip.isPromoted());
            tripDTO.setAdultBed(trip.getAdultBed());
            tripDTO.setKidsBed(trip.getKidsBed());
            tripDTO.setStock(trip.getStock());
            tripDTOList.add(tripDTO);
        }
        return tripDTOList;
    }

}
