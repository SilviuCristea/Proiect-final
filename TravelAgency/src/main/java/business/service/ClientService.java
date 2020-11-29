package business.service;

import business.dto.ClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.dao.ClientDAO;
import persistence.entities.Client;


@Service
public class ClientService {
    @Autowired
    ClientDAO clientDAO;

    public void inset(ClientDTO clientDTO){
        Client client = new Client();
        client.setFirstName(clientDTO.getFirstName());
        client.setSurname(clientDTO.getSurname());
        client.setYearOfBirth(clientDTO.getYearOfBirth());
        client.setAdress(clientDTO.getAdress());
        client.setPhoneNumber(clientDTO.getPhoneNumber());
        client.setEmail(clientDTO.getEmail());
        clientDAO.insert(client);
    }
}
