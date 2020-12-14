package persistence.entities;

import javax.persistence.*;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = "findClientByName", query = "select client from Client client where client.firstName=:firstName and client.surname=:surname"),
        @NamedQuery(name = "findClientByUser", query = "select client.surname from Client client inner join client.user user where user.userName = :userName")
})

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "surname")
    private String surname;
    @Column(name = "year_of_birth")
    private int yearOfBirth;
    @Column(name = "adress")
    private String adress;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "email")
    private String email;
    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    private User user;
    @ManyToMany(mappedBy = "clientSet")
    private Set<Purchase> purchaseSet;

    public Client(String firstName, String surname, int yearOfBirth, String adress, String phoneNumber, String email) {
        this.firstName = firstName;
        this.surname = surname;
        this.yearOfBirth = yearOfBirth;
        this.adress = adress;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Client(String firstName, String surname, int yearOfBirth, String adress, String phoneNumber, String email, User user, Set<Purchase> purchaseSet) {
        this.firstName = firstName;
        this.surname = surname;
        this.yearOfBirth = yearOfBirth;
        this.adress = adress;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.user = user;
        this.purchaseSet = purchaseSet;
    }

    public Client() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Set<Purchase> getPurchaseSet() {
        return purchaseSet;
    }

    public void setPurchaseSet(Set<Purchase> purchaseSet) {
        this.purchaseSet = purchaseSet;
    }

    @Override
    public String toString() {
        return "Client{" +
                "firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                ", adress='" + adress + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", user=" + user +
                ", purchaseSet=" + purchaseSet +
                '}';
    }
}
