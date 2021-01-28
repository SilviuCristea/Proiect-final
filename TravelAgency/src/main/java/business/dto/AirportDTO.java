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
    private Set<FlightDTO> flightDTOSet;



    public AirportDTO(@NotEmpty @NotNull @NotBlank String name, CityDTO cityDTO) {
        this.name = name;
        this.cityDTO = cityDTO;
    }

    public AirportDTO(String name) {
        this.name = name;
    }

    public AirportDTO() {
    }

    public AirportDTO(@NotEmpty @NotNull @NotBlank String name, CityDTO cityDTO, Set<FlightDTO> flightDTOSet) {
        this.name = name;
        this.cityDTO = cityDTO;
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

    public Set<FlightDTO> getFlightDTOSet() {
        return flightDTOSet;
    }

    public void setFlightDTOSet(Set<FlightDTO> flightDTOSet) {
        this.flightDTOSet = flightDTOSet;
    }

    @Override
    public String toString() {
        return "AirportDTO{" +
                "name='" + name + '\'' +
                ", cityDTO=" + cityDTO +
                '}';
    }
}
