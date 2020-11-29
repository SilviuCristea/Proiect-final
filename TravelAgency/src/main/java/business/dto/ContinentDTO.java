package business.dto;

import java.util.Set;

public class ContinentDTO {
    private int id;
    private String name;
    private Set<CountryDTO> countryDTOSet;

    public ContinentDTO(String name, Set<CountryDTO> countryDTOSet) {
        this.name = name;
        this.countryDTOSet = countryDTOSet;
    }

    public ContinentDTO(String name) {
        this.name = name;
    }

    public ContinentDTO() {
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

    public Set<CountryDTO> getCountryDTOSet() {
        return countryDTOSet;
    }

    public void setCountryDTOSet(Set<CountryDTO> countryDTOSet) {
        this.countryDTOSet = countryDTOSet;
    }

    @Override
    public String toString() {
        return "ContinentDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
