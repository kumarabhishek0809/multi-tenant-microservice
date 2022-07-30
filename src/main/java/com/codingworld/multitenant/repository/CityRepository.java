package com.codingworld.multitenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codingworld.multitenant.entity.City;

public interface CityRepository extends JpaRepository<City,Long> {

    City findByName(String name);

    void deleteByName(String name);
}
