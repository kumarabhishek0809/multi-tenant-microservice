package com.codingworld.multitenant.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.codingworld.multitenant.entity.City;

@Service
public class CityService {

	private final MongoTemplate mongoTemplate;

	public CityService(@Lazy MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public City save(City city) {
		return mongoTemplate.save(city);
	}

	public List<City> getAll() throws SQLException {
		return mongoTemplate.findAll(City.class);
	}

	public City get(Long id) {
		return mongoTemplate.findById(id, City.class);
	}
}
