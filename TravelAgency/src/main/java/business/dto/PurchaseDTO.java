package business.dto;

import java.util.Set;

public class PurchaseDTO {
    private int id;
    private double amount;
    private TripDTO tripDTO;
    private Set<ClientDTO> clientDTOSet;

    public PurchaseDTO(double amount, TripDTO tripDTO, Set<ClientDTO> clientDTOSet) {
        this.amount = amount;
        this.tripDTO = tripDTO;
        this.clientDTOSet = clientDTOSet;
    }

    public PurchaseDTO(double amount) {
        this.amount = amount;
    }

    public PurchaseDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TripDTO getTripDTO() {
        return tripDTO;
    }

    public void setTripDTO(TripDTO tripDTO) {
        this.tripDTO = tripDTO;
    }

    public Set<ClientDTO> getClientDTOSet() {
        return clientDTOSet;
    }

    public void setClientDTOSet(Set<ClientDTO> clientDTOSet) {
        this.clientDTOSet = clientDTOSet;
    }

    @Override
    public String toString() {
        return "PurchaseDTO{" +
                "amount=" + amount +
                ", tripDTO=" + tripDTO +
                ", clientDTOSet=" + clientDTOSet +
                '}';
    }
}
