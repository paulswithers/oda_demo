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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.Future;

import org.openntf.domino.Document;
import org.openntf.domino.View;
import org.openntf.domino.demoApp.application.XotsTests.StateUserSummary;
import org.openntf.domino.demoApp.components.Html_Separator;
import org.openntf.domino.demoApp.components.Html_Separator.SeparatorType;
import org.openntf.domino.demoApp.pages.BaseView;
import org.openntf.domino.demoAppUtil.FactoryUtils;
import org.openntf.domino.xots.Xots;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.themes.ValoTheme;

public class Xots_CallableExample extends BaseSubPage {
	private static final long serialVersionUID = 1L;

	public Xots_CallableExample(BaseView parentView) {
		super(parentView);
	}

	@Override
	public void loadContent() {
		final Panel panel = new Panel();
		final Button button1 = new Button("Count Users By State");
		button1.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				testThread(panel);
			}
		});
		addComponents(button1, new Html_Separator(SeparatorType.NEW_LINE), panel, new Html_Separator(SeparatorType.NEW_LINE));
	}

	private void testThread(Panel panel) {

		try {
			// Get a list of states names for state codes
			final TreeMap<String, String> states = new TreeMap<String, String>();
			final View allStates = FactoryUtils.getDemoDatabase().getView("AllStates");
			for (final Document doc : allStates.getAllDocuments()) {
				states.put(doc.getItemValueString("Key"), doc.getItemValueString("Name"));
			}

			// Load futures
			final List<Future<TreeMap<String, Integer>>> results = new ArrayList<Future<TreeMap<String, Integer>>>();
			final String demoDbFolder = FactoryUtils.getDemoDatabasesFolder();
			final int numberOfDemos = FactoryUtils.getNumberOfDemosAsInt();
			for (Integer i = 1; i <= numberOfDemos; i++) {
				final String dbPath = demoDbFolder + "/oda_" + i.toString() + ".nsf";
				results.add(Xots.getService().submit(new StateUserSummary(i, dbPath, states)));
			}

			// Add states and "Totals" labels to grid
			final GridLayout grid = new GridLayout(numberOfDemos + 2, states.size() + 1);
			grid.setDefaultComponentAlignment(Alignment.TOP_CENTER);
			grid.setWidth(100, Unit.PERCENTAGE);
			int row = 1;
			for (final String state : states.keySet()) {
				final Label label1 = new Label(states.get(state));
				label1.setStyleName(ValoTheme.LABEL_COLORED);
				label1.setWidth(170, Unit.PIXELS);
				grid.addComponent(label1, 0, row);
				grid.setComponentAlignment(label1, Alignment.TOP_LEFT);
				row++;
			}
			final Label label1 = new Label("Totals");
			label1.setStyleName(ValoTheme.LABEL_COLORED);
			grid.addComponent(label1, numberOfDemos + 1, 0);

			// Initialise totals map
			final HashMap<String, Integer> totals = new HashMap<String, Integer>();
			for (final String stateKey : states.keySet()) {
				totals.put(stateKey, 0);
			}

			// Loop through Callables
			for (final Future<TreeMap<String, Integer>> f : results) {
				final TreeMap<String, Integer> output = f.get();
				final Integer dbNo = output.get("dbNo");
				// Add Db label
				final Label label2 = new Label("Demo " + dbNo.toString());
				label2.setStyleName(ValoTheme.LABEL_COLORED);
				grid.addComponent(label2, dbNo, 0);
				// Add Database-State tally and add to totals
				row = 1;
				for (final String state : states.keySet()) {
					final Integer count = output.get(state);
					grid.addComponent(new Label(count.toString()), dbNo, row);
					totals.put(state, totals.get(state) + count);
					row++;
				}
			}

			row = 1;
			for (final String state : states.keySet()) {
				final Integer count = totals.get(state);
				final Label label3 = new Label(count.toString());
				label3.setStyleName(ValoTheme.LABEL_BOLD);
				grid.addComponent(label3, numberOfDemos + 1, row);
				row++;
			}
			panel.setContent(grid);
		} catch (final Exception e) {
			panel.setContent(new Label(e.toString()));
			e.printStackTrace();
		}
	}

}
