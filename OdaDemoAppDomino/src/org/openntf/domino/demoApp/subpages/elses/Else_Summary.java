package org.openntf.domino.demoApp.subpages.elses;

import org.openntf.domino.demoApp.pages.BaseView;
import org.openntf.domino.demoApp.pages.DominoElseView;
import org.openntf.domino.demoApp.pages.DominoElseView.MethodType;
import org.openntf.domino.demoApp.subpages.BaseSubPage;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

public class Else_Summary extends BaseSubPage {
	private static final long serialVersionUID = 1L;

	public Else_Summary(BaseView parentView) {
		super(parentView);
	}

	@Override
	public void loadContent() {
		Label label1 = new Label(getProps().getProperty("summary"));
		addComponent(label1);
		for (final MethodType methodType : MethodType.values()) {
			if (!MethodType.DATE.equals(methodType) && !MethodType.EMAIL.equals(methodType)
					&& !MethodType.OPENLOG.equals(methodType) && !MethodType.SORTER.equals(methodType)) {
				Button button1 = new Button(methodType.getValue());
				button1.setStyleName(ValoTheme.BUTTON_LINK);
				button1.addClickListener(new Button.ClickListener() {

					@Override
					public void buttonClick(ClickEvent event) {
						DominoElseView parent = (DominoElseView) getParentView();
						parent.setCurrentMethodPage(methodType);
						switch (methodType) {
						case VIEW_ENTRY_COLLECTION:
							parent.loadEntCollSource();
							break;
						case DOCUMENT_COLLECTION:
							parent.loadDocCollSource();
							break;
						case FORM:
							parent.loadFormSource();
							break;
						case COLOR_OBJECT:
							parent.loadColorSource();
							break;
						default:
							parent.loadNoSource();
						}
						parent.loadMethodList();
					}
				});
				addComponent(button1);
			}
		}

	}

}
