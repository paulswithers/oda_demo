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
import com.vaadin.ui.themes.ValoTheme;

public class Document_Autoboxing extends BaseSubPage {
	private static final long serialVersionUID = 1L;

	public Document_Autoboxing(BaseView parentView) {
		super(parentView);
	}

	@Override
	public void loadContent() {
		Label label1 = new Label("Multi-Value Fields");
		label1.setStyleName(ValoTheme.LABEL_H3);
		Label label2 = new Label(getProps().getProperty("autoboxMv"));
		Label label3 = new Label("Booleans");
		label3.setStyleName(ValoTheme.LABEL_H3);
		Label label4 = new Label(MessageFormat.format(getProps().getProperty("autoboxBoolean"),
				FactoryUtils.addCodeString("lotus.domino.Document"), FactoryUtils.addCodeString("dominoDocument")));
		label4.setContentMode(ContentMode.HTML);
		Label label5 = new Label("Numbers");
		label5.setStyleName(ValoTheme.LABEL_H3);
		Label label6 = new Label(MessageFormat.format(getProps().getProperty("autoboxNumber"),
				FactoryUtils.addCodeString(".doubleValue()")));
		label6.setContentMode(ContentMode.HTML);
		Label label7 = new Label("Dates");
		label7.setStyleName(ValoTheme.LABEL_H3);
		Label label8 = new Label(MessageFormat.format(getProps().getProperty("autoboxDate"),
				FactoryUtils.addCodeString("java.sql.Date"), FactoryUtils.addCodeString("java.sql.Time")));
		label8.setContentMode(ContentMode.HTML);
		Label label9 = new Label(MessageFormat.format(getProps().getProperty("autoboxDateTimezone"),
				FactoryUtils.addCodeString("java.util.Calendar")));
		label9.setContentMode(ContentMode.HTML);
		Label label10 = new Label("Miscellaneous");
		label10.setStyleName(ValoTheme.LABEL_H3);
		Label label11 = new Label(getProps().getProperty("autoboxMisc"));
		Label label12 = new Label(MessageFormat.format(getProps().getProperty("autoboxPattern"),
				FactoryUtils.addCodeString("java.util.regex.Pattern"),
				FactoryUtils.addCodeString("Pattern.pattern()")));
		label12.setContentMode(ContentMode.HTML);
		Label label13 = new Label(
				MessageFormat.format(getProps().getProperty("autoboxClass"), FactoryUtils.addCodeString("Class<?>")));
		label13.setContentMode(ContentMode.HTML);
		Label label14 = new Label(MessageFormat.format(getProps().getProperty("autoboxFormula"),
				FactoryUtils.addCodeString("org.openntf.domino.ext.Formula")));
		label14.setContentMode(ContentMode.HTML);
		Label label15 = new Label(MessageFormat.format(getProps().getProperty("autoboxNoteCoord"),
				FactoryUtils.addCodeString("org.openntf.domino.big.NoteCoordinate")));
		label15.setContentMode(ContentMode.HTML);
		Label label16 = new Label("Default");
		label16.setStyleName(ValoTheme.LABEL_H3);
		Label label17 = new Label(MessageFormat.format(getProps().getProperty("autoboxElse"),
				FactoryUtils.addCodeString("serialize()"), FactoryUtils.addCodeString("java.util.Map"),
				FactoryUtils.addCodeString("deserialize()"), FactoryUtils.addCodeString("java.util.Map")));
		label17.setContentMode(ContentMode.HTML);
		addComponents(label1, label2, label3, label4, label5, label6, label7, label8, label9, label10, label11, label12,
				label13, label14, label15, label16, label17);
	}

}
