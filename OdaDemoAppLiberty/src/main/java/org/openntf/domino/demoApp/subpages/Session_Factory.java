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
import org.openntf.domino.demoApp.DemoUI;
import org.openntf.domino.demoApp.components.Html_Separator;
import org.openntf.domino.demoApp.components.Html_Separator.SeparatorType;
import org.openntf.domino.demoApp.components.TargetSelector.Target;
import org.openntf.domino.demoApp.pages.BaseView;
import org.openntf.domino.demoApp.pages.SessionView;
import org.openntf.domino.demoAppUtil.FactoryUtils;
import org.openntf.domino.utils.Factory.SessionType;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

public class Session_Factory extends BaseSubPage {
	private static final long serialVersionUID = 1L;

	public Session_Factory(BaseView parentView) {
		super(parentView);
	}

	public void loadContent() {
		((SessionView) getParentView()).checkLoadSetupButton(this);
		Label label1 = new Label("HISTORICAL XPAGES ACCESS");
		label1.addStyleName(ValoTheme.LABEL_H3);
		Label label2 = new Label("In previous releases there were a number of ways to access sessions:" + "<ul><li>In XPages resolving the variables "
				+ FactoryUtils.addCodeString("session") + " / " + FactoryUtils.addCodeString("opensession") + "</li>" + "<li>"
				+ FactoryUtils.addCodeString("Factory.getSession()") + " and variants</li></ul>");
		label2.setContentMode(ContentMode.HTML);
		addComponents(label1, label2);

		Target currTarget = DemoUI.get().getAppTarget();
		if (!Target.NON_XPAGES.equals(currTarget)) {
			Label label3 = new Label("XPAGES ACCESS");
			label3.addStyleName(ValoTheme.LABEL_H3);
			Label label4 = new Label("In XPages, resolving the " + FactoryUtils.addCodeString("session / opensession")
					+ "variables is still a valid approach.<br/>" + FactoryUtils.addCodeString("Factory.getSession()")
					+ " will still work, but is now deprecated." + "Since RC3 (release candidate for M1.5) the "
					+ FactoryUtils.addCodeString("SessionType") + " Enum has been implemented. That should be used instead.");
			label4.setContentMode(ContentMode.HTML);
			addComponents(label3, label4);
		}

		if (!Target.XPAGES.equals(currTarget)) {
			Label label5 = new Label("NON-XPAGES ACCESS");
			label5.addStyleName(ValoTheme.LABEL_H3);
			StringBuilder sb = new StringBuilder();
			sb.append("CrossWorlds / OSGiWorlds / Graph REST will handle setting up Sessions and Threads.");
			sb.append("<ul><li><b>CrossWorlds</b> uses a Filter to filter all HTTP requests for any web application, see "
					+ FactoryUtils.addCodeString("org.openntf.xworlds.core") + " bundle's "
					+ FactoryUtils.addCodeString("org.openntf.xworlds.appservers.webapp.XWorldsRequestFilter")
					+ ". This uses configuration to set up Sessions for the current user and the application signer, see "
					+ FactoryUtils.addCodeString("org.openntf.xworlds.appservers.webapp.config.DefaultXWorldsApplicationConfig")
					+ ". You can extend this class in your web application to change the base functionality.</li>");
			sb.append("<li><b>OsgiWorlds</b> uses an approach specifically for Vaadin development, extending the VaadinServlet class and its "
					+ FactoryUtils.addCodeString("service") + " method, see " + FactoryUtils.addCodeString("org.openntf.osgiworlds.ODA_VaadinServlet")
					+ ". This uses configuration to set up Sessions for the current user and the application signer, see "
					+ FactoryUtils.addCodeString("org.openntf.osgiworlds.DefaultDominoApplicationConfig")
					+ ". Remember to register the servlet in the normal Vaadin way (the older method is in web.xml, "
					+ "the newer method is using @Annotations in the application's main UI class.</li>");
			sb.append("<li><b>The graph REST API</b> uses an extension to " + FactoryUtils.addCodeString("com.ibm.domino.das.servlet.DasServlet")
					+ ", see" + FactoryUtils.addCodeString("org.openntf.domino.rest.servlet.ODADataServlet") + " class. This class's "
					+ FactoryUtils.addCodeString("doService") + " method creates a Session as the current user using the "
					+ FactoryUtils.addCodeString("org.openntf.domino.xsp.session.DasCurrentSessionFactory")
					+ " class to retrieve the current user and current database from the Domino browser session. The same classes can be used for custom REST servlets.</li>");
			addComponents(label5, new Html_Separator(SeparatorType.NEW_LINE));
		}

		Label label6 = new Label("NEW APPROACH");
		label6.addStyleName(ValoTheme.LABEL_H3);
		StringBuilder sb = new StringBuilder();
		sb.append("The recommended approach now is to use " + FactoryUtils.addCodeString("Factory.getSession(SessionType)")
				+ ". This was added because of access from XPages, XOTS, CrossWorlds and more, to manage more session types than just CURRENT, SIGNER and NATIVE. "
				+ "The following Enum options are available:<br><ul>");
		for (SessionType sessType : SessionType.values()) {
			String sessExplanation = new String("");
			switch (sessType) {
			case CURRENT:
				sessExplanation = "A named Session for the current user";
				break;
			case CURRENT_FULL_ACCESS:
				sessExplanation = "A named Session for the current user but with full access";
				break;
			case SIGNER:
				sessExplanation = "A named Session for whichever Notes ID is defined as the current application's signer";
				break;
			case SIGNER_FULL_ACCESS:
				sessExplanation = "A named Session for whichever Notes ID is defined as the current application's signer with full access";
				break;
			case NATIVE:
				sessExplanation = "A Session based on the current server's access";
				break;
			case FULL_ACCESS:
				sessExplanation = "A Session equivalent to Full Access Administration setting in Domino Administrator";
				break;
			case PASSWORD:
				sessExplanation = "A Session based on a specific Notes Client user ID and password";
				break;
			case TRUSTED:
				sessExplanation = "Returns a Trusted Session, not yet implemented";
				break;
			case _NAMED_internal:
				// Nothing to see, move along please!!
				break;
			case _NAMED_FULL_ACCESS_internal:
				// Nothing to see, move along please!!
				break;
			default:
				sessExplanation = "Whoops!! This must be a new SessionType. Please let us know so we can add documentation.";
			}
			if (StringUtils.isNotEmpty(sessExplanation)) {
				sb.append("<li>" + sessType.name() + " - " + sessExplanation + "</li>");
			}
		}
		sb.append("</ul>");
		Label label7 = new Label(sb.toString());
		label7.setContentMode(ContentMode.HTML);
		addComponents(label7, new Html_Separator(SeparatorType.NEW_LINE));
	}

}
