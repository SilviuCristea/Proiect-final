package business.service;

import business.dto.HotelDTO;
import business.dto.RoomDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.dao.HotelDAO;
import persistence.entities.City;
import persistence.entities.Hotel;
import persistence.entities.Room;

import java.util.HashSet;
import java.util.Set;

@Service
public class HotelService {
    @Autowired
    HotelDAO hotelDAO;

    private void insert(HotelDTO hotelDTO){
        Set<Room> roomSet = new HashSet<Room>();
        for (RoomDTO roomDTO : hotelDTO.getRoomDTOSet()) {
            Room room = new Room();
            room.setType(roomDTO.getType());
            room.setNumber(roomDTO.getNumber());
            room.setExtraBed(roomDTO.isExtraBed());
            roomSet.add(room);
        }
            City city = new City();
            city.setName(hotelDTO.getCityDTO().getName());
            Hotel hotel = new Hotel();
            hotel.setName(hotelDTO.getName());
            hotel.setStars(hotelDTO.getStars());
            hotel.setDescription(hotelDTO.getDecription());
            hotel.setCity(city);
            hotel.setRoomSet(roomSet);
            hotelDAO.insert(hotel);
    }
}
