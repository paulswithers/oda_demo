package org.openntf.domino.demoApp.subpages.session;

import java.text.MessageFormat;

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

import org.openntf.domino.Session;
import org.openntf.domino.demoApp.components.Html_Separator;
import org.openntf.domino.demoApp.components.Html_Separator.SeparatorType;
import org.openntf.domino.demoApp.pages.BaseView;
import org.openntf.domino.demoApp.pages.SessionView;
import org.openntf.domino.demoApp.subpages.BaseSubPage;
import org.openntf.domino.demoAppUtil.FactoryUtils;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

public class Session_Fixes extends BaseSubPage {
	private static final long serialVersionUID = 1L;

	public Session_Fixes(BaseView parentView) {
		super(parentView);
	}

	@Override
	public void loadContent() {
		((SessionView) getParentView()).checkLoadSetupButton(this);
		for (Session.Fixes fix : Session.Fixes.values()) {
			Label label1 = new Label(fix.name());
			label1.setStyleName(ValoTheme.LABEL_H3);
			String fixExplanation;
			switch (fix) {
			case APPEND_ITEM_VALUE:
				fixExplanation = MessageFormat.format(getProps().getProperty("fixAppendItemValue"),
						FactoryUtils.addCodeString("Document.appendItemValue()"));
				break;

			case CREATE_DB:
				fixExplanation = MessageFormat.format(getProps().getProperty("fixCreateDb"),
						FactoryUtils.addCodeString("DbDirectory.createDatabase(String)"));
				break;
			case DOC_UNID_NULLS:
				fixExplanation = MessageFormat.format(getProps().getProperty("fixDocUnid"),
						FactoryUtils.addCodeString("Database.getDocumentByUNID()"));
				break;
			case FORCE_JAVA_DATES:
				fixExplanation = getProps().getProperty("fixForceJavaDates");
				break;
			case MIME_BLOCK_ITEM_INTERFACE:
				fixExplanation = getProps().getProperty("fixMimeBlock");
				break;
			case MIME_CONVERT:
				fixExplanation = getProps().getProperty("fixMimeConvert");
				break;
			case ODA_NAMES:
				fixExplanation = getProps().getProperty("fixOdaNames");
				break;
			case REMOVE_ITEM:
				fixExplanation = getProps().getProperty("fixRemoveItem");
				break;
			case REPLACE_ITEM_NULL:
				fixExplanation = "This extension allows developers to use "
						+ FactoryUtils.addCodeString("Document.replaceItemValue(String, null)") + " to remove the Item";
				break;
			case VIEW_UPDATE_OFF:
				fixExplanation = MessageFormat.format(getProps().getProperty("fixViewUpdate"),
						FactoryUtils.addCodeString("View.setAutoUpdate(false)"));
				break;
			case VIEWENTRY_RETURN_CONSTANT_VALUES:
				fixExplanation = MessageFormat.format(getProps().getProperty("fixViewEntryConstants"),
						FactoryUtils.addCodeString("ViewEntry.getColumnValues()"),
						FactoryUtils.addCodeString("ViewColumn.getColumnValuesIndex()"));
				break;
			case PEDANTIC_GC_TRACKING:
				fixExplanation = getProps().getProperty("fixPedantic");
				break;
			case FORCE_HEX_LOWER_CASE:
				fixExplanation = getProps().getProperty("fixHex");
				break;
			default:
				fixExplanation = getProps().getProperty("fixMissing");
				break;
			}
			Label label2 = new Label(fixExplanation + "<br/>", ContentMode.HTML);
			addComponents(label1, label2);
		}
		addComponent(new Html_Separator(SeparatorType.NEW_LINE));
	}

}
