package org.openntf.domino.demoApp.subpages.document;

import java.text.MessageFormat;

import org.openntf.domino.demoApp.pages.BaseView;
import org.openntf.domino.demoApp.subpages.BaseSubPage;
import org.openntf.domino.demoApp.utils.FactoryUtils;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

public class Document_LegacyFields extends BaseSubPage {
	private static final long serialVersionUID = 1L;

	public Document_LegacyFields(BaseView parentView) {
		super(parentView);
	}

	@Override
	public void loadContent() {
		Label label1 = new Label("legacyIntro");
		Label label2 = new Label("Field_1, Field_2, Field_3 etc");
		label2.setStyleName(ValoTheme.LABEL_H3);
		Label label3 = new Label(MessageFormat.format(getProps().getProperty("series"),
				FactoryUtils.addCodeString("getItemValueSeries(CharSequence)")));
		label3.setContentMode(ContentMode.HTML);
		Label label4 = new Label("Tables of Fields");
		label4.setStyleName(ValoTheme.LABEL_H3);
		Label label5 = new Label(MessageFormat.format(getProps().getProperty("tableFields"),
				FactoryUtils.addCodeString("getItemTable()"), FactoryUtils.addCodeString("setItemTable()")));
		label5.setContentMode(ContentMode.HTML);
		Label label6 = new Label(MessageFormat.format(getProps().getProperty("tableFieldsPivot"),
				FactoryUtils.addCodeString("getItemTablePivot()"), FactoryUtils.addCodeString("setItemValuePivot()")));
		label6.setContentMode(ContentMode.HTML);
		addComponents(label1, label2, label3, label4, label5, label6);
	}

}
