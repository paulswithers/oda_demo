
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
package org.openntf.domino.demoApp.subpages.document;

import java.text.MessageFormat;

import org.openntf.domino.demoApp.pages.BaseView;
import org.openntf.domino.demoApp.subpages.BaseSubPage;
import org.openntf.domino.demoApp.utils.FactoryUtils;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;

public class Document_SyncHelper extends BaseSubPage {
	private static final long serialVersionUID = 1L;

	public Document_SyncHelper(BaseView parentView) {
		super(parentView);
	}

	@Override
	public void loadContent() {
		Label label1 = new Label(getProps().getProperty("syncIntro"));
		Label label2 = new Label(MessageFormat.format(getProps().getProperty("syncHelp"),
				FactoryUtils.addCodeString("map.put(\"Name\",\"CompanyName\")"),
				FactoryUtils.addCodeString("DocumentSyncHelper"),
				FactoryUtils.addCodeString("DocumentSyncHelper.Strategy"),
				FactoryUtils.addCodeString("View.getAllDocumentsByKey()"), FactoryUtils.addCodeString("process()")));
		label2.setContentMode(ContentMode.HTML);
		addComponents(label1, label2);
	}

}
