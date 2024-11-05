package com.example.travelrecs.controller;


import com.example.travelrecs.model.Day;
import com.example.travelrecs.model.Trip;
import org.springframework.web.bind.annotation.*;

@RestController("/trip")
public class TripController {

    @PostMapping("/add")
    public Trip addTrip(@RequestBody Trip trip) {
        //TODO
    }

    @DeleteMapping("/delete")
    public Trip deleteTrip(@RequestBody Trip trip) {
        //TODO
    }


    @PostMapping("/addday")
    public String addDay(@RequestParam Day day) {
        //TODO
    }

    @DeleteMapping("/deleteday")
    public String deleteDay(@RequestParam Day day) {
        //TODO

    }


}
