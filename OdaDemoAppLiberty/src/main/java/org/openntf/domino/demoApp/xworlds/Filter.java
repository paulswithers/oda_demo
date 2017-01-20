package org.openntf.domino.demoApp.xworlds;

import javax.servlet.annotation.WebFilter;

import org.openntf.xworlds.appservers.webapp.XWorldsRequestsFilter;

@WebFilter(urlPatterns = "/*", asyncSupported = true, filterName = "XWorldsRequestsFilter")
public class Filter extends XWorldsRequestsFilter {

	public Filter() {

	}

}