package org.openntf.domino.demoApp.subpages.view;

import java.io.Serializable;
import java.util.TreeMap;

import org.openntf.domino.Database;
import org.openntf.domino.View;
import org.openntf.domino.View.IndexType;
import org.openntf.domino.demoApp.pages.BaseView;
import org.openntf.domino.demoApp.subpages.BaseSubPage;
import org.openntf.domino.demoAppUtil.FactoryUtils;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class View_Summary extends BaseSubPage {
	private static final long serialVersionUID = 1L;
	private VerticalLayout table;

	public View_Summary(BaseView parentView) {
		super(parentView);
	}

	@Override
	public void loadContent() {
		TextField dbPath = new TextField("Alternate Database Path");
		loadViewSummary("");
		dbPath.addValueChangeListener(new ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				loadViewSummary((String) event.getProperty().getValue());
			}
		});

		addComponents(dbPath, getTable());
	}

	private void loadViewSummary(String dbPath) {
		Database db1 = FactoryUtils.getDemoDatabase();
		if (!"".equals(dbPath)) {
			try {
				db1 = FactoryUtils.getUserSession().getDatabase(dbPath);
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
		private String alias;
		private String altXPage;
		private String indexType;
		private String autoUpdateDisabled;
		private String hideEmptyCats;
		private String discardIndex;
		private String refreshType;
		private int refreshInterval;
		private int indexDiscardedAfter;

		public ViewItem(View srcView) {
			if ("".equals(srcView.getName())) {
				setName("<NO NAME>");
			} else {
				setName(srcView.getName());
			}
			StringBuilder sb = new StringBuilder();
			for (String alias : srcView.getAliases()) {
				if (sb.length() > 1) {
					sb.append(",");
				}
				sb.append(alias);
			}
			setAlias(sb.toString());
			setAltXPage(srcView.getXPageAlt());
			setIndexType(srcView.getIndexType());
			if (srcView.isDisableAutoUpdate()) {
				setAutoUpdateDisabled("YES");
			} else {
				setAutoUpdateDisabled("NO");
			}
			if (srcView.isHideEmptyCategories()) {
				setHideEmptyCats("YES");
			} else {
				setHideEmptyCats("NO");
			}
			if (srcView.isDiscardIndex()) {
				setDiscardIndex("YES");
			} else {
				setDiscardIndex("NO");
			}
			if (srcView.isManualRefresh()) {
				setRefreshType("MANUAL");
			} else if (srcView.isAutomaticRefresh()) {
				setRefreshType("AUTOMATIC");
			} else if (srcView.isAutoRefreshAfterFirstUse()) {
				setRefreshType("AUTOMATIC AFTER FIRST USE");
			}
			setRefreshInterval(srcView.getAutoRefreshSeconds());
			setIndexDiscardedAfter(srcView.getDiscardHours());
		}

		public String getSummary() {
			String retVal = "";
			try {
				StringBuilder sb = new StringBuilder();
				sb.append("<b>Aliases: </b>" + getAlias());
				sb.append("<br/><b>Index Type: </b>" + getIndexType());
				sb.append("<br/><b>Refresh Type: </b>" + getRefreshType());
				sb.append("<br/><b>Auto Update Disabled: </b>" + getAutoUpdateDisabled());
				sb.append("<br/><b>Hide Empty Categories: </b>" + getHideEmptyCats());
				sb.append("<br/><b>Discard Index: </b>" + getDiscardIndex());
				sb.append("<br/><b>Refresh Interval: </b>" + Integer.toString(getRefreshInterval()));
				sb.append(
						"<br/><b>Index Discarded After: </b>" + Integer.toString(getIndexDiscardedAfter()) + " hours");
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

		public String getAlias() {
			return alias;
		}

		public void setAlias(String alias) {
			this.alias = alias;
		}

		public String getAltXPage() {
			return altXPage;
		}

		public void setAltXPage(String altXPage) {
			this.altXPage = altXPage;
		}

		public String getIndexType() {
			return indexType;
		}

		public void setIndexType(String indexType) {
			this.indexType = indexType;
		}

		public void setIndexType(IndexType indexType) {
			this.indexType = indexType.name();
		}

		public String getAutoUpdateDisabled() {
			return autoUpdateDisabled;
		}

		public void setAutoUpdateDisabled(String autoUpdateDisabled) {
			this.autoUpdateDisabled = autoUpdateDisabled;
		}

		public String getHideEmptyCats() {
			return hideEmptyCats;
		}

		public void setHideEmptyCats(String hideEmptyCats) {
			this.hideEmptyCats = hideEmptyCats;
		}

		public String getDiscardIndex() {
			return discardIndex;
		}

		public void setDiscardIndex(String discardIndex) {
			this.discardIndex = discardIndex;
		}

		public String getRefreshType() {
			return refreshType;
		}

		public void setRefreshType(String refreshType) {
			this.refreshType = refreshType;
		}

		public int getRefreshInterval() {
			return refreshInterval;
		}

		public void setRefreshInterval(int refreshInterval) {
			this.refreshInterval = refreshInterval;
		}

		public int getIndexDiscardedAfter() {
			return indexDiscardedAfter;
		}

		public void setIndexDiscardedAfter(int indexDiscardedAfter) {
			this.indexDiscardedAfter = indexDiscardedAfter;
		}

	}

}
