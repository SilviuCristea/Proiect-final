package frontEnd;

import business.dto.AirportDTO;
import business.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AirportController {
    @Autowired
    AirportService airportService;

    @PostMapping(path = "/insertAirport")
    private ResponseEntity insert(@RequestBody @Valid AirportDTO airportDTO){
        List<String> airportList = airportService.findAirportByCity(airportDTO.getCityDTO().getName());
        if (airportList == null){
            airportService.insert(airportDTO);
            return ResponseEntity.ok(airportDTO.getName()+" a fost adaugat in baza de date.");
        }else if (airportList.contains(airportDTO.getName())){
            return ResponseEntity.badRequest().body("Aeroportul "+airportDTO.getName()+" exista in baza de date.");
        }else{
            airportService.insert(airportDTO);
            return ResponseEntity.ok(airportDTO.getName()+" a fost adaugat in baza de date.");
        }
    }

    @GetMapping(path = "/findAirportByName")
    public ResponseEntity findAirportByName(@RequestParam String name){
        AirportDTO airportDTO = airportService.findAirportByName(name);
        if (airportDTO == null){
            return ResponseEntity.badRequest().body("Aeroportul "+name+" nu exista in baza de date.");
        }else{
            return ResponseEntity.ok(airportDTO);
        }
    }
}
