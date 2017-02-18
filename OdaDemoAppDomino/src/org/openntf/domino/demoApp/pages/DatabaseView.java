package org.openntf.domino.demoApp.pages;

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

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.TreeMap;

import org.openntf.domino.Database;
import org.openntf.domino.demoApp.DemoUI;
import org.openntf.domino.demoApp.components.TargetSelector;
import org.openntf.domino.demoApp.components.TargetSelector.Target;
import org.openntf.domino.demoApp.subpages.database.Database_CompactOptions;
import org.openntf.domino.demoApp.subpages.database.Database_DatabaseOptions;
import org.openntf.domino.demoApp.subpages.database.Database_FTIndex;
import org.openntf.domino.demoApp.subpages.database.Database_FixupOption;
import org.openntf.domino.demoApp.subpages.database.Database_GettingDbs;
import org.openntf.domino.demoApp.subpages.database.Database_GettingDocuments;
import org.openntf.domino.demoApp.subpages.database.Database_Listeners;
import org.openntf.domino.demoApp.subpages.database.Database_Summary;
import org.openntf.domino.demoApp.subpages.database.Database_Transaction;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

public class DatabaseView extends BaseView {
	private static final long serialVersionUID = 1L;
	public static String VIEW_NAME = "Database";
	public static String VIEW_LABEL = "Database";
	private DatabaseSubPage currentPage;
	private SourceCodeType currentSourcePage;
	private Database_Summary summaryDetails = new Database_Summary(this);
	private Database_GettingDbs gettingDbs = new Database_GettingDbs(this);
	private Database_GettingDocuments gettingDocs = new Database_GettingDocuments(this);
	private Database_CompactOptions compactOpts = new Database_CompactOptions(this);
	private Database_FixupOption fixupOpts = new Database_FixupOption(this);
	private Database_DatabaseOptions dbOpts = new Database_DatabaseOptions(this);
	private Database_FTIndex ftIndex = new Database_FTIndex(this);
	private Database_Transaction transactionSummary = new Database_Transaction(this);
	private Database_Listeners listenerSummary = new Database_Listeners(this);
	private Label databaseMethodLabel;
	private Label databaseTransactionMethodLabel;
	private Label databaseListenerMethodLabel;

	private enum SourceCodeType {
		GET_DB, GET_DOC, LISTENER, TRANSACTION;
	}

	public enum DatabaseSubPage {
		SUMMARY_DETAILS("Summary Details", Target.BOTH, SourceCodeType.GET_DB), GETTING_DBS("Getting Databases",
				Target.BOTH, SourceCodeType.GET_DB), GETTING_DOCS("Getting Documents", Target.BOTH,
						SourceCodeType.GET_DOC), FT_INDEXING("Full Text Indexing", Target.BOTH,
								SourceCodeType.GET_DB), COMPACT_OPTIONS("Compact Options", Target.BOTH,
										SourceCodeType.GET_DB), FIXUP_OPTIONS("Fixup Options", Target.BOTH,
												SourceCodeType.GET_DB), DB_OPTIONS("Database Options", Target.BOTH,
														SourceCodeType.GET_DB), LISTENERS("Listeners", Target.BOTH,
																SourceCodeType.LISTENER), TRANSACTION(
																		"Transactional Processing", Target.BOTH,
																		SourceCodeType.TRANSACTION);
		private String value_;
		private Target target_;
		private SourceCodeType sourcePage_;

		private DatabaseSubPage(String subPage, Target target, SourceCodeType sourcePage) {
			value_ = subPage;
			target_ = target;
			sourcePage_ = sourcePage;
		}

		public String getValue() {
			return value_;
		}

		public Target getTarget() {
			return target_;
		}

		public SourceCodeType getSourcePage() {
			return sourcePage_;
		}
	}

	public DatabaseView() {
		setShowNavigation(true);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		super.enter(event);
	}

	@Override
	public void loadContent() {
		loadContent(getCurrentPage());
	}

	public void loadContent(DatabaseSubPage subPage) {
		if (!getCurrentPage().equals(subPage)) {
		}
		switch (subPage) {
		case SUMMARY_DETAILS:
			summaryDetails.load();
			getContentPanel().setContent(summaryDetails);
			getMethodList().setContent(getDatabaseMethodLabel());
			break;
		case GETTING_DBS:
			gettingDbs.load();
			getContentPanel().setContent(gettingDbs);
			getMethodList().setContent(getDatabaseMethodLabel());
			break;
		case GETTING_DOCS:
			gettingDocs.load();
			getContentPanel().setContent(gettingDocs);
			getMethodList().setContent(getDatabaseMethodLabel());
			break;
		case COMPACT_OPTIONS:
			compactOpts.load();
			getContentPanel().setContent(compactOpts);
			getMethodList().setContent(getDatabaseMethodLabel());
			break;
		case FIXUP_OPTIONS:
			fixupOpts.load();
			getContentPanel().setContent(fixupOpts);
			getMethodList().setContent(getDatabaseMethodLabel());
			break;
		case DB_OPTIONS:
			dbOpts.load();
			getContentPanel().setContent(dbOpts);
			getMethodList().setContent(getDatabaseMethodLabel());
			break;
		case FT_INDEXING:
			ftIndex.load();
			getContentPanel().setContent(ftIndex);
			getMethodList().setContent(getDatabaseMethodLabel());
			break;
		case TRANSACTION:
			transactionSummary.load();
			getContentPanel().setContent(transactionSummary);
			getMethodList().setContent(getDatabaseTransactionMethodLabel());
			break;
		case LISTENERS:
			listenerSummary.load();
			getContentPanel().setContent(listenerSummary);
			getMethodList().setContent(getDatabaseListenerMethodLabel());
			break;
		default:
			getContentPanel().setContent(new Label("<b>NO CONTENT SET FOR THIS PAGE</b>", ContentMode.HTML));
		}
		if (!subPage.getSourcePage().equals(getCurrentSourcePage())) {
			switch (subPage.getSourcePage()) {
			case GET_DOC:
				loadGetDocSource();
				break;
			case LISTENER:
				loadListenerSource();
				break;
			case TRANSACTION:
				loadTransactionSource();
				break;
			default:
				loadGetDbSource();
			}
			setCurrentSourcePage(subPage.getSourcePage());
		}
	}

	@Override
	public void loadNavigation() {
		getSubNavigation().removeAllComponents();

		TargetSelector target1 = new TargetSelector(this);
		getSubNavigation().addComponent(target1);
		Target currTarget = DemoUI.get().getAppTarget();

		for (final DatabaseSubPage subPage : DatabaseSubPage.values()) {
			if (Target.BOTH.equals(currTarget) || Target.BOTH.equals(subPage.getTarget())
					|| currTarget.equals(subPage.getTarget())) {
				Button button1 = new Button(subPage.getValue());
				button1.addStyleName(ValoTheme.BUTTON_LINK);
				button1.addStyleName(ValoTheme.BUTTON_SMALL);
				button1.addStyleName("navigation-button");
				button1.addClickListener(new ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						// Reset relevant page
						loadContent(subPage);
					}

				});
				getSubNavigation().addComponents(button1);
			}
		}
	}

	@Override
	public void loadMethodList() {
		Label methLabel = getDatabaseMethodLabel();
		getMethodList().setContent(methLabel);
	}

	private Label getDatabaseMethodLabel() {
		if (null == databaseMethodLabel) {
			setDatabaseMethodLabel();
		}
		return databaseMethodLabel;
	}

	private void setDatabaseMethodLabel() {
		StringBuilder sb = new StringBuilder();
		ArrayList<String> newMethods = new ArrayList<String>();
		for (Method newCrystal : org.openntf.domino.ext.Database.class.getMethods()) {
			newMethods.add(newCrystal.getName() + newCrystal.hashCode());
		}
		TreeMap<String, String> methSummary = new TreeMap<String, String>();
		for (Method crystal : Database.class.getMethods()) {
			methSummary.put(crystal.getName() + crystal.hashCode(), getMethodSummary(newMethods, crystal));
		}
		for (String content : methSummary.values()) {
			sb.append(content);
		}
		databaseMethodLabel = new Label(sb.toString(), ContentMode.HTML);
	}

	private Label getDatabaseTransactionMethodLabel() {
		if (null == databaseTransactionMethodLabel) {
			setDatabaseTransactionMethodLabel();
		}
		return databaseTransactionMethodLabel;
	}

	public void setDatabaseTransactionMethodLabel() {
		StringBuilder sb = new StringBuilder();
		ArrayList<String> newMethods = new ArrayList<String>();
		for (Method newCrystal : org.openntf.domino.transactions.DatabaseTransaction.class.getMethods()) {
			newMethods.add(newCrystal.getName() + newCrystal.hashCode());
		}
		TreeMap<String, String> methSummary = new TreeMap<String, String>();
		for (Method crystal : org.openntf.domino.transactions.DatabaseTransaction.class.getMethods()) {
			methSummary.put(crystal.getName() + crystal.hashCode(), getMethodSummary(newMethods, crystal));
		}
		for (String content : methSummary.values()) {
			sb.append(content);
		}
		databaseTransactionMethodLabel = new Label(sb.toString(), ContentMode.HTML);
	}

	public Label getDatabaseListenerMethodLabel() {
		if (null == databaseListenerMethodLabel) {
			setDatabaseListenerMethodLabel();
		}
		return databaseListenerMethodLabel;
	}

	public void setDatabaseListenerMethodLabel() {
		StringBuilder sb = new StringBuilder();
		sb.append("<h3>IDominoEvent</h3>");
		ArrayList<String> newMethods = new ArrayList<String>();
		for (Method newCrystal : org.openntf.domino.events.IDominoEvent.class.getMethods()) {
			newMethods.add(newCrystal.getName() + newCrystal.hashCode());
		}
		TreeMap<String, String> methSummary = new TreeMap<String, String>();
		for (Method crystal : org.openntf.domino.events.IDominoEvent.class.getMethods()) {
			methSummary.put(crystal.getName() + crystal.hashCode(), getMethodSummary(newMethods, crystal));
		}
		for (String content : methSummary.values()) {
			sb.append(content);
		}
		sb.append("<br/><h3>IDominoListener</h3>");
		newMethods = new ArrayList<String>();
		for (Method newCrystal : org.openntf.domino.events.IDominoListener.class.getMethods()) {
			newMethods.add(newCrystal.getName());
		}
		methSummary = new TreeMap<String, String>();
		for (Method crystal : org.openntf.domino.events.IDominoListener.class.getMethods()) {
			methSummary.put(crystal.getName(), getMethodSummary(newMethods, crystal));
		}
		for (String content : methSummary.values()) {
			sb.append(content);
		}
		databaseListenerMethodLabel = new Label(sb.toString(), ContentMode.HTML);
	}

	@Override
	public void loadSource() {
		loadGetDbSource();
	}

	public void loadGetDbSource() {
		loadSimpleSource("getDb");
	}

	public void loadGetDocSource() {
		loadSimpleSource("getDoc");
	}

	public void loadListenerSource() {
		loadSimpleSource("listener");
	}

	public void loadTransactionSource() {
		loadSimpleSource("transaction");
	}

	public DatabaseSubPage getCurrentPage() {
		if (null == currentPage) {
			setCurrentPage(DatabaseSubPage.SUMMARY_DETAILS);
		}
		return currentPage;
	}

	public void setCurrentPage(DatabaseSubPage currentPage) {
		this.currentPage = currentPage;
	}

	public SourceCodeType getCurrentSourcePage() {
		if (null == currentSourcePage) {
			setCurrentSourcePage(SourceCodeType.GET_DB);
		}
		return currentSourcePage;
	}

	public void setCurrentSourcePage(SourceCodeType currentSourcePage) {
		this.currentSourcePage = currentSourcePage;
	}

}
