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

import java.util.concurrent.Future;

import org.openntf.domino.demoApp.application.XotsTests.SessionCallable;
import org.openntf.domino.demoApp.components.Html_Separator;
import org.openntf.domino.demoApp.components.Html_Separator.SeparatorType;
import org.openntf.domino.demoApp.pages.BaseView;
import org.openntf.domino.demoApp.subpages.BaseSubPage;
import org.openntf.domino.demoApp.utils.FactoryUtils;
import org.openntf.domino.xots.Xots;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

public class Xots_Summary extends BaseSubPage {
	private static final long serialVersionUID = 1L;

	public Xots_Summary(BaseView parentView) {
		super(parentView);
	}

	@Override
	public void loadContent() {
		Label label1 = new Label(getProps().getProperty("xotsIntro"));
		Label label2 = new Label(MessageFormat.format(getProps().getProperty("xotsIntro2"),
				FactoryUtils.addCodeString("Xots.getService()"), FactoryUtils.addCodeString("submit(Runnable)"),
				FactoryUtils.addCodeString("submit(Callable)")));
		label2.setContentMode(ContentMode.HTML);
		Label label3 = new Label("Architecture");
		label3.setStyleName(ValoTheme.LABEL_H3);
		Label label4 = new Label(getProps().getProperty("xotsArch"));
		label4.setContentMode(ContentMode.HTML);
		Label label5 = new Label("Limitations (as at ODA 3.2.0)");
		label5.setStyleName(ValoTheme.LABEL_H3);
		Label label6 = new Label(
				MessageFormat.format(getProps().getProperty("xotsLimit"), FactoryUtils.addCodeString("invokeAll()"),
						FactoryUtils.addCodeString("submit()"), FactoryUtils.addCodeString("XotsContext")));
		label6.setContentMode(ContentMode.HTML);
		Label label7 = new Label("Xots Tasklet Retrieving Current Username");
		label7.setStyleName(ValoTheme.LABEL_H3);
		Label label8 = testSessionPassing();

		addComponents(label1, label2, label3, label4, label5, label6, label7, label8,
				new Html_Separator(SeparatorType.NEW_LINE));

	}

	private Label testSessionPassing() {
		Future<Object> future = Xots.getService().submit(new SessionCallable());
		Label label6 = new Label("XOTS FAILED");
		try {
			label6.setValue((String) future.get());
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return label6;
	}

}
