package persistence.entities;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = "findCountryByName", query = "select country from Country country where country.name = :name"),
        @NamedQuery(name = "findCountryByContinent", query = "select country from Country country inner join country.continent continent where continent.name = :name")
})

@Entity
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "continent_id")
    private Continent continent;


    public Country(String name, Continent continent) {
        this.name = name;
        this.continent = continent;
    }

    public Country() {
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

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }


    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", continent=" + continent +
                '}';
    }
}
