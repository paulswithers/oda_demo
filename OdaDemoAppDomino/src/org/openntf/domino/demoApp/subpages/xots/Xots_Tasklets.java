package org.openntf.domino.demoApp.subpages.xots;

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

import org.openntf.domino.demoApp.pages.BaseView;
import org.openntf.domino.demoApp.subpages.BaseSubPage;
import org.openntf.domino.demoApp.utils.FactoryUtils;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;

public class Xots_Tasklets extends BaseSubPage {
	private static final long serialVersionUID = 1L;

	public Xots_Tasklets(BaseView parentView) {
		super(parentView);
	}

	@Override
	public void loadContent() {
		Label label1 = new Label(MessageFormat.format(getProps().getProperty("taskletRules"),
				FactoryUtils.addCodeString("org.openntf.domino.xots.AbstractXotsCallable"),
				FactoryUtils.addCodeString("org.openntf.domino.xots.AbstractXotsRunnable"),
				FactoryUtils.addCodeString("org.openntf.domino.xsp.xots.AbstractXspXotsCallable"),
				FactoryUtils.addCodeString("org.openntf.domino.xsp.xots.AbstractXspXotsRunnable"),
				FactoryUtils.addCodeString("@Tasklet"), FactoryUtils.addCodeString("session"),
				FactoryUtils.addCodeString("Tasklet.Session")));
		label1.setContentMode(ContentMode.HTML);
		Label label2 = new Label(MessageFormat.format(getProps().getProperty("taskletContext"),
				FactoryUtils.addCodeString("org.openntf.domino.xots.XotsContext"),
				FactoryUtils.addCodeString("contextApiPath"), FactoryUtils.addCodeString("openLogApiPath"),
				FactoryUtils.addCodeString("taskletClass")));
		label2.setContentMode(ContentMode.HTML);
		addComponents(label1, label2);
		if (FactoryUtils.contextIsXPagesOrBoth()) {
			Label label3 = new Label(MessageFormat.format(getProps().getProperty("taskletXspContext"),
					FactoryUtils.addCodeString("org.openntf.domino.xsp.xots.XotsXspContext"),
					FactoryUtils.addCodeString("requestScope, viewScope, sessionScope, applicationScope"),
					FactoryUtils.addCodeString("facesContext"), FactoryUtils.addCodeString("XspContext")));
			label3.setContentMode(ContentMode.HTML);
			addComponent(label3);
		}
		Label label4 = new Label(MessageFormat.format(getProps().getProperty("taskletRun"),
				FactoryUtils.addCodeString("AbstractXotsRunnable"),
				FactoryUtils.addCodeString("AbstractXotsCallable")));
		label4.setContentMode(ContentMode.HTML);
		addComponent(label4);
	}

}
