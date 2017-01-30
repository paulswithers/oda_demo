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
import org.openntf.domino.demoApp.utils.FactoryUtils;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

public class Database_CompactOptions extends BaseSubPage {
	private static final long serialVersionUID = 1L;

	public Database_CompactOptions(BaseView parentView) {
		super(parentView);
	}

	@Override
	public void loadContent() {
		Label label1 = new Label(MessageFormat.format(getProps().getProperty("compactIntro"),
				FactoryUtils.addCodeString("Database.CompactOption"),
				FactoryUtils.addCodeString("Database.compactWithOptions(int)"),
				FactoryUtils.addCodeString("Database.compactWithOptions(int, String)"),
				FactoryUtils.addCodeString("Database.compactWithOptions(Set<CompactOption>)"),
				FactoryUtils.addCodeString("Database.compactWithOptions(Set<CompactOption>, String)")));
		label1.setContentMode(ContentMode.HTML);
		addComponent(label1);
		addCompactOptions();
		addComponent(new Html_Separator(SeparatorType.NEW_LINE));
	}

	public void addCompactOptions() {
		for (Database.CompactOption opt : Database.CompactOption.values()) {
			Label label1 = new Label(opt.name());
			label1.setStyleName(ValoTheme.LABEL_H3);
			String optExplanation = "Corresponds to ";
			boolean isDefault = false;
			switch (opt) {
			case ARCHIVE_DELETE_COMPACT:
				optExplanation += FactoryUtils.addCodeString("Database.CMPC_ARCHIVE_DELETE_COMPACT");
				break;
			case ARCHIVE_DELETE_ONLY:
				optExplanation += FactoryUtils.addCodeString("Database.CMPC_ARCHIVE_DELETE_ONLY");
				break;
			case CHK_OVERLAP:
				optExplanation += FactoryUtils.addCodeString("Database.CMPC_CHK_OVERLAP");
				break;
			case COPYSTYLE:
				optExplanation += FactoryUtils.addCodeString("Database.CMPC_COPYSTYLE");
				break;
			case DISABLE_DOCTBLBIT_OPTMZN:
				optExplanation += FactoryUtils.addCodeString("Database.CMPC_DISABLE_DOCTBLBIT_OPTMZN");
				break;
			case DISABLE_LARGE_UNKTBL:
				optExplanation += FactoryUtils.addCodeString("Database.CMPC_DISABLE_LARGE_UNKTBL");
				break;
			case DISABLE_RESPONSE_INFO:
				optExplanation += FactoryUtils.addCodeString("Database.CMPC_DISABLE_RESPONSE_INFO");
				break;
			case DISABLE_TRANSACTIONLOGGING:
				optExplanation += FactoryUtils.addCodeString("Database.CMPC_DISABLE_TRANSACTIONLOGGING");
				break;
			case DISABLE_UNREAD_MARKS:
				optExplanation += FactoryUtils.addCodeString("Database.CMPC_DISABLE_UNREAD_MARKS");
				break;
			case DISCARD_VIEW_INDICIES:
				optExplanation += FactoryUtils.addCodeString("Database.CMPC_DISCARD_VIEW_INDICES");
				break;
			case ENABLE_DOCTBLBIT_OPTMZN:
				optExplanation += FactoryUtils.addCodeString("Database.CMPC_ENABLE_DOCTBLBIT_OPTMZN");
				break;
			case ENABLE_LARGE_UNKTBL:
				optExplanation += FactoryUtils.addCodeString("Database.CMPC_ENABLE_LARGE_UNKTBL");
				break;
			case ENABLE_RESPONSE_INFO:
				optExplanation += FactoryUtils.addCodeString("Database.CMPC_ENABLE_RESPONSE_INFO");
				break;
			case ENABLE_TRANSACTIONLOGGING:
				optExplanation += FactoryUtils.addCodeString("Database.CMPC_ENABLE_TRANSACTIONLOGGING");
				break;
			case ENABLE_UNREAD_MARKS:
				optExplanation += FactoryUtils.addCodeString("Database.CMPC_ENABLE_UNREAD_MARKS");
				break;
			case IGNORE_COPYSTYLE_ERRORS:
				optExplanation += FactoryUtils.addCodeString("Database.CMPC_IGNORE_COPYSTYLE_ERRORS");
				break;
			case MAX_4GB:
				optExplanation += FactoryUtils.addCodeString("Database.CMPC_MAX_4GB");
				break;
			case NO_LOCKOUT:
				optExplanation += FactoryUtils.addCodeString("Database.CMPC_NO_LOCKOUT");
				break;
			case RECOVER_INPLACE:
				optExplanation += FactoryUtils.addCodeString("Database.CMPC_RECOVER_INPLACE");
				break;
			case RECOVER_REDUCE_INPLACE:
				optExplanation += FactoryUtils.addCodeString("Database.CMPC_RECOVER_REDUCE_INPLACE");
				break;
			case REVERT_FILEFORMAT:
				optExplanation += FactoryUtils.addCodeString("Database.CMPC_REVERT_FILEFORMAT");
				break;
			default:
				isDefault = true;
				optExplanation = getProps().getProperty("compactDefault");
			}
			if (!isDefault) {
				optExplanation += " - " + Integer.toString(opt.getValue());
			}
			Label label2 = new Label(optExplanation + "<br/>", ContentMode.HTML);
			addComponents(label1, label2);
		}
	}

}
