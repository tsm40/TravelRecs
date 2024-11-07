package com.example.travelrecs.controller;

import com.example.travelrecs.dto.StateDTO;
import com.example.travelrecs.exception.ResourceNotFoundException;
import com.example.travelrecs.model.State;
import com.example.travelrecs.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/states")
public class StateController {

    @Autowired
    private StateService stateService;

    // 创建 State
    @PostMapping
    public ResponseEntity<StateDTO> createState(@RequestBody StateDTO stateDTO) {
        StateDTO createdState = stateService.createState(stateDTO);
        return ResponseEntity.ok(createdState);
    }

    // 获取所有 States
    @GetMapping
    public ResponseEntity<List<StateDTO>> getAllStates() {
        List<StateDTO> states = stateService.getAllStates();
        return ResponseEntity.ok(states);
    }

    // 根据 ID 获取 State
    @GetMapping("/{id}")
    public ResponseEntity<StateDTO> getStateById(@PathVariable Long id) {
        StateDTO stateDTO = stateService.getStateById(id)
                .orElseThrow(() -> new ResourceNotFoundException("State not found with id " + id));
        return ResponseEntity.ok(stateDTO);
    }

    // 更新 State
    @PutMapping("/{id}")
    public ResponseEntity<StateDTO> updateState(@PathVariable Long id, @RequestBody StateDTO stateDTO) {
        StateDTO updatedState = stateService.updateState(id, stateDTO);
        return ResponseEntity.ok(updatedState);
    }

    // 删除 State
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteState(@PathVariable Long id) {
        stateService.deleteState(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/get/{countryName}")
    public ResponseEntity<List<StateDTO>> getStatesByCountryName(@PathVariable String countryName) {
        List<StateDTO> states = stateService.getStatesByCountryName(countryName);
        return ResponseEntity.ok(states);
    }
}

