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
import org.openntf.domino.demoApp.pages.SessionView;
import org.openntf.domino.demoAppUtil.FactoryUtils;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

public class Session_XspProperties extends BaseSubPage {
	private static final long serialVersionUID = 1L;

	public Session_XspProperties(BaseView parentView) {
		super(parentView);
	}

	public void loadContent() {
		((SessionView) getParentView()).checkLoadSetupButton(this);
		Label label1 = new Label("XPages applications should enable fixes by setting Xsp Properties accordingly."
				+ "The switches should be comma-delimited in the format:<br/>"
				+ FactoryUtils.addCodeString("org.openntf.domino.xsp=godmode,marcel,khan,bubbleExceptions") + "<br/>The switches available are:");
		label1.setContentMode(ContentMode.HTML);

		Label label2 = new Label("GODMODE");
		label2.addStyleName(ValoTheme.LABEL_H3);
		Label label3 = new Label("Overrides the XPages <i>session</i> and <i>database</i> objects, "
				+ "converting the lotus.domino objects to their org.openntf.domino counterparts. "
				+ "If the switch is not enabled, the org.openntf.domino counterparts are loaded "
				+ "in addition to the lotus.domino objects, as <i>opensession</i> and <i>opendatabase</i>");
		label3.setContentMode(ContentMode.HTML);

		Label label4 = new Label("KHAN");
		label4.addStyleName(ValoTheme.LABEL_H3);
		Label label5 = new Label(
				"Turns on all \"improvements\" like forcing " + FactoryUtils.addCodeString("setAutoUpdate(false)") + " for all view actions.");
		label5.setContentMode(ContentMode.HTML);

		Label label6 = new Label("MARCEL");
		label6.addStyleName(ValoTheme.LABEL_H3);
		Label label7 = new Label("Auto-handles converting session to handle MIME where appropriate." + "The core API does not enforce setting "
				+ FactoryUtils.addCodeString(">Session.setConvertMIME()")
				+ "when handling MIME items and unsetting it once complete, but failing to do so can cause problems, "
				+ "e.g. accessing two different MIME items at once can cause a JRE and server crash.");
		label7.setContentMode(ContentMode.HTML);

		Label label8 = new Label("RAID");
		label8.addStyleName(ValoTheme.LABEL_H3);
		Label label9 = new Label("Turns on debug level messages");

		Label label10 = new Label("BUBBLEEXCEPTIONS");
		label10.addStyleName(ValoTheme.LABEL_H3);
		Label label11 = new Label("Pushes ODA errors up to application-level error handling. "
				+ "Otherwise ODA handles the error internally, logging out and avoiding application-level errors as far as possible");

		Label label12 = new Label("AUTOMIMENONE");
		label12.addStyleName(ValoTheme.LABEL_H3);
		Label label13 = new Label("Does not automatic conversion of Items to MIME. By default, if 32k limit on a field is hit or "
				+ "any other exception is hit, the Item will be converted to MIME.");
		label13.setContentMode(ContentMode.HTML);

		Label label14 = new Label("AUTOMIME23K");
		label14.addStyleName(ValoTheme.LABEL_H3);
		Label label15 = new Label(
				"Only does automatic conversion of Items to MIME if 32k limit is hit. By default, if 32k limit on a field is hit or "
						+ "any other exception is hit, the Item will be converted to MIME.");
		label15.setContentMode(ContentMode.HTML);

		addComponents(label1, label2, label3, label4, label5, label6, label7, label8, label9, label10, label11, label12, label13, label14, label15,
				new Html_Separator(SeparatorType.NEW_LINE));
	}

}
