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

    public CityDTO(@NotNull @NotEmpty @NotBlank @Pattern(regexp = "([a-z A-Z])*") String name) {
        this.name = name;
    }

    public CityDTO(@NotNull @NotEmpty @NotBlank @Pattern(regexp = "([a-z A-Z])*") String name, CountryDTO countryDTO) {
        this.name = name;
        this.countryDTO = countryDTO;
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

    @Override
    public String toString() {
        return "CityDTO{" +
                "name='" + name + '\'' +
                ", countryDTO=" + countryDTO +
                '}';
    }
}
