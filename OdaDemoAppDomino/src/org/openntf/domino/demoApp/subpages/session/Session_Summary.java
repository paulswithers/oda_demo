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

import org.apache.commons.lang3.StringUtils;
import org.openntf.domino.Session;
import org.openntf.domino.demoApp.pages.BaseView;
import org.openntf.domino.demoApp.pages.SessionView;
import org.openntf.domino.demoApp.subpages.BaseSubPage;
import org.openntf.domino.demoApp.utils.FactoryUtils;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class Session_Summary extends BaseSubPage {
	private static final long serialVersionUID = 1L;

	public Session_Summary(BaseView parentView) {
		super(parentView);
	}

	@Override
	public void loadContent() {
		((SessionView) getParentView()).checkLoadSetupButton(this);
		Session currSess = FactoryUtils.getUserSession();
		Label label1 = new Label(MessageFormat.format(getProps().getProperty("sessionRunning"),
				currSess.getEffectiveUserName(), currSess.getServerName()), ContentMode.HTML);
		Label label2 = new Label(MessageFormat.format(getProps().getProperty("userGroupsNames"), "groups")
				+ StringUtils.join(currSess.getUserGroupNameCollection(), "<br/>"), ContentMode.HTML);
		Label label3 = new Label(MessageFormat.format(getProps().getProperty("userGroupsNames"), "names")
				+ StringUtils.join(currSess.getUserNameCollection(), "<br/>"), ContentMode.HTML);
		Label label4 = new Label(getProps().getProperty("sessionUnique") + currSess.getUnique(), ContentMode.HTML);
		HorizontalLayout fixLayout = new HorizontalLayout();
		fixLayout.setSizeFull();
		StringBuilder sb = new StringBuilder();
		sb.append(getProps().getProperty("sessionFixes"));
		for (Session.Fixes fix : Session.Fixes.values()) {
			sb.append("<br/>" + fix.name());
		}
		Label label5 = new Label(sb.toString(), ContentMode.HTML);
		sb = new StringBuilder();
		sb.append(getProps().getProperty("sessionEnabledFixes"));
		for (Session.Fixes fix : Session.Fixes.values()) {
			if (currSess.isFixEnabled(fix)) {
				sb.append("<br/>ENABLED");
			} else {
				sb.append("<br/><b>DISABLED</b>");
			}
		}
		Label label6 = new Label(sb.toString(), ContentMode.HTML);
		fixLayout.addComponents(label5, label6);
		addComponents(label1, label2, label3, label4, fixLayout);
	}

}
