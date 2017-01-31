package org.openntf.domino.demoApp.pages;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.TreeMap;

import org.openntf.domino.Document;
import org.openntf.domino.demoApp.DemoUI;
import org.openntf.domino.demoApp.components.TargetSelector;
import org.openntf.domino.demoApp.components.TargetSelector.Target;
import org.openntf.domino.demoApp.subpages.database.Database_GettingDocuments;

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

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

public class DocumentView extends BaseView {
	private static final long serialVersionUID = 1L;
	public static String VIEW_NAME = "Document";
	public static String VIEW_LABEL = "Document";
	private DocumentSubPage currentPage;
	private Database_GettingDocuments gettingDocs = new Database_GettingDocuments(this);
	private Label documentMethodLabel;

	public enum DocumentSubPage {
		GETTING_DOCUMENTS("Getting Documents", Target.BOTH), GETTING_PUTTING("Getting / Putting Values",
				Target.BOTH), AUTO_BOXING("Auto-Boxing Values", Target.BOTH), SUMMARY_FIELDS("Summary Fields",
						Target.BOTH), TABLE_FIELD("Table Fields", Target.XPAGES);

		private String value_;
		private Target target_;

		private DocumentSubPage(String subPage, Target target) {
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

	public DocumentView() {
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

	public void loadContent(DocumentSubPage subPage) {
		if (!getCurrentPage().equals(subPage)) {
		}
		switch (subPage) {
		case GETTING_DOCUMENTS:
			gettingDocs.load();
			getContentPanel().setContent(gettingDocs);
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

		for (final DocumentSubPage subPage : DocumentSubPage.values()) {
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

	public DocumentSubPage getCurrentPage() {
		if (null == currentPage) {
			setCurrentPage(DocumentSubPage.GETTING_DOCUMENTS);
		}
		return currentPage;
	}

	public void setCurrentPage(DocumentSubPage currentPage) {
		this.currentPage = currentPage;
	}

	@Override
	public void loadSource() {
		return;
	}

	@Override
	public void loadMethodList() {
		Label methLabel = getDocumentMethodLabel();
		getMethodList().setContent(methLabel);
	}

	public Label getDocumentMethodLabel() {
		if (null == documentMethodLabel) {
			setDocumentMethodLabel();
		}
		return documentMethodLabel;
	}

	public void setDocumentMethodLabel() {

		StringBuilder sb = new StringBuilder();
		ArrayList<String> newMethods = new ArrayList<String>();
		for (Method newCrystal : org.openntf.domino.ext.Document.class.getMethods()) {
			newMethods.add(newCrystal.getName() + newCrystal.hashCode());
		}
		TreeMap<String, String> methSummary = new TreeMap<String, String>();
		for (Method crystal : Document.class.getMethods()) {
			methSummary.put(crystal.getName() + crystal.hashCode(), getMethodSummary(newMethods, crystal));
		}
		for (String content : methSummary.values()) {
			sb.append(content);
		}
		documentMethodLabel = new Label(sb.toString(), ContentMode.HTML);
	}

}
