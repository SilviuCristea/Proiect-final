package business.dto;

import java.util.Set;

public class HotelDTO {
    private String name;
    private int stars;
    private String decription;
    private CityDTO cityDTO;
    private Set<RoomDTO> roomDTOSet;

    public HotelDTO(String name, int stars, String decription, CityDTO cityDTO, Set<RoomDTO> roomDTOSet) {
        this.name = name;
        this.stars = stars;
        this.decription = decription;
        this.cityDTO = cityDTO;
        this.roomDTOSet = roomDTOSet;
    }

    public HotelDTO(String name, int stars, String decription) {
        this.name = name;
        this.stars = stars;
        this.decription = decription;
    }

    public HotelDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public CityDTO getCityDTO() {
        return cityDTO;
    }

    public void setCityDTO(CityDTO cityDTO) {
        this.cityDTO = cityDTO;
    }

    public Set<RoomDTO> getRoomDTOSet() {
        return roomDTOSet;
    }

    public void setRoomDTOSet(Set<RoomDTO> roomDTOSet) {
        this.roomDTOSet = roomDTOSet;
    }

    @Override
    public String toString() {
        return "HotelDTO{" +
                "name='" + name + '\'' +
                ", stars=" + stars +
                ", decription='" + decription + '\'' +
                '}';
    }
}
