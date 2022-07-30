package com.codingworld.multitenant.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "city")
@NoArgsConstructor
@Data
public class City {
	@Id
	private String id;

	private String name;

	private String tenantId;

}
