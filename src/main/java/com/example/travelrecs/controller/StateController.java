package com.example.travelrecs.controller;

import com.example.travelrecs.model.State;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/state")
public class StateController {
    @PostMapping("/add")
    public @ResponseBody String addState(@RequestBody State state) {
        //TODO

    }


    @DeleteMapping("/delete")
    public @ResponseBody String deleteState(@RequestBody State state) {
        //TODO

    }

    @PostMapping("/update")
    public @ResponseBody String updateState(@RequestBody State state) {
        //TODO

    }

    @GetMapping("/get/{countryName}")
    public @ResponseBody List<State> getStateByCountryName(@PathVariable String countryName) {
        //TODO

    }

}
