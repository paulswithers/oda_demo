package org.openntf.domino.demoApp.subpages;

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

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openntf.domino.demoApp.DemoUI;
import org.openntf.domino.demoApp.components.TargetSelector.Target;

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

import org.openntf.domino.demoApp.pages.BaseView;
import org.openntf.domino.demoApp.utils.DatabaseUtils;

import com.vaadin.ui.VerticalLayout;

public class BaseSubPage extends VerticalLayout implements BaseSubPageInterface {
	public static final Logger logger = Logger.getLogger(DatabaseUtils.class.getName());
	private static final long serialVersionUID = 1L;
	private static final String ERROR_DONT_USE_DIRECTLY = "This base method shouldn't be used directly.";
	private BaseView parentView;
	private boolean loaded = false;
	private Target targetContext;
	private Properties props = new Properties();

	public BaseSubPage(BaseView parentView) {
		setParentView(parentView);
		addStyleName("body-layout");
		try {
			InputStream inputStream = getClass().getResourceAsStream("strings.properties");
			props.load(inputStream);
		} catch (IOException t) {
			logger.log(Level.SEVERE, t.getMessage(), t);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openntf.domino.demoApp.subpages.BaseSubPageInterface#load()
	 */
	@Override
	public void load() {
		if (!isLoaded()) {
			setTargetContext(DemoUI.get().getAppTarget());
			loadContent();
			setLoaded(true);
		} else {
			if (!getTargetContext().equals(DemoUI.get().getAppTarget())) {
				loadContent();
			}
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

	@Override
	public Properties getProps() {
		return props;
	}

	@Override
	public void setProps(Properties props) {
		this.props = props;
	}

	@Override
	public Target getTargetContext() {
		return targetContext;
	}

	@Override
	public void setTargetContext(Target targetContext) {
		this.targetContext = targetContext;
	}

}
