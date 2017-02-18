package org.openntf.domino.demoApp.subpages.elses;

import org.openntf.domino.demoApp.pages.BaseView;
import org.openntf.domino.demoApp.subpages.BaseSubPage;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;

public class Else_OtherDemos extends BaseSubPage {
	private static final long serialVersionUID = 1L;

	public Else_OtherDemos(BaseView parentView) {
		super(parentView);
	}

	@Override
	public void loadContent() {
		Label label1 = new Label(getProps().getProperty("other"));
		label1.setContentMode(ContentMode.HTML);
		addComponent(label1);
	}

}
