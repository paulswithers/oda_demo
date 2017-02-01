package org.openntf.domino.demoApp.pages;

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

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Properties;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.VerticalLayout;

public interface BaseViewInterface {

	void enter(ViewChangeEvent event);

	String getMethodSummary(ArrayList<String> newMethods, Method crystal);

	/**
	 * Shows an error message on the screen
	 *
	 * @param msg
	 *            String error to display
	 */
	void showError(String msg);

	/**
	 * Load the content for the page
	 */
	void loadContent();

	/**
	 * Load the navigation for the page
	 */
	void loadNavigation();

	/**
	 * Load the method list for the page
	 */
	void loadMethodList();

	/**
	 * Load the source code for the page
	 */
	void loadSource();

	boolean isLoaded();

	void setLoaded(boolean loaded);

	TabSheet getRightSliderContent();

	void setRightSliderContent(TabSheet rightSliderContent);

	Panel getMethodList();

	void setMethodList(Panel methodList);

	VerticalLayout getSourceCode();

	void setSourceCode(VerticalLayout sourceCode);

	HorizontalLayout getPageBody();

	void setPageBody(HorizontalLayout pageBody);

	CssLayout getSubNavigation();

	void setSubNavigation(CssLayout subNavigation);

	Panel getContentPanel();

	void setContentPanel(Panel contentPanel);

	boolean isShowNavigation();

	void setShowNavigation(boolean showNavigation);

	public Tab getMethodTab();

	public void setMethodTab(Tab methodTab);

	public Tab getSourceTab();

	public void setSourceTab(Tab sourceTab);

	public Properties getProps();

	public void setProps(Properties props);

}