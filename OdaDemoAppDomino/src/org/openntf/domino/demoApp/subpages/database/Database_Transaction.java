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

import org.openntf.domino.demoApp.components.Html_Separator;
import org.openntf.domino.demoApp.components.Html_Separator.SeparatorType;
import org.openntf.domino.demoApp.components.StateSelector;
import org.openntf.domino.demoApp.pages.BaseView;
import org.openntf.domino.demoApp.subpages.BaseSubPage;
import org.openntf.domino.demoApp.utils.DatabaseUtils;
import org.openntf.domino.demoApp.utils.FactoryUtils;

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
		Label label2 = new Label(MessageFormat.format(getProps().getProperty("transIntro"),
				FactoryUtils.addCodeString("DatabaseTransaction"), FactoryUtils.addCodeString("Database"),
				FactoryUtils.addCodeString("DatabaseTransaction"), FactoryUtils.addCodeString("Database"),
				FactoryUtils.addCodeString("Database.setTransaction(DatabaseTransaction)"),
				FactoryUtils.addCodeString("DatabaseTransaction"), FactoryUtils.addCodeString("Database"),
				FactoryUtils.addCodeString("Database.startTransaction()"),
				FactoryUtils.addCodeString("DatabaseTransaction"), FactoryUtils.addCodeString("DatabaseTransaction"),
				FactoryUtils.addCodeString("Database"), FactoryUtils.addCodeString("Database.closeTransaction()"),
				FactoryUtils.addCodeString("DatabaseTransaction")));
		label2.setContentMode(ContentMode.HTML);
		Label label3 = new Label("Processing the Database Transaction");
		label3.setStyleName(ValoTheme.LABEL_H3);
		Label label4 = new Label(MessageFormat.format(getProps().getProperty("transProc"),
				FactoryUtils.addCodeString("Item"), FactoryUtils.addCodeString("Document"),
				FactoryUtils.addCodeString("DatabaseTransaction"), FactoryUtils.addCodeString("DatabaseTransaction"),
				FactoryUtils.addCodeString("DatabaseTransaction.queueUpdate()"),
				FactoryUtils.addCodeString("DatabaseTransaction"),
				FactoryUtils.addCodeString("DatabaseTransaction.queueRemove()"),
				FactoryUtils.addCodeString("DatabaseTransaction.getUpdateSize()"),
				FactoryUtils.addCodeString("DatabaseTransaction.getRemoveSize()"),
				FactoryUtils.addCodeString("DatabaseTransaction"),
				FactoryUtils.addCodeString("DatabaseTransaction.commit()"),
				FactoryUtils.addCodeString("DatabaseTransaction.rollback()")));
		Label label5 = new Label(getProps().getProperty("transDemo"));
		Label label6 = new Label("State:");
		Button button1 = new Button("Run Transaction and Roll Back");
		button1.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				getOutputLabel()
						.setCaption(DatabaseUtils.transactionTest((String) getStateSelector().getValue(), false));
			}
		});
		button1.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		button1.addStyleName("floating-btn");
		Button button2 = new Button("Run Transaction and Commit");
		button2.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				getOutputLabel()
						.setCaption(DatabaseUtils.transactionTest((String) getStateSelector().getValue(), true));
			}
		});
		button2.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		button2.addStyleName("floating-btn");
		getOutputLabel().setCaptionAsHtml(true);
		addComponents(label5, new Html_Separator(SeparatorType.NEW_LINE), label6, getStateSelector(), button1, button2,
				getOutputLabel());
	}

	public Label getOutputLabel() {
		return outputLabel;
	}

	public StateSelector getStateSelector() {
		return stateSelector;
	}

}
