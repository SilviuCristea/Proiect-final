package business.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

public class HotelDTO {
    @NotNull
    @NotBlank
    @NotEmpty
    @Pattern(regexp = "([a-z A-Z])*")
    private String name;
    private int stars;
    private String description;
    private CityDTO cityDTO;
    private Set<RoomDTO> roomDTOSet;

    public HotelDTO(@NotNull @NotBlank @NotEmpty @Pattern(regexp = "([a-z A-Z])*") String name, int stars, String description, CityDTO cityDTO, Set<RoomDTO> roomDTOSet) {
        this.name = name;
        this.stars = stars;
        this.description = description;
        this.cityDTO = cityDTO;
        this.roomDTOSet = roomDTOSet;
    }

    public HotelDTO(@NotNull @NotBlank @NotEmpty @Pattern(regexp = "([a-z A-Z])*") String name, int stars, String description, CityDTO cityDTO) {
        this.name = name;
        this.stars = stars;
        this.description = description;
        this.cityDTO = cityDTO;
    }

    public HotelDTO(String name, int stars, String description) {
        this.name = name;
        this.stars = stars;
        this.description = description;
    }

    public HotelDTO() {
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

    public CityDTO getCityDTO() {
        return cityDTO;
    }

    public void setCityDTO(CityDTO cityDTO) {
        this.cityDTO = cityDTO;
    }

    public Set<RoomDTO> getRoomDTOSet() {
        return roomDTOSet;
    }

    public void setRoomDTOSet(Set<RoomDTO> roomDTOSet) {
        this.roomDTOSet = roomDTOSet;
    }

    @Override
    public String toString() {
        return "HotelDTO{" +
                "name='" + name + '\'' +
                ", stars=" + stars +
                ", description='" + description + '\'' +
                ", cityDTO=" + cityDTO +
                '}';
    }
}
