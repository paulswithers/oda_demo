package org.openntf.domino.demoApp.subpages.elses;

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
import com.vaadin.ui.themes.ValoTheme;

public class Else_DateTime extends BaseSubPage {
	private static final long serialVersionUID = 1L;

	public Else_DateTime(BaseView parentView) {
		super(parentView);
	}

	@Override
	public void loadContent() {
		Label label1 = new Label("Comparing Dates");
		label1.setStyleName(ValoTheme.LABEL_H3);
		Label label2 = new Label(MessageFormat.format(getProps().getProperty("compare"),
				FactoryUtils.addCodeString("DateTime.getTimeDifferenceDouble(DateTime)"),
				FactoryUtils.addCodeString("DateTime.getTimeDifference(DateTime)")));
		label2.setContentMode(ContentMode.HTML);
		Label label3 = new Label(MessageFormat.format(getProps().getProperty("compareMeths"),
				FactoryUtils.addCodeString("isBefore()"), FactoryUtils.addCodeString("isBeforeIgnoreDate()"),
				FactoryUtils.addCodeString("isBeforeIgnoreTime()"), FactoryUtils.addCodeString("isAfter()"),
				FactoryUtils.addCodeString("isAfterIgnoreDate()"), FactoryUtils.addCodeString("isAfterIgnoreTime()"),
				FactoryUtils.addCodeString("equals()"), FactoryUtils.addCodeString("equalsIgnoreDate()"),
				FactoryUtils.addCodeString("equalsIgnoreTime()")));
		label3.setContentMode(ContentMode.HTML);
		Label label4 = new Label("Storing / Retrieving");
		label4.setStyleName(ValoTheme.LABEL_H3);
		Label label5 = new Label(MessageFormat.format(getProps().getProperty("partOnly"),
				FactoryUtils.addCodeString("java.util.Date"), FactoryUtils.addCodeString("java.util.Calendar"),
				FactoryUtils.addCodeString("java.sql.Date"), FactoryUtils.addCodeString("java.sql.Time")));
		label5.setContentMode(ContentMode.HTML);
		Label label6 = new Label("Timezones");
		label6.setStyleName(ValoTheme.LABEL_H3);
		Label label7 = new Label(MessageFormat.format(getProps().getProperty("timezone"),
				FactoryUtils.addCodeString("java.util.Date"), FactoryUtils.addCodeString("notesZone_")));
		label7.setContentMode(ContentMode.HTML);
		addComponents(label1, label2, label3, label4, label5, label6, label7);
	}

}
