package org.openntf.domino.demoApp.components;

import java.util.Collection;

import org.openntf.domino.demoApp.utils.FactoryUtils;

import com.vaadin.data.Container;
import com.vaadin.ui.NativeSelect;

public class DbInstanceSelector extends NativeSelect {
	private static final long serialVersionUID = 1L;

	public DbInstanceSelector() {
		loadOptions();
	}

	private void loadOptions() {
		boolean defaultSet = false;
		for (int i = 1; i <= FactoryUtils.getNumberOfDemosAsInt(); i++) {
			String value = Integer.toString(i);
			addItem(value);
			setItemCaption(value, "ODA Demo Database " + i);
			if (!defaultSet) {
				setValue(value);
				defaultSet = true;
			}
		}
		addStyleName("navigation-selector");
	}

	public DbInstanceSelector(String caption) {
		super(caption);
		loadOptions();
	}

	public DbInstanceSelector(String caption, Collection<?> options) {
		super(caption, options);
	}

	public DbInstanceSelector(String caption, Container dataSource) {
		super(caption, dataSource);
	}
}
