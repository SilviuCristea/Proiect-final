package business.dto;

import java.util.Set;

public class ClientDTO {
    private String firstName;
    private String surname;
    private int yearOfBirth;
    private String adress;
    private String phoneNumber;
    private String email;
    private Set<PurchaseDTO> purchaseDTOSet;

    public ClientDTO(String firstName, String surname, int yearOfBirth, String adress, String phoneNumber, String email, Set<PurchaseDTO> purchaseDTOSet) {
        this.firstName = firstName;
        this.surname = surname;
        this.yearOfBirth = yearOfBirth;
        this.adress = adress;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.purchaseDTOSet = purchaseDTOSet;
    }

    public ClientDTO(String firstName, String surname, int yearOfBirth, String adress, String phoneNumber, String email) {
        this.firstName = firstName;
        this.surname = surname;
        this.yearOfBirth = yearOfBirth;
        this.adress = adress;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public ClientDTO() {
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
