package com.ecommerceapp.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerceapp.entity.State;
import com.ecommerceapp.servic.StateService;

@RestController
@RequestMapping("/api/admin/states")
public class StateController {

    @Autowired
    private StateService stateService;

    @PostMapping
    public ResponseEntity<State> create(@RequestBody State state) {
        return ResponseEntity.ok(stateService.createState(state));
    }

    @GetMapping("/{id}")
    public ResponseEntity<State> getById(@PathVariable int id) {
        return ResponseEntity.ok(stateService.getStateById(id));
    }

    @GetMapping
    public ResponseEntity<List<State>> getAll() {
        return ResponseEntity.ok(stateService.getAllStates());
    }

    @PutMapping("/{id}")
    public ResponseEntity<State> update(@PathVariable int id, @RequestBody State state) {
        return ResponseEntity.ok(stateService.updateState(id, state));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        stateService.deleteState(id);
        return ResponseEntity.ok("Deleted state with ID: " + id);
    }
    @GetMapping("/search")
    public ResponseEntity<List<State>> searchStates(@RequestParam String name) {
        List<State> result = stateService.searchStatesByName(name);
        return ResponseEntity.ok(result);
    }

}
