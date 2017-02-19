package org.openntf.domino.demoApp.subpages.document;

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

import org.openntf.domino.demoApp.pages.BaseView;
import org.openntf.domino.demoApp.subpages.BaseSubPage;
import org.openntf.domino.demoApp.utils.FactoryUtils;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;

public class Document_Items extends BaseSubPage {
	private static final long serialVersionUID = 1L;

	public Document_Items(BaseView parentView) {
		super(parentView);
	}

	@Override
	public void loadContent() {
		Label label1 = new Label(MessageFormat.format(getProps().getProperty("getPut"),
				FactoryUtils.addCodeString("getItemValue()"), FactoryUtils.addCodeString("replaceItemValue()"),
				FactoryUtils.addCodeString("Document"), FactoryUtils.addCodeString("Map"),
				FactoryUtils.addCodeString("get()"), FactoryUtils.addCodeString("put()"),
				FactoryUtils.addCodeString("containsValue()"), FactoryUtils.addCodeString("containsValues()")));
		label1.setContentMode(ContentMode.HTML);
		Label label2 = new Label(MessageFormat.format(getProps().getProperty("append"),
				FactoryUtils.addCodeString("APPEND_ITEM_VALUE"), FactoryUtils.addCodeString("appendItemValue()")));
		label2.setContentMode(ContentMode.HTML);
		Label label3 = new Label(MessageFormat.format(getProps().getProperty("replace"),
				FactoryUtils.addCodeString("REPLACE_ITEM_NULL"),
				FactoryUtils.addCodeString("replaceItemValue(String, null)"), FactoryUtils.addCodeString("REMOVE_ITEM"),
				FactoryUtils.addCodeString("replaceItemValue(String, null)"),
				FactoryUtils.addCodeString("removeItem(String)"), FactoryUtils.addCodeString("replaceItemValue()")));
		label3.setContentMode(ContentMode.HTML);
		Label label4 = new Label(
				MessageFormat.format(getProps().getProperty("readers"), FactoryUtils.addCodeString("hasReaders()"),
						FactoryUtils.addCodeString("Item.setReaders()"), FactoryUtils.addCodeString("ReadersList"),
						FactoryUtils.addCodeString("AuthorsList"), FactoryUtils.addCodeString("NamesList")));
		label4.setContentMode(ContentMode.HTML);
		addComponents(label1, label2, label3, label4);
	}

}
