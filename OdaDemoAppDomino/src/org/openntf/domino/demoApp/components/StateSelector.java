package org.openntf.domino.demoApp.components;

/*

<!--
Copyright 2015 Paul Withers
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and limitations under the License
-->

*/

import java.util.Collection;

import org.openntf.domino.Document;
import org.openntf.domino.View;
import org.openntf.domino.demoApp.utils.FactoryUtils;

import com.vaadin.data.Container;
import com.vaadin.ui.NativeSelect;

public class StateSelector extends NativeSelect {
	private static final long serialVersionUID = 1L;

	public StateSelector() {
		loadOptions();
	}

	private void loadOptions() {
		View allStates = FactoryUtils.getDemoDatabase().getView("AllStates");
		boolean defaultSet = false;
		for (Document doc : allStates.getAllDocuments()) {
			String value = doc.getItemValueString("Key");
			addItem(value);
			setItemCaption(value, doc.getItemValueString("Name"));
			if (!defaultSet) {
				setValue(value);
				defaultSet = true;
			}
		}
		addStyleName("navigation-selector");
	}

	public StateSelector(String caption) {
		super(caption);
		loadOptions();
	}

	public StateSelector(String caption, Collection<?> options) {
		super(caption, options);
	}

	public StateSelector(String caption, Container dataSource) {
		super(caption, dataSource);
	}

}
