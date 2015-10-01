package org.openntf.domino.demoApp.subpages;

import org.openntf.domino.demoApp.pages.BaseView;

public interface BaseSubPageInterface {

	void load();

	void loadContent();

	boolean isLoaded();

	void setLoaded(boolean loaded);

	BaseView getParentView();

	void setParentView(BaseView parentView);

}