package business.dto;

import java.util.Date;

public class TripDTO {
    private int id;
    private String name;
    private AirportDTO airportDTO;
    private HotelDTO hotelDTO;
    private Date departureDate;
    private Date returnDate;
    private int numberDays;
    private String tripType;
    private double adultPrice;
    private double kidPrice;
    private boolean promoted;
    private int adultBed;
    private int kidsBed;
    private int stock;

    public TripDTO(String name, AirportDTO airportDTO, HotelDTO hotelDTO, Date departureDate, Date returnDate, int numberDays, String tripType, double adultPrice, double kidPrice, boolean promoted, int adultBed, int kidsBed, int stock) {
        this.name = name;
        this.airportDTO = airportDTO;
        this.hotelDTO = hotelDTO;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.numberDays = numberDays;
        this.tripType = tripType;
        this.adultPrice = adultPrice;
        this.kidPrice = kidPrice;
        this.promoted = promoted;
        this.adultBed = adultBed;
        this.kidsBed = kidsBed;
        this.stock = stock;
    }

    public TripDTO(String name, Date departureDate, Date returnDate, int numberDays, String tripType, double adultPrice, double kidPrice, boolean promoted, int adultBed, int kidsBed, int stock) {
        this.name = name;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.numberDays = numberDays;
        this.tripType = tripType;
        this.adultPrice = adultPrice;
        this.kidPrice = kidPrice;
        this.promoted = promoted;
        this.adultBed = adultBed;
        this.kidsBed = kidsBed;
        this.stock = stock;
    }

    public TripDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AirportDTO getAirportDTO() {
        return airportDTO;
    }

    public void setAirportDTO(AirportDTO airportDTO) {
        this.airportDTO = airportDTO;
    }

    public HotelDTO getHotelDTO() {
        return hotelDTO;
    }

    public void setHotelDTO(HotelDTO hotelDTO) {
        this.hotelDTO = hotelDTO;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public int getNumberDays() {
        return numberDays;
    }

    public void setNumberDays(int numberDays) {
        this.numberDays = numberDays;
    }

    public String getTripType() {
        return tripType;
    }

    public void setTripType(String tripType) {
        this.tripType = tripType;
    }

    public double getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(double adultPrice) {
        this.adultPrice = adultPrice;
    }

    public double getKidPrice() {
        return kidPrice;
    }

    public void setKidPrice(double kidPrice) {
        this.kidPrice = kidPrice;
    }

    public boolean isPromoted() {
        return promoted;
    }

    public void setPromoted(boolean promoted) {
        this.promoted = promoted;
    }

    public int getAdultBed() {
        return adultBed;
    }

    public void setAdultBed(int adultBed) {
        this.adultBed = adultBed;
    }

    public int getKidsBed() {
        return kidsBed;
    }

    public void setKidsBed(int kidsBed) {
        this.kidsBed = kidsBed;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "TripDTO{" +
                "name='" + name + '\'' +
                ", airportDTO=" + airportDTO +
                ", hotelDTO=" + hotelDTO +
                ", departureDate=" + departureDate +
                ", returnDate=" + returnDate +
                ", numberDays=" + numberDays +
                ", tripType='" + tripType + '\'' +
                ", adultPrice=" + adultPrice +
                ", kidPrice=" + kidPrice +
                ", promoted=" + promoted +
                ", adultBed=" + adultBed +
                ", kidsBed=" + kidsBed +
                ", stock=" + stock +
                '}';
    }
}
