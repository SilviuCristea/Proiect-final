package frontEnd;

import business.dto.CityDTO;
import business.service.CityService;
import business.service.ContinentService;
import business.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CityController {
    @Autowired
    CityService cityService;
    @Autowired
    CountryService countryService;
    @Autowired
    ContinentService continentService;

    @PostMapping(path = "/insertCity")
    public ResponseEntity insert(@RequestBody @Valid CityDTO cityDTO){
        if (cityService.findCityByName(cityDTO.getName()) == null){
            cityService.insert(cityDTO);
            return ResponseEntity.ok("Orasul " + cityDTO.getName() + " a fost introdus cu succes.");
        }else{
            return ResponseEntity.badRequest().body(cityDTO.getName()+" exista in baza de date.");
        }
    }

    @GetMapping(path = "/findCityByName")
    public ResponseEntity findCityByName(@RequestParam String name){
        CityDTO cityDTO = cityService.findCityByName(name);
        if (cityDTO==null){
            return ResponseEntity.badRequest().body("Orasul "+name+" nu exista in baza de date.");
        }else{
            return ResponseEntity.ok(cityDTO);
        }

    }
}
