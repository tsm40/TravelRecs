package com.example.travelrecs.controller;

import com.example.travelrecs.interfaces.DayInterface;
import com.example.travelrecs.model.Day;
import com.example.travelrecs.model.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/day")
public class DayController {
    @Autowired
    private DayInterface dayInterface;


    @PostMapping("/add")
    public @ResponseBody String addNewDay(@RequestParam Integer tripID, @RequestParam Integer placeID,
                                          @RequestParam Integer index ) {
        Day day = new Day();
        day.setTripID(tripID);
        day.setPlaceID(placeID);
        day.setIndex(index);
        dayInterface.save(day);
        return "Saved";
    }


    @DeleteMapping("/{id}")
    public String deleteDay(@PathVariable Integer id) {
        if (dayInterface.existsById(id)) {
            dayInterface.deleteById(id);
            return "Day with ID " + id + " has been deleted.";
        }
        else {
            return "Day with ID " + id + " does not exist.";
        }
    }


    @PostMapping("/addtrip")
    public String addTripToDay(@RequestParam Day day, @RequestParam Trip trip) {
        //TODO

    }




}
