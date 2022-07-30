package com.codingworld.multitenant.config;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.codingworld.multitenant.repository.DataSourceConfigRepository;

@Component
public class TenantDataSource implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<String, DataSource> dataSources = new HashMap<>();

	private DataSourceConfigRepository configRepo;

	public TenantDataSource(DataSourceConfigRepository configRepo) {
		this.configRepo = configRepo;

	}

	public DataSource getDataSource(String name) {
		if (dataSources.get(name) != null) {
			return dataSources.get(name);
		}
		DataSource dataSource = createDataSource(name);
		if (dataSource != null) {
			dataSources.put(name, dataSource);
		}
		return dataSource;
	}

	@PostConstruct
	public Map<String, DataSource> getAll() {
		List<DataSourceConfig> configList = configRepo.findAll();
		if (CollectionUtils.isEmpty(configList)) {
			configList = Arrays.asList( //
					DataSourceConfig.builder()//
							.name("Tenant-1")//
							.url("jdbc:h2:mem:MultiTenant1") //
							.driverClassName("org.h2.Driver")//
							.initialize(true)//
							.username("sa")//
							.build(),
					DataSourceConfig.builder()//
							.name("Tenant-2")//
							.username("sa")//
							.url("jdbc:h2:mem:MultiTenant2") //
							.driverClassName("org.h2.Driver")//
							.initialize(true)//
							.build());

			configList = configRepo.saveAll(configList);
		}
		Map<String, DataSource> result = new HashMap<>();
		for (DataSourceConfig config : configList) {
			DataSource dataSource = getDataSource(config.getName());
			result.put(config.getName(), dataSource);
		}
		return result;
	}

	private DataSource createDataSource(String name) {
		DataSourceConfig config = configRepo.findByName(name);
		if (config != null) {
			DataSourceBuilder<?> factory = DataSourceBuilder.create()//
					.driverClassName(config.getDriverClassName())
					.username(config.getUsername())//
					.password(config.getPassword())//
					.url(config.getUrl());
			return factory.build();
		}
		return null;
	}

}
