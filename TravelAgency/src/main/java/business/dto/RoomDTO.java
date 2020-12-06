package business.dto;

import java.util.Set;

public class RoomDTO {
    private String type;
    private int number;
    private boolean extraBed;
    private Set<HotelDTO> hotelDTOSet;

    public RoomDTO(String type, int number, boolean extraBed, Set<HotelDTO> hotelDTOSet) {
        this.type = type;
        this.number = number;
        this.extraBed = extraBed;
        this.hotelDTOSet = hotelDTOSet;
    }

    public RoomDTO(String type, int number, boolean extraBed) {
        this.type = type;
        this.number = number;
        this.extraBed = extraBed;
    }

    public RoomDTO() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isExtraBed() {
        return extraBed;
    }

    public void setExtraBed(boolean extraBed) {
        this.extraBed = extraBed;
    }

    public Set<HotelDTO> getHotelDTOSet() {
        return hotelDTOSet;
    }

    public void setHotelDTOSet(Set<HotelDTO> hotelDTOSet) {
        this.hotelDTOSet = hotelDTOSet;
    }

    @Override
    public String toString() {
        return "RoomDTO{" +
                "type='" + type + '\'' +
                ", number=" + number +
                ", extraBed=" + extraBed +
                '}';
    }
}
