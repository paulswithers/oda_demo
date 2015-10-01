package org.openntf.domino.demoAppUtil;

import java.util.Date;

import org.openntf.domino.Database;
import org.openntf.domino.Document;
import org.openntf.domino.DocumentCollection;
import org.openntf.domino.View;
import org.openntf.domino.transactions.DatabaseTransaction;

public class DatabaseUtils {

	public DatabaseUtils() {

	}

	public static String transactionTest(String selectedState, boolean successOrFail) {
		StringBuilder sb = new StringBuilder();
		Database db = FactoryUtils.getDemoDatabase();
		DatabaseTransaction txn = db.startTransaction();
		try {
			boolean toggle = true;
			int count = 0;
			sb.append("Starting update with " + selectedState);
			View view = db.getView("allStates");
			Document state = view.getFirstDocumentByKey(selectedState, true);
			state.replaceItemValue("txnTest", new Date());
			sb.append("...Updated State pending committal, value is " + state.get("txnTest").toString());
			View contacts = db.getView("AllContactsByState");
			DocumentCollection dc = contacts.getAllDocumentsByKey(selectedState, true);
			for (Document doc : dc) {
				if (toggle) {
					doc.replaceItemValue("txnTest", new Date());
					count += 1;
				}
				toggle = !toggle;
			}
			sb.append("...Updated " + Integer.toString(count) + " Contacts pending committal.");
			if (successOrFail) {
				txn.commit();
				sb.append("...Committed");
			} else {
				throw new Exception("Now roll back");
			}
		} catch (Exception e) {
			sb.append("...Rolling back");
			txn.rollback();
		} finally {
			db.closeTransaction();
			return sb.toString();
		}
	}

}
