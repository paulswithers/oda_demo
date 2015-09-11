package org.openntf.domino.demoApp.subpages;

import org.openntf.domino.demoApp.pages.BaseView;

import com.vaadin.ui.VerticalLayout;

public class BaseSubPage extends VerticalLayout implements BaseSubPageInterface {
	private static final long serialVersionUID = 1L;
	private static final String ERROR_DONT_USE_DIRECTLY = "This base method shouldn't be used directly.";
	private BaseView parentView;
	private boolean loaded = false;

	public BaseSubPage(BaseView parentView) {
		setParentView(parentView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openntf.domino.demoApp.subpages.BaseSubPageInterface#load()
	 */
	@Override
	public void load() {
		if (!isLoaded()) {
			loadContent();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.openntf.domino.demoApp.subpages.BaseSubPageInterface#loadContent()
	 */
	@Override
	public void loadContent() {
		throw new IllegalStateException(ERROR_DONT_USE_DIRECTLY);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openntf.domino.demoApp.subpages.BaseSubPageInterface#isLoaded()
	 */
	@Override
	public boolean isLoaded() {
		return loaded;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openntf.domino.demoApp.subpages.BaseSubPageInterface#setLoaded(
	 * boolean)
	 */
	@Override
	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.openntf.domino.demoApp.subpages.BaseSubPageInterface#getParentView()
	 */
	@Override
	public BaseView getParentView() {
		return parentView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.openntf.domino.demoApp.subpages.BaseSubPageInterface#setParentView(
	 * org.openntf.domino.demoApp.pages.BaseView)
	 */
	@Override
	public void setParentView(BaseView parentView) {
		this.parentView = parentView;
	}

}
