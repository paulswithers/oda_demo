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
