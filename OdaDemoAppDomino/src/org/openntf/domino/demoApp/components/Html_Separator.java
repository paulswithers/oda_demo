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

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;

public class Html_Separator extends Label {
	private static final long serialVersionUID = 1L;

	public enum SeparatorType {
		NEW_LINE, SPACE;
	}

	public Html_Separator(SeparatorType type) {
		if (SeparatorType.NEW_LINE.equals(type)) {
			setValue("<br/>");
		} else {
			setValue("&#160;");
		}
		setContentMode(ContentMode.HTML);
	}

	public Html_Separator(SeparatorType type, int count) {
		String newStringValue = "";
		if (SeparatorType.NEW_LINE.equals(type)) {
			for (int i = 0; i < count; i++) {
				newStringValue = newStringValue + "<br/>";
			}
		} else {
			for (int i = 0; i < count; i++) {
				newStringValue = newStringValue + "&#160;";
			}
		}
		setValue(newStringValue);
		setContentMode(ContentMode.HTML);
	}

}
