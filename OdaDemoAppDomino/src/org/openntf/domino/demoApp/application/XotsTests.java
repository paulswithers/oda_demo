package org.openntf.domino.demoApp.application;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentSkipListSet;

import org.apache.commons.lang3.StringUtils;
import org.openntf.domino.Database;
import org.openntf.domino.View;
import org.openntf.domino.ViewEntry;
import org.openntf.domino.ViewEntryCollection;
import org.openntf.domino.ViewNavigator;
import org.openntf.domino.utils.Factory;
import org.openntf.domino.utils.Factory.SessionType;
import org.openntf.domino.xots.Tasklet;

import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

public class XotsTests {

	@Tasklet(session = Tasklet.Session.CLONE)
	public static class SessionCallable implements Callable<String> {

		public SessionCallable() {

		}

		public String call() {
			try {
				String name = Factory.getSession(SessionType.CURRENT).getEffectiveUserName();
				return name;
			} catch (Throwable t) {
				t.printStackTrace();
				return t.getMessage();
			}
		}
	}

	@Tasklet(session = Tasklet.Session.CLONE)
	public static class StateUserSummary implements Callable<TreeMap<String, Integer>> {
		private String dbPath;
		private Integer dbNo;
		private TreeMap<String, String> states;

		public StateUserSummary(Integer dbNo, String dbPath, TreeMap<String, String> states) {
			super();
			this.dbNo = dbNo;
			this.states = states;
			this.dbPath = dbPath;
		}

		public TreeMap<String, Integer> call() {
			try {
				TreeMap<String, Integer> results = new TreeMap<String, Integer>();
				// Initialise to zero
				results.put("dbNo", dbNo);
				for (String stateKey : states.keySet()) {
					results.put(stateKey, 0);
				}

				Database dataDb = Factory.getSession(SessionType.CURRENT).getDatabase(dbPath);
				View people = dataDb.getView("AllContactsByState");
				ViewNavigator nav = people.createViewNav();
				ViewEntry cat = nav.getFirst();
				cat = nav.getNextSibling(cat);
				ViewEntry lastEntInCat;
				while (null != cat) {
					lastEntInCat = nav.getPrev(cat);
					String st = (String) lastEntInCat.getColumnValues().get(0);
					String pos = (String) lastEntInCat.getPosition();
					Integer count = Integer.parseInt(StringUtils.substringAfter(pos, "."));
					results.put(st, count);
					cat = nav.getNextSibling(cat);
				}
				// We won't have got the last category!
				lastEntInCat = nav.getLast();
				String st = (String) lastEntInCat.getColumnValues().get(0);
				String pos = (String) lastEntInCat.getPosition();
				Integer count = Integer.parseInt(StringUtils.substringAfter(pos, "."));
				results.put(st, count);
				return results;
			} catch (Throwable t) {
				t.printStackTrace();
				return null;
			}
		}
	}

	@Tasklet(session = Tasklet.Session.NATIVE)
	public static class UserMergeView implements Runnable {
		private String dbPath;
		private String stateCode;
		private ConcurrentSkipListSet<ContactSummary> contacts;
		private ConcurrentSkipListSet<String> completeThreads;
		private UI currUi;

		public UserMergeView(String dbPath, String stateKey, ConcurrentSkipListSet<ContactSummary> contacts,
				ConcurrentSkipListSet<String> completeThreads, UI currUi) {
			this.dbPath = dbPath;
			stateCode = stateKey;
			this.contacts = contacts;
			this.completeThreads = completeThreads;
			this.currUi = currUi;
		}

		@Override
		public void run() {
			try {
				Database dataDb = Factory.getSession(SessionType.NATIVE).getDatabase(dbPath);
				String dbName = StringUtils.substringAfterLast(dbPath, "/");
				View people = dataDb.getView("AllContactsByState");
				ViewEntryCollection coll = people.getAllEntriesByKey(stateCode, true);
				ViewEntry ent = coll.getFirstEntry();
				while (null != ent) {
					Map<String, Object> vals = ent.getColumnValuesMap();
					ContactSummary contact = new ContactSummary();
					contact.setMetaversalId(ent.getDocument().getMetaversalID());
					contact.setFirstName((String) vals.get("FirstName"));
					contact.setLastName((String) vals.get("LastName"));
					contact.setEmail((String) vals.get("EMail"));
					contact.setCity((String) vals.get("City"));
					contact.setState((String) vals.get("State"));
					contact.setDbName(dbName);
					contacts.add(contact);
					ent = coll.getNextEntry();
				}
				currUi.access(new Runnable() {

					@Override
					public void run() {
						Notification msg = new Notification("Completed loading content from " + dbPath, Notification.Type.TRAY_NOTIFICATION);
						msg.show(currUi.getPage());
					}

				});
			} catch (Exception e) {
				e.printStackTrace();
				Notification msg = new Notification(e.getMessage());
				msg.show(currUi.getPage());
			} finally {
				completeThreads.add(dbPath);
			}
		}
	}

}
