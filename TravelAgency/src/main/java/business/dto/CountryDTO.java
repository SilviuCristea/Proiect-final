package business.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

public class CountryDTO {
    @NotNull
    @NotBlank
    @NotEmpty
    @Pattern(regexp = "([a-z A-Z])*")
    private String name;
    private ContinentDTO continentDTO;

    public CountryDTO(@NotNull @NotBlank @NotEmpty @Pattern(regexp = "([a-z A-Z])*") String name, ContinentDTO continentDTO) {
        this.name = name;
        this.continentDTO = continentDTO;
    }

    public CountryDTO(@NotNull @NotBlank @NotEmpty @Pattern(regexp = "([a-z A-Z])*") String name) {
        this.name = name;
    }

    public CountryDTO() {
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

    @Override
    public String toString() {
        return "CountryDTO{" +
                "name='" + name + '\'' +
                ", continentDTO=" + continentDTO +
                '}';
    }
}
