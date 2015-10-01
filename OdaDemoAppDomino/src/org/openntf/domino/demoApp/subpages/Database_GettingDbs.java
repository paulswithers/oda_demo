package org.openntf.domino.demoApp.subpages;

import org.openntf.domino.demoApp.components.Html_Separator;
import org.openntf.domino.demoApp.components.Html_Separator.SeparatorType;
import org.openntf.domino.demoApp.pages.BaseView;
import org.openntf.domino.demoAppUtil.FactoryUtils;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;

public class Database_GettingDbs extends BaseSubPage {
	private static final long serialVersionUID = 1L;

	public Database_GettingDbs(BaseView parentView) {
		super(parentView);
	}

	public void loadContent() {
		Label label1 = new Label("In the core Domino API, accessing a database is done via a variety of methods "
				+ "both in the Session class and the Database class: <ul><li>Session.getCurrentDatabase()</li>"
				+ "<li>Session.getDatabase(String, String)</li><li>Session.getCurrentDatabase(String, String, boolean)</li>"
				+ "<li>Session.getCurrentDatabase()</li><li>DbDirectory.openDatabase(String)</li>"
				+ "<li>DbDirectory.openDatatbase(String, boolean)</li><li>DbDirectory.openDatabaseByReplicaID()</li>"
				+ "<li>DbDirectory.openDatabaseIfModified(String,Date)</li><li>DbDirectory.openDatabaseIfModified(String,DateTime)</li>"
				+ "<li>DbDirectory.openMailDatabase()</li><li>Database.open()</li><li>Database.openByReplicaID()</li>"
				+ "<li>Database.openIfModified()</li><li>Database.openWithFailover()</li><ul><br/>");
		label1.setContentMode(ContentMode.HTML);
		Label label2 = new Label("This means code needs to call different methods depending on whether a filepath or replica id is provided. "
				+ "<br/>XPages added a different format for retrieving the database, " + FactoryUtils.addCodeString("ServerName!!Database")
				+ " but no programmatic API." + "<br/>There are also gotchas like "
				+ FactoryUtils.addCodeString("Session.getDatabase(String, String)")
				+ " does not return null if the database does not exist. Instead it returns a valid " + FactoryUtils.addCodeString("Database")
				+ " object and the developer needs to check " + FactoryUtils.addCodeString("Database.isOpen()") + ". Best practice then is to use "
				+ FactoryUtils.addCodeString("Session.getDatabase(String, String, false)") + " which will return null."
				+ " object but one that is not open.<br/>Opening a user's mail database also requires getting the DbDirectory object "
				+ "from the Session and calling the " + FactoryUtils.addCodeString("openMailDatabase()") + " method.");
		label2.setContentMode(ContentMode.HTML);
		Label label3 = new Label("<br/>While ODA keeps the various methods, we also provide a single "
				+ FactoryUtils.addCodeString("Session.getDatabase()") + " method that can take a database path, "
				+ "server and database path, replica id, server and replica id or API path. If the method fails, "
				+ "the return object will be null, giving more standard Java coding.<br/>"
				+ "Methods have also been added to the Session class to get the mail database, get databases with failover or if modified "
				+ "since a given date, thus allowing the Session to be used as the single entry point for retrieving any database.");
		label3.setContentMode(ContentMode.HTML);
		addComponents(label1, label2, label3, new Html_Separator(SeparatorType.NEW_LINE));
	}

}
