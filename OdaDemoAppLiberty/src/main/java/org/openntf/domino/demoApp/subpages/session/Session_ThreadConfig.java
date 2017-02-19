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
import org.openntf.domino.demoApp.pages.SessionView;
import org.openntf.domino.demoApp.subpages.BaseSubPage;
import org.openntf.domino.demoApp.utils.FactoryUtils;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

public class Session_ThreadConfig extends BaseSubPage {
	private static final long serialVersionUID = 1L;

	public Session_ThreadConfig(BaseView parentView) {
		super(parentView);
	}

	@Override
	public void loadContent() {
		((SessionView) getParentView()).checkLoadSetupButton(this);
		Label label1 = new Label(MessageFormat.format(getProps().getProperty("threadIntro"),
				FactoryUtils.addCodeString("Factory.initThread(ThreadConfig)")), ContentMode.HTML);

		Label label2 = new Label("STRICT_THREAD_CONFIG");
		label2.setStyleName(ValoTheme.LABEL_H3);
		Label label3 = new Label(
				MessageFormat.format(getProps().getProperty("threadStrict"), FactoryUtils.addCodeString("WRAP_32k")),
				ContentMode.HTML);

		Label label4 = new Label("PERMISSIVE_THREAD_CONFIG");
		label4.setStyleName(ValoTheme.LABEL_H3);
		Label label5 = new Label(MessageFormat.format(getProps().getProperty("threadPermissive"),
				FactoryUtils.addCodeString("WRAP_ALL")), ContentMode.HTML);

		Label label6 = new Label(MessageFormat.format(getProps().getProperty("threadManual"),
				FactoryUtils.addCodeString("ThreadConfig"), FactoryUtils.addCodeString("Fixes"),
				FactoryUtils.addCodeString("AutoMime"), FactoryUtils.addCodeString("org.openntf.domino.AutoMime")),
				ContentMode.HTML);
		addComponents(label1, label2, label3, label4, label5, label6);
	}

}
