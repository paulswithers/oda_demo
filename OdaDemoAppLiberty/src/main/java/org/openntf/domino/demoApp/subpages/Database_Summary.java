package org.openntf.domino.demoApp.subpages;

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

import org.openntf.domino.Database;
import org.openntf.domino.demoApp.pages.BaseView;
import org.openntf.domino.demoAppUtil.FactoryUtils;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;

public class Database_Summary extends BaseSubPage {
	private static final long serialVersionUID = 1L;

	public Database_Summary(BaseView parentView) {
		super(parentView);
	}

	public void loadContent() {
		Database db1 = FactoryUtils.getDemoDatabase();
		Label label1 = new Label("Database details are: " + db1.getServer() + ", " + db1.getFilePath() + "<br/>ApiPath is: " + db1.getApiPath()
				+ "<br/>MetaReplicaId is: " + db1.getMetaReplicaID());
		label1.setContentMode(ContentMode.HTML);
		addComponent(label1);
	}

}
