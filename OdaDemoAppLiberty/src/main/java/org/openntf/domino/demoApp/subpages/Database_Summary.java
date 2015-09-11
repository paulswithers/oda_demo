package org.openntf.domino.demoApp.subpages;

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
