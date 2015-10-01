package org.openntf.domino.demoApp.subpages;

import org.openntf.domino.demoApp.pages.BaseView;
import org.openntf.domino.demoAppUtil.FactoryUtils;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;

public class Xots_Tasklets extends BaseSubPage {
	private static final long serialVersionUID = 1L;

	public Xots_Tasklets(BaseView parentView) {
		super(parentView);
	}

	@Override
	public void loadContent() {
		Label label1 = new Label("A Xots Tasklet must follow a few standard rules:<ul><li>It must implement either the "
				+ FactoryUtils.addCodeString("Callable") + " or " + FactoryUtils.addCodeString("Runnable") + " interface.<li>"
				+ "<li>The class must be annotated by the " + FactoryUtils.addCodeString("@Tasklet") + " annotation</li>"
				+ "<li>The annotation should define the " + FactoryUtils.addCodeString("session") + " variable as one of the "
				+ FactoryUtils.addCodeString("Tasklet.Session") + " enum options</li>");
		label1.setContentMode(ContentMode.HTML);
		Label label2 = new Label("If a Xots Tasklet implements " + FactoryUtils.addCodeString("Runnable")
				+ " the tasklet will run in the background and the code will continue. If a Xots Tasklet implements "
				+ FactoryUtils.addCodeString("Callable") + " the tasklet returns a value and the code can wait for and process the response.");
		label2.setContentMode(ContentMode.HTML);
		addComponents(label1, label2);
	}

}
