package business.dto;


public class RoomDTO {
    private String type;
    private int number;
    private boolean extraBed;
    private HotelDTO hotelDTO;

    public RoomDTO(String type, int number, boolean extraBed, HotelDTO hotelDTO) {
        this.type = type;
        this.number = number;
        this.extraBed = extraBed;
        this.hotelDTO = hotelDTO;
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

    public HotelDTO getHotelDTO() {
        return hotelDTO;
    }

    public void setHotelDTO(HotelDTO hotelDTO) {
        this.hotelDTO = hotelDTO;
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
