package frontEnd;

import business.dto.ClientDTO;
import business.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ClientController {
    @Autowired
    ClientService clientService;

    @PostMapping(path = "/insertClient")
    public ResponseEntity insert(@RequestBody @Valid ClientDTO clientDTO){
        List<String> clientList = clientService.findClientByUser(clientDTO.getUserDTO().getUserName());
        if (clientList==null){
            clientService.insert(clientDTO);
            return ResponseEntity.ok("Inregistrare efectuata cu succes.");
        }else if (clientService.findClientByUser(clientDTO.getUserDTO().getUserName()).contains(clientDTO.getSurname())){
            return ResponseEntity.badRequest().body("Sunteti inregistrat in baza de date. Va rog sa va logati.");
        }else{
            clientService.insert(clientDTO);
            return ResponseEntity.ok("Inregistrare efectuata cu succes.");
        }
    }
}
