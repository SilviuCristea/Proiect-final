package persistence.entities;

import javax.persistence.*;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = "findHotelByName", query = "select hotel from Hotel hotel where hotel.name = :name"),
        @NamedQuery(name = "findHotelByCity", query = "select hotel.name from Hotel hotel inner join hotel.city city where city.name = :name"),
        @NamedQuery(name = "findHotelByNameAndCity", query = "select hotel from Hotel hotel inner join hotel.city city where hotel.name = :hotelName and city.name = :cityName")
})

@Entity
@Table(name = "hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "stars")
    private int stars;
    @Column(name = "description")
    private String description;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id")
    private City city;
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Room> roomSet;

    public Hotel(String name, int stars, String description, City city, Set<Room> roomSet) {
        this.name = name;
        this.stars = stars;
        this.description = description;
        this.city = city;
        this.roomSet = roomSet;
    }

    public Hotel(String name, int stars, String description, City city) {
        this.name = name;
        this.stars = stars;
        this.description = description;
        this.city = city;
    }


    public Hotel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Set<Room> getRoomSet() {
        return roomSet;
    }

    public void setRoomSet(Set<Room> roomSet) {
        this.roomSet = roomSet;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "name='" + name + '\'' +
                ", stars=" + stars +
                ", description='" + description + '\'' +
                ", city=" + city +
                ", roomSet=" + roomSet +
                '}';
    }
}
