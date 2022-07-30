package com.codingworld.multitenant.interceptor;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class TenantFilter extends OncePerRequestFilter {

	private static final String TENANT_HEADER = "X-Tenant";
	private static final String CONNECTION_STRING = "mongodb://localhost:27017/TENANT?readPreference=primary";
	private static final String TENANT_REPLACEMENT = "TENANT";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String tenantHeader = request.getHeader(TENANT_HEADER);
		if (tenantHeader == null || tenantHeader.trim().isEmpty()) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		} else {

			String dbConnectionString = CONNECTION_STRING.replace(TENANT_REPLACEMENT, tenantHeader);
			ConnectionStorage.setConnection(dbConnectionString);
			filterChain.doFilter(request, response);
			ConnectionStorage.clear();
		}
	}

}
