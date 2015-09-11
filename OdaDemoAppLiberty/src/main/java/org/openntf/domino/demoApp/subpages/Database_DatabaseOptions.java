package org.openntf.domino.demoApp.subpages;

import org.openntf.domino.Database;
import org.openntf.domino.demoApp.components.Html_Separator;
import org.openntf.domino.demoApp.components.Html_Separator.SeparatorType;
import org.openntf.domino.demoApp.pages.BaseView;
import org.openntf.domino.demoAppUtil.FactoryUtils;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

public class Database_DatabaseOptions extends BaseSubPage {
	private static final long serialVersionUID = 1L;

	public Database_DatabaseOptions(BaseView parentView) {
		super(parentView);
	}

	public void loadContent() {
		Label label1 = new Label("The " + FactoryUtils.addCodeString("Database.DBOption")
				+ " admin-related enums have been added to make code more readable. The core API has "
				+ FactoryUtils.addCodeString("Database.getOption(int)") + " and " + FactoryUtils.addCodeString("Database.setOption(int, boolean)")
				+ ", where the int is the integer for the selected setting, e.g. looking for soft deletes is 49. " + "The new methods "
				+ FactoryUtils.addCodeString("Database.getOption(DBOption)") + " and "
				+ FactoryUtils.addCodeString("Database.setOption(DBOption, true)")
				+ " take an Enum for the option required. Resulting code is easier to support.");

		label1.setContentMode(ContentMode.HTML);
		addComponents(label1);
		addDatabaseOptions();
		addComponent(new Html_Separator(SeparatorType.NEW_LINE));
	}

	public void addDatabaseOptions() {
		for (Database.DBOption opt : Database.DBOption.values()) {
			Label label1 = new Label(opt.name());
			label1.setStyleName(ValoTheme.LABEL_H3);
			String optExplanation = "";
			boolean isDefault = false;
			switch (opt) {
			case COMPRESSDESIGN:
				optExplanation = "Corresponds to " + FactoryUtils.addCodeString("Database.DBOPT_COMPRESSDESIGN");
				break;
			case COMPRESSDOCUMENTS:
				optExplanation = "Corresponds to " + FactoryUtils.addCodeString("Database.DBOPT_COMPRESSDOCUMENTS");
				break;
			case LZ1:
				optExplanation = "Corresponds to " + FactoryUtils.addCodeString("Database.DBOPT_LZ1");
				break;
			case LZCOMPRESSION:
				optExplanation = "Corresponds to " + FactoryUtils.addCodeString("Database.DBOPT_LZCOMPRESSION");
				break;
			case MAINTAINLASTACCESSED:
				optExplanation = "Corresponds to " + FactoryUtils.addCodeString("Database.DBOPT_MAINTAINLASTACCESSED");
				break;
			case MOREFIELDS:
				optExplanation = "Corresponds to " + FactoryUtils.addCodeString("Database.DBOPT_MOREFIELDS");
				break;
			case NOHEADLINEMONITORS:
				optExplanation = "Corresponds to " + FactoryUtils.addCodeString("Database.DBOPT_NOHEADLINEMONITORS");
				break;
			case NOOVERWRITE:
				optExplanation = "Corresponds to " + FactoryUtils.addCodeString("Database.DBOPT_NOOVERWRITE");
				break;
			case NORESPONSEINFO:
				optExplanation = "Corresponds to " + FactoryUtils.addCodeString("Database.DBOPT_NORESPONSEINFO");
				break;
			case NOSIMPLESEARCH:
				optExplanation = "Corresponds to " + FactoryUtils.addCodeString("Database.DBOPT_NOSIMPLESEARCH");
				break;
			case NOTRANSACTIONLOGGING:
				optExplanation = "Corresponds to " + FactoryUtils.addCodeString("Database.DBOPT_NOTRANSACTIONLOGGING");
				break;
			case NOUNREAD:
				optExplanation = "Corresponds to " + FactoryUtils.addCodeString("Database.DBOPT_NOUNREAD");
				break;
			case OPTIMIZAION:
				optExplanation = "Corresponds to " + FactoryUtils.addCodeString("Database.DBOPT_OPTIMIZATION");
				break;
			case OUTOFOFFICEENABLED:
				optExplanation = "Corresponds to " + FactoryUtils.addCodeString("Database.DBOPT_OUTOFOFFICEENABLED");
				break;
			case REPLICATEUNREADMARKSNEVER:
				optExplanation = "Corresponds to " + FactoryUtils.addCodeString("Database.DBOPT_REPLICATEUNREADMARKSNEVER");
				break;
			case REPLICATEUNREADMARKSTOANY:
				optExplanation = "Corresponds to " + FactoryUtils.addCodeString("Database.DBOPT_REPLICATEUNREADMARKSTOANY");
				break;
			case REPLICATEUNREADMARKSTOCLUSTER:
				optExplanation = "Corresponds to " + FactoryUtils.addCodeString("Database.DBOPT_REPLICATEUNREADMARKSTOCLUSTER");
				break;
			case SOFTDELETE:
				optExplanation = "Corresponds to " + FactoryUtils.addCodeString("Database.DBOPT_SOFTDELETE");
				break;
			case USEDAOS:
				optExplanation = "Corresponds to " + FactoryUtils.addCodeString("Database.DBOPT_USEDAOS");
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
