package org.openntf.domino.demoApp.subpages.database;

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
import org.openntf.domino.demoApp.subpages.BaseSubPage;
import org.openntf.domino.demoApp.utils.FactoryUtils;

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
		Label label1 = new Label(MessageFormat.format(getProps().getProperty("gettingDocsIntro"),
				FactoryUtils.addCodeString("Database.get()")));
		label1.setContentMode(ContentMode.HTML);
		Label label2 = new Label(FactoryUtils.addCodeString(getProps().getProperty("gettingDocsIdMethods")));
		label2.setContentMode(ContentMode.HTML);
		label2.setStyleName(ValoTheme.LABEL_H3);
		Label label3 = new Label(getProps().getProperty("gettingDocsId"));
		Label label4 = new Label(FactoryUtils.addCodeString(getProps().getProperty("gettingDocsUnidMethods")));
		label4.setContentMode(ContentMode.HTML);
		label4.setStyleName(ValoTheme.LABEL_H3);
		Label label5 = new Label(getProps().getProperty("gettingDocsUnid"));
		Label label6 = new Label(FactoryUtils.addCodeString(getProps().getProperty("gettingDocsUrlMethods")));
		label6.setContentMode(ContentMode.HTML);
		label6.setStyleName(ValoTheme.LABEL_H3);
		Label label7 = new Label(getProps().getProperty("gettingDocsUrl"));
		Label label8 = new Label(FactoryUtils.addCodeString(getProps().getProperty("gettingDocsMapMethods")));
		label8.setContentMode(ContentMode.HTML);
		label8.setStyleName(ValoTheme.LABEL_H3);
		Label label9 = new Label(MessageFormat.format(getProps().getProperty("gettingDocsMap"),
				FactoryUtils.addCodeString("Database.get()"),
				FactoryUtils.addCodeString("getDocumentWithKey(Serializable)"),
				FactoryUtils.addCodeString("getDocumentWithKey(Serializable, boolean)")));
		label9.setContentMode(ContentMode.HTML);
		Label label10 = new Label(FactoryUtils.addCodeString(getProps().getProperty("gettingDocsMetaversalMethods")));
		label10.setContentMode(ContentMode.HTML);
		label10.setStyleName(ValoTheme.LABEL_H3);
		Label label11 = new Label(getProps().getProperty("gettingDocsMetaversal"));
		addComponents(label1, label2, label3, label4, label5, label6, label7, label8, label9, label10, label11,
				new Html_Separator(SeparatorType.NEW_LINE));
	}

}
