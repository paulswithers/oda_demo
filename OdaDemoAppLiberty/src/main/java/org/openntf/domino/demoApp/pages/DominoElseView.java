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

import org.openntf.domino.ColorObject;
import org.openntf.domino.DateTime;
import org.openntf.domino.DocumentCollection;
import org.openntf.domino.Form;
import org.openntf.domino.ViewEntry;
import org.openntf.domino.ViewEntryCollection;
import org.openntf.domino.demoApp.DemoUI;
import org.openntf.domino.demoApp.components.TargetSelector;
import org.openntf.domino.demoApp.components.TargetSelector.Target;
import org.openntf.domino.demoApp.subpages.elses.Else_DateTime;
import org.openntf.domino.demoApp.subpages.elses.Else_DocumentSorter;
import org.openntf.domino.demoApp.subpages.elses.Else_Email;
import org.openntf.domino.demoApp.subpages.elses.Else_OpenLog;
import org.openntf.domino.demoApp.subpages.elses.Else_Summary;
import org.openntf.domino.email.DominoEmail;
import org.openntf.domino.helpers.DocumentSorter;
import org.openntf.domino.logging.BaseOpenLogItem;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

public class DominoElseView extends BaseView {
	private static final long serialVersionUID = 1L;
	public static String VIEW_NAME = "Other";
	public static String VIEW_LABEL = "Other";
	private ElseSubPage currentPage;
	private SourceCodeType currentSourcePage;
	private MethodType currentMethodPage;
	private Else_DateTime dateTime = new Else_DateTime(this);
	private Else_Summary summary = new Else_Summary(this);
	private Else_OpenLog openLog = new Else_OpenLog(this);
	private Else_DocumentSorter sorter = new Else_DocumentSorter(this);
	private Else_Email email = new Else_Email(this);
	private Label elseMethodLabel;

	private enum SourceCodeType {
		SUMMARY, DATE, OPENLOG, SORTER, EMAIL;
	}

	public enum MethodType {
		DATE("DateTime"), DOCUMENT_COLLECTION("DocumentCollection"), VIEW_ENTRY_COLLECTION(
				"ViewEntryCollection"), VIEW_ENTRY("ViewEntry"), FORM("Form"), COLOR_OBJECT(
						"ColorObject"), OPENLOG("OpenLog"), SORTER("DocumentSorter"), EMAIL("Email");

		private String value_;

		private MethodType(String value) {
			value_ = value;
		}

		public String getValue() {
			return value_;
		}
	}

	public enum ElseSubPage {
		SUMMARY_PAGE("Summary", Target.BOTH, SourceCodeType.SUMMARY, MethodType.DOCUMENT_COLLECTION), DATES_PAGE(
				"DateTimes", Target.BOTH, SourceCodeType.DATE,
				MethodType.DATE), OPENLOG("OpenLog", Target.BOTH, SourceCodeType.OPENLOG, MethodType.OPENLOG), SORTER(
						"DocumentSorter", Target.BOTH, SourceCodeType.SORTER,
						MethodType.SORTER), EMAIL("Email", Target.BOTH, SourceCodeType.EMAIL, MethodType.EMAIL);

		private String value_;
		private Target target_;
		private SourceCodeType sourcePage_;
		private MethodType methodPage_;

		private ElseSubPage(String subPage, Target target, SourceCodeType sourcePage, MethodType methodPage) {
			value_ = subPage;
			target_ = target;
			sourcePage_ = sourcePage;
			methodPage_ = methodPage;
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

		public MethodType getMethodPage() {
			return methodPage_;
		}
	}

	public DominoElseView() {
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

	public void loadContent(ElseSubPage subPage) {
		if (!getCurrentPage().equals(subPage)) {
		}
		switch (subPage) {
		case DATES_PAGE:
			dateTime.load();
			getContentPanel().setContent(dateTime);
			setCurrentMethodPage(MethodType.DATE);
			break;
		case SUMMARY_PAGE:
			summary.load();
			getContentPanel().setContent(summary);
			setCurrentMethodPage(MethodType.DOCUMENT_COLLECTION);
			break;
		case OPENLOG:
			openLog.load();
			getContentPanel().setContent(openLog);
			setCurrentMethodPage(MethodType.OPENLOG);
			break;
		case SORTER:
			sorter.load();
			getContentPanel().setContent(sorter);
			setCurrentMethodPage(MethodType.SORTER);
			break;
		case EMAIL:
			email.load();
			getContentPanel().setContent(email);
			setCurrentMethodPage(MethodType.EMAIL);
			break;
		default:
			getContentPanel().setContent(new Label("<b>NO CONTENT SET FOR THIS PAGE</b>", ContentMode.HTML));
		}

		if (!subPage.getSourcePage().equals(getCurrentSourcePage())) {
			switch (subPage.getSourcePage()) {
			case DATE:
				loadDateSource();
				break;
			case OPENLOG:
				loadOpenLogSource();
				break;
			case SORTER:
				loadSorterSource();
				break;
			case EMAIL:
				loadEmailSource();
				break;
			default:
				loadDocCollSource();
			}
		}

		if (!subPage.getMethodPage().equals(getCurrentMethodPage())) {
			getMethodList().setContent(getElseMethodLabel(subPage.getMethodPage()));
		}
	}

	@Override
	public void loadNavigation() {
		getSubNavigation().removeAllComponents();

		TargetSelector target1 = new TargetSelector(this);
		getSubNavigation().addComponent(target1);
		Target currTarget = DemoUI.get().getAppTarget();

		for (final ElseSubPage subPage : ElseSubPage.values()) {
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
	public void loadSource() {
		loadDocCollSource();
	}

	public void loadNoSource() {
		getSourceCode().removeAllComponents();
		Label label1 = new Label("");
		getSourceCode().addComponent(label1);
	}

	public void loadDateSource() {
		loadSimpleSource("dateTimes");
	}

	public void loadDocCollSource() {
		loadSimpleSource("docForLoop");
	}

	public void loadEntCollSource() {
		loadSimpleSource("entryForLoop");
	}

	public void loadFormSource() {
		loadSimpleSource("form");
	}

	public void loadColorSource() {
		loadSimpleSource("color");
	}

	public void loadOpenLogSource() {
		loadSimpleSource("openLog");
	}

	public void loadSorterSource() {
		loadSimpleSource("documentSorter");
	}

	public void loadEmailSource() {
		loadSimpleSource("email");
	}

	@Override
	public void loadMethodList() {
		getMethodList().setContent(getElseMethodLabel(getCurrentMethodPage()));
	}

	public Label getElseMethodLabel(MethodType newMethodPage) {
		StringBuilder sb = new StringBuilder();
		ArrayList<String> newMethods = new ArrayList<String>();
		for (Method newCrystal : getNewClassMethods()) {
			newMethods.add(newCrystal.getName() + newCrystal.hashCode());
		}
		TreeMap<String, String> methSummary = new TreeMap<String, String>();
		for (Method crystal : getCoreClassMethods()) {
			methSummary.put(crystal.getName() + crystal.hashCode(), getMethodSummary(newMethods, crystal));
		}

		for (String content : methSummary.values()) {
			sb.append(content);
		}
		return new Label(sb.toString(), ContentMode.HTML);
	}

	public Method[] getNewClassMethods() {
		switch (getCurrentMethodPage()) {
		case DATE:
			return org.openntf.domino.ext.DateTime.class.getMethods();
		case COLOR_OBJECT:
			return org.openntf.domino.ext.ColorObject.class.getMethods();
		case FORM:
			return org.openntf.domino.ext.Form.class.getMethods();
		case VIEW_ENTRY:
			return org.openntf.domino.ext.ViewEntry.class.getMethods();
		case VIEW_ENTRY_COLLECTION:
			return org.openntf.domino.ext.ViewEntryCollection.class.getMethods();
		case OPENLOG:
			return BaseOpenLogItem.class.getMethods();
		case SORTER:
			return DocumentSorter.class.getMethods();
		case EMAIL:
			return DominoEmail.class.getMethods();
		default:
			return org.openntf.domino.ext.DocumentCollection.class.getMethods();
		}
	}

	public Method[] getCoreClassMethods() {
		switch (getCurrentMethodPage()) {
		case DATE:
			return DateTime.class.getMethods();
		case COLOR_OBJECT:
			return ColorObject.class.getMethods();
		case FORM:
			return Form.class.getMethods();
		case VIEW_ENTRY:
			return ViewEntry.class.getMethods();
		case VIEW_ENTRY_COLLECTION:
			return ViewEntryCollection.class.getMethods();
		case OPENLOG:
			return BaseOpenLogItem.class.getMethods();
		case SORTER:
			return DocumentSorter.class.getMethods();
		case EMAIL:
			return DominoEmail.class.getMethods();
		default:
			return DocumentCollection.class.getMethods();
		}
	}

	public ElseSubPage getCurrentPage() {
		if (null == currentPage) {
			setCurrentPage(ElseSubPage.SUMMARY_PAGE);
		}
		return currentPage;
	}

	public void setCurrentPage(ElseSubPage currentPage) {
		this.currentPage = currentPage;
	}

	public SourceCodeType getCurrentSourcePage() {
		if (null == currentSourcePage) {
			setCurrentSourcePage(SourceCodeType.SUMMARY);
		}
		return currentSourcePage;
	}

	public void setCurrentSourcePage(SourceCodeType currentSourcePage) {
		this.currentSourcePage = currentSourcePage;
	}

	public MethodType getCurrentMethodPage() {
		if (null == currentMethodPage) {
			setCurrentMethodPage(MethodType.DATE);
		}
		return currentMethodPage;
	}

	public void setCurrentMethodPage(MethodType currentMethodPage) {
		this.currentMethodPage = currentMethodPage;
	}
}
