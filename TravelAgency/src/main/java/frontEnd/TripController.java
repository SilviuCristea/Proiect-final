package frontEnd;

import business.dto.TripDTO;
import business.service.AirportService;
import business.service.HotelService;
import business.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
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
    public ResponseEntity insert(@RequestBody TripDTO tripDTO) {
        List<String> tripList = tripService.findTripByName(tripDTO.getName());
        if (tripList == null) {
            tripService.insert(tripDTO);
            return ResponseEntity.ok("Excursia " + tripDTO.getName() + " a fost introdusa cu succes.");
        } else if (tripList.contains(tripDTO.getName())) {
            return ResponseEntity.badRequest().body("Excursia " + tripDTO.getName() + " exista in baza de date.");
        } else {
            tripService.insert(tripDTO);
            return ResponseEntity.ok("Excursia " + tripDTO.getName() + " a fost introdusa cu succes.");
        }
    }


    @GetMapping(path = "/findPromotedTrips")
    public ResponseEntity findPromotedTrip(@RequestParam boolean promoted) {
        List<TripDTO> tripDTOList = tripService.findPromotedTrips(promoted);
        if (tripDTOList.isEmpty()) {
            return ResponseEntity.badRequest().body("Nu exista vacante promovate.");
        } else {
            return ResponseEntity.ok(tripDTOList);
        }
    }

    @GetMapping(path = "/findAllTrips")
    public ResponseEntity findAllTrips() {
        List<TripDTO> tripDTOList = tripService.findAllTrips();
        return ResponseEntity.ok(tripDTOList);
    }

    @GetMapping(path = "/findUpcomingTrips")
    public ResponseEntity findUpcomingTrips(@RequestParam("departureDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date departureDate) {
        List<TripDTO> tripDTOList = tripService.findUpcomingTrips(departureDate);
        if (tripDTOList.isEmpty()) {
            return ResponseEntity.badRequest().body("Nu exista vacante dupa data de " + departureDate);
        } else {
            return ResponseEntity.ok(tripDTOList);
        }
    }

    @GetMapping(path = "/findUpcomingTripsByContinent")
    public ResponseEntity findUpcomingTripsByContinent(@RequestParam String name, @RequestParam("departureDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date departureDate){
        List<TripDTO> tripDTOList = tripService.findUpcomingTripsByContinent(name, departureDate);
        try {
            if (tripDTOList.isEmpty()) {
                return ResponseEntity.badRequest().body("Nu exista excursii in " + name + " dupa data de " + departureDate + ".");
            } else {
                return ResponseEntity.ok(tripDTOList);
            }
        }catch (NullPointerException e){
            return ResponseEntity.badRequest().body("Nu exista excursii dupa criteriile de cautare.");
        }
    }

    @GetMapping(path = "/findUpcomingTripsByCountry")
    public ResponseEntity findUpcomingTripsByCountry(@RequestParam String name, @RequestParam("departureDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date departureDate){
        List<TripDTO> tripDTOList = tripService.findUpcomingTripsByCountry(name, departureDate);
        try {
            if (tripDTOList.isEmpty()) {
                return ResponseEntity.badRequest().body("Nu exista excursii in " + name + " dupa data de " + departureDate + ".");
            } else {
                return ResponseEntity.ok(tripDTOList);
            }
        }catch (NullPointerException e){
            return ResponseEntity.badRequest().body("Nu exista excursii dupa criteriile de cautare.");
        }
    }

    @GetMapping(path = "/findUpcomingTripsByCity")
    public ResponseEntity findUpcomingTripsByCity(@RequestParam String name, @RequestParam("departureDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date departureDate){
        List<TripDTO> tripDTOList = tripService.findUpcomingTripsByCity(name, departureDate);
        try {
            if (tripDTOList.isEmpty()) {
                return ResponseEntity.badRequest().body("Nu exista excursii in " + name + " dupa data de " + departureDate + ".");
            } else {
                return ResponseEntity.ok(tripDTOList);
            }
        }catch (NullPointerException e){
            return ResponseEntity.badRequest().body("Nu exista excursii dupa criteriile de cautare.");
        }
    }

    @GetMapping(path = "/findUpcomingTripsByHotel")
    public ResponseEntity findUpcomingTripsByHotel(@RequestParam String name, @RequestParam("departureDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date departureDate){
        List<TripDTO> tripDTOList = tripService.findUpcomingTripsByHotel(name, departureDate);
        try {
            if (tripDTOList.isEmpty()) {
                return ResponseEntity.badRequest().body("Nu exista excursii in " + name + " dupa data de " + departureDate + ".");
            } else {
                return ResponseEntity.ok(tripDTOList);
            }
        }catch (NullPointerException e){
            return ResponseEntity.badRequest().body("Nu exista excursii dupa criteriile de cautare.");
        }
    }

}


