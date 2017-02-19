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

public class Database_GettingDbs extends BaseSubPage {
	private static final long serialVersionUID = 1L;

	public Database_GettingDbs(BaseView parentView) {
		super(parentView);
	}

	@Override
	public void loadContent() {
		Label label1 = new Label(MessageFormat.format(getProps().getProperty("gettingDbsIntro"),
				FactoryUtils.addCodeString("Session.getCurrentDatabase()"),
				FactoryUtils.addCodeString("Session.getDatabase(String, String)"),
				FactoryUtils.addCodeString("Session.getCurrentDatabase(String, String, boolean)"),
				FactoryUtils.addCodeString("Session.getCurrentDatabase()"),
				FactoryUtils.addCodeString("DbDirectory.openDatabase(String)"),
				FactoryUtils.addCodeString("DbDirectory.openDatatbase(String, boolean)"),
				FactoryUtils.addCodeString("DbDirectory.openDatabaseByReplicaID()"),
				FactoryUtils.addCodeString("DbDirectory.openDatabaseIfModified(String,Date)"),
				FactoryUtils.addCodeString("DbDirectory.openDatabaseIfModified(String,DateTime)"),
				FactoryUtils.addCodeString("DbDirectory.openMailDatabase()"),
				FactoryUtils.addCodeString("Database.open()"), FactoryUtils.addCodeString("Database.openByReplicaID()"),
				FactoryUtils.addCodeString("Database.openIfModified()"),
				FactoryUtils.addCodeString("Database.openWithFailover()")));
		label1.setContentMode(ContentMode.HTML);
		Label label2 = new Label(MessageFormat.format(getProps().getProperty("gettingDbsIntro2"),
				FactoryUtils.addCodeString("ServerName!!Database"),
				FactoryUtils.addCodeString("Session.getDatabase(String, String)"),
				FactoryUtils.addCodeString("Database"), FactoryUtils.addCodeString("Database.isOpen()"),
				FactoryUtils.addCodeString("Session.getDatabase(String, String, false)"),
				FactoryUtils.addCodeString("DbDirectory"), FactoryUtils.addCodeString("openMailDatabase()")));
		label2.setContentMode(ContentMode.HTML);
		Label label3 = new Label(MessageFormat.format(getProps().getProperty("gettingDbsIntro3"),
				FactoryUtils.addCodeString("Session.getDatabase()"), FactoryUtils.addCodeString("Session"),
				FactoryUtils.addCodeString("Session"), FactoryUtils.addCodeString("Session")));
		label3.setContentMode(ContentMode.HTML);
		addComponents(label1, label2, label3, new Html_Separator(SeparatorType.NEW_LINE));
	}

}
