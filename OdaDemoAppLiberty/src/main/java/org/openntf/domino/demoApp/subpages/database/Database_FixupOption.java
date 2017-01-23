package org.openntf.domino.demoApp.subpages.database;

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

import org.openntf.domino.Database;
import org.openntf.domino.demoApp.components.Html_Separator;
import org.openntf.domino.demoApp.components.Html_Separator.SeparatorType;
import org.openntf.domino.demoApp.pages.BaseView;
import org.openntf.domino.demoApp.subpages.BaseSubPage;
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
		Label label1 = new Label(MessageFormat.format(getProps().getProperty("fixupIntro"),
				FactoryUtils.addCodeString("Database.FixupOption"), FactoryUtils.addCodeString("Database.fixup(int)"),
				FactoryUtils.addCodeString("Database.fixup(Set<FixupOption>)")));
		label1.setContentMode(ContentMode.HTML);
		addComponents(label1);
		addFixupOptions();
		addComponent(new Html_Separator(SeparatorType.NEW_LINE));
	}

	public void addFixupOptions() {
		for (Database.FixupOption opt : Database.FixupOption.values()) {
			Label label1 = new Label(opt.name());
			label1.setStyleName(ValoTheme.LABEL_H3);
			String optExplanation = "Corresponds to ";
			boolean isDefault = false;
			switch (opt) {
			case INCREMENTAL:
				optExplanation += FactoryUtils.addCodeString("Database.FIXUP_INCREMENTAL");
				break;
			case NODELETE:
				optExplanation += FactoryUtils.addCodeString("Database.FIXUP_NODELETE");
				break;
			case NOVIEWS:
				optExplanation += FactoryUtils.addCodeString("Database.FIXUP_NOVIEWS");
				break;
			case QUICK:
				optExplanation += FactoryUtils.addCodeString("Database.FIXUP_QUICK");
				break;
			case REVERT:
				optExplanation += FactoryUtils.addCodeString("Database.FIXUP_REVERT");
				break;
			case TXLOGGED:
				optExplanation += FactoryUtils.addCodeString("Database.FIXUP_TXLOGGED");
				break;
			case VERIFY:
				optExplanation += FactoryUtils.addCodeString("Database.FIXUP_VERIFY");
				break;
			default:
				isDefault = true;
				optExplanation = getProps().getProperty("fixupDefault");
			}
			if (!isDefault) {
				optExplanation += " - " + Integer.toString(opt.getValue());
			}
			Label label2 = new Label(optExplanation + "<br/>", ContentMode.HTML);
			addComponents(label1, label2);
		}
	}

}
