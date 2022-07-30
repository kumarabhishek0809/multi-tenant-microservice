package com.codingworld.multitenant.config;

import java.util.Objects;

import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

import com.codingworld.multitenant.interceptor.ConnectionStorage;
import com.mongodb.ConnectionString;
import com.mongodb.client.MongoDatabase;

public class DatabaseConfiguration extends SimpleMongoClientDatabaseFactory {

	  public DatabaseConfiguration(ConnectionString connectionString) {
	    super(connectionString);
	  }

	  @Override
	  protected MongoDatabase doGetMongoDatabase(String dbName) {

	    ConnectionString connectionString = new ConnectionString(ConnectionStorage.getConnection());
	    return super.doGetMongoDatabase(Objects.requireNonNull(connectionString.getDatabase()));
	  }
	}
