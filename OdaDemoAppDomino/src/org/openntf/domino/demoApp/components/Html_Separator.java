package org.openntf.domino.demoApp.components;

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
