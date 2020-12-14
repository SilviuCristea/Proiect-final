package frontEnd;

import business.dto.HotelDTO;
import business.service.CityService;
import business.service.ContinentService;
import business.service.CountryService;
import business.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class HotelController {
    @Autowired
    HotelService hotelService;
    @Autowired
    CityService cityService;
    @Autowired
    CountryService countryService;
    @Autowired
    ContinentService continentService;

    @PostMapping(path = "/insertHotel")
    public ResponseEntity insert(@RequestBody @Valid HotelDTO hotelDTO){
        List<String> hotelList = hotelService.findHotelByCity(hotelDTO.getCityDTO().getName());
        if (hotelList==null){
            hotelService.insert(hotelDTO);
            return ResponseEntity.ok(hotelDTO.getName()+" a fost adaugat in baza de date.");
        }
        else if (hotelList.contains(hotelDTO.getName())){
            return ResponseEntity.badRequest().body("Hotelul "+hotelDTO.getName()+" exista in baza de date.");
        }else{
            hotelService.insert(hotelDTO);
            return ResponseEntity.ok(hotelDTO.getName()+" a fost adaugat in baza de date.");
        }

    }

    @GetMapping(path = "/findHotelByName")
    public ResponseEntity findHotelByName(@RequestParam String name){
        List<HotelDTO> hotelDTOList = hotelService.findHotelByName(name);
        if (hotelDTOList.isEmpty()){
            return ResponseEntity.badRequest().body("Hotelul "+name+" nu exista in baza de date.");
    }else{
            return ResponseEntity.ok(hotelDTOList);
        }
    }
}
