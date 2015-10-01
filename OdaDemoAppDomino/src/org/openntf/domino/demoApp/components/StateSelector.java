package org.openntf.domino.demoApp.components;

import java.util.Collection;
import java.util.TreeMap;

import org.openntf.domino.Document;
import org.openntf.domino.View;
import org.openntf.domino.demoAppUtil.FactoryUtils;

import com.vaadin.data.Container;
import com.vaadin.ui.NativeSelect;

public class StateSelector extends NativeSelect {

	public StateSelector() {
		TreeMap<String, String> stateList = new TreeMap<String, String>();
		View allStates = FactoryUtils.getDemoDatabase().getView("AllStates");
		boolean defaultSet = false;
		for (Document doc : allStates.getAllDocuments()) {
			addItem(doc.getItemValueString("Key"));
			setItemCaption(doc.getItemValueString("Key"), doc.getItemValueString("Name"));
			if (!defaultSet) {
				setValue(doc.getItemValueString("Key"));
				defaultSet = true;
			}
		}
		addStyleName("navigation-selector");
	}

	public StateSelector(String caption) {
		super(caption);
	}

	public StateSelector(String caption, Collection<?> options) {
		super(caption, options);
	}

	public StateSelector(String caption, Container dataSource) {
		super(caption, dataSource);
	}

}
