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

import java.util.concurrent.Future;

import org.openntf.domino.demoApp.application.XotsTests.SessionCallable;
import org.openntf.domino.demoApp.components.Html_Separator;
import org.openntf.domino.demoApp.components.Html_Separator.SeparatorType;
import org.openntf.domino.demoApp.pages.BaseView;
import org.openntf.domino.demoAppUtil.FactoryUtils;
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
		Label label1 = new Label("Xots is designed as an all-encompassing replacement for multi-threaded processing, asynchronous jobs, "
				+ "Java agents and DOTS tasks. It is designed to enable these tasks to be coded within the application and re-use existing logic without needing to duplicate code. "
				+ "The functionality for multi-threaded or asynchronous jobs is fully-functional and production-ready.");
		Label label2 = new Label("The Xots service should be started automatically by the platform's implementation of ODA and you can access it via "
				+ FactoryUtils.addCodeString("Xots.getService()") + ". From there you can call " + FactoryUtils.addCodeString("submit(Runnable)")
				+ " or " + FactoryUtils.addCodeString("submit(Callable)") + " to register and trigger a Xots tasklet to the service. "
				+ "See the sub-pages for examples and information about creating the tasklet classes.");
		label2.setContentMode(ContentMode.HTML);
		Label label3 = new Label("Architecture");
		label3.setStyleName(ValoTheme.LABEL_H3);
		Label label4 = new Label(
				"Xots Tasklets are Java classes implementing Callable (to wait for a return value) or Runnable (for background tasks). "
						+ "The classes will also need the @Tasklet annotation. The tasklet needs passing to the Xots service, "
						+ "an extension on the AbstractDominoExecutor class. That class wraps the tasklet, as either a XotsWrappedCallable "
						+ "or XotsWrappedRunnable. Those wrappers create the relevant Session (and in the future will possibly do more) "
						+ "and allow a single method to process the tasklet.");
		label4.setContentMode(ContentMode.HTML);
		Label label5 = new Label("Limitations (as at ODA 2.0.0)");
		label5.setStyleName(ValoTheme.LABEL_H3);
		Label label6 = new Label("<ul><li>Scheduler is not yet implemented</li><li>TRUSTED session type is not yet implemented</li>" + "<li>The "
				+ FactoryUtils.addCodeString("invokeAll()") + " method doesn't work. Use " + FactoryUtils.addCodeString("submit()") + " instead.</li>"
				+ "<li>XPages scoped variables / application-specific objects you wish to re-use within the tasklet need passing into the Xots class as properties. "
				+ "See examples for more details.</li></ul>");
		label6.setContentMode(ContentMode.HTML);
		Label label7 = new Label("Xots Tasklet Retrieving Current Username");
		label7.setStyleName(ValoTheme.LABEL_H3);
		Label label8 = testSessionPassing();

		addComponents(label1, label2, label3, label4, label5, label6, label7, label8, new Html_Separator(SeparatorType.NEW_LINE));

	}

	private Label testSessionPassing() {
		Future<String> future = Xots.getService().submit(new SessionCallable());
		Label label6 = new Label("XOTS FAILED");
		try {
			label6.setValue(future.get());
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return label6;
	}

}
