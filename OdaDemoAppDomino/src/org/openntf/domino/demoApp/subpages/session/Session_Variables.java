package org.openntf.domino.demoApp.subpages.session;

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

public class Session_Variables extends BaseSubPage {
	private static final long serialVersionUID = 1L;

	public Session_Variables(BaseView parentView) {
		super(parentView);
	}

	@Override
	public void loadContent() {
		Label label1 = new Label("The following implicit variables are available for XPages use:");
		Label label2 = new Label("session / openSession");
		label2.addStyleName(ValoTheme.LABEL_H3);
		Label label3 = new Label(MessageFormat.format(getProps().getProperty("varSession"),
				FactoryUtils.addCodeString("session"), FactoryUtils.addCodeString("openSession")));
		label3.setContentMode(ContentMode.HTML);
		Label label4 = new Label("sessionAsSigner / openSessionAsSigner");
		label4.addStyleName(ValoTheme.LABEL_H3);
		Label label5 = new Label(MessageFormat.format(getProps().getProperty("varSessionAsSigner"),
				FactoryUtils.addCodeString("sessionAsSigner"), FactoryUtils.addCodeString("openSessionAsSigner"),
				FactoryUtils.addCodeString("Factory.getSession(SessionType.NATIVE)")));
		label5.setContentMode(ContentMode.HTML);
		Label label6 = new Label("sessionAsSignerFullAccess / openSessionAsSignerFullAccess");
		label6.addStyleName(ValoTheme.LABEL_H3);
		Label label7 = new Label(MessageFormat.format(getProps().getProperty("varSessionAsSigner"),
				FactoryUtils.addCodeString("sessionAsSignerFullAccess"),
				FactoryUtils.addCodeString("openSessionAsSignerFullAccess"),
				FactoryUtils.addCodeString("Factory.getSession(SessionType.NATIVE)")));
		label7.setContentMode(ContentMode.HTML);
		Label label8 = new Label("openLogBean");
		label8.addStyleName(ValoTheme.LABEL_H3);
		Label label9 = new Label(MessageFormat.format(getProps().getProperty("varOpenLogBean"),
				FactoryUtils.addCodeString("openLogBean")));
		label9.setContentMode(ContentMode.HTML);
		Label label10 = new Label("serverScope");
		label10.addStyleName(ValoTheme.LABEL_H3);
		Label label11 = new Label(getProps().getProperty("varServerScope"));
		Label label12 = new Label("identityScope");
		label12.addStyleName(ValoTheme.LABEL_H3);
		Label label13 = new Label(getProps().getProperty("varIdentityScope"));
		Label label14 = new Label("userScope");
		label14.addStyleName(ValoTheme.LABEL_H3);
		Label label15 = new Label(getProps().getProperty("varUserScope"));
		addComponents(label1, label2, label3, label4, label5, label6, label7, label8, label9, label10, label11, label12,
				label13, label14, label15);
	}

}
