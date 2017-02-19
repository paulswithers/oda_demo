package org.openntf.domino.demoApp.subpages.database;

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
import org.openntf.domino.ext.Database;
import org.openntf.domino.ext.Database.Events;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

public class Database_Listeners extends BaseSubPage {
	private static final long serialVersionUID = 1L;

	public Database_Listeners(BaseView parentView) {
		super(parentView);
	}

	@Override
	public void loadContent() {
		Label label1 = new Label(getProps().getProperty("listenersIntro"));
		addComponent(label1);
		for (Events event : Database.Events.values()) {
			Label label2 = new Label(event.name());
			label2.setStyleName(ValoTheme.LABEL_H3);
			String eventExplanation;
			String beforeAfter = "beginning";
			switch (event) {
			case AFTER_CREATE_DOCUMENT:
				beforeAfter = "end";
			case BEFORE_CREATE_DOCUMENT:
				eventExplanation = MessageFormat.format(getProps().getProperty("createDoc"), beforeAfter,
						FactoryUtils.addCodeString("Database.createDocument()"));
				break;
			case AFTER_DELETE_DOCUMENT:
				beforeAfter = "end";
			case BEFORE_DELETE_DOCUMENT:
				eventExplanation = MessageFormat.format(getProps().getProperty("deleteDoc"), beforeAfter,
						FactoryUtils.addCodeString("Document.remove()"),
						FactoryUtils.addCodeString("Document.removePermanently()"));
				break;
			case AFTER_REPLICATION:
				beforeAfter = "end";
			case BEFORE_REPLICATION:
				eventExplanation = MessageFormat.format(getProps().getProperty("replication"), beforeAfter,
						FactoryUtils.addCodeString("Database.replicate()"));
				break;
			case AFTER_RUN_AGENT:
				beforeAfter = "end";
			case BEFORE_RUN_AGENT:
				eventExplanation = MessageFormat.format(getProps().getProperty("runAgent"), beforeAfter,
						FactoryUtils.addCodeString("Agent.run()"));
				break;
			case BEFORE_UPDATE_DOCUMENT:
				beforeAfter = "end";
			case AFTER_UPDATE_DOCUMENT:
				eventExplanation = MessageFormat.format(getProps().getProperty("updateDoc"), beforeAfter,
						FactoryUtils.addCodeString("Document.save()"));
				break;
			default:
				eventExplanation = getProps().getProperty("listenerDefault");
				break;
			}
			Label label3 = new Label(eventExplanation + "<br/>", ContentMode.HTML);
			addComponents(label2, label3);
		}
		Label label4 = new Label(MessageFormat.format(getProps().getProperty("listenerImplementation"),
				FactoryUtils.addCodeString("IDominoListener"), FactoryUtils.addCodeString("IDominoListener"),
				FactoryUtils.addCodeString("getEventTypes()"), FactoryUtils.addCodeString("EnumEvent"),
				FactoryUtils.addCodeString("EnumEvent"),
				FactoryUtils.addCodeString("org.openntf.domino.ext.Database.Events"),
				FactoryUtils.addCodeString("eventHappened(IDominoEvent)")));
		label4.setContentMode(ContentMode.HTML);
		addComponent(label4);

	}

}
