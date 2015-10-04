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

import org.apache.commons.lang3.StringUtils;
import org.openntf.domino.Database;
import org.openntf.domino.Session;
import org.openntf.domino.demoApp.DemoUI;
import org.openntf.domino.demoApp.application.XotsDatabaseLoader;
import org.openntf.domino.demoApp.components.TargetSelector;
import org.openntf.domino.demoApp.components.TargetSelector.Target;
import org.openntf.domino.demoApp.subpages.Session_Factory;
import org.openntf.domino.demoApp.subpages.Session_Fixes;
import org.openntf.domino.demoApp.subpages.Session_Summary;
import org.openntf.domino.demoApp.subpages.Session_XspProperties;
import org.openntf.domino.demoAppUtil.FactoryUtils;
import org.openntf.domino.demoAppUtil.SampleDataUtil;
import org.openntf.domino.xots.Xots;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class SessionView extends BaseView {
	private static final long serialVersionUID = 1L;
	public static final String VIEW_NAME = ""; // Default view name is ""
	public static final String VIEW_LABEL = "Session";
	private boolean showSetupButton = false;
	private SessionSubPage currentPage;
	private Session_Summary summaryDetails = new Session_Summary(this);
	private Session_Fixes fixesDetails = new Session_Fixes(this);
	private Session_Factory factorySessionDetails = new Session_Factory(this);
	private Session_XspProperties xspPropertyDetails = new Session_XspProperties(this);

	public enum SessionSubPage {
		SUMMARY_DETAILS("Summary Details", Target.BOTH), FIXES("Fixes", Target.NON_XPAGES), XSP_PROPS("Xsp Properties",
				Target.XPAGES), FACTORY_SESSION("Getting Sessions", Target.BOTH);
		private String value_;
		private Target target_;

		private SessionSubPage(String subPage, Target target) {
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

	public SessionView() {
		setShowNavigation(true);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		super.enter(event);
	}

	public void checkIsSetup() {
		String setupErrors = "";
		Database demoDb = FactoryUtils.getDemoTemplate();
		if (null == demoDb) {
			setupErrors = String.format(
					"Please amend the XPages Extension Library Demo database filepath in the application's web.xml.\nAlternatively create an instance of Extension Library Demo Database at '%s'\n",
					FactoryUtils.getDemoTemplateFilepath());
		}
		if (null == FactoryUtils.getDemoDatabase()) {
			setupErrors = setupErrors + "Demo databases have not been initialised yet.\nPlease generate using setup button";
			setShowSetupButton(true);
		}
		if (StringUtils.isNotEmpty(setupErrors)) {
			DemoUI.get().setSetup(false);
			showError(setupErrors);
		} else {
			DemoUI.get().setSetup(true);
			Notification.show("Database set up and ready to go!", Notification.Type.HUMANIZED_MESSAGE);
		}
	}

	@Override
	public void loadContent() {
		loadContent(getCurrentPage());
	}

	public void loadContent(SessionSubPage subPage) {
		if (!getCurrentPage().equals(subPage)) {
			if (!subPage.equals(SessionSubPage.FACTORY_SESSION)) {
				// This has XPages / Non-XPages content, so need to reload
				setCurrentPage(subPage);
			}
		}
		switch (subPage) {
		case SUMMARY_DETAILS:
			summaryDetails.load();
			getContentPanel().setContent(summaryDetails);
			break;
		case FIXES:
			fixesDetails.load();
			getContentPanel().setContent(fixesDetails);
			break;
		case FACTORY_SESSION:
			factorySessionDetails.loadContent(); // we want to reload
			getContentPanel().setContent(factorySessionDetails);
			break;
		case XSP_PROPS:
			xspPropertyDetails.load();
			getContentPanel().setContent(xspPropertyDetails);
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

		for (final SessionSubPage subPage : SessionSubPage.values()) {
			if (Target.BOTH.equals(currTarget) || Target.BOTH.equals(subPage.getTarget()) || currTarget.equals(subPage.getTarget())) {
				Button button1 = new Button(subPage.getValue());
				button1.addStyleName(ValoTheme.BUTTON_LINK);
				button1.addStyleName(ValoTheme.BUTTON_SMALL);
				button1.addStyleName("navigation-button");
				button1.addClickListener(new ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
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
		for (Method newCrystal : org.openntf.domino.ext.Session.class.getMethods()) {
			newMethods.add(newCrystal.getName());
		}
		TreeMap<String, String> methSummary = new TreeMap<String, String>();
		for (Method crystal : Session.class.getMethods()) {
			methSummary.put(crystal.getName(), getMethodSummary(newMethods, crystal));
		}
		for (String content : methSummary.values()) {
			sb.append(content);
		}
		Label methLabel = new Label(sb.toString(), ContentMode.HTML);
		getMethodList().setContent(methLabel);
	}

	@Override
	public String getMethodSummary(ArrayList<String> newMethods, Method crystal) {
		return super.getMethodSummary(newMethods, crystal);
	}

	@Override
	public void loadSource() {
		return;
	}

	public SessionSubPage getCurrentPage() {
		if (null == currentPage) {
			setCurrentPage(SessionSubPage.SUMMARY_DETAILS);
		}
		return currentPage;
	}

	public void setCurrentPage(SessionSubPage currentPage) {
		this.currentPage = currentPage;
	}

	public boolean isShowSetupButton() {
		return showSetupButton;
	}

	public void setShowSetupButton(boolean showSetupButton) {
		this.showSetupButton = showSetupButton;
	}

	public void checkLoadSetupButton(VerticalLayout gridBody) {
		if (isShowSetupButton()) {
			Button setupButton = new Button("Set Up Demo Databases");
			setupButton.setIcon(FontAwesome.DATABASE);
			setupButton.addClickListener(new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					loadDatabases();
				}

			});
			gridBody.addComponent(setupButton);
		}
	}

	public void loadDatabases() {
		try {
			int numberOfDemos = FactoryUtils.getNumberOfDemosAsInt();
			String templatePath = FactoryUtils.getDemoTemplateFilepath();
			String demoDbFolder = FactoryUtils.getDemoDatabasesFolder();
			String[] firstNames = SampleDataUtil.readFirstNames();
			String[] lastNames = SampleDataUtil.readLastNames();
			String[] cities = SampleDataUtil.readCities();
			String[] states = SampleDataUtil.readStates();
			String[] loremIpsum = SampleDataUtil.readLoremIpsum();
			for (Integer i = 1; i <= numberOfDemos; i++) {
				String dbPath = demoDbFolder + "/oda_" + i.toString() + ".nsf";
				XotsDatabaseLoader dbInitialiser = new XotsDatabaseLoader(dbPath, templatePath, 20000, 1000, 5, firstNames, lastNames, cities, states,
						loremIpsum);
				Xots.getService().submit(dbInitialiser);
			}
			Notification.show(
					"Loading databases...use refresh link in 'Configuration Settings' panel to check progress.\nOnce complete, refresh the page.",
					Notification.Type.WARNING_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
