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
import org.openntf.domino.demoApp.components.Html_Separator;
import org.openntf.domino.demoApp.components.Html_Separator.SeparatorType;
import org.openntf.domino.demoApp.pages.BaseView;
import org.openntf.domino.demoApp.pages.SessionView;
import org.openntf.domino.demoApp.subpages.BaseSubPage;
import org.openntf.domino.demoApp.utils.FactoryUtils;
import org.openntf.domino.utils.Factory.SessionType;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

public class Session_Factory extends BaseSubPage {
	private static final long serialVersionUID = 1L;

	public Session_Factory(BaseView parentView) {
		super(parentView);
	}

	@Override
	public void loadContent() {
		((SessionView) getParentView()).checkLoadSetupButton(this);
		if (FactoryUtils.contextIsXPagesOrBoth()) {
			Label label1 = new Label(getProps().getProperty("factoryHistoricalTitle"));
			label1.addStyleName(ValoTheme.LABEL_H3);
			Label label2 = new Label(MessageFormat.format(getProps().getProperty("factoryHistorical"),
					FactoryUtils.addCodeString("session / opensession"),
					FactoryUtils.addCodeString("Factory.getSession()")));
			label2.setContentMode(ContentMode.HTML);
			addComponents(label1, label2);
		}

		if (FactoryUtils.contextIsXPagesOrBoth()) {
			Label label3 = new Label(getProps().getProperty("factoryXPagesTitle"));
			label3.addStyleName(ValoTheme.LABEL_H3);
			Label label4 = new Label(MessageFormat.format(getProps().getProperty("factoryXPages"),
					FactoryUtils.addCodeString("session / opensession"),
					FactoryUtils.addCodeString("Factory.getSession()"), FactoryUtils.addCodeString("SessionType")));
			label4.setContentMode(ContentMode.HTML);
			addComponents(label3, label4);
		}

		if (FactoryUtils.contextIsJavaOrBoth()) {
			Label label5 = new Label(getProps().getProperty("factoryJavaTitle"));
			label5.addStyleName(ValoTheme.LABEL_H3);
			StringBuilder sb = new StringBuilder();
			sb.append(getProps().getProperty("factoryJava"));
			sb.append(MessageFormat.format(getProps().getProperty("factoryCrossWorlds"),
					FactoryUtils.addCodeString("org.openntf.xworlds.core"),
					FactoryUtils.addCodeString("org.openntf.xworlds.appservers.webapp.XWorldsRequestFilter"),
					FactoryUtils.addCodeString(
							"org.openntf.xworlds.appservers.webapp.config.DefaultXWorldsApplicationConfig")));
			sb.append(MessageFormat.format(getProps().getProperty("factoryOsgiWorlds"),
					FactoryUtils.addCodeString("service"),
					FactoryUtils.addCodeString("org.openntf.osgiworlds.ODA_VaadinServlet"),
					FactoryUtils.addCodeString("org.openntf.osgiworlds.DefaultDominoApplicationConfig")));
			sb.append(MessageFormat.format(getProps().getProperty("factoryGraphRest"),
					FactoryUtils.addCodeString("com.ibm.domino.das.service.RestService"),
					FactoryUtils.addCodeString("com.ibm.domino.das.service.IRestServiceExt"),
					FactoryUtils.addCodeString("org.openntf.domino.rest.service.ODAGraphService"),
					FactoryUtils.addCodeString("beforeDoService"),
					FactoryUtils.addCodeString("org.openntf.domino.xsp.session.DasCurrentSessionFactory"),
					FactoryUtils.addCodeString("beforeDoService")));
			sb.append(MessageFormat.format(getProps().getProperty("factoryRest"),
					FactoryUtils.addCodeString("com.ibm.domino.services.AbstractRestServlet"),
					FactoryUtils.addCodeString("doInit()"), FactoryUtils.addCodeString("ODAPlatform.start()"),
					FactoryUtils.addCodeString("doDestroy()"), FactoryUtils.addCodeString("ODAPlatform.stop()"),
					FactoryUtils.addCodeString("doService()"),
					FactoryUtils.addCodeString("org.openntf.domino.xsp.session.DasCurrentSessionFactory")));
			Label label8 = new Label(sb.toString());
			label8.setContentMode(ContentMode.HTML);
			addComponents(label5, label8, new Html_Separator(SeparatorType.NEW_LINE));
		}

		Label label6 = new Label(getProps().getProperty("factoryNewTitle"));
		label6.addStyleName(ValoTheme.LABEL_H3);
		StringBuilder sb = new StringBuilder();
		sb.append(MessageFormat.format(getProps().getProperty("factoryNew"),
				FactoryUtils.addCodeString("Factory.getSession(SessionType)")));
		for (SessionType sessType : SessionType.values()) {
			String sessExplanation = new String("");
			switch (sessType) {
			case CURRENT:
				sessExplanation = getProps().getProperty("factoryEnumCurrent");
				break;
			case CURRENT_FULL_ACCESS:
				sessExplanation = getProps().getProperty("factoryEnumCurrentFA");
				break;
			case SIGNER:
				sessExplanation = getProps().getProperty("factoryEnumSigner");
				break;
			case SIGNER_FULL_ACCESS:
				sessExplanation = getProps().getProperty("factoryEnumSignerFA");
				break;
			case NATIVE:
				sessExplanation = getProps().getProperty("factoryEnumNative");
				break;
			case FULL_ACCESS:
				sessExplanation = getProps().getProperty("factoryEnumFA");
				break;
			case PASSWORD:
				sessExplanation = getProps().getProperty("factoryEnumPassword");
				break;
			case TRUSTED:
				sessExplanation = getProps().getProperty("factoryEnumTrusted");
				break;
			case _NAMED_internal:
				// Nothing to see, move along please!!
				break;
			case _NAMED_FULL_ACCESS_internal:
				// Nothing to see, move along please!!
				break;
			default:
				sessExplanation = getProps().getProperty("factoryEnumMissing");
			}
			if (StringUtils.isNotEmpty(sessExplanation)) {
				sb.append("<li>" + sessType.name() + " - " + sessExplanation + "</li>");
			}
		}
		sb.append("</ul>");
		Label label7 = new Label(sb.toString());
		label7.setContentMode(ContentMode.HTML);
		addComponents(label6, label7, new Html_Separator(SeparatorType.NEW_LINE));
	}

}
