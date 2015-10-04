package org.openntf.domino.demoApp.application;

/*

<!--
Copyright 2015 Paul Withers
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and limitations under the License
-->

*/

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.apache.commons.lang3.StringUtils;
import org.openntf.domino.Database;
import org.openntf.domino.Database.FTIndexOption;
import org.openntf.domino.DateRange;
import org.openntf.domino.Document;
import org.openntf.domino.Session;
import org.openntf.domino.View;
import org.openntf.domino.ViewEntry;
import org.openntf.domino.ViewEntryCollection;
import org.openntf.domino.demoAppUtil.SampleDataUtil;
import org.openntf.domino.utils.Factory;
import org.openntf.domino.utils.Factory.SessionType;
import org.openntf.domino.xots.Tasklet;

@Tasklet(session = Tasklet.Session.NATIVE)
public class XotsDatabaseLoader implements Runnable {
	private final String dbPath;
	private final String templatePath;
	private final int userCount;
	private final int disc_rootDocs;
	private final int disc_maxDepth;
	private final String[] firstNames;
	private final String[] lastNames;
	private final String[] cities;
	private final String[] states;
	private final String[] loremIpsum;

	public XotsDatabaseLoader(String dbPath, String templatePath, int userCount, int rootDocs, int disc_MaxDepth, String[] firstNames, String[] lastNames, String[] cities,
			String[] states, String[] loremIpsum) {
		this.dbPath = dbPath;
		this.templatePath = templatePath;
		this.userCount = userCount;
		this.disc_rootDocs = rootDocs;
		this.disc_maxDepth = disc_MaxDepth;
		this.firstNames = firstNames;
		this.lastNames = lastNames;
		this.cities = cities;
		this.states = states;
		this.loremIpsum = loremIpsum;
	}

	@Override
	public void run() {
		try {

			final Session sess = Factory.getSession(SessionType.NATIVE);
			final Database dbTemplate = sess.getDatabase(templatePath);
			final Database dbNew = dbTemplate.createCopy(dbTemplate.getServer(), dbPath);

			createUsers(dbNew);
			createStates(dbNew);
			createDiscussionDocuments(dbNew);
			createAllTypes(dbNew);
			final Set<FTIndexOption> options = new HashSet<FTIndexOption>();
			dbNew.createFTIndex(options, true);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	void createUsers(Database db) throws IOException {
		final View w = db.getView("AllContacts");
		w.getAllEntries().removeAll(true);

		final HashSet<String> users = new HashSet<String>();
		for (int i = 0; i < userCount; i++) {
			while (true) {
				final HashMap<String, String> userData = new HashMap<String, String>();
				final String firstName = firstNames[(int) (Math.random() * firstNames.length)];
				final String lastName = lastNames[(int) (Math.random() * lastNames.length)];
				userData.put("Form", "Contact");
				userData.put("id", "CN=" + firstName + " " + lastName + "/O=renovations");
				userData.put("FirstName", firstName);
				userData.put("LastName", lastName);
				final String fullcity = cities[(int) (Math.random() * cities.length)];
				userData.put("City", SampleDataUtil.cityName(fullcity));
				userData.put("State", SampleDataUtil.cityState(fullcity));
				userData.put("email", createEmail(firstName, lastName, userData.get("City")));

				// If user already there, then reject and continue
				// Else, create it...
				final String nn = lastName + " " + firstName;
				if (users == null || !users.contains(nn)) {
					if (users != null) {
						users.add(nn);
					}
					createUser(db, userData);
					break;
				}
			}
		}
	}

	/**
	 * Create method passing a Map (one of the options added by ODA)
	 *
	 * @param db
	 *            Database to create in
	 * @param fieldVals
	 *            Map of field names / values
	 */
	void createUser(Database db, Map<String, String> fieldVals) {
		final Document doc = db.createDocument(fieldVals);
		doc.save();
	}

	String createEmail(String firstName, String lastName, String city) {
		final StringBuilder b = new StringBuilder();
		for (int i = 0; i < firstName.length(); i++) {
			final char c = Character.toLowerCase(firstName.charAt(i));
			if (c >= 'a' && c <= 'z') {
				b.append(c);
			}
		}
		b.append('_');
		for (int i = 0; i < lastName.length(); i++) {
			final char c = Character.toLowerCase(lastName.charAt(i));
			if (c >= 'a' && c <= 'z') {
				b.append(c);
			}
		}
		b.append("@");
		b.append("renovations.com");
		return b.toString();
	}

	void createStates(Database db) throws IOException {
		final View w = db.getView("AllStates");
		w.getAllEntries().removeAll(true);

		for (final String state : states) {
			final String[] s = StringUtils.split(state, ',');
			createState(db, s[1], s[0]);
		}
	}

	/**
	 * Create method passing multiple parameters in format field, value, field,
	 * value etc
	 *
	 * @param db
	 *            Database to create in
	 * @param key
	 *            String state code
	 * @param name
	 *            String state name
	 */
	void createState(Database db, String key, String name) {
		final Document doc = db.createDocument("Form", "State", "Key", key, "Name", name);
		doc.save();
	}

	void createDiscussionDocuments(Database db) throws IOException {
		// Construct a list of authors
		// As we want the tag cloud to render differences between the authors,
		// we give
		// as different weight to each author by adding it a random # of times
		// in the list
		// We read the author names from the database
		final ArrayList<String> users = new ArrayList<String>();
		final View authorView = db.getView("AllContacts");
		authorView.refresh();
		final int maxAuthors = 15;
		int nAuthor = 0;
		final ViewEntryCollection ec = authorView.getAllEntries();
		for (final ViewEntry e : ec) {
			final Vector<?> values = e.getColumnValues();
			final String name = (String) values.get(6);
			// Add it a random number of times to the list
			final int n = ((int) (Math.random() * maxAuthors)) + 1;
			for (int jj = 0; jj < n; jj++) {
				users.add(name);
			}
			nAuthor++;
			if (nAuthor > maxAuthors) {
				break;
			}
		}
		if (users.size() == 0) {
			// Just in case they were not created...
			users.add("John Doe");
		}

		final View w = db.getView("AllThreads");
		w.getAllEntries().removeAll(true);
		createDiscussionDocument(db, null, users, new int[] { 0 }, disc_rootDocs);
	}

	void createDiscussionDocument(Database db, Document parent, ArrayList<String> users, int[] pos, int nDoc) throws IOException {
		final Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(new Date());
		for (int j = 0; j < nDoc; j++) {
			pos[pos.length - 1] = j + 1;

			final Document doc = db.createDocument();
			doc.replaceItemValue("Form", "Discussion");
			final StringBuilder b = new StringBuilder();
			for (int i = 0; i < pos.length; i++) {
				if (i > 0) {
					b.append("/");
				}
				b.append(pos[i]);
			}
			final int idx = (int) (Math.random() * (loremIpsum.length - 1));
			final String body = loremIpsum[idx];
			int dot = body.indexOf('.');
			if (dot < 0) {
				dot = body.length() - 1;
			}
			int coma = body.indexOf(',');
			if (coma < 0) {
				coma = body.length() - 1;
			}
			final String title = body.substring(0, Math.min(dot, coma));

			// Get a random author
			final int x = Math.min((int) (Math.random() * (users.size())), users.size());
			final String author = users.get(x);

			doc.replaceItemValue("Title", title);
			doc.replaceItemValue("Body", body);
			doc.replaceItemValue("Author", author);
			doc.replaceItemValue("Date", cal.getTime());
			if (parent != null) {
				doc.makeResponse(parent);
			}
			doc.computeWithForm(false, false);
			doc.save();

			if (pos.length < disc_maxDepth) {
				final double r = Math.random();
				if (r <= (1.0 / pos.length)) {
					final int[] newPos = new int[pos.length + 1];
					System.arraycopy(pos, 0, newPos, 0, pos.length);
					final int n = (int) (Math.random() * 5);
					createDiscussionDocument(db, doc, users, newPos, n);
				}
			}
			// Move the date to the day before if requested
			final boolean mvd = Math.random() <= 0.40;
			if (mvd) {
				// Previous day...
				cal.add(Calendar.DATE, -1);
			}
		}
	}

	void createAllTypes(Database db) throws IOException {
		final View w = db.getView("AllTypes");
		w.getAllEntries().removeAll(true);

		for (int i = 1; i < 25; i++) {
			createAllType(db, i);
		}
	}

	void createAllType(Database db, int index) {
		final Session session = db.getParent();
		final String sIndex = Integer.toString(index);
		final Document doc = db.createDocument();
		doc.put("Form", "AllTypes");

		doc.put("fldIcon", index);
		doc.put("fldText", "text_" + sIndex);
		doc.put("fldNumber", index * 100);
		doc.put("fldDate", createDate(2012, Calendar.JANUARY, index));
		doc.put("fldTime", createTime(session, 5, 1, index));
		doc.put("fldDateTime", createDateTime(2011, 2, index, 8, 9, index));
		doc.put("fldDateTimeRange", createDateTimeRange(session, 2012, 3, index, 8, 9, index));
		doc.put("fldDialogList", "dlg_" + sIndex);

		final ArrayList<String> mx = new ArrayList<String>();
		mx.add("text_" + sIndex + "_1");
		mx.add("text_" + sIndex + "_2");
		mx.add("text_" + sIndex + "_3");
		doc.put("fldText2", mx);

		final ArrayList<Object> mn = new ArrayList<Object>();
		mn.add(index * 100 + 1);
		mn.add(index * 100 + 2);
		mn.add(index * 100 + 3);
		doc.put("fldNumber2", mn);

		final ArrayList<Date> md = new ArrayList<Date>();
		md.add(createDate(2010, Calendar.JANUARY, index));
		md.add(createDate(2010, Calendar.FEBRUARY, index));
		md.add(createDate(2010, Calendar.MARCH, index));
		doc.put("fldDate2", md);

		final Vector<Date> mt = new Vector<Date>();
		mt.add(createTime(session, 6, 1, index));
		mt.add(createTime(session, 6, 2, index));
		mt.add(createTime(session, 6, 3, index));
		doc.put("fldTime2", mt);

		final Vector<Date> mdt = new Vector<Date>();
		mdt.add(createDateTime(2011, 1, index, 6, 1, index));
		mdt.add(createDateTime(2011, 2, index, 6, 2, index));
		mdt.add(createDateTime(2011, 3, index, 6, 3, index));
		doc.put("fldDateTime2", mdt);

		if (false) { // DateTime range do not work with multiple values?
			final Vector<DateRange> mrg = new Vector<DateRange>();
			mrg.add(createDateTimeRange(session, 2012, 2, index, 4, 1, index));
			mrg.add(createDateTimeRange(session, 2012, 3, index, 5, 1, index));
			mrg.add(createDateTimeRange(session, 2012, 4, index, 6, 1, index));
			doc.put("fldDateTimeRange2", mrg);
		}

		final ArrayList<Object> mdg = new ArrayList<Object>();
		mdg.add("dlgx_" + sIndex + "_1");
		mdg.add("dlgx_" + sIndex + "_1");
		mdg.add("dlgx_" + sIndex + "_1");
		doc.put("fldDialogList2", mdg);

		doc.save();
	}

	protected Date createDate(int year, int month, int day) {
		final Calendar c1 = GregorianCalendar.getInstance();
		c1.set(year, month, day);
		final Date d = c1.getTime();
		return d;
	}

	protected Date createTime(Session session, int hour, int minute, int second) {
		final Calendar c1 = GregorianCalendar.getInstance();
		c1.set(Calendar.HOUR_OF_DAY, hour);
		c1.set(Calendar.MINUTE, minute);
		c1.set(Calendar.SECOND, second);
		final Date d = c1.getTime();
		return d;
	}

	protected Date createDateTime(int year, int month, int day, int hour, int minute, int second) {
		final Calendar c1 = GregorianCalendar.getInstance();
		c1.set(year, month, day, hour, minute, second);
		final Date d = c1.getTime();
		return d;
	}

	protected DateRange createDateTimeRange(Session session, int year, int month, int day, int hour, int minute, int second) {
		final DateRange r = session.createDateRange(new Date(), new Date());
		r.setStartDateTime(session.createDateTime(createDateTime(year, month, day, hour, minute, second)));
		r.setEndDateTime(session.createDateTime(createDateTime(year + 1, month, day, hour + 1, minute, second)));
		return r;
	}

}
