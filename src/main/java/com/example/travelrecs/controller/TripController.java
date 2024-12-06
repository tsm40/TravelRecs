package com.example.travelrecs.controller;


import com.example.travelrecs.dto.TripDTO;
import com.example.travelrecs.exception.ResourceNotFoundException;
import com.example.travelrecs.model.Day;
import com.example.travelrecs.model.Trip;
import com.example.travelrecs.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/trips")
public class TripController {

    @Autowired
    private TripService tripService;

    @PostMapping
    public ResponseEntity<TripDTO> createTrip(@RequestBody TripDTO tripDTO) {
        TripDTO createdTrip = tripService.createTrip(tripDTO);
        return ResponseEntity.ok(createdTrip);
    }

    @GetMapping
    public ResponseEntity<Page<TripDTO>> getAllTrips(Pageable pageable) {
        Page<TripDTO> trips = tripService.getAllTrips(pageable);
        return ResponseEntity.ok(trips);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TripDTO> getTripById(@PathVariable Long id) {
        TripDTO tripDTO = tripService.getTripById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trip not found with id " + id));
        return ResponseEntity.ok(tripDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TripDTO> updateTrip(@PathVariable Long id, @RequestBody TripDTO tripDTO) {
        TripDTO updatedTrip = tripService.updateTrip(id, tripDTO);
        return ResponseEntity.ok(updatedTrip);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable Long id) {
        tripService.deleteTrip(id);
        return ResponseEntity.noContent().build();
    }
}




//@RestController("/trip")
//public class TripController {
//
//    @PostMapping("/add")
//    public Trip addTrip(@RequestBody Trip trip) {
//        //TODO
//    }
//
//    @DeleteMapping("/delete")
//    public Trip deleteTrip(@RequestBody Trip trip) {
//        //TODO
//    }
//
//
//    @PostMapping("/addday")
//    public String addDay(@RequestParam Day day) {
//        //TODO
//    }
//
//    @DeleteMapping("/deleteday")
//    public String deleteDay(@RequestParam Day day) {
//        //TODO
//
//    }
//
//
//}
