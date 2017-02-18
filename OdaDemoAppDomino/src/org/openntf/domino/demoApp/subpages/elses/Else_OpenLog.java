package org.openntf.domino.demoApp.subpages.elses;

import java.text.MessageFormat;

import org.openntf.domino.demoApp.pages.BaseView;
import org.openntf.domino.demoApp.subpages.BaseSubPage;
import org.openntf.domino.demoApp.utils.FactoryUtils;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;

public class Else_OpenLog extends BaseSubPage {
	private static final long serialVersionUID = 1L;

	public Else_OpenLog(BaseView parentView) {
		super(parentView);
	}

	@Override
	public void loadContent() {
		Label label1 = new Label(MessageFormat.format(getProps().getProperty("openLog"),
				FactoryUtils.addCodeString("org.openntf.domino.logging.BaseOpenLogItem"),
				FactoryUtils.addCodeString("org.openntf.domino.xsp"), FactoryUtils.addCodeString("openLogBean"),
				FactoryUtils.addCodeString("org.openntf.domino.xsp.XspOpenLogUtil")));
		label1.setContentMode(ContentMode.HTML);
		addComponent(label1);
	}

}
