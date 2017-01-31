package org.openntf.domino.demoApp.pages;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.TreeMap;

import org.openntf.domino.View;
import org.openntf.domino.demoApp.DemoUI;
import org.openntf.domino.demoApp.components.TargetSelector;
import org.openntf.domino.demoApp.components.TargetSelector.Target;
import org.openntf.domino.demoApp.subpages.view.View_GetEntries;
import org.openntf.domino.demoApp.subpages.view.View_IndexFlags;
import org.openntf.domino.demoApp.subpages.view.View_Summary;
import org.openntf.domino.demoApp.subpages.view.View_TimeSensitive;
import org.openntf.domino.demoApp.subpages.view.View_Unique;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

public class ViewView extends BaseView {
	private static final long serialVersionUID = 1L;
	public static String VIEW_NAME = "View";
	public static String VIEW_LABEL = "View";
	private ViewSubPage currentPage;
	private View_Summary summaryDetails = new View_Summary(this);
	private View_GetEntries getDetails = new View_GetEntries(this);
	private View_Unique uniqueDetails = new View_Unique(this);
	private View_TimeSensitive timeDetails = new View_TimeSensitive(this);
	private View_IndexFlags indexDetails = new View_IndexFlags(this);
	private Label viewMethodLabel;

	public enum ViewSubPage {
		SUMMARY_DETAILS("Summary Details", Target.BOTH), GET_ENTRIES_DOCUMENTS("Getting Entries/Documents",
				Target.BOTH), UNIQUE("Is Unique", Target.BOTH), TIME_SENSITIVE("Is Time Sensitive",
						Target.BOTH), INDEX_FLAGS("Index Flags", Target.BOTH);

		private String value_;
		private Target target_;

		private ViewSubPage(String subPage, Target target) {
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

	public ViewView() {
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

	public void loadContent(ViewSubPage subPage) {
		if (!getCurrentPage().equals(subPage)) {
		}
		switch (subPage) {
		case SUMMARY_DETAILS:
			summaryDetails.load();
			getContentPanel().setContent(summaryDetails);
			break;
		case GET_ENTRIES_DOCUMENTS:
			getDetails.load();
			getContentPanel().setContent(getDetails);
			break;
		case UNIQUE:
			uniqueDetails.load();
			getContentPanel().setContent(uniqueDetails);
			break;
		case TIME_SENSITIVE:
			timeDetails.load();
			getContentPanel().setContent(timeDetails);
			break;
		case INDEX_FLAGS:
			indexDetails.load();
			getContentPanel().setContent(indexDetails);
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

		for (final ViewSubPage subPage : ViewSubPage.values()) {
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
		Label methLabel = getViewMethodLabel();
		getMethodList().setContent(methLabel);
	}

	public Label getViewMethodLabel() {
		if (null == viewMethodLabel) {
			setViewMethodLabel();
		}
		return viewMethodLabel;
	}

	public void setViewMethodLabel() {
		StringBuilder sb = new StringBuilder();
		ArrayList<String> newMethods = new ArrayList<String>();
		for (Method newCrystal : org.openntf.domino.ext.View.class.getMethods()) {
			newMethods.add(newCrystal.getName());
		}
		TreeMap<String, String> methSummary = new TreeMap<String, String>();
		for (Method crystal : View.class.getMethods()) {
			methSummary.put(crystal.getName(), getMethodSummary(newMethods, crystal));
		}
		for (String content : methSummary.values()) {
			sb.append(content);
		}
		viewMethodLabel = new Label(sb.toString(), ContentMode.HTML);
	}

	@Override
	public void loadSource() {
		return;
	}

	public ViewSubPage getCurrentPage() {
		if (null == currentPage) {
			setCurrentPage(ViewSubPage.SUMMARY_DETAILS);
		}
		return currentPage;
	}

	public void setCurrentPage(ViewSubPage currentPage) {
		this.currentPage = currentPage;
	}
}
