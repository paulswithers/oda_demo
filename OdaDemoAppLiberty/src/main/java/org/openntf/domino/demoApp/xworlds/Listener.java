package org.openntf.domino.demoApp.xworlds;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

import org.openntf.xworlds.appservers.webapp.XWorldsApplicationListener;

@WebListener
public class Listener extends XWorldsApplicationListener {

	@Override
	public void contextInitialized(ServletContextEvent appEvent) {
		super.contextInitialized(appEvent);
	}

	@Override
	public void contextDestroyed(ServletContextEvent appEvent) {
		super.contextDestroyed(appEvent);
	}
	
}
