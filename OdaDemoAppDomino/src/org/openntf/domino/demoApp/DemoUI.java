package org.openntf.domino.demoApp;

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

import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import org.openntf.domino.demoApp.components.HeaderComponent;
import org.openntf.domino.demoApp.components.TargetSelector.Target;
import org.openntf.domino.demoApp.pages.DatabaseView;
import org.openntf.domino.demoApp.pages.DateTimeView;
import org.openntf.domino.demoApp.pages.DocumentView;
import org.openntf.domino.demoApp.pages.ErrorView;
import org.openntf.domino.demoApp.pages.MiscView;
import org.openntf.domino.demoApp.pages.SessionView;
import org.openntf.domino.demoApp.pages.ViewView;
import org.openntf.domino.demoApp.pages.XotsView;
import org.openntf.domino.demoApp.utils.FactoryUtils;
import org.vaadin.sliderpanel.SliderPanel;
import org.vaadin.sliderpanel.SliderPanelBuilder;
import org.vaadin.sliderpanel.SliderPanelStyles;
import org.vaadin.sliderpanel.client.SliderMode;
import org.vaadin.sliderpanel.client.SliderTabPosition;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Viewport;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@Viewport("user-scalable=no,initial-scale=1.0")
@SuppressWarnings("serial")
@Theme("OdaDemoTheme")
@Widgetset("org.openntf.domino.demoApp.widgetset.Oda_demoappWidgetset")
public class DemoUI extends UI {
	private static final Logger logger = Logger.getLogger(DemoUI.class.getName());

	private Navigator uiNavigator;
	private HeaderComponent header;
	private VerticalLayout body;
	private SliderPanel rightSlider;
	private boolean setup;
	private Target appTarget;
	private Label configDetails;
	private ConcurrentHashMap<String, Integer> createdDocs;
	private ConcurrentHashMap<String, Integer> updatedDocs;

	@VaadinServletConfiguration(ui = DemoUI.class, productionMode = false, heartbeatInterval = 300)
	public static class UIServlet extends VaadinServlet {

	}

	public Navigator getUiNavigator() {
		return uiNavigator;
	}

	public void setUiNavigator(Navigator uiNavigator) {
		this.uiNavigator = uiNavigator;
	}

	@Override
	protected void init(VaadinRequest request) {
		Responsive.makeResponsive(this);

		setAppTarget(Target.BOTH);

		addStyleName(ValoTheme.UI_WITH_MENU);

		getPage().setTitle("OpenNTF Domino API Demo App");

		setStyleName("main-screen");

		// Component that allows bottom SliderPanel
		final VerticalLayout outerLayout = new VerticalLayout();
		outerLayout.setSizeFull();
		outerLayout.setMargin(false);
		outerLayout.setSpacing(false);

		// Bottom Slider Panel
		VerticalLayout cfgSettings = new VerticalLayout();
		cfgSettings.setStyleName("config-settings");
		Label cfgBody;
		Button button1 = new Button("Refresh");
		button1.setIcon(FontAwesome.REFRESH);
		button1.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
		button1.addStyleName(ValoTheme.BUTTON_QUIET);
		button1.addStyleName("right");
		button1.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				getConfigDetails().setValue(FactoryUtils.dumpConfigSettings());
			}

		});
		setConfigDetails(new Label(FactoryUtils.dumpConfigSettings(), ContentMode.HTML));
		cfgSettings.addComponents(button1, getConfigDetails());
		SliderPanel cfgSlider = new SliderPanelBuilder(cfgSettings).caption("CONFIGURATION SETTINGS")
				.mode(SliderMode.BOTTOM).style(SliderPanelStyles.COLOR_GRAY).tabPosition(SliderTabPosition.MIDDLE)
				.build();

		// contentLayout component contains Header and Body, stored in
		// middleLayout
		final VerticalLayout contentLayout = new VerticalLayout();
		contentLayout.setSpacing(false);
		contentLayout.setSizeFull();

		// Add header to contentMayout
		setHeader(new HeaderComponent(this));
		getHeader().setHeight("70px");
		contentLayout.addComponent(getHeader());

		// Add body to content layout
		setBody(new VerticalLayout());
		getBody().setStyleName("body-layout");
		getBody().setHeight(100, Unit.PERCENTAGE);
		contentLayout.addComponent(getBody());
		contentLayout.setExpandRatio(getBody(), 1);

		// Component allowing Right Slider Panel
		final HorizontalLayout innerLayout = new HorizontalLayout();
		innerLayout.setSizeFull();
		innerLayout.addComponent(contentLayout);
		innerLayout.setExpandRatio(contentLayout, 1);
		setRightSlider(new SliderPanelBuilder(new VerticalLayout()).caption("INFORMATION").mode(SliderMode.RIGHT)
				.style(SliderPanelStyles.COLOR_WHITE).tabPosition(SliderTabPosition.MIDDLE).fixedContentSize(450)
				.build());
		innerLayout.addComponent(getRightSlider());

		// Add Navigator and menu items
		setUiNavigator(new Navigator(this, getBody()));
		getUiNavigator().setErrorView(ErrorView.class);
		addNewMenuItem(SessionView.VIEW_NAME, SessionView.VIEW_LABEL, new SessionView());
		addNewMenuItem(DatabaseView.VIEW_NAME, DatabaseView.VIEW_LABEL, new DatabaseView());
		addNewMenuItem(ViewView.VIEW_NAME, ViewView.VIEW_LABEL, new ViewView());
		addNewMenuItem(DocumentView.VIEW_NAME, DocumentView.VIEW_LABEL, new SessionView());
		addNewMenuItem(DateTimeView.VIEW_NAME, DateTimeView.VIEW_LABEL, new SessionView());
		addNewMenuItem(MiscView.VIEW_NAME, MiscView.VIEW_LABEL, new SessionView());
		addNewMenuItem(XotsView.VIEW_NAME, XotsView.VIEW_LABEL, new XotsView());

		// Add inner layout to outer layout
		outerLayout.addComponent(innerLayout);
		outerLayout.setExpandRatio(innerLayout, 1);
		outerLayout.addComponent(cfgSlider);

		// Add outer layout to DemoUI
		setContent(outerLayout);
	}

	public void addNewMenuItem(final String viewName, final String viewLabel, final View viewObj) {
		getUiNavigator().addView(viewName, viewObj);
		getHeader().getMenubar().addItem(viewLabel, new MenuBar.Command() {
			@Override
			public void menuSelected(MenuItem selectedItem) {
				if (isSetup()) {
					for (MenuItem itm : getHeader().getMenubar().getItems()) {
						if ("highlight".equals(itm.getStyleName())) {
							itm.setStyleName("");
						}
					}

					selectedItem.setStyleName("highlight");
					DemoUI.getCurrent().getNavigator().navigateTo(viewName);
				} else {
					addMessage("", "You must set up the database before navigating the application",
							Type.ERROR_MESSAGE);
				}
			}
		});
	}

	public void addMessage(String title, String msg, Type notificationType) {
		Notification.show(title, msg, notificationType);
	}

	public static DemoUI get() {
		return (DemoUI) UI.getCurrent();
	}

	public HeaderComponent getHeader() {
		return header;
	}

	public void setHeader(HeaderComponent header) {
		this.header = header;
	}

	public VerticalLayout getBody() {
		return body;
	}

	public void setBody(VerticalLayout body) {
		this.body = body;
	}

	public SliderPanel getRightSlider() {
		return rightSlider;
	}

	public void setRightSlider(SliderPanel rightSlider) {
		this.rightSlider = rightSlider;
	}

	public boolean isSetup() {
		return setup;
	}

	public void setSetup(boolean setup) {
		this.setup = setup;
	}

	public Target getAppTarget() {
		return appTarget;
	}

	public void setAppTarget(Target appTarget) {
		this.appTarget = appTarget;
	}

	public Label getConfigDetails() {
		return configDetails;
	}

	public void setConfigDetails(Label configDetails) {
		this.configDetails = configDetails;
	}

	public ConcurrentHashMap<String, Integer> getCreatedDocs() {
		return createdDocs;
	}

	public void setCreatedDocs(ConcurrentHashMap<String, Integer> createdDocs) {
		this.createdDocs = createdDocs;
	}

	public ConcurrentHashMap<String, Integer> getUpdatedDocs() {
		return updatedDocs;
	}

	public void setUpdatedDocs(ConcurrentHashMap<String, Integer> updatedDocs) {
		this.updatedDocs = updatedDocs;
	}

}