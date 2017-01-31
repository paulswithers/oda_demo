package org.openntf.domino.demoApp.xworlds;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.openntf.xworlds.appservers.webapp.XWorldsRequestsFilter;

@WebFilter(urlPatterns = "/*", asyncSupported = true, filterName = "XWorldsRequestsFilter")
public class Filter extends XWorldsRequestsFilter {

	public Filter() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		super.doFilter(request, response, chain);
	}

}