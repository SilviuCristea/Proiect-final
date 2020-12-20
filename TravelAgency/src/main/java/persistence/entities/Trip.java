package persistence.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = "findPromotedTrips", query = "select trip from Trip trip where trip.promoted = :promoted"),
        @NamedQuery(name = "findAllTrip", query = "select trip from Trip trip"),
        @NamedQuery(name = "findTripByName", query = "select trip.name from Trip trip where trip.name = :name")
        //@NamedQuery(name = "findTripByContinent", query = "select trip from Trip trip inner join trip.hotel inner join hotel.city inner join city.country inner join country.continent where continent.name = :name"),
        //@NamedQuery(name = "findTripByCountry", query = "select trip from Trip trip inner join trip.hotel inner join hotel.city inner join city.country where country.name = :name")
})

@Entity
@Table(name = "trips")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "airport_id")
    private Airport airport;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
    @Column(name = "departure_date")
    private Date departureDate;
    @Column(name = "return_date")
    private Date returnDate;
    @Column(name = "number_days")
    private int numberDays;
    @Column(name = "trip_type")
    private String tripType;
    @Column(name = "adult_price")
    private double adultPrice;
    @Column(name = "kid_price")
    private double kidPrice;
    @Column(name = "promoted")
    private boolean promoted;
    @Column(name = "adult_bed")
    private int adultBed;
    @Column(name = "kids_bed")
    private int kidsBed;
    @Column(name = "stock")
    private int stock;
    @OneToMany(mappedBy = "trip")
    private Set<Purchase> purchaseSet;

    public Trip(String name, Airport airport, Hotel hotel, Date departureDate, Date returnDate, int numberDays, String tripType, double adultPrice, double kidPrice, boolean promoted, int adultBed, int kidsBed, int stock) {
        this.name = name;
        this.airport = airport;
        this.hotel = hotel;
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

    public Trip(String name, Airport airport, Hotel hotel, Date departureDate, Date returnDate, int numberDays, String tripType, double adultPrice, double kidPrice, boolean promoted, int adultBed, int kidsBed, int stock, Set<Purchase> purchaseSet) {
        this.name = name;
        this.airport = airport;
        this.hotel = hotel;
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
        this.purchaseSet = purchaseSet;
    }

    public Trip() {
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

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
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

    public Double getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(double adultPrice) {
        this.adultPrice = adultPrice;
    }

    public Double getKidPrice() {
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

    public Set<Purchase> getPurchaseSet() {
        return purchaseSet;
    }

    public void setPurchaseSet(Set<Purchase> purchaseSet) {
        this.purchaseSet = purchaseSet;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "name='" + name + '\'' +
                ", airport=" + airport +
                ", hotel=" + hotel +
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
