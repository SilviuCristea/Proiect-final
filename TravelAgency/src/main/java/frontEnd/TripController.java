package frontEnd;

import business.dto.TripDTO;
import business.service.AirportService;
import business.service.HotelService;
import business.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class TripController {
    @Autowired
    TripService tripService;
    @Autowired
    AirportService airportService;
    @Autowired
    HotelService hotelService;

    @PostMapping(path = "/insertTrip")
    public ResponseEntity insert(@RequestBody TripDTO tripDTO){
        List<String> tripList = tripService.findTripByName(tripDTO.getName());
        if (tripList==null){
            tripService.insert(tripDTO);
            return ResponseEntity.ok("Excursia "+tripDTO.getName()+" a fost introdusa cu succes.");
        }else if (tripList.contains(tripDTO.getName())){
            return ResponseEntity.badRequest().body("Excursia "+tripDTO.getName()+" exista in baza de date.");
        }else{
            tripService.insert(tripDTO);
            return ResponseEntity.ok("Excursia "+tripDTO.getName()+" a fost introdusa cu succes.");
        }
    }
}
