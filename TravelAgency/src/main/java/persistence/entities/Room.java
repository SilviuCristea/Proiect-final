package persistence.entities;

import javax.persistence.*;
import java.util.Set;


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
    @ManyToMany(mappedBy = "roomSet", cascade = CascadeType.ALL)
    private Set<Hotel> hotelSet;

    public Room(String type, int number, boolean extraBed, Set<Hotel> hotelSet) {
        this.type = type;
        this.number = number;
        this.extraBed = extraBed;
        this.hotelSet = hotelSet;
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

    public Set<Hotel> getHotelSet() {
        return hotelSet;
    }

    public void setHotelSet(Set<Hotel> hotelSet) {
        this.hotelSet = hotelSet;
    }

    @Override
    public String toString() {
        return "Room{" +
                "type='" + type + '\'' +
                ", number=" + number +
                ", extraBed=" + extraBed +
                '}';
    }
}
