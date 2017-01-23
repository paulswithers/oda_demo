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

public class Database_DatabaseOptions extends BaseSubPage {
	private static final long serialVersionUID = 1L;

	public Database_DatabaseOptions(BaseView parentView) {
		super(parentView);
	}

	@Override
	public void loadContent() {
		Label label1 = new Label(MessageFormat.format(getProps().getProperty("optsIntro"),
				FactoryUtils.addCodeString("Database.DBOption"), FactoryUtils.addCodeString("Database.getOption(int)"),
				FactoryUtils.addCodeString("Database.setOption(int, boolean)"),
				FactoryUtils.addCodeString("Database.getOption(DBOption)"),
				FactoryUtils.addCodeString("Database.setOption(DBOption, true)")));

		label1.setContentMode(ContentMode.HTML);
		addComponents(label1);
		addDatabaseOptions();
		addComponent(new Html_Separator(SeparatorType.NEW_LINE));
	}

	public void addDatabaseOptions() {
		for (Database.DBOption opt : Database.DBOption.values()) {
			Label label1 = new Label(opt.name());
			label1.setStyleName(ValoTheme.LABEL_H3);
			String optExplanation = "Corresponds to ";
			boolean isDefault = false;
			switch (opt) {
			case COMPRESSDESIGN:
				optExplanation += FactoryUtils.addCodeString("Database.DBOPT_COMPRESSDESIGN");
				break;
			case COMPRESSDOCUMENTS:
				optExplanation += FactoryUtils.addCodeString("Database.DBOPT_COMPRESSDOCUMENTS");
				break;
			case LZ1:
				optExplanation += FactoryUtils.addCodeString("Database.DBOPT_LZ1");
				break;
			case LZCOMPRESSION:
				optExplanation += FactoryUtils.addCodeString("Database.DBOPT_LZCOMPRESSION");
				break;
			case MAINTAINLASTACCESSED:
				optExplanation += FactoryUtils.addCodeString("Database.DBOPT_MAINTAINLASTACCESSED");
				break;
			case MOREFIELDS:
				optExplanation += FactoryUtils.addCodeString("Database.DBOPT_MOREFIELDS");
				break;
			case NOHEADLINEMONITORS:
				optExplanation += FactoryUtils.addCodeString("Database.DBOPT_NOHEADLINEMONITORS");
				break;
			case NOOVERWRITE:
				optExplanation += FactoryUtils.addCodeString("Database.DBOPT_NOOVERWRITE");
				break;
			case NORESPONSEINFO:
				optExplanation += FactoryUtils.addCodeString("Database.DBOPT_NORESPONSEINFO");
				break;
			case NOSIMPLESEARCH:
				optExplanation += FactoryUtils.addCodeString("Database.DBOPT_NOSIMPLESEARCH");
				break;
			case NOTRANSACTIONLOGGING:
				optExplanation += FactoryUtils.addCodeString("Database.DBOPT_NOTRANSACTIONLOGGING");
				break;
			case NOUNREAD:
				optExplanation += FactoryUtils.addCodeString("Database.DBOPT_NOUNREAD");
				break;
			case OPTIMIZAION:
				optExplanation += FactoryUtils.addCodeString("Database.DBOPT_OPTIMIZATION");
				break;
			case OPTIMIZATION:
				optExplanation += FactoryUtils.addCodeString("Database.DBOPT_OPTIMIZATION");
				break;
			case OUTOFOFFICEENABLED:
				optExplanation += FactoryUtils.addCodeString("Database.DBOPT_OUTOFOFFICEENABLED");
				break;
			case REPLICATEUNREADMARKSNEVER:
				optExplanation += FactoryUtils.addCodeString("Database.DBOPT_REPLICATEUNREADMARKSNEVER");
				break;
			case REPLICATEUNREADMARKSTOANY:
				optExplanation += FactoryUtils.addCodeString("Database.DBOPT_REPLICATEUNREADMARKSTOANY");
				break;
			case REPLICATEUNREADMARKSTOCLUSTER:
				optExplanation += FactoryUtils.addCodeString("Database.DBOPT_REPLICATEUNREADMARKSTOCLUSTER");
				break;
			case SOFTDELETE:
				optExplanation += FactoryUtils.addCodeString("Database.DBOPT_SOFTDELETE");
				break;
			case USEDAOS:
				optExplanation += FactoryUtils.addCodeString("Database.DBOPT_USEDAOS");
				break;
			default:
				isDefault = true;
				optExplanation = getProps().getProperty("optsDefault");
			}
			if (!isDefault) {
				optExplanation += " - " + Integer.toString(opt.getValue());
			}
			Label label2 = new Label(optExplanation + "<br/>", ContentMode.HTML);
			addComponents(label1, label2);
		}
	}

}
