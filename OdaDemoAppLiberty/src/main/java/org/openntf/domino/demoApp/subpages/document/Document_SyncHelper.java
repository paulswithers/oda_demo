package org.openntf.domino.demoApp.subpages.document;

import java.text.MessageFormat;

import org.openntf.domino.demoApp.pages.BaseView;
import org.openntf.domino.demoApp.subpages.BaseSubPage;
import org.openntf.domino.demoApp.utils.FactoryUtils;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;

public class Document_SyncHelper extends BaseSubPage {
	private static final long serialVersionUID = 1L;

	public Document_SyncHelper(BaseView parentView) {
		super(parentView);
	}

	@Override
	public void loadContent() {
		Label label1 = new Label(getProps().getProperty("syncIntro"));
		Label label2 = new Label(MessageFormat.format(getProps().getProperty("syncHelp"),
				FactoryUtils.addCodeString("map.put(\"Name\",\"CompanyName\")"),
				FactoryUtils.addCodeString("DocumentSyncHelper"),
				FactoryUtils.addCodeString("DocumentSyncHelper.Strategy"),
				FactoryUtils.addCodeString("View.getAllDocumentsByKey()"), FactoryUtils.addCodeString("process()")));
		label2.setContentMode(ContentMode.HTML);
		addComponents(label1, label2);
	}

}
