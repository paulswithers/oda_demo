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

import org.openntf.domino.demoApp.DemoUI;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public abstract class BaseView extends CssLayout implements View, BaseViewInterface {
	private static final long serialVersionUID = 1L;
	private static final String ERROR_DONT_USE_DIRECTLY = "This base method shouldn't be used directly.";
	private boolean _loaded;
	private HorizontalLayout pageBody = new HorizontalLayout();
	private CssLayout subNavigation = new CssLayout();
	private Panel contentPanel = new Panel();
	private TabSheet rightSliderContent = new TabSheet();
	private Panel methodList = new Panel();
	private VerticalLayout sourceCode = new VerticalLayout();
	private boolean showNavigation;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openntf.domino.demoApp.pages.DemoAppViewInterface#enter(com.vaadin. navigator.ViewChangeListener.ViewChangeEvent)
	 */
	@Override
	public void enter(ViewChangeEvent event) {
		if (!isLoaded()) {
			checkIsSetup();

			getPageBody().setSizeFull();
			// Need to add a height for scroll bars to appear
			getPageBody().setHeight(UI.getCurrent().getPage().getBrowserWindowHeight() - 100, Unit.PIXELS);
			if (isShowNavigation()) {
				getSubNavigation().setStyleName("navigation");
				getPageBody().addComponent(getSubNavigation());
				getPageBody().setExpandRatio(getSubNavigation(), 15);
				getPageBody().addComponents(getContentPanel());
				getPageBody().setExpandRatio(getContentPanel(), 85);
				loadNavigation();
			} else {
				getPageBody().addComponents(getContentPanel());
			}
			getContentPanel().setSizeFull();
			loadContent();
			loadMethodList();
			loadSource();
			addComponent(getPageBody());

			// Add right slider
			getRightSliderContent().setSizeFull();
			getRightSliderContent().addTab(getMethodList(), "Public Methods", FontAwesome.INFO);
			getRightSliderContent().addTab(getSourceCode(), "Source", FontAwesome.CODE);

			setLoaded(true);
		}
		DemoUI.get().getRightSlider().setContent(getRightSliderContent());
	}

	public void checkIsSetup() {
		// Check databases are set up
		if (!DemoUI.get().isSetup()) {
			DemoUI.get().getUiNavigator().navigateTo("");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openntf.domino.demoApp.pages.DemoAppViewInterface#getMethodSummary( java.util.ArrayList, java.lang.reflect.Method)
	 */
	@Override
	public String getMethodSummary(ArrayList<String> newMethods, Method crystal) {
		StringBuilder meths = new StringBuilder();
		String style = "<div class=\"coreMethod\">";
		if (newMethods.contains(crystal.getName())) {
			style = "<div class=\"newMethod\">";
		}
		meths.append(style + "<b>" + crystal.getName() + "</b>, return value=" + crystal.getReturnType().getName());
		int i = 0;
		for (Class<?> param : crystal.getParameterTypes()) {
			if (i == 0) {
				meths.append(", parameters={arg" + Integer.toString(i) + "=" + param.getName());
			} else {
				meths.append(", arg" + Integer.toString(i) + "=" + param.getName());
			}
			i++;
		}
		if (i > 0) {
			meths.append("}");
		}
		meths.append("</div>");
		return meths.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openntf.domino.demoApp.pages.DemoAppViewInterface#showError(java.lang .String)
	 */
	@Override
	public void showError(String msg) {
		Notification.show(msg, Type.ERROR_MESSAGE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openntf.domino.demoApp.pages.DemoAppViewInterface#loadContent()
	 */
	@Override
	public void loadContent() {
		throw new IllegalStateException(ERROR_DONT_USE_DIRECTLY);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openntf.domino.demoApp.pages.DemoAppViewInterface#loadNavigation()
	 */
	@Override
	public void loadNavigation() {
		throw new IllegalStateException(ERROR_DONT_USE_DIRECTLY);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openntf.domino.demoApp.pages.DemoAppViewInterface#loadMethodList()
	 */
	@Override
	public void loadMethodList() {
		throw new IllegalStateException(ERROR_DONT_USE_DIRECTLY);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openntf.domino.demoApp.pages.DemoAppViewInterface#loadSource()
	 */
	@Override
	public void loadSource() {
		throw new IllegalStateException(ERROR_DONT_USE_DIRECTLY);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openntf.domino.demoApp.pages.DemoAppViewInterface#isLoaded()
	 */
	@Override
	public boolean isLoaded() {
		return _loaded;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openntf.domino.demoApp.pages.DemoAppViewInterface#setLoaded(boolean)
	 */
	@Override
	public void setLoaded(boolean loaded) {
		_loaded = loaded;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openntf.domino.demoApp.pages.DemoAppViewInterface# getRightSliderContent()
	 */
	@Override
	public TabSheet getRightSliderContent() {
		return rightSliderContent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openntf.domino.demoApp.pages.DemoAppViewInterface# setRightSliderContent(com.vaadin.ui.TabSheet)
	 */
	@Override
	public void setRightSliderContent(TabSheet rightSliderContent) {
		this.rightSliderContent = rightSliderContent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openntf.domino.demoApp.pages.DemoAppViewInterface#getMethodList()
	 */
	@Override
	public Panel getMethodList() {
		return methodList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openntf.domino.demoApp.pages.DemoAppViewInterface#setMethodList(com. vaadin.ui.VerticalLayout)
	 */
	@Override
	public void setMethodList(Panel methodList) {
		this.methodList = methodList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openntf.domino.demoApp.pages.DemoAppViewInterface#getSourceCode()
	 */
	@Override
	public VerticalLayout getSourceCode() {
		return sourceCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openntf.domino.demoApp.pages.DemoAppViewInterface#setSourceCode(com. vaadin.ui.VerticalLayout)
	 */
	@Override
	public void setSourceCode(VerticalLayout sourceCode) {
		this.sourceCode = sourceCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openntf.domino.demoApp.pages.DemoAppViewInterface#getPageBody()
	 */
	@Override
	public HorizontalLayout getPageBody() {
		return pageBody;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openntf.domino.demoApp.pages.DemoAppViewInterface#setPageBody(com. vaadin.ui.HorizontalLayout)
	 */
	@Override
	public void setPageBody(HorizontalLayout pageBody) {
		this.pageBody = pageBody;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openntf.domino.demoApp.pages.DemoAppViewInterface#getSubNavigation()
	 */
	@Override
	public CssLayout getSubNavigation() {
		return subNavigation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openntf.domino.demoApp.pages.DemoAppViewInterface#setSubNavigation( com.vaadin.ui.CssLayout)
	 */
	@Override
	public void setSubNavigation(CssLayout subNavigation) {
		this.subNavigation = subNavigation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openntf.domino.demoApp.pages.DemoAppViewInterface#getContentPanel()
	 */
	@Override
	public Panel getContentPanel() {
		return contentPanel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openntf.domino.demoApp.pages.DemoAppViewInterface#setContentPanel(com .vaadin.ui.Panel)
	 */
	@Override
	public void setContentPanel(Panel contentPanel) {
		this.contentPanel = contentPanel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openntf.domino.demoApp.pages.DemoAppViewInterface#isShowNavigation()
	 */
	@Override
	public boolean isShowNavigation() {
		return showNavigation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openntf.domino.demoApp.pages.DemoAppViewInterface#setShowNavigation( boolean)
	 */
	@Override
	public void setShowNavigation(boolean showNavigation) {
		this.showNavigation = showNavigation;
	}

}
