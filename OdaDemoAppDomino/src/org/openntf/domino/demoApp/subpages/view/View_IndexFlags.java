package org.openntf.domino.demoApp.subpages.view;

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

import java.text.MessageFormat;

import org.openntf.domino.View.IndexType;
import org.openntf.domino.demoApp.pages.BaseView;
import org.openntf.domino.demoApp.subpages.BaseSubPage;
import org.openntf.domino.demoApp.utils.FactoryUtils;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

public class View_IndexFlags extends BaseSubPage {
	private static final long serialVersionUID = 1L;

	public View_IndexFlags(BaseView parentView) {
		super(parentView);
	}

	@Override
	public void loadContent() {
		Label label1 = new Label(MessageFormat.format(getProps().getProperty("getIndex"),
				FactoryUtils.addCodeString("isCalendar()"), FactoryUtils.addCodeString("isFolder()"),
				FactoryUtils.addCodeString("isPrivate()"), FactoryUtils.addCodeString("isPrivate()")));
		label1.setContentMode(ContentMode.HTML);
		addComponent(label1);
		for (IndexType indexType : IndexType.values()) {
			Label label2 = new Label(indexType.name());
			label2.setStyleName(ValoTheme.LABEL_H3);
			String indexExplanation;
			switch (indexType) {
			case SHARED:
				indexExplanation = getProps().getProperty("indexShared");
				break;
			case PRIVATE:
				indexExplanation = getProps().getProperty("indexPrivate");
				break;
			case SHAREDPRIVATEONSERVER:
				indexExplanation = getProps().getProperty("indexPrivateOnServer");
				break;
			case SHAREDPRIVATEONDESKTOP:
				indexExplanation = getProps().getProperty("indexPrivateOnDesktop");
				break;
			case SHAREDINCLUDESDELETES:
				indexExplanation = getProps().getProperty("indexDeletes");
				break;
			case SHAREDNOTINFOLDERS:
				indexExplanation = getProps().getProperty("indexNotFoldered");
				break;
			default:
				indexExplanation = getProps().getProperty("indexMissing");
				break;
			}
			Label label3 = new Label(indexExplanation + "<br/>", ContentMode.HTML);
			addComponents(label2, label3);
		}

		Label label4 = new Label(getProps().getProperty("indexOptsHeader"));
		label4.addStyleName(ValoTheme.LABEL_H3);
		Label label5 = new Label(getProps().getProperty("indexOpts"));
		label5.setContentMode(ContentMode.HTML);
		Label label6 = new Label(getProps().getProperty("indexDisableAutoUpdateHeader"));
		label6.addStyleName(ValoTheme.LABEL_H3);
		Label label7 = new Label(getProps().getProperty("indexDisableAutoUpdate"));
		label7.setContentMode(ContentMode.HTML);
		Label label8 = new Label(getProps().getProperty("indexHideCategoriesHeader"));
		label8.addStyleName(ValoTheme.LABEL_H3);
		Label label9 = new Label(getProps().getProperty("indexHideCategories"));
		label9.setContentMode(ContentMode.HTML);
		Label label10 = new Label(getProps().getProperty("indexManualAutoRefreshHeader"));
		label10.addStyleName(ValoTheme.LABEL_H3);
		Label label11 = new Label(getProps().getProperty("indexManualAutoRefresh"));
		label11.setContentMode(ContentMode.HTML);
		Label label12 = new Label(getProps().getProperty("indexAutoAfterHeader"));
		label12.addStyleName(ValoTheme.LABEL_H3);
		Label label13 = new Label(getProps().getProperty("indexAutoAfter"));
		label13.setContentMode(ContentMode.HTML);
		Label label14 = new Label(getProps().getProperty("indexDiscardIndexHeader"));
		label14.addStyleName(ValoTheme.LABEL_H3);
		Label label15 = new Label(getProps().getProperty("indexDiscardIndex"));
		label15.setContentMode(ContentMode.HTML);
		Label label16 = new Label(getProps().getProperty("indexDiscardHoursHeader"));
		label16.addStyleName(ValoTheme.LABEL_H3);
		Label label17 = new Label(getProps().getProperty("indexDiscardHours"));
		label17.setContentMode(ContentMode.HTML);
		addComponents(label4, label5, label6, label7, label8, label9, label10, label11, label12, label13, label14,
				label15, label16, label17);

	}

}
