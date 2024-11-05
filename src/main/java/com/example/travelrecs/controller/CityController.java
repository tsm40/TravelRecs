package com.example.travelrecs.controller;

import com.example.travelrecs.model.City;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/city")
public class CityController {
    @PostMapping("/add")
    public @ResponseBody String addCity(@RequestBody City city) {
        //TODO

    }


    @DeleteMapping("/delete")
    public @ResponseBody String deleteCity(@RequestBody City city) {
        //TODO

    }

    @PostMapping("/update")
    public @ResponseBody String updateCity(@RequestBody City city) {
        //TODO

    }

    @GetMapping("/get/{stateName}")
    public @ResponseBody List<City> getCityByStateName(@PathVariable String stateName) {
        //TODO

    }

}
