package org.openntf.domino.demoApp.subpages.view;

/*

<!--
Copyright 2017 Paul Withers
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

import java.text.MessageFormat;

import org.openntf.domino.demoApp.components.StateSelector;
import org.openntf.domino.demoApp.pages.BaseView;
import org.openntf.domino.demoApp.subpages.BaseSubPage;
import org.openntf.domino.demoApp.utils.DatabaseUtils;
import org.openntf.domino.demoApp.utils.FactoryUtils;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

public class View_GetEntries extends BaseSubPage {
	private static final long serialVersionUID = 1L;
	private final Label outputLabel = new Label();
	private StateSelector stateSelector;

	public View_GetEntries(BaseView parentView) {
		super(parentView);
	}

	@Override
	public void loadContent() {
		Label label1 = new Label(
				MessageFormat.format(getProps().getProperty("getSummary"), FactoryUtils.addCodeString("recycle"),
						FactoryUtils.addCodeString("getAllEntriesByKey() / getEntryByKey()"),
						FactoryUtils.addCodeString("getAllDocumentsByKey() / getDocumentByKey()"),
						FactoryUtils.addCodeString("for"), FactoryUtils.addCodeString("VIEW_UPDATE_OFF"),
						FactoryUtils.addCodeString("VIEWENTRY_RETURN_CONSTANT_VALUES")));
		label1.setContentMode(ContentMode.HTML);
		Label label2 = new Label(MessageFormat.format(getProps().getProperty("getFirst"),
				FactoryUtils.addCodeString("getEntryByKey()"), FactoryUtils.addCodeString("getDocumentByKey"),
				FactoryUtils.addCodeString("getFirstEntryByKey()"),
				FactoryUtils.addCodeString("getFirstDocumentByKey")));
		label2.setContentMode(ContentMode.HTML);
		stateSelector = new StateSelector();
		Button button1 = new Button("Get Entries");
		button1.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		button1.addStyleName("floating-btn");
		button1.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				getOutputLabel().setCaption(
						DatabaseUtils.getEntriesDocumentsTest((String) getStateSelector().getValue(), true));
			}
		});
		Button button2 = new Button("Get Documents");
		button2.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		button2.addStyleName("floating-btn");
		button2.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				getOutputLabel().setCaption(
						DatabaseUtils.getEntriesDocumentsTest((String) getStateSelector().getValue(), false));
			}
		});
		getOutputLabel().setCaptionAsHtml(true);
		addComponents(label1, label2, getStateSelector(), button1, button2, getOutputLabel());
	}

	public Label getOutputLabel() {
		return outputLabel;
	}

	public StateSelector getStateSelector() {
		return stateSelector;
	}

}
