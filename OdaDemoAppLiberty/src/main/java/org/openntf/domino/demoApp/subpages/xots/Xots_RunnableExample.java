package org.openntf.domino.demoApp.subpages.xots;

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

import java.util.concurrent.ConcurrentSkipListSet;

import org.openntf.domino.demoApp.application.ContactSummary;
import org.openntf.domino.demoApp.application.XotsTests.UserMergeView;
import org.openntf.domino.demoApp.components.StateSelector;
import org.openntf.domino.demoApp.pages.BaseView;
import org.openntf.domino.demoApp.subpages.BaseSubPage;
import org.openntf.domino.demoApp.utils.FactoryUtils;
import org.openntf.domino.xots.Xots;

import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class Xots_RunnableExample extends BaseSubPage {
	private static final long serialVersionUID = 1L;
	private VerticalLayout viewPanel;
	private BeanContainer contactsContainer = new BeanContainer(ContactSummary.class);
	private ConcurrentSkipListSet<ContactSummary> contacts = new ConcurrentSkipListSet<ContactSummary>();
	private ConcurrentSkipListSet<String> completeThreads = new ConcurrentSkipListSet<String>();

	public Xots_RunnableExample(BaseView parentView) {
		super(parentView);
	}

	@Override
	public void load() {
		super.load();
		if (getCompleteThreads().size() == FactoryUtils.getNumberOfDemosAsInt()) {
			for (ContactSummary contact : getContacts()) {
				getContactsContainer().addBean(contact);
			}
			setContacts(new ConcurrentSkipListSet<ContactSummary>());
			setCompleteThreads(new ConcurrentSkipListSet<String>());
			new Notification("Loaded " + getContactsContainer().size() + " contacts", Notification.Type.HUMANIZED_MESSAGE)
					.show(UI.getCurrent().getPage());
		}
	}

	@Override
	public void loadContent() {
		Label label1 = new Label("State:");
		StateSelector states = new StateSelector();
		states.addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(com.vaadin.data.Property.ValueChangeEvent event) {
				loadView((String) event.getProperty().getValue());
			}
		});

		states.setValue("FL");
		loadView((String) states.getValue());

		Grid viewGrid = new Grid();
		viewGrid.setSizeFull();
		viewGrid.setContainerDataSource(getContactsContainer());
		Grid.Column col = viewGrid.getColumn("firstName");
		col.setHeaderCaption("First Name");
		col.setSortable(true);
		col = viewGrid.getColumn("lastName");
		col.setHeaderCaption("Last Name");
		col.setSortable(true);
		viewGrid.setColumnOrder("firstName", "lastName", "email", "city", "state");

		addComponents(label1, states, viewGrid);
	}

	public void loadView(String stateKey) {
		Notification msg = new Notification("Just gathering all Contacts for " + stateKey + ". You will be notified once complete.");
		msg.show(UI.getCurrent().getPage());
		getContactsContainer().removeAllItems();
		getContactsContainer().setBeanIdProperty("metaversalId");

		String demoDbFolder = FactoryUtils.getDemoDatabasesFolder();
		int numberOfDemos = FactoryUtils.getNumberOfDemosAsInt();
		for (Integer i = 1; i <= numberOfDemos; i++) {
			String dbPath = demoDbFolder + "/oda_" + i.toString() + ".nsf";
			Xots.getService().submit(new UserMergeView(dbPath, stateKey, contacts, completeThreads, UI.getCurrent()));
		}
	}

	public VerticalLayout getViewPanel() {
		return viewPanel;
	}

	public void setViewPanel(VerticalLayout viewPanel) {
		this.viewPanel = viewPanel;
	}

	public BeanContainer getContactsContainer() {
		return contactsContainer;
	}

	public void setContactsContainer(BeanContainer contactsContainer) {
		this.contactsContainer = contactsContainer;
	}

	public ConcurrentSkipListSet<ContactSummary> getContacts() {
		return contacts;
	}

	public void setContacts(ConcurrentSkipListSet<ContactSummary> contacts) {
		this.contacts = contacts;
	}

	public ConcurrentSkipListSet<String> getCompleteThreads() {
		return completeThreads;
	}

	public void setCompleteThreads(ConcurrentSkipListSet<String> completeThreads) {
		this.completeThreads = completeThreads;
	}

}
