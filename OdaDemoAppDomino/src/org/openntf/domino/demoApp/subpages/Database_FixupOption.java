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

import org.openntf.domino.Database;
import org.openntf.domino.demoApp.components.Html_Separator;
import org.openntf.domino.demoApp.components.Html_Separator.SeparatorType;
import org.openntf.domino.demoApp.pages.BaseView;
import org.openntf.domino.demoAppUtil.FactoryUtils;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

public class Database_FixupOption extends BaseSubPage {
	private static final long serialVersionUID = 1L;

	public Database_FixupOption(BaseView parentView) {
		super(parentView);
	}

	@Override
	public void loadContent() {
		final Label label1 = new Label("The " + FactoryUtils.addCodeString("Database.FixupOption")
				+ " admin-related enums have been added to make code more readable. The core API has " + FactoryUtils.addCodeString("Database.fixup(int)")
				+ ", where the int is a total of the integers for the selected settings, e.g. incremental with no views " + "would expect 4 + 64 = 68. The new method "
				+ FactoryUtils.addCodeString("Database.fixup(Set<FixupOption>)")
				+ " take instead a Set of the options selected. Resulting code takes more lines, but is easier to support.");
		label1.setContentMode(ContentMode.HTML);
		addComponents(label1);
		addFixupOptions();
		addComponent(new Html_Separator(SeparatorType.NEW_LINE));
	}

	public void addFixupOptions() {
		for (final Database.FixupOption opt : Database.FixupOption.values()) {
			final Label label1 = new Label(opt.name());
			label1.setStyleName(ValoTheme.LABEL_H3);
			String optExplanation = "";
			boolean isDefault = false;
			switch (opt) {
			case INCREMENTAL:
				optExplanation = "Corresponds to " + FactoryUtils.addCodeString("Database.FIXUP_INCREMENTAL");
				break;
			case NODELETE:
				optExplanation = "Corresponds to " + FactoryUtils.addCodeString("Database.FIXUP_NODELETE");
				break;
			case NOVIEWS:
				optExplanation = "Corresponds to " + FactoryUtils.addCodeString("Database.FIXUP_NOVIEWS");
				break;
			case QUICK:
				optExplanation = "Corresponds to " + FactoryUtils.addCodeString("Database.FIXUP_QUICK");
				break;
			case REVERT:
				optExplanation = "Corresponds to " + FactoryUtils.addCodeString("Database.FIXUP_REVERT");
				break;
			case TXLOGGED:
				optExplanation = "Corresponds to " + FactoryUtils.addCodeString("Database.FIXUP_TXLOGGED");
				break;
			case VERIFY:
				optExplanation = "Corresponds to " + FactoryUtils.addCodeString("Database.FIXUP_VERIFY");
				break;
			default:
				isDefault = true;
				optExplanation = "Whoops!! This must be a new setting. Please let us know so we can add documentation.";
			}
			if (!isDefault) {
				optExplanation = optExplanation + " - " + Integer.toString(opt.getValue());
			}
			final Label label2 = new Label(optExplanation + "<br/>", ContentMode.HTML);
			addComponents(label1, label2);
		}
	}

}