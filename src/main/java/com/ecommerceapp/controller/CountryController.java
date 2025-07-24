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

import com.ecommerceapp.entity.Country;
import com.ecommerceapp.servic.CountryService;

@RestController
@RequestMapping("/api/admin/countries")
public class CountryController {

	@Autowired
	private CountryService countryService;

	@PostMapping
	public ResponseEntity<Country> createCountry(@RequestBody Country country) {
		Country created = countryService.createCountry(country);
		return ResponseEntity.ok(created);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Country> getCountryById(@PathVariable int id) {
		Country country = countryService.getCountryById(id);
		return ResponseEntity.ok(country);
	}

	@GetMapping
	public ResponseEntity<List<Country>> getAllCountries() {
		List<Country> countries = countryService.getAllCountries();
		return ResponseEntity.ok(countries);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Country> updateCountry(@PathVariable int id, @RequestBody Country updatedCountry) {
		Country updated = countryService.updateCountry(id, updatedCountry);
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCountry(@PathVariable int id) {
		countryService.deleteCountry(id);
		return ResponseEntity.ok("Country deleted successfully with ID: " + id);
	}

	@GetMapping("/filter")
	public ResponseEntity<List<Country>> filterCountries(@RequestParam(required = false) String name,
			@RequestParam(required = false) Boolean status) {
		List<Country> filtered = countryService.filterCountries(name, status);
		return ResponseEntity.ok(filtered);
	}
}
