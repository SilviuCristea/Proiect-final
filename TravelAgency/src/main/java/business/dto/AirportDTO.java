package business.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

public class AirportDTO {
    @NotEmpty
    @NotNull
    @NotBlank
    private String name;
    private CityDTO cityDTO;
    private Set<TripDTO> tripDTOSet;
    private Set<FlightDTO> flightDTOSet;

    public AirportDTO(@NotNull @NotBlank @NotEmpty String name, CityDTO cityDTO, Set<TripDTO> tripDTOSet, Set<FlightDTO> flightDTOSet) {
        this.name = name;
        this.cityDTO = cityDTO;
        this.tripDTOSet = tripDTOSet;
        this.flightDTOSet = flightDTOSet;
    }

    public AirportDTO(String name) {
        this.name = name;
    }

    public AirportDTO() {
    }

    public Set<TripDTO> getTripDTOSet() {
        return tripDTOSet;
    }

    public void setTripDTOSet(Set<TripDTO> tripDTOSet) {
        this.tripDTOSet = tripDTOSet;
    }

    public Set<FlightDTO> getFlightDTOSet() {
        return flightDTOSet;
    }

    public void setFlightDTOSet(Set<FlightDTO> flightDTOSet) {
        this.flightDTOSet = flightDTOSet;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CityDTO getCityDTO() {
        return cityDTO;
    }

    public void setCityDTO(CityDTO cityDTO) {
        this.cityDTO = cityDTO;
    }

    @Override
    public String toString() {
        return "AirportDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
