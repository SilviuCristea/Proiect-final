package frontEnd;

import business.dto.CityDTO;
import business.service.CityService;
import business.service.ContinentService;
import business.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
