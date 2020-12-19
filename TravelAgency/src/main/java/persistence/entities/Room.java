package persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "type")
    private String type;
    @Column(name = "number")
    private int number;
    @Column(name = "extra_bed")
    private boolean extraBed;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    public Room(String type, int number, boolean extraBed, Hotel hotel) {
        this.type = type;
        this.number = number;
        this.extraBed = extraBed;
        this.hotel = hotel;
    }

    public Room(String type, int number, boolean extraBed) {
        this.type = type;
        this.number = number;
        this.extraBed = extraBed;
    }

    public Room() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public String toString() {
        return "Room{" +
                "type='" + type + '\'' +
                ", number=" + number +
                ", extraBed=" + extraBed +
                ", hotel=" + hotel +
                '}';
    }
}
