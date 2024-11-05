package com.example.travelrecs.controller;


import com.example.travelrecs.model.Place;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/place")
public class PlaceController {
    @PostMapping("/add")
    public @ResponseBody String addPlace(@RequestBody Place place) {
        //TODO

    }


    @DeleteMapping("/delete")
    public @ResponseBody String deletePlace(@RequestBody Place place) {
        //TODO

    }

    @PostMapping("/update")
    public @ResponseBody String updatePlace(@RequestBody Place place) {
        //TODO

    }

    @GetMapping("/get/{cityId}")
    public @ResponseBody List<Place> getPlaceByCityId(@PathVariable Long cityId) {
        //TODO

    }

    //TODO: pagination


}
