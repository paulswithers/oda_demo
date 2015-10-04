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

import org.openntf.domino.demoApp.pages.BaseView;
import org.openntf.domino.demoAppUtil.FactoryUtils;
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
		Label label1 = new Label("Database Listeners can be used to run code automatically for various events. "
				+ "There are currently only Events added at Database level. The Events that have hooks are:");
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
				eventExplanation = "Triggered at the " + beforeAfter + " of the " + FactoryUtils.addCodeString("Database.createDocument()")
						+ " method. Source and target will both be the database - the newly-created document has no properties or items set, "
						+ "so there is not point passing that.";
				break;
			case AFTER_DELETE_DOCUMENT:
				beforeAfter = "end";
			case BEFORE_DELETE_DOCUMENT:
				eventExplanation = "Triggered at the " + beforeAfter + " of the " + FactoryUtils.addCodeString("Document.remove()") + " or "
						+ FactoryUtils.addCodeString("Document.removePermanently()") + " methods. The source will be the document "
						+ "and the target will be the database.";
				break;
			case AFTER_REPLICATION:
				beforeAfter = "end";
			case BEFORE_REPLICATION:
				eventExplanation = "Triggered at the " + beforeAfter + " of the " + FactoryUtils.addCodeString("Database.replicate()") + " method. "
						+ " The source will be the database and the target will be the server the replication is to be performed with.";
				break;
			case AFTER_RUN_AGENT:
				beforeAfter = "end";
			case BEFORE_RUN_AGENT:
				eventExplanation = "Triggered at the " + beforeAfter + " of the " + FactoryUtils.addCodeString("Agent.run()")
						+ " method and its variants. The source will be the database and the target will be the agent.";
				break;
			case BEFORE_UPDATE_DOCUMENT:
				beforeAfter = "end";
			case AFTER_UPDATE_DOCUMENT:
				eventExplanation = "Triggered at the " + beforeAfter + " of the " + FactoryUtils.addCodeString("Document.save()")
						+ " method and its variants. The source will be the document and the target will be the database.";
				break;
			default:
				eventExplanation = "Whoops!! This must be a new switch. Please let us know so we can add documentation.";
				break;
			}
			Label label3 = new Label(eventExplanation + "<br/>", ContentMode.HTML);
			addComponents(label2, label3);
		}
		Label label4 = new Label("A Listener must implement the IDominoListener interface. The IDominoListener interface has two methods:<ul>"
				+ "<li>" + FactoryUtils.addCodeString("getEventTypes()") + " - returns a List of EnumEvents managed by this Listener. "
				+ "(EvenEvent is the interface which" + FactoryUtils.addCodeString("org.openntf.domino.ext.Database.Events") + " implements.)</li>"
				+ "<li>" + FactoryUtils.addCodeString("eventHappened(IDominoEvent)")
				+ " - runs the code, passing the current EnumEvent being processed."
				+ " Use an 'if' statement to check which event is being triggered and run the relevant code.");
		label4.setContentMode(ContentMode.HTML);
		addComponent(label4);

	}

}
