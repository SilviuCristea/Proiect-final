package business.dto;

import java.util.Set;

public class CountryDTO {
    private int id;
    private String name;
    private ContinentDTO continentDTO;
    private Set<CityDTO> cityDTOSet;

    public CountryDTO(String name, ContinentDTO continentDTO, Set<CityDTO> cityDTOSet) {
        this.name = name;
        this.continentDTO = continentDTO;
        this.cityDTOSet = cityDTOSet;
    }

    public CountryDTO(String name) {
        this.name = name;
    }

    public CountryDTO() {
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

    public ContinentDTO getContinentDTO() {
        return continentDTO;
    }

    public void setContinentDTO(ContinentDTO continentDTO) {
        this.continentDTO = continentDTO;
    }

    public Set<CityDTO> getCityDTOSet() {
        return cityDTOSet;
    }

    public void setCityDTOSet(Set<CityDTO> cityDTOSet) {
        this.cityDTOSet = cityDTOSet;
    }

    @Override
    public String toString() {
        return "CountryDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
