package frontEnd;

import business.dto.ContinentDTO;
import business.service.ContinentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
public class ContinentController {
    @Autowired
    ContinentService continentService;

    @PostMapping(path = "/insertContinent")
    public ResponseEntity insert(@RequestBody @Valid ContinentDTO continentDTO){
        if (continentService.findContinentByName(continentDTO.getName()) == null){
            continentService.insert(continentDTO);
            return ResponseEntity.ok("Continentul " + continentDTO.getName() + " a fost introdus cu succes.");
        }else {
            return ResponseEntity.badRequest().body(continentDTO.getName()+" exista in baza de date.");
        }
    }

    @GetMapping(path = "/findContinentByNamne")
    public ResponseEntity findContinentByName(@RequestParam String name){
        ContinentDTO continentDTO = continentService.findContinentByName(name);
        if (continentDTO == null){
            return ResponseEntity.badRequest().body(name + " nu este in baza de date");
        }else {
            return ResponseEntity.ok(continentDTO);
        }
    }
}
