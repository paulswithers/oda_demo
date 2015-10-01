package org.openntf.domino.demoApp.subpages;

import org.openntf.domino.Session;
import org.openntf.domino.demoApp.components.Html_Separator;
import org.openntf.domino.demoApp.components.Html_Separator.SeparatorType;
import org.openntf.domino.demoApp.pages.BaseView;
import org.openntf.domino.demoApp.pages.SessionView;
import org.openntf.domino.demoAppUtil.FactoryUtils;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

public class Session_Fixes extends BaseSubPage {
	private static final long serialVersionUID = 1L;

	public Session_Fixes(BaseView parentView) {
		super(parentView);
	}

	public void loadContent() {
		((SessionView) getParentView()).checkLoadSetupButton(this);
		for (Session.Fixes fix : Session.Fixes.values()) {
			Label label1 = new Label(fix.name());
			label1.setStyleName(ValoTheme.LABEL_H3);
			String fixExplanation;
			switch (fix) {
			case APPEND_ITEM_VALUE:
				fixExplanation = "Normal use of " + FactoryUtils.addCodeString("Document.appendItemValue()")
						+ " appends a <b>new item of the same name</b> with the new value.<br/>"
						+ "This switch changes the behaviour to work in a more intuitive manner, appending the value to the existing Item.";
				break;

			case CREATE_DB:
				fixExplanation = "Extends the use of " + FactoryUtils.addCodeString("DbDirectory.createDatabase(String)")
						+ " to return the existing Database object for the specified path "
						+ "or create a new database at the specified path and return the Database object relating to that.";
				break;
			case DOC_UNID_NULLS:
				fixExplanation = "Normal behaviour of " + FactoryUtils.addCodeString("Database.getDocumentByUNID()")
						+ " is to throw an error if the UNID cannot be found. "
						+ "With this fix, that method will now just return a null object and suppress the error.";
				break;
			case FORCE_JAVA_DATES:
				fixExplanation = "See <a href=\"http://dontpanic82.blogspot.co.uk/2013/09/strange-implementation-of.html\">Tommy Valand's blog post</a>. "
						+ "This method ensures Java dates are always returned when interacting with Document Items or ViewEntry column vallues.";
				break;
			case MIME_BLOCK_ITEM_INTERFACE:
				fixExplanation = "Just writes a warning to the console if Items are accessed while MIME Items are still open. "
						+ "This fix has been downgraded from blocking to just warning against access";
				break;
			case MIME_CONVERT:
				fixExplanation = "This no longer seems to be in use.";
				break;
			case ODA_NAMES:
				fixExplanation = "Uses org.openntf.domino.impl.NameODA class whenever methods would normally use lotus.domino.Name. "
						+ "This class can be stored in Java obejcts and uses String manipulation to convert between formats.";
				break;
			case REMOVE_ITEM:
				fixExplanation = "Normal behaviour is to remove the <b>first</b> Item with the specified name. "
						+ "This switch removes <b>all</b> Items with the specified name.";
				break;
			case REPLACE_ITEM_NULL:
				fixExplanation = "This extension allows developers to use " + FactoryUtils.addCodeString("Document.replaceItemValue(String, null)")
						+ " to remove the Item";
				break;
			case VIEW_UPDATE_OFF:
				fixExplanation = "Ensures any method accessing view entries calls " + FactoryUtils.addCodeString("View.setAutoUpdate(false)")
						+ " before doing anything.";
				break;
			case VIEWENTRY_RETURN_CONSTANT_VALUES:
				fixExplanation = "Ensures " + FactoryUtils.addCodeString("ViewEntry.getColumnValues()") + " and "
						+ FactoryUtils.addCodeString("ViewColumn.getColumnValuesIndex()") + " include view columns "
						+ "whose value is a constant value rather than a field value or result of an @formula.";
				break;
			default:
				fixExplanation = "Whoops!! This must be a new switch. Please let us know so we can add documentation.";
				break;
			}
			Label label2 = new Label(fixExplanation + "<br/>", ContentMode.HTML);
			addComponents(label1, label2);
		}
		addComponent(new Html_Separator(SeparatorType.NEW_LINE));
	}

}
