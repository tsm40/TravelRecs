package com.example.travelrecs.controller;

import com.example.travelrecs.dto.DayDTO;
import com.example.travelrecs.exception.ResourceNotFoundException;

import com.example.travelrecs.service.DayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/days")
public class DayController {

    @Autowired
    private DayService dayService;

    // Create a new Day
    @PostMapping
    public ResponseEntity<DayDTO> createDay(@RequestBody DayDTO dayDTO) {
        DayDTO createdDay = dayService.createDay(dayDTO);
        return ResponseEntity.ok(createdDay);
    }

    // Get all Days with pagination
    @GetMapping
    public ResponseEntity<Page<DayDTO>> getAllDays(Pageable pageable) {
        Page<DayDTO> days = dayService.getAllDays(pageable);
        return ResponseEntity.ok(days);
    }

    // Get a Day by ID
    @GetMapping("/{id}")
    public ResponseEntity<DayDTO> getDayById(@PathVariable Long id) {
        DayDTO dayDTO = dayService.getDayById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Day not found with id " + id));
        return ResponseEntity.ok(dayDTO);
    }

    // Update a Day
    @PutMapping("/{id}")
    public ResponseEntity<DayDTO> updateDay(@PathVariable Long id, @RequestBody DayDTO dayDTO) {
        DayDTO updatedDay = dayService.updateDay(id, dayDTO);
        return ResponseEntity.ok(updatedDay);
    }

    // Delete a Day
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDay(@PathVariable Long id) {
        dayService.deleteDay(id);
        return ResponseEntity.noContent().build();
    }
}


//@Controller
//@RequestMapping("/day")
//public class DayController {
//    @Autowired
//    private DayInterface dayInterface;
//
//
//    @PostMapping("/add")
//    public @ResponseBody String addNewDay(@RequestParam Integer tripID, @RequestParam Integer placeID,
//                                          @RequestParam Integer index ) {
//        Day day = new Day();
//        day.setTripID(tripID);
//        day.setPlaceID(placeID);
//        day.setIndex(index);
//        dayInterface.save(day);
//        return "Saved";
//    }
//
//
//    @DeleteMapping("/{id}")
//    public String deleteDay(@PathVariable Integer id) {
//        if (dayInterface.existsById(id)) {
//            dayInterface.deleteById(id);
//            return "Day with ID " + id + " has been deleted.";
//        }
//        else {
//            return "Day with ID " + id + " does not exist.";
//        }
//    }
//
//
//    @PostMapping("/addtrip")
//    public String addTripToDay(@RequestParam Day day, @RequestParam Trip trip) {
//        //TODO
//
//    }
//
//
//
//
//}
