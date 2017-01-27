package org.openntf.domino.demoApp.subpages.view;

import java.text.MessageFormat;

import org.openntf.domino.demoApp.components.StateSelector;
import org.openntf.domino.demoApp.pages.BaseView;
import org.openntf.domino.demoApp.subpages.BaseSubPage;
import org.openntf.domino.demoAppUtil.DatabaseUtils;
import org.openntf.domino.demoAppUtil.FactoryUtils;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;

public class View_Unique extends BaseSubPage {
	private static final long serialVersionUID = 1L;
	private final Label outputLabel = new Label();
	private StateSelector stateSelector = new StateSelector();

	public View_Unique(BaseView parentView) {
		super(parentView);
	}

	@Override
	public void loadContent() {
		Label label1 = new Label(MessageFormat.format(getProps().getProperty("getUnique"),
				FactoryUtils.addCodeString("View.checkUnique(Object, Document)"),
				FactoryUtils.addCodeString("getAllDocumentsBykey")));
		label1.setContentMode(ContentMode.HTML);
		Button button1 = new Button("Run as Existing State");
		button1.addStyleName("floating-btn");
		button1.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				getOutputLabel()
						.setCaption(DatabaseUtils.checkUniqueTest((String) getStateSelector().getValue(), true));
			}
		});
		Button button2 = new Button("Run as New State");
		button2.addStyleName("floating-btn");
		button2.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				getOutputLabel()
						.setCaption(DatabaseUtils.checkUniqueTest((String) getStateSelector().getValue(), false));
			}
		});
		getOutputLabel().setCaptionAsHtml(true);
		addComponents(label1, getStateSelector(), button1, button2, getOutputLabel());
	}

	public Label getOutputLabel() {
		return outputLabel;
	}

	public StateSelector getStateSelector() {
		return stateSelector;
	}

}
