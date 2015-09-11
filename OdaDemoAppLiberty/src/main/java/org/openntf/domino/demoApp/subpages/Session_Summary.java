package org.openntf.domino.demoApp.subpages;

import java.util.concurrent.Future;

import org.apache.commons.lang3.StringUtils;
import org.openntf.domino.Session;
import org.openntf.domino.demoApp.XotsTests.SessionCallable;
import org.openntf.domino.demoApp.pages.BaseView;
import org.openntf.domino.demoApp.pages.SessionView;
import org.openntf.domino.demoAppUtil.FactoryUtils;
import org.openntf.domino.xots.Xots;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class Session_Summary extends BaseSubPage {
	private static final long serialVersionUID = 1L;

	public Session_Summary(BaseView parentView) {
		super(parentView);
	}

	public void loadContent() {
		((SessionView) getParentView()).checkLoadSetupButton(this);
		Session currSess = FactoryUtils.getUserSession();
		Label label1 = new Label("Session running as " + currSess.getEffectiveUserName() + " on server " + currSess.getServerName(),
				ContentMode.HTML);
		Label label2 = new Label("<b>User groups available are:</b><br/>" + StringUtils.join(currSess.getUserGroupNameCollection(), "<br/>"),
				ContentMode.HTML);
		Label label3 = new Label("<b>User names available are:</b><br/>" + StringUtils.join(currSess.getUserNameCollection(), "<br/>"),
				ContentMode.HTML);
		Label label4 = new Label("<b>Unique reference using Session.getUnique():</b> " + currSess.getUnique(), ContentMode.HTML);
		HorizontalLayout fixLayout = new HorizontalLayout();
		StringBuilder sb = new StringBuilder();
		sb.append("<b>Available fixtures:</b>");
		for (Session.Fixes fix : Session.Fixes.values()) {
			sb.append("<br/>" + fix.name());
		}
		Label label5 = new Label(sb.toString(), ContentMode.HTML);
		sb = new StringBuilder();
		sb.append("<b>Currently enabled fixtures:</b>");
		for (Session.Fixes fix : currSess.getEnabledFixes()) {
			sb.append("<br/>" + fix.name());
		}
		Label label6 = new Label(sb.toString(), ContentMode.HTML);
		Future<String> future = Xots.getService().submit(new SessionCallable());
		Label label7 = new Label("XOTS FAILED");
		try {
			label7.setValue(future.get());
		} catch (Throwable t) {
			t.printStackTrace();
		}
		fixLayout.addComponents(label5, label6);
		addComponents(label1, label2, label3, label4, label7, fixLayout);
	}

}
