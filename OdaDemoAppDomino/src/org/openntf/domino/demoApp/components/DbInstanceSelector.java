package org.openntf.domino.demoApp.components;

/*

<!--
Copyright 2017 Paul Withers
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
