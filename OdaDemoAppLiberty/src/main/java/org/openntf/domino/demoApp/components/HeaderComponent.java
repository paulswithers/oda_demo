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
import org.openntf.domino.demoAppUtil.FactoryUtils;
import org.openntf.domino.utils.DominoUtils;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class HeaderComponent extends VerticalLayout {

	private static final long serialVersionUID = 4465325630005163198L;
	private MenuBar menubar;
	private String userName;

	public HeaderComponent(DemoUI ui) {
		setStyleName("header");

		HorizontalLayout bannerArea = new HorizontalLayout();
		bannerArea.setStyleName("menuArea");
		bannerArea.setSizeFull();

		ThemeResource resource = new ThemeResource("img/openntf_banner.jpg");
		Image bannerImg = new Image();
		bannerImg.setAlternateText("OpenNTF");
		bannerImg.setHeight("70px");
		bannerImg.setDescription("OpenNTF image");
		bannerImg.setSource(resource);
		bannerImg.setWidth(null);

		setMenubar(new MenuBar());
		getMenubar().setStyleName(ValoTheme.MENU_SUBTITLE);
		getMenubar().setWidth(100, Unit.PERCENTAGE);

		MenuItem logout = menubar.addItem("Logout", null);
		logout.setStyleName("menuRight");

		MenuItem userItem = menubar.addItem(getUserName(), null);
		userItem.setStyleName("menuRight");

		bannerArea.addComponents(bannerImg, menubar);
		bannerArea.setExpandRatio(menubar, 1);
		addComponent(bannerArea);
		setExpandRatio(bannerArea, 1);
		setSizeFull();
	}

	public String getUserName() {
		if (null == userName) {
			setUserName();
		}
		return userName;
	}

	public void setUserName() {
		String name = FactoryUtils.getUserSession().getEffectiveUserName();
		this.userName = DominoUtils.toCommonName(name);
	}

	public MenuBar getMenubar() {
		return menubar;
	}

	public void setMenubar(MenuBar menubar) {
		this.menubar = menubar;
	}

}
