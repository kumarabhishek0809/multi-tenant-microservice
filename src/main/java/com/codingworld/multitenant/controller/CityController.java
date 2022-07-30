package com.codingworld.multitenant.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingworld.multitenant.entity.City;
import com.codingworld.multitenant.service.CityService;

@RestController
@RequestMapping("/api/v1/")
public class CityController {

	@Autowired
	private CityService cityService;

	@PostMapping(value = "/createcity")
	public ResponseEntity<City> save(@RequestBody City city) {
		return ResponseEntity.ok(cityService.save(city));
	}

	@GetMapping(value = "/getcity/all")
	public ResponseEntity<List<City>> getAll() throws SQLException {
		List<City> cities = cityService.getAll();
		return new ResponseEntity<>(cities, HttpStatus.OK);
	}

	@GetMapping(value = "/getcity/{id}")
	public ResponseEntity<City> get(@PathVariable(value = "id") Long id) {
		City city = cityService.get(id);
		return new ResponseEntity<>(city, HttpStatus.OK);
	}

}
