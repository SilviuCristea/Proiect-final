package business.service;

import business.dto.CityDTO;
import business.dto.HotelDTO;
import business.dto.RoomDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.dao.CityDAO;
import persistence.dao.ContinentDAO;
import persistence.dao.CountryDAO;
import persistence.dao.HotelDAO;
import persistence.entities.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class HotelService {
    @Autowired
    HotelDAO hotelDAO;
    @Autowired
    CityDAO cityDAO;
    @Autowired
    CountryDAO countryDAO;
    @Autowired
    ContinentDAO continentDAO;

    public void insert(HotelDTO hotelDTO){
        Hotel hotel = new Hotel();
        City foundCity = cityDAO.findCityByName(hotelDTO.getCityDTO().getName());
        if (foundCity==null){
            City city = new City();
            Country foundCountry = countryDAO.findCountryByName(hotelDTO.getCityDTO().getCountryDTO().getName());
            if (foundCountry==null){
                Country country = new Country();
                Continent foundContinent = continentDAO.findContinentByName(hotelDTO.getCityDTO().getCountryDTO().getContinentDTO().getName());
                if (foundContinent==null){
                    Continent continent = new Continent();
                    continent.setName(hotelDTO.getCityDTO().getCountryDTO().getContinentDTO().getName());
                    country.setContinent(continent);
                }else{
                    country.setContinent(foundContinent);
                }
                country.setName(hotelDTO.getCityDTO().getCountryDTO().getName());
                city.setCountry(country);
            }else{
                city.setCountry(foundCountry);
            }
            city.setName(hotelDTO.getCityDTO().getName());
            hotel.setCity(city);
        }else{
            hotel.setCity(foundCity);
        }
        hotel.setName(hotelDTO.getName());
        hotel.setStars(hotelDTO.getStars());
        hotel.setDescription(hotelDTO.getDescription());
        Set<Room> roomSet = new HashSet<>();
        for (RoomDTO roomDTO : hotelDTO.getRoomDTOSet()){
            Room room = new Room();
            room.setType(roomDTO.getType());
            room.setNumber(roomDTO.getNumber());
            room.setExtraBed(roomDTO.isExtraBed());
            roomSet.add(room);
        }
        hotel.setRoomSet(roomSet);
        hotelDAO.insert(hotel);
    }

    public List<HotelDTO> findHotelByName(String name){
        List<HotelDTO> hotelDTOList = new ArrayList<>();
        List<Hotel> hotelList = hotelDAO.findHotelByName(name);
        if (hotelList.isEmpty()){
            return null;
        }
        for (Hotel hotel : hotelList){
            HotelDTO hotelDTO = new HotelDTO();
            hotelDTO.setName(hotel.getName());
            hotelDTO.setStars(hotel.getStars());
            hotelDTO.setDecription(hotel.getDescription());
            CityDTO cityDTO = new CityDTO();
            cityDTO.setName(hotel.getCity().getName());
            hotelDTO.setCityDTO(cityDTO);
            Set<RoomDTO> roomDTOSet = new HashSet<>();
            for (Room room : hotel.getRoomSet()){
                RoomDTO roomDTO = new RoomDTO();
                roomDTO.setType(room.getType());
                roomDTO.setNumber(room.getNumber());
                roomDTO.setExtraBed(room.isExtraBed());
                roomDTOSet.add(roomDTO);
            }
            hotelDTO.setRoomDTOSet(roomDTOSet);
            hotelDTOList.add(hotelDTO);
        }
        return hotelDTOList;
    }

    public List<String> findHotelByCity(String name){
        List<String> hotelList = hotelDAO.findHotelByCity(name);
        if (hotelList.isEmpty()){
            return null;
        }
        return hotelList;
    }

}
