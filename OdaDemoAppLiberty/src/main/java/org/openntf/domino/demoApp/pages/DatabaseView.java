package org.openntf.domino.demoApp.pages;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.TreeMap;

import org.openntf.domino.Database;
import org.openntf.domino.demoApp.DemoUI;
import org.openntf.domino.demoApp.components.TargetSelector;
import org.openntf.domino.demoApp.components.TargetSelector.Target;
import org.openntf.domino.demoApp.subpages.Database_CompactOptions;
import org.openntf.domino.demoApp.subpages.Database_DatabaseOptions;
import org.openntf.domino.demoApp.subpages.Database_FixupOption;
import org.openntf.domino.demoApp.subpages.Database_GettingDbs;
import org.openntf.domino.demoApp.subpages.Database_Summary;

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
	private Database_Summary summaryDetails = new Database_Summary(this);
	private Database_GettingDbs gettingDbs = new Database_GettingDbs(this);
	private Database_CompactOptions compactOpts = new Database_CompactOptions(this);
	private Database_FixupOption fixupOpts = new Database_FixupOption(this);
	private Database_DatabaseOptions dbOpts = new Database_DatabaseOptions(this);

	public enum DatabaseSubPage {
		SUMMARY_DETAILS("Summary Details", Target.BOTH), GETTING_DBS("Getting Databases", Target.BOTH), GETTING_DOCS("Getting Documents",
				Target.BOTH), FT_INDEXING("Full Text Indexing", Target.BOTH), COMPACT_OPTIONS("Compact Options",
						Target.BOTH), FIXUP_OPTIONS("Fixup Options", Target.BOTH), DB_OPTIONS("Database Options", Target.BOTH), LISTENERS("Listeners",
								Target.BOTH), TRANSACTION("Transactional Processing", Target.BOTH);
		private String value_;
		private Target target_;

		private DatabaseSubPage(String subPage, Target target) {
			value_ = subPage;
			target_ = target;
		}

		public String getValue() {
			return value_;
		}

		public Target getTarget() {
			return target_;
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
			break;
		case GETTING_DBS:
			gettingDbs.load();
			getContentPanel().setContent(gettingDbs);
			break;
		case COMPACT_OPTIONS:
			compactOpts.load();
			getContentPanel().setContent(compactOpts);
			break;
		case FIXUP_OPTIONS:
			fixupOpts.load();
			getContentPanel().setContent(fixupOpts);
			break;
		case DB_OPTIONS:
			dbOpts.load();
			getContentPanel().setContent(dbOpts);
			break;
		default:
			getContentPanel().setContent(new Label("<b>NO CONTENT SET FOR THIS PAGE</b>", ContentMode.HTML));
		}
	}

	@Override
	public void loadNavigation() {
		getSubNavigation().removeAllComponents();

		TargetSelector target1 = new TargetSelector(this);
		getSubNavigation().addComponent(target1);
		Target currTarget = DemoUI.get().getAppTarget();

		for (final DatabaseSubPage subPage : DatabaseSubPage.values()) {
			if (Target.BOTH.equals(currTarget) || Target.BOTH.equals(subPage.getTarget()) || currTarget.equals(subPage.getTarget())) {
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
		StringBuilder sb = new StringBuilder();
		ArrayList<String> newMethods = new ArrayList<String>();
		for (Method newCrystal : org.openntf.domino.ext.Database.class.getMethods()) {
			newMethods.add(newCrystal.getName());
		}
		TreeMap<String, String> methSummary = new TreeMap<String, String>();
		for (Method crystal : Database.class.getMethods()) {
			methSummary.put(crystal.getName(), getMethodSummary(newMethods, crystal));
		}
		for (String content : methSummary.values()) {
			sb.append(content);
		}
		Label methLabel = new Label(sb.toString(), ContentMode.HTML);
		getMethodList().addComponent(methLabel);
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

}
