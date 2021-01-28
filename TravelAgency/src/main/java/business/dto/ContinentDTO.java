package business.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

public class ContinentDTO {
    @NotEmpty
    @NotNull
    @NotBlank
    @Pattern(regexp = "([a-z A-Z])*")
    private String name;



    public ContinentDTO(@NotEmpty @NotBlank @NotNull @Pattern(regexp = "([a-z A-Z])*") String name) {
        this.name = name;
    }

    public ContinentDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "ContinentDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
