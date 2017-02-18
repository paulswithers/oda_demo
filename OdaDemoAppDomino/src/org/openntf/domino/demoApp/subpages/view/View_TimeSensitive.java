package org.openntf.domino.demoApp.subpages.view;

import java.io.Serializable;
import java.util.TreeMap;

import org.openntf.domino.Database;
import org.openntf.domino.View;
import org.openntf.domino.demoApp.components.DbInstanceSelector;
import org.openntf.domino.demoApp.pages.BaseView;
import org.openntf.domino.demoApp.subpages.BaseSubPage;
import org.openntf.domino.demoApp.utils.FactoryUtils;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class View_TimeSensitive extends BaseSubPage {
	private static final long serialVersionUID = 1L;
	private VerticalLayout table;

	public View_TimeSensitive(BaseView parentView) {
		super(parentView);
	}

	@Override
	public void loadContent() {
		Label label1 = new Label(getProps().getProperty("getTime"));
		final DbInstanceSelector dbPath = new DbInstanceSelector("Alternate Database");
		dbPath.addValueChangeListener(new ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				loadViewSummary((String) event.getProperty().getValue());
			}
		});
		loadViewSummary("");

		addComponents(label1, dbPath, getTable());
	}

	private void loadViewSummary(String dbPath) {
		Database db1 = FactoryUtils.getDemoDatabase();
		if (!"".equals(dbPath)) {
			try {
				db1 = FactoryUtils.getDemoDatabase(Integer.parseInt(dbPath));
			} catch (Exception e) {
				db1 = FactoryUtils.getDemoDatabase();
			}
		}
		TreeMap<String, ViewItem> viewsMap = new TreeMap<String, ViewItem>();

		for (View view : db1.getViews()) {
			ViewItem itm = new ViewItem(view);
			viewsMap.put(itm.getName(), itm);
		}

		getTable().removeAllComponents();
		table.setSizeFull();
		HorizontalLayout row = new HorizontalLayout();
		row.setSizeFull();
		row.setSpacing(true);
		int col = 0;
		for (String key : viewsMap.keySet()) {
			VerticalLayout column = new VerticalLayout();
			column.setSizeFull();
			column.setSpacing(true);
			ViewItem item = viewsMap.get(key);
			Label label1 = new Label(item.getName());
			label1.setStyleName(ValoTheme.LABEL_H3);
			Label label2 = new Label(item.getSummary());
			label2.setContentMode(ContentMode.HTML);
			column.addComponents(label1, label2);
			row.addComponent(column);
			col++;
			if (col == 3) {
				col = 0;
				getTable().addComponent(row);
				row = new HorizontalLayout();
				row.setSizeFull();
			}
		}
		if (col > 0) {
			getTable().addComponent(row);
		}
	}

	public VerticalLayout getTable() {
		if (null == table) {
			setTable(new VerticalLayout());
		}
		return table;
	}

	public void setTable(VerticalLayout table) {
		this.table = table;
	}

	private class ViewItem implements Serializable {
		private static final long serialVersionUID = 1L;
		private String name;
		private String isTimeSensitive;
		private int indexCount;

		public ViewItem(View srcView) {
			if ("".equals(srcView.getName())) {
				setName("<NO NAME>");
			} else {
				setName(srcView.getName());
			}
			if (srcView.isTimeSensitive()) {
				setIsTimeSensitive("YES");
				;
			} else {
				setIsTimeSensitive("NO");
			}
			setIndexCount(srcView.getIndexCount());
		}

		public String getSummary() {
			String retVal = "";
			try {
				StringBuilder sb = new StringBuilder();
				sb.append("<b>Is Time Sensitive: </b>" + getIsTimeSensitive());
				sb.append("<br/><b>Index Count: </b>" + Integer.toString(getIndexCount()));
				retVal = sb.toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			return retVal;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getIsTimeSensitive() {
			return isTimeSensitive;
		}

		public void setIsTimeSensitive(String isTimeSensitive) {
			this.isTimeSensitive = isTimeSensitive;
		}

		public int getIndexCount() {
			return indexCount;
		}

		public void setIndexCount(int indexCount) {
			this.indexCount = indexCount;
		}

	}

}
