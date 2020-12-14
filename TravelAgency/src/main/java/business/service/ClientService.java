package business.service;

import business.dto.ClientDTO;
import business.dto.HotelDTO;
import business.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.dao.ClientDAO;
import persistence.entities.Client;
import persistence.entities.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {
    @Autowired
    ClientDAO clientDAO;

    public void insert(ClientDTO clientDTO){
        Client client = new Client();
        client.setFirstName(clientDTO.getFirstName());
        client.setSurname(clientDTO.getSurname());
        client.setYearOfBirth(clientDTO.getYearOfBirth());
        client.setAdress(clientDTO.getAdress());
        client.setPhoneNumber(clientDTO.getPhoneNumber());
        client.setEmail(clientDTO.getEmail());
        User user = new User();
        user.setUserName(clientDTO.getUserDTO().getUserName());
        user.setPassword(clientDTO.getUserDTO().getPassword());
        user.setClient(client);
        client.setUser(user);
        clientDAO.insert(client);
    }

    public List<ClientDTO> findClientByName(String firstName, String surname){
        List<ClientDTO> clientDTOList = new ArrayList<>();
        List<Client> clientList = clientDAO.findClientByName(firstName, surname);
        if (clientList.isEmpty()){
            return null;
        }
        for (Client client : clientList){
            ClientDTO clientDTO = new ClientDTO();
            clientDTO.setFirstName(client.getFirstName());
            clientDTO.setSurname(client.getSurname());
            clientDTO.setYearOfBirth(client.getYearOfBirth());
            clientDTO.setAdress(client.getAdress());
            clientDTO.setPhoneNumber(client.getPhoneNumber());
            clientDTO.setEmail(client.getEmail());
            UserDTO userDTO = new UserDTO();
            userDTO.setUserName(client.getUser().getUserName());
            userDTO.setPassword(client.getUser().getPassword());
            userDTO.setClientDTO(clientDTO);
            clientDTO.setUserDTO(userDTO);
            clientDTOList.add(clientDTO);
        }
        return clientDTOList;
    }

    public List<String> findClientByUser(String userName){
        List<String> clientList = clientDAO.findClientByUser(userName);
        if (clientList == null){
            return null;
        }
        return clientList;
    }
}
