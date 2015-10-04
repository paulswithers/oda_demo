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

import org.openntf.domino.demoApp.components.Html_Separator;
import org.openntf.domino.demoApp.components.Html_Separator.SeparatorType;
import org.openntf.domino.demoApp.components.StateSelector;
import org.openntf.domino.demoApp.pages.BaseView;
import org.openntf.domino.demoAppUtil.DatabaseUtils;
import org.openntf.domino.demoAppUtil.FactoryUtils;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

public class Database_Transaction extends BaseSubPage {
	private static final long serialVersionUID = 1L;
	private final Label outputLabel = new Label();
	private StateSelector stateSelector;

	public Database_Transaction(BaseView parentView) {
		super(parentView);
	}

	@Override
	public void loadContent() {
		stateSelector = new StateSelector();
		Label label1 = new Label("Defining a Database Transaction.");
		label1.setStyleName(ValoTheme.LABEL_H3);
		Label label2 = new Label("A " + FactoryUtils.addCodeString("DatabaseTransaction") + " is assigned to one or more "
				+ FactoryUtils.addCodeString("Database") + " objects. Assigning multiple will allow a cross-database transaction"
				+ "There are two ways to load a " + FactoryUtils.addCodeString("DatabaseTransaction") + " for a "
				+ FactoryUtils.addCodeString("Database") + ". <ul><li>Call "
				+ FactoryUtils.addCodeString("Database.setTransaction(DatabaseTransaction)") + ". This method will throw an error if a "
				+ FactoryUtils.addCodeString("DatabaseTransaction") + " is already assigned to the " + FactoryUtils.addCodeString("Database")
				+ "</li><li>Call " + FactoryUtils.addCodeString("Database.startTransaction()") + " which will create a new "
				+ FactoryUtils.addCodeString("DatabaseTransaction") + " if none has already been created and return the "
				+ FactoryUtils.addCodeString("DatabaseTransaction") + "assigned to the " + FactoryUtils.addCodeString("Database") + "</li></ul>"
				+ "In the finally block the developer should call " + FactoryUtils.addCodeString(
						"Database.closeTransaction()" + " to clear the " + FactoryUtils.addCodeString("DatabaseTransaction") + " from the Database"));
		label2.setContentMode(ContentMode.HTML);
		Label label3 = new Label("Processing the Database Transaction");
		label3.setStyleName(ValoTheme.LABEL_H3);
		Label label4 = new Label("Create or Update processes on any document's " + FactoryUtils.addCodeString("Item") + " will add that "
				+ FactoryUtils.addCodeString("Document") + " to the " + FactoryUtils.addCodeString("DatabaseTransaction")
				+ ". But any Domino object can be added to a " + FactoryUtils.addCodeString("DatabaseTransaction") + " by calling "
				+ FactoryUtils.addCodeString("DatabaseTransaction.queueUpdate()") + ". Documents for deletion can be added to the "
				+ FactoryUtils.addCodeString("DatabaseTransaction") + " by calling " + FactoryUtils.addCodeString("DatabaseTransaction.queueRemove()")
				+ " The number of updates or deletes queued can be found by calling "
				+ FactoryUtils.addCodeString("DatabaseTransaction.getUpdateSize()") + " or "
				+ FactoryUtils.addCodeString("DatabaseTransaction.getRemoveSize()") + ". The " + FactoryUtils.addCodeString("DatabaseTransaction")
				+ " can be processed by calling " + FactoryUtils.addCodeString("DatabaseTransaction.commit()") + " or aborted by calling "
				+ FactoryUtils.addCodeString("DatabaseTransaction.rollback()"));
		Label label5 = new Label("The following demo shows transaction processing within Domino. Changing the state will "
				+ "update the txProcessed field on every other contact for that state in oda_1.nsf. "
				+ "The first button will run the transaction and roll back. The second button will run the transaction and save."
				+ "Only contacts updated will be saved. This is handled automatically by the transactional code.");
		Label label6 = new Label("State:");
		Button button1 = new Button("Run Transaction and Roll Back");
		button1.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				getOutputLabel().setCaption(DatabaseUtils.transactionTest((String) getStateSelector().getValue(), false));
			}
		});
		button1.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		button1.addStyleName("floating-btn");
		Button button2 = new Button("Run Transaction and Commit");
		button2.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				getOutputLabel().setCaption(DatabaseUtils.transactionTest((String) getStateSelector().getValue(), true));
			}
		});
		button2.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		button2.addStyleName("floating-btn");
		getOutputLabel().setContentMode(ContentMode.HTML);
		addComponents(label5, new Html_Separator(SeparatorType.NEW_LINE), label6, getStateSelector(), button1, button2, getOutputLabel());
	}

	public Label getOutputLabel() {
		return outputLabel;
	}

	public StateSelector getStateSelector() {
		return stateSelector;
	}

}
