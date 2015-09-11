package org.openntf.domino.demoAppUtil;

import java.util.Date;

import org.openntf.domino.Database;
import org.openntf.domino.Session;
import org.openntf.domino.utils.Factory;
import org.openntf.domino.utils.Factory.SessionType;

import com.vaadin.server.VaadinServlet;

public class FactoryUtils {

	public static Session getUserSession() {
		return Factory.getSession(SessionType.CURRENT);
	}

	public static Database getDemoTemplate() {
		Database retVal_ = null;
		try {
			Session userSess = getUserSession();
			String filePath = getDemoTemplateFilepath();
			retVal_ = userSess.getDatabase(userSess.getServerName(), filePath, false);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return retVal_;
	}

	public static String getDemoTemplateFilepath() {
		return VaadinServlet.getCurrent().getServletContext().getInitParameter("org.openntf.domino.extlib.filepath");
	}

	public static String getDemoDatabasesFolder() {
		return VaadinServlet.getCurrent().getServletContext().getInitParameter("org.openntf.domino.demoApp.folder");
	}

	public static Database getDemoDatabase() {
		return getDemoDatabase(1);
	}

	public static Database getDemoDatabase(Integer idex) {
		Database retVal_ = null;
		try {
			if (null == idex) {
				idex = 1;
			}
			Session userSess = getUserSession();
			String filePath = getDemoDatabasesFolder();
			retVal_ = userSess.getDatabase(userSess.getServerName(), filePath + "/oda_" + idex.toString() + ".nsf", false);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return retVal_;
	}

	public static String getNumberOfDemos() {
		return VaadinServlet.getCurrent().getServletContext().getInitParameter("org.openntf.domino.demoApp.instances");
	}

	public static String dumpConfigSettings() {
		String demoCount = getNumberOfDemos();
		StringBuilder s = new StringBuilder();
		Date dt = new Date();
		s.append("Configuration loaded at " + dt.toString());
		s.append("<br/>Configured to look for Extension Library demo database at " + getDemoTemplateFilepath());
		s.append("<br/>\nConfigured to create ODA Demo databases in folder " + getDemoDatabasesFolder());
		s.append("<br/>\nConfigured to work with " + demoCount + " ODA Demo instances.");

		int numberOfDemos = Integer.parseInt(demoCount);
		for (Integer i = 1; i <= numberOfDemos; i++) {
			int count = 0;
			try {
				count = getDemoDatabase(i).getAllDocuments().getCount();
			} catch (Exception e) {
				// TODO: handle exception
			}
			s.append("<br/>ODA_" + i.toString() + ": " + Integer.toString(count) + " documents");
		}

		return s.toString();
	}

	public static String addCodeString(String content) {
		return "<span class=\"domino-code\">" + content + "</span>";
	}

}
