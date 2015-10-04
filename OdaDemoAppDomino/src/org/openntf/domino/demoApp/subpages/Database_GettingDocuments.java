package org.openntf.domino.demoApp.subpages;

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

import org.openntf.domino.demoApp.components.Html_Separator;
import org.openntf.domino.demoApp.components.Html_Separator.SeparatorType;
import org.openntf.domino.demoApp.pages.BaseView;
import org.openntf.domino.demoAppUtil.FactoryUtils;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

public class Database_GettingDocuments extends BaseSubPage {
	private static final long serialVersionUID = 1L;

	public Database_GettingDocuments(BaseView parentView) {
		super(parentView);
	}

	@Override
	public void loadContent() {
		final Label label1 = new Label("Getting documents from a database via the core Domino API has required certain knowledge of the API. "
				+ "There have been three different methods - by Note ID, Universal ID or URL. " + "The intuitive expectation is that "
				+ "if no document can be found the return value would be null. But instead, the method will throw an error. The fix DOC_UNID_NULLS, "
				+ "available in XPages with the KHAN xsp.property, overrides this and returns null, as in other Documet getter methods. "
				+ "ODA also adds a number of additional methods, like making a Database act like a Map, allowing " + FactoryUtils.addCodeString("Database.get()")
				+ " to retrieve a document via UNID. There are also methods to get a document with a key, "
				+ "which will get a document by UNID or convert a String to a UNID (because the UNID is read-write, so you can convert any String "
				+ "and use it as a UNID). The list of methods available are:");
		label1.setContentMode(ContentMode.HTML);
		final Label label2 = new Label("getDocumentByID(int, boolean), getDocumentByID(String), getDocumentByID(String, boolean)");
		label2.setStyleName(ValoTheme.LABEL_H3);
		final Label label3 = new Label("Gets a document by a String or int ID, allowing you to create the document regardless of whether it already exists. "
				+ "Historically, developers had to convert int IDs to String.");
		final Label label4 = new Label("getDocumentByUNID(String), getDocumentByUNID(String, boolean)");
		label4.setStyleName(ValoTheme.LABEL_H3);
		final Label label5 = new Label("Allows the developer to optionally create the document with the passed UNID regardless of whether it already exists. "
				+ "NOTE: These methods will still throw an error trying to get the document unless Session.FIXES.DOC_UNID_NULLS is true. "
				+ "That switch is set automatically via KHAN mode, in OsgiWorlds, or can be set for a specific session.");
		final Label label6 = new Label("getDocumentByURL(String), getDocumentByURL(String, boolean, boolean, boolean, String, String, String, String, String, boolean)");
		label6.setStyleName(ValoTheme.LABEL_H3);
		final Label label7 = new Label("These methods are unchanged from the lotus.domino versions");
		final Label label8 = new Label("get(), getDocumentWithKey(Serializable), getDocumentWithKey(Serializable, boolean)");
		label8.setStyleName(ValoTheme.LABEL_H3);
		final Label label9 = new Label(FactoryUtils.addCodeString("Database.get()") + " calls " + FactoryUtils.addCodeString("getDocumentWithKey(Serializable)") + " which calls "
				+ FactoryUtils.addCodeString("getDocumentWithKey(Serializable, boolean)") + " passing false as the second parameter. "
				+ "That method checks whether the first parameter is a UNID, otherwise converts it to a UNID, and tries to get the document. "
				+ "If the second parameter is true, it will create the document if it fails.");
		label9.setContentMode(ContentMode.HTML);
		addComponents(label1, label2, label3, label4, label5, label6, label7, label8, label9, new Html_Separator(SeparatorType.NEW_LINE));
	}

}
