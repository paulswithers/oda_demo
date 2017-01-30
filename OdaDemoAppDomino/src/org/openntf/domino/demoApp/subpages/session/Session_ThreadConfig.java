package org.openntf.domino.demoApp.subpages.session;

import java.text.MessageFormat;

import org.openntf.domino.demoApp.pages.BaseView;
import org.openntf.domino.demoApp.pages.SessionView;
import org.openntf.domino.demoApp.subpages.BaseSubPage;
import org.openntf.domino.demoApp.utils.FactoryUtils;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

public class Session_ThreadConfig extends BaseSubPage {
	private static final long serialVersionUID = 1L;

	public Session_ThreadConfig(BaseView parentView) {
		super(parentView);
	}

	@Override
	public void loadContent() {
		((SessionView) getParentView()).checkLoadSetupButton(this);
		Label label1 = new Label(MessageFormat.format(getProps().getProperty("threadIntro"),
				FactoryUtils.addCodeString("Factory.initThread(ThreadConfig)")), ContentMode.HTML);

		Label label2 = new Label("STRICT_THREAD_CONFIG");
		label2.setStyleName(ValoTheme.LABEL_H3);
		Label label3 = new Label(
				MessageFormat.format(getProps().getProperty("threadStrict"), FactoryUtils.addCodeString("WRAP_32k")),
				ContentMode.HTML);

		Label label4 = new Label("PERMISSIVE_THREAD_CONFIG");
		label4.setStyleName(ValoTheme.LABEL_H3);
		Label label5 = new Label(MessageFormat.format(getProps().getProperty("threadPermissive"),
				FactoryUtils.addCodeString("WRAP_ALL")), ContentMode.HTML);

		Label label6 = new Label(MessageFormat.format(getProps().getProperty("threadManual"),
				FactoryUtils.addCodeString("ThreadConfig"), FactoryUtils.addCodeString("Fixes"),
				FactoryUtils.addCodeString("AutoMime"), FactoryUtils.addCodeString("org.openntf.domino.AutoMime")),
				ContentMode.HTML);
		addComponents(label1, label2, label3, label4, label5, label6);
	}

}
