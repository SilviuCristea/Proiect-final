package business.service;

import business.dto.ClientDTO;
import business.dto.PurchaseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.dao.PurchaseDAO;
import persistence.entities.Client;
import persistence.entities.Trip;

import java.util.HashSet;
import java.util.Set;

@Service
public class PurchaseService {
    @Autowired
    PurchaseDAO purchaseDAO;

    public void insert(PurchaseDTO purchaseDTO){
        Set<Client> clientSet = new HashSet<Client>();
        for (ClientDTO clientDTO : purchaseDTO.getClientDTOSet()){
            Client client = new Client();
            client.setFirstName(clientDTO.getFirstName());
            client.setSurname(clientDTO.getSurname());
            client.setYearOfBirth(clientDTO.getYearOfBirth());
            client.setAdress(clientDTO.getAdress());
            client.setPhoneNumber(clientDTO.getPhoneNumber());
            client.setEmail(clientDTO.getEmail());
            clientSet.add(client);
        }
        Trip trip = new Trip();

        //Am ramas aici
    }
}

