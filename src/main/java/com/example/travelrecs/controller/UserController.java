package com.example.travelrecs.controller;

import com.example.travelrecs.model.User;
import com.example.travelrecs.interfaces.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserInterface userInterface;
    @PostMapping("/add")
    public @ResponseBody String addNewUser(@RequestParam String userName, @RequestParam String password) {
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        userInterface.save(user);
        return "Saved";
    }
    @GetMapping("/getByUserName")
    public @ResponseBody User getUserByUserName(@RequestParam String userName) {
        return userInterface.findByUserName(userName);
    }
    @GetMapping("/{id}")
    public @ResponseBody Optional<User> getUserById(@PathVariable Integer id) {
        return userInterface.findById(id);
    }
    // 1. get user name and password
}
