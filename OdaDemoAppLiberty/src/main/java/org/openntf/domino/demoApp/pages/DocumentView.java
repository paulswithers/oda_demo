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

import org.openntf.domino.Document;
import org.openntf.domino.demoApp.DemoUI;
import org.openntf.domino.demoApp.components.TargetSelector;
import org.openntf.domino.demoApp.components.TargetSelector.Target;
import org.openntf.domino.demoApp.subpages.database.Database_GettingDocuments;
import org.openntf.domino.demoApp.subpages.document.Document_Autoboxing;
import org.openntf.domino.demoApp.subpages.document.Document_Items;

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
	private SourceCodeType currentSourcePage;
	private Database_GettingDocuments gettingDocs = new Database_GettingDocuments(this);
	private Document_Items putItems = new Document_Items(this);
	private Document_Autoboxing autobox = new Document_Autoboxing(this);
	private Label documentMethodLabel;

	private enum SourceCodeType {
		GET_DOC, ITEMS, AUTO_BOXING, TABLE;
	}

	public enum DocumentSubPage {
		GETTING_DOCUMENTS("Getting Documents", Target.BOTH, SourceCodeType.GET_DOC), GETTING_PUTTING(
				"Getting / Putting Values", Target.BOTH, SourceCodeType.ITEMS), AUTO_BOXING("Auto-Boxing Values",
						Target.BOTH,
						SourceCodeType.AUTO_BOXING), TABLE_FIELD("Table Fields", Target.XPAGES, SourceCodeType.TABLE);

		private String value_;
		private Target target_;
		private SourceCodeType sourcePage_;

		private DocumentSubPage(String subPage, Target target, SourceCodeType sourcePage) {
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
		case GETTING_PUTTING:
			putItems.load();
			getContentPanel().setContent(putItems);
			break;
		case AUTO_BOXING:
			autobox.load();
			getContentPanel().setContent(autobox);
			break;
		default:
			getContentPanel().setContent(new Label("<b>NO CONTENT SET FOR THIS PAGE</b>", ContentMode.HTML));
		}
		if (!subPage.getSourcePage().equals(getCurrentSourcePage())) {
			switch (subPage.getSourcePage()) {
			case ITEMS:
				loadItemSource();
				break;
			case AUTO_BOXING:
				loadAutoboxSource();
				break;
			default:
				loadGetDocSource();
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

	@Override
	public void loadSource() {
		loadGetDocSource();
	}

	public void loadGetDocSource() {
		loadSimpleSource("getDoc");
	}

	public void loadItemSource() {
		loadSimpleSource("putItem");
	}

	public void loadAutoboxSource() {
		loadSimpleSource("autobox");
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

	public DocumentSubPage getCurrentPage() {
		if (null == currentPage) {
			setCurrentPage(DocumentSubPage.GETTING_DOCUMENTS);
		}
		return currentPage;
	}

	public void setCurrentPage(DocumentSubPage currentPage) {
		this.currentPage = currentPage;
	}

	public SourceCodeType getCurrentSourcePage() {
		if (null == currentSourcePage) {
			setCurrentSourcePage(SourceCodeType.GET_DOC);
		}
		return currentSourcePage;
	}

	public void setCurrentSourcePage(SourceCodeType currentSourcePage) {
		this.currentSourcePage = currentSourcePage;
	}

}
