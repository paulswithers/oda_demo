package org.openntf.domino.demoApp.subpages.document;

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
