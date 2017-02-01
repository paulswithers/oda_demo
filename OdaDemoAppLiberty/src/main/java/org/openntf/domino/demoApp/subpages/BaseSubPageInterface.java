package org.openntf.domino.demoApp.subpages;

import java.util.Properties;

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

public interface BaseSubPageInterface {

	void load();

	void loadContent();

	boolean isLoaded();

	void setLoaded(boolean loaded);

	BaseView getParentView();

	void setParentView(BaseView parentView);

	public Properties getProps();

	public void setProps(Properties props);

	public Target getTargetContext();

	public void setTargetContext(Target targetContext);

}