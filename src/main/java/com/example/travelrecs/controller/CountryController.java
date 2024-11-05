package com.example.travelrecs.controller;

import com.example.travelrecs.model.Country;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/country")
public class CountryController {
    @PostMapping("/add")
    public @ResponseBody String addCountry(@RequestBody Country country) {
        //TODO

    }


    @DeleteMapping("/delete")
    public @ResponseBody String deleteCountry(@RequestBody Country country) {
        //TODO

    }

    @PostMapping("/update")
    public @ResponseBody String updateCountry(@RequestBody Country country) {
        //TODO

    }

    @GetMapping("/get/{name}")
    public @ResponseBody Country getCountry(@PathVariable String name) {
        //TODO

    }

    @GetMapping("/getall")
    public @ResponseBody List<Country> getAllCountry() {
        //TODO

    }

}
