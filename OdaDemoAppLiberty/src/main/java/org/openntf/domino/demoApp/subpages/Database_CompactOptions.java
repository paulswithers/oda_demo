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

public class Database_CompactOptions extends BaseSubPage {
	private static final long serialVersionUID = 1L;

	public Database_CompactOptions(BaseView parentView) {
		super(parentView);
	}

	public void loadContent() {
		Label label1 = new Label("The " + FactoryUtils.addCodeString("Database.CompactOption")
				+ " admin-related enums have been added to make code more readable. The core API has "
				+ FactoryUtils.addCodeString("Database.compactWithOptions(int)") + " and "
				+ FactoryUtils.addCodeString("Database.compactWithOptions(int)")
				+ ", where the int is a total of the integers for the selected settings, e.g. a copy style compact discarding view indices "
				+ "would expect 16 + 32 = 48. The new methods " + FactoryUtils.addCodeString("Database.compactWithOptions(Set<CompactOption>)")
				+ " and " + FactoryUtils.addCodeString("Database.compactWithOptions(Set<CompactOption>, string)")
				+ " take instead a Set of the options selected. Resulting code takes more lines, but is easier to support.");
		label1.setContentMode(ContentMode.HTML);
		addComponent(label1);
		addCompactOptions();
		addComponent(new Html_Separator(SeparatorType.NEW_LINE));
	}

	public void addCompactOptions() {
		for (Database.CompactOption opt : Database.CompactOption.values()) {
			Label label1 = new Label(opt.name());
			label1.setStyleName(ValoTheme.LABEL_H3);
			String optExplanation = "";
			boolean isDefault = false;
			switch (opt) {
			case ARCHIVE_DELETE_COMPACT:
				optExplanation = "Corresponds to " + FactoryUtils.addCodeString("Database.CMPC_ARCHIVE_DELETE_COMPACT");
				break;
			case ARCHIVE_DELETE_ONLY:
				optExplanation = "Corresponds to " + FactoryUtils.addCodeString("Database.CMPC_ARCHIVE_DELETE_ONLY");
				break;
			case CHK_OVERLAP:
				optExplanation = "Corresponds to " + FactoryUtils.addCodeString("Database.CMPC_CHK_OVERLAP");
				break;
			case COPYSTYLE:
				optExplanation = "Corresponds to " + FactoryUtils.addCodeString("Database.CMPC_COPYSTYLE");
				break;
			case DISABLE_DOCTBLBIT_OPTMZN:
				optExplanation = "Corresponds to " + FactoryUtils.addCodeString("Database.CMPC_DISABLE_DOCTBLBIT_OPTMZN");
				break;
			case DISABLE_LARGE_UNKTBL:
				optExplanation = "Corresponds to " + FactoryUtils.addCodeString("Database.CMPC_DISABLE_LARGE_UNKTBL");
				break;
			case DISABLE_RESPONSE_INFO:
				optExplanation = "Corresponds to " + FactoryUtils.addCodeString("Database.CMPC_DISABLE_RESPONSE_INFO");
				break;
			case DISABLE_TRANSACTIONLOGGING:
				optExplanation = "Corresponds to " + FactoryUtils.addCodeString("Database.CMPC_DISABLE_TRANSACTIONLOGGING");
				break;
			case DISABLE_UNREAD_MARKS:
				optExplanation = "Corresponds to " + FactoryUtils.addCodeString("Database.CMPC_DISABLE_UNREAD_MARKS");
				break;
			case DISCARD_VIEW_INDICIES:
				optExplanation = "Corresponds to " + FactoryUtils.addCodeString("Database.CMPC_DISCARD_VIEW_INDICES");
				break;
			case ENABLE_DOCTBLBIT_OPTMZN:
				optExplanation = "Corresponds to " + FactoryUtils.addCodeString("Database.CMPC_ENABLE_DOCTBLBIT_OPTMZN");
				break;
			case ENABLE_LARGE_UNKTBL:
				optExplanation = "Corresponds to " + FactoryUtils.addCodeString("Database.CMPC_ENABLE_LARGE_UNKTBL");
				break;
			case ENABLE_RESPONSE_INFO:
				optExplanation = "Corresponds to " + FactoryUtils.addCodeString("Database.CMPC_ENABLE_RESPONSE_INFO");
				break;
			case ENABLE_TRANSACTIONLOGGING:
				optExplanation = "Corresponds to " + FactoryUtils.addCodeString("Database.CMPC_ENABLE_TRANSACTIONLOGGING");
				break;
			case ENABLE_UNREAD_MARKS:
				optExplanation = "Corresponds to " + FactoryUtils.addCodeString("Database.CMPC_ENABLE_UNREAD_MARKS");
				break;
			case IGNORE_COPYSTYLE_ERRORS:
				optExplanation = "Corresponds to " + FactoryUtils.addCodeString("Database.CMPC_IGNORE_COPYSTYLE_ERRORS");
				break;
			case MAX_4GB:
				optExplanation = "Corresponds to " + FactoryUtils.addCodeString("Database.CMPC_MAX_4GB");
				break;
			case NO_LOCKOUT:
				optExplanation = "Corresponds to " + FactoryUtils.addCodeString("Database.CMPC_NO_LOCKOUT");
				break;
			case RECOVER_INPLACE:
				optExplanation = "Corresponds to " + FactoryUtils.addCodeString("Database.CMPC_RECOVER_INPLACE");
				break;
			case RECOVER_REDUCE_INPLACE:
				optExplanation = "Corresponds to " + FactoryUtils.addCodeString("Database.CMPC_RECOVER_REDUCE_INPLACE");
				break;
			case REVERT_FILEFORMAT:
				optExplanation = "Corresponds to " + FactoryUtils.addCodeString("Database.CMPC_REVERT_FILEFORMAT");
				break;
			default:
				isDefault = true;
				optExplanation = "Whoops!! This must be a new setting. Please let us know so we can add documentation.";
			}
			if (!isDefault) {
				optExplanation = optExplanation + " - " + Integer.toString(opt.getValue());
			}
			Label label2 = new Label(optExplanation + "<br/>", ContentMode.HTML);
			addComponents(label1, label2);
		}
	}

}
