package business.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

public class CityDTO {
    @NotNull
    @NotEmpty
    @NotBlank
    @Pattern(regexp = "([a-z A-Z])*")
    private String name;
    private CountryDTO countryDTO;
    private Set<AirportDTO> airportDTOSet;
    private Set<HotelDTO> hotelDTOSet;

    public CityDTO(@NotNull @NotEmpty @NotBlank @Pattern(regexp = "([a-z A-Z])*") String name) {
        this.name = name;
    }

    public CityDTO(String name, CountryDTO countryDTO, Set<AirportDTO> airportDTOSet, Set<HotelDTO> hotelDTOSet) {
        this.name = name;
        this.countryDTO = countryDTO;
        this.airportDTOSet = airportDTOSet;
        this.hotelDTOSet = hotelDTOSet;
    }

    public CityDTO() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CountryDTO getCountryDTO() {
        return countryDTO;
    }

    public void setCountryDTO(CountryDTO countryDTO) {
        this.countryDTO = countryDTO;
    }

    public Set<AirportDTO> getAirportDTOSet() {
        return airportDTOSet;
    }

    public void setAirportDTOSet(Set<AirportDTO> airportDTOSet) {
        this.airportDTOSet = airportDTOSet;
    }

    public Set<HotelDTO> getHotelDTOSet() {
        return hotelDTOSet;
    }

    public void setHotelDTOSet(Set<HotelDTO> hotelDTOSet) {
        this.hotelDTOSet = hotelDTOSet;
    }

    @Override
    public String toString() {
        return "CityDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
