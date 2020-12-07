package frontEnd;

import business.dto.CountryDTO;
import business.service.ContinentService;
import business.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CountryController {
    @Autowired
    CountryService countryService;
    @Autowired
    ContinentService continentService;

    @PostMapping(path = "/insertCountry")
    public ResponseEntity insert(@RequestBody @Valid CountryDTO countryDTO){
        if(countryService.findCountryByName(countryDTO.getName()) == null){
            countryService.insert(countryDTO);
            return ResponseEntity.ok("Tara "+countryDTO.getName()+" a fost introdusa cu succes.");
        }else{
            return ResponseEntity.badRequest().body(countryDTO.getName() + " exista in baza de date.");
        }
    }

    @GetMapping(path = "/findCountryByName")
    public ResponseEntity findCountryByName(@RequestParam String name){
        CountryDTO countryDTO = countryService.findCountryByName(name);
        if (countryDTO == null){
            return ResponseEntity.badRequest().body(name+" nu exista in baza de date.");
        }else{
            return ResponseEntity.ok(countryDTO);
        }
    }

    @GetMapping(path = "/findCountryByContinent")
    public ResponseEntity findCountryByContinent(@RequestParam String name){
        List<CountryDTO> countryDTOList = countryService.findCountryByContinent(name);
        if (continentService.findContinentByName(name) == null){
            return ResponseEntity.badRequest().body(name+" nu exista in baza de date.");
        }else{
            return ResponseEntity.ok(countryDTOList);
        }
    }
}
