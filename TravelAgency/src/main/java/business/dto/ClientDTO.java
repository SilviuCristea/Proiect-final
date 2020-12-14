package business.dto;

import javax.validation.constraints.*;
import java.util.Set;

public class ClientDTO {
    @NotNull
    @NotEmpty
    @NotBlank
    @Pattern(regexp = "([a-z A-Z])*")
    private String firstName;
    @NotNull
    @NotEmpty
    @NotBlank
    @Pattern(regexp = "([a-z A-Z])*")
    private String surname;
    @NotNull
    private int yearOfBirth;
    @NotNull
    @NotEmpty
    @NotBlank
    private String adress;
    @NotNull
    @NotEmpty
    @NotBlank
    @Pattern(regexp = "([0-9])*")
    private String phoneNumber;
    @NotNull
    @NotEmpty
    @NotBlank
    @Email
    private String email;
    private UserDTO userDTO;
    private Set<PurchaseDTO> purchaseDTOSet;

    public ClientDTO(@NotNull @NotEmpty @NotBlank @Pattern(regexp = "([a-z A-Z])*") String firstName, @NotNull @NotEmpty @NotBlank @Pattern(regexp = "([a-z A-Z])*") String surname, @NotNull int yearOfBirth, @NotNull @NotEmpty @NotBlank String adress, @NotNull @NotEmpty @NotBlank @Pattern(regexp = "([0-9])*") String phoneNumber, @NotNull @NotEmpty @NotBlank @Email String email, UserDTO userDTO, Set<PurchaseDTO> purchaseDTOSet) {
        this.firstName = firstName;
        this.surname = surname;
        this.yearOfBirth = yearOfBirth;
        this.adress = adress;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.userDTO = userDTO;
        this.purchaseDTOSet = purchaseDTOSet;
    }

    public ClientDTO(@NotNull @NotEmpty @NotBlank @Pattern(regexp = "([a-z A-Z])*") String firstName, @NotNull @NotEmpty @NotBlank @Pattern(regexp = "([a-z A-Z])*") String surname, @NotNull @NotEmpty @NotBlank int yearOfBirth, @NotNull @NotEmpty @NotBlank String adress, @NotNull @NotEmpty @NotBlank String phoneNumber, @NotNull @NotEmpty @NotBlank @Email String email) {
        this.firstName = firstName;
        this.surname = surname;
        this.yearOfBirth = yearOfBirth;
        this.adress = adress;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public ClientDTO() {
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<PurchaseDTO> getPurchaseDTOSet() {
        return purchaseDTOSet;
    }

    public void setPurchaseDTOSet(Set<PurchaseDTO> purchaseDTOSet) {
        this.purchaseDTOSet = purchaseDTOSet;
    }

    @Override
    public String toString() {
        return "ClientDTO{" +
                "firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                ", adress='" + adress + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
