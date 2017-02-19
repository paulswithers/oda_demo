package org.openntf.domino.demoApp.subpages.elses;

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
