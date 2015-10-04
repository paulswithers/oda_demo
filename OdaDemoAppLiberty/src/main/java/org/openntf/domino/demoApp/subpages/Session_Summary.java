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

import org.apache.commons.lang3.StringUtils;
import org.openntf.domino.Session;
import org.openntf.domino.demoApp.pages.BaseView;
import org.openntf.domino.demoApp.pages.SessionView;
import org.openntf.domino.demoAppUtil.FactoryUtils;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class Session_Summary extends BaseSubPage {
	private static final long serialVersionUID = 1L;

	public Session_Summary(BaseView parentView) {
		super(parentView);
	}

	public void loadContent() {
		((SessionView) getParentView()).checkLoadSetupButton(this);
		Session currSess = FactoryUtils.getUserSession();
		Label label1 = new Label("Session running as " + currSess.getEffectiveUserName() + " on server " + currSess.getServerName(),
				ContentMode.HTML);
		Label label2 = new Label("<b>User groups available are:</b><br/>" + StringUtils.join(currSess.getUserGroupNameCollection(), "<br/>"),
				ContentMode.HTML);
		Label label3 = new Label("<b>User names available are:</b><br/>" + StringUtils.join(currSess.getUserNameCollection(), "<br/>"),
				ContentMode.HTML);
		Label label4 = new Label("<b>Unique reference using Session.getUnique():</b> " + currSess.getUnique(), ContentMode.HTML);
		HorizontalLayout fixLayout = new HorizontalLayout();
		StringBuilder sb = new StringBuilder();
		sb.append("<b>Available fixtures:</b>");
		for (Session.Fixes fix : Session.Fixes.values()) {
			sb.append("<br/>" + fix.name());
		}
		Label label5 = new Label(sb.toString(), ContentMode.HTML);
		sb = new StringBuilder();
		sb.append("<b>Currently enabled fixtures:</b>");
		for (Session.Fixes fix : currSess.getEnabledFixes()) {
			sb.append("<br/>" + fix.name());
		}
		Label label6 = new Label(sb.toString(), ContentMode.HTML);
		fixLayout.addComponents(label5, label6);
		addComponents(label1, label2, label3, label4, fixLayout);
	}

}
