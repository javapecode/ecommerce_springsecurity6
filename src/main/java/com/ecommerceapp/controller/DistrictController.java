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

import com.ecommerceapp.entity.District;
import com.ecommerceapp.servic.DistrictService;

@RestController
@RequestMapping("/api/admin/districts")
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    @PostMapping
    public ResponseEntity<District> create(@RequestBody District district) {
        return ResponseEntity.ok(districtService.createDistrict(district));
    }

    @GetMapping("/{id}")
    public ResponseEntity<District> getById(@PathVariable int id) {
        return ResponseEntity.ok(districtService.getDistrictById(id));
    }

    @GetMapping
    public ResponseEntity<List<District>> getAll() {
        return ResponseEntity.ok(districtService.getAllDistricts());
    }

    @GetMapping("/search")
    public ResponseEntity<List<District>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(districtService.searchDistrictsByName(name));
    }

    @PutMapping("/{id}")
    public ResponseEntity<District> update(@PathVariable int id, @RequestBody District district) {
        return ResponseEntity.ok(districtService.updateDistrict(id, district));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        districtService.deleteDistrict(id);
        return ResponseEntity.noContent().build();
    }
}