package com.codingworld.multitenant.interceptor;

public class ConnectionStorage {

	private ConnectionStorage() {

	}

	private static ThreadLocal<String> currentTenant = new InheritableThreadLocal<>();

	public static void clear() {
		currentTenant.remove();
	}

	public static String getConnection() {
		return currentTenant.get();
	}

	public static void setConnection(final String connectionString) {
		currentTenant.set(connectionString);
	}
}
