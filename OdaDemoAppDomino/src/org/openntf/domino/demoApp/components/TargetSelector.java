package org.openntf.domino.demoApp.components;

import org.openntf.domino.demoApp.DemoUI;
import org.openntf.domino.demoApp.pages.BaseViewInterface;

import com.vaadin.ui.NativeSelect;

public class TargetSelector extends NativeSelect {
	private static final long serialVersionUID = 1L;
	private BaseViewInterface currPage;

	public enum Target {
		XPAGES("XPages Only"), NON_XPAGES("Non XPages"), BOTH("Any");
		private String value_;

		private Target(String value) {
			value_ = value;
		}

		public String getValue() {
			return value_;
		}
	}

	public TargetSelector(BaseViewInterface thisPage) {
		setCurrPage(thisPage);
		setCaption("Target:");
		for (Target tgt : Target.values()) {
			addItem(tgt);
			setItemCaption(tgt, tgt.getValue());
		}
		setValue(DemoUI.get().getAppTarget());
		addStyleName("navigation-selector");
		addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(com.vaadin.data.Property.ValueChangeEvent event) {
				Target tgt = (Target) event.getProperty().getValue();
				DemoUI.get().setAppTarget(tgt);
				getCurrPage().loadNavigation();
			}
		});
	}

	public BaseViewInterface getCurrPage() {
		return currPage;
	}

	public void setCurrPage(BaseViewInterface currPage) {
		this.currPage = currPage;
	}

}
