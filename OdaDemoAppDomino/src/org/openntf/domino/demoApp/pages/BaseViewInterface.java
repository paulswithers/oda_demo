package org.openntf.domino.demoApp.pages;

import java.lang.reflect.Method;
import java.util.ArrayList;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
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

}