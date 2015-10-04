package org.openntf.domino.demoAppUtil;

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
		final StringBuilder sb = new StringBuilder();
		final Database db = FactoryUtils.getDemoDatabase();
		final DatabaseTransaction txn = db.startTransaction();
		try {
			boolean toggle = true;
			int count = 0;
			sb.append("Starting update with " + selectedState);
			final View view = db.getView("allStates");
			final Document state = view.getFirstDocumentByKey(selectedState, true);
			state.replaceItemValue("txnTest", new Date());
			sb.append("...Updated State pending committal, value is " + state.get("txnTest").toString());
			final View contacts = db.getView("AllContactsByState");
			final DocumentCollection dc = contacts.getAllDocumentsByKey(selectedState, true);
			for (final Document doc : dc) {
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
		} catch (final Exception e) {
			sb.append("...Rolling back");
			txn.rollback();
		} finally {
			db.closeTransaction();
			return sb.toString();
		}
	}

}
