package persistence.entities;

import javax.persistence.*;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = "findAirportByName", query = "select airport from Airport airport where airport.name = :name"),
        @NamedQuery(name = "findAirportByCity", query = "select airport.name from Airport airport inner join airport.city city where city.name = :name")
})

@Entity
@Table(name = "airports")
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_airport_id")
    private City city;
    @OneToMany(mappedBy = "airport", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Flight> flightSet;

    public Airport(String name, City city, Set<Flight> flightSet) {
        this.name = name;
        this.city = city;
        this.flightSet = flightSet;
    }

    public Airport(String name, City city) {
        this.name = name;
        this.city = city;
    }

    public Airport(String name) {
        this.name = name;
    }

    public Airport() {
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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Set<Flight> getFlightSet() {
        return flightSet;
    }

    public void setFlightSet(Set<Flight> flightSet) {
        this.flightSet = flightSet;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "name='" + name + '\'' +
                ", city=" + city +
                ", flightSet=" + flightSet +
                '}';
    }
}
