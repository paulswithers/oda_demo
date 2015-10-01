package org.openntf.domino.demoApp.subpages;

import org.openntf.domino.Database.FTDomainSearchOption;
import org.openntf.domino.Database.FTDomainSortOption;
import org.openntf.domino.Database.FTIndexFrequency;
import org.openntf.domino.Database.FTIndexOption;
import org.openntf.domino.Database.FTSearchOption;
import org.openntf.domino.Database.FTSortOption;
import org.openntf.domino.demoApp.components.Html_Separator;
import org.openntf.domino.demoApp.components.Html_Separator.SeparatorType;
import org.openntf.domino.demoApp.pages.BaseView;
import org.openntf.domino.demoAppUtil.FactoryUtils;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class Database_FTIndex extends BaseSubPage {
	private static final long serialVersionUID = 1L;
	private Panel subContentPanel = new Panel();
	private VerticalLayout ftIndexLayout = null;
	private VerticalLayout ftFrequencyLayout = null;
	private VerticalLayout ftSearchOptionLayout = null;
	private VerticalLayout ftSortOptionLayout = null;
	private VerticalLayout ftDomainSearchOptionLayout = null;
	private VerticalLayout ftDomainSortOptionLayout = null;

	public Database_FTIndex(BaseView parentView) {
		super(parentView);
	}

	@Override
	public void loadContent() {
		Label label1 = new Label("A number of enums related to Full Text Indexing have been added to OpenNTF Domino API.");
		Button button1 = new Button("FTIndexOption");
		button1.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		button1.addStyleName("floating-btn");
		button1.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				getSubContentPanel().setContent(getFtIndexLayout());
			}
		});
		Button button2 = new Button("FTIndexFrequency");
		button2.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		button2.addStyleName("floating-btn");
		button2.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				getSubContentPanel().setContent(getFtFrequencyLayout());
			}
		});
		Button button3 = new Button("FTSearchOption");
		button3.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		button3.addStyleName("floating-btn");
		button3.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				getSubContentPanel().setContent(getFtSearchOptionLayout());
			}
		});
		Button button4 = new Button("FTSortOption");
		button4.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		button4.addStyleName("floating-btn");
		button4.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				getSubContentPanel().setContent(getFtSortOptionLayout());
			}
		});
		Button button5 = new Button("FTDomainSearchOption");
		button5.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		button5.addStyleName("floating-btn");
		button5.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				getSubContentPanel().setContent(getFtDomainSearchOptionLayout());
			}
		});
		Button button6 = new Button("FTDomainSortOption");
		button6.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		button6.addStyleName("floating-btn");
		button6.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				getSubContentPanel().setContent(getFtDomainSortOptionLayout());
			}
		});
		addComponents(label1, new Html_Separator(SeparatorType.NEW_LINE), button1, button2, button3, button4, button5, button6,
				new Html_Separator(SeparatorType.NEW_LINE), getSubContentPanel());
	}

	private Panel getSubContentPanel() {
		return subContentPanel;
	}

	private void setSubContentPanel(Panel subContentPanel) {
		this.subContentPanel = subContentPanel;
	}

	private VerticalLayout getFtIndexLayout() {
		if (null == ftIndexLayout) {
			setFtIndexLayout();
		}
		return ftIndexLayout;
	}

	private void setFtIndexLayout() {
		ftIndexLayout = new VerticalLayout();
		Label label1 = new Label("FTIndexOption");
		label1.setStyleName(ValoTheme.LABEL_H3);
		Label label2 = new Label("Used by " + FactoryUtils.addCodeString("Database.createFTIndex()")
				+ ". Normally that method takes an integer which is the total of the options desired, but this overloaded method "
				+ "allows developers to use an enum. Options are:<ul>");
		label2.setContentMode(ContentMode.HTML);
		ftIndexLayout.addComponents(label1, label2);
		for (FTIndexOption elem : FTIndexOption.values()) {
			Label label3 = new Label("<li>" + elem + " - " + elem.getValue() + "</li>", ContentMode.HTML);
			ftIndexLayout.addComponent(label3);
		}
		Label label4 = new Label("</ul>", ContentMode.HTML);
		ftIndexLayout.addComponent(label4);
	}

	private VerticalLayout getFtFrequencyLayout() {
		if (null == ftFrequencyLayout) {
			setFtFrequencyLayout();
		}
		return ftFrequencyLayout;
	}

	private void setFtFrequencyLayout() {
		ftFrequencyLayout = new VerticalLayout();
		Label label1 = new Label("FTIndexFrequency");
		label1.setStyleName(ValoTheme.LABEL_H3);
		Label label2 = new Label("Used by " + FactoryUtils.addCodeString("Database.setFTIndexFrequency()")
				+ ". Normally that method takes an integer, but this overloaded method allows developers to use an enum. "
				+ "<b>NOTE:</b> There is no corresponding getter, to get the FT index frequency as an enum. " + "Options are:<ul>");
		label2.setContentMode(ContentMode.HTML);
		ftFrequencyLayout.addComponents(label1, label2);
		for (FTIndexFrequency elem : FTIndexFrequency.values()) {
			Label label3 = new Label("<li>" + elem + " - " + elem.getValue() + "</li>", ContentMode.HTML);
			ftFrequencyLayout.addComponent(label3);
		}
		Label label4 = new Label("</ul>", ContentMode.HTML);
		ftFrequencyLayout.addComponent(label4);
	}

	private VerticalLayout getFtSearchOptionLayout() {
		if (null == ftSearchOptionLayout) {
			setFtSearchOptionLayout();
		}
		return ftSearchOptionLayout;
	}

	private void setFtSearchOptionLayout() {
		ftSearchOptionLayout = new VerticalLayout();
		Label label1 = new Label("FTSearchOption");
		label1.setStyleName(ValoTheme.LABEL_H3);
		Label label2 = new Label("Used by " + FactoryUtils.addCodeString("Database.FTSearch()")
				+ " methods. Normally those methods takes an integer, but the overloaded methods allows developers to use an enum. "
				+ "Options are:<ul>");
		label2.setContentMode(ContentMode.HTML);
		ftSearchOptionLayout.addComponents(label1, label2);
		for (FTSearchOption elem : FTSearchOption.values()) {
			Label label3 = new Label("<li>" + elem + " - " + elem.getValue() + "</li>", ContentMode.HTML);
			ftSearchOptionLayout.addComponent(label3);
		}
		Label label4 = new Label("</ul>", ContentMode.HTML);
		ftSearchOptionLayout.addComponent(label4);
	}

	private VerticalLayout getFtSortOptionLayout() {
		if (null == ftSortOptionLayout) {
			setFtSortOptionLayout();
		}
		return ftSortOptionLayout;
	}

	private void setFtSortOptionLayout() {
		ftSortOptionLayout = new VerticalLayout();
		Label label1 = new Label("FTSortOption");
		label1.setStyleName(ValoTheme.LABEL_H3);
		Label label2 = new Label("Used by " + FactoryUtils.addCodeString("Database.FTSearch()")
				+ " methods. Normally those methods takes an integer, but the overloaded methods allows developers to use an enum. "
				+ "Options are:<ul>");
		label2.setContentMode(ContentMode.HTML);
		ftSortOptionLayout.addComponents(label1, label2);
		for (FTSortOption elem : FTSortOption.values()) {
			Label label3 = new Label("<li>" + elem + " - " + elem.getValue() + "</li>", ContentMode.HTML);
			ftSortOptionLayout.addComponent(label3);
		}
		Label label4 = new Label("</ul>", ContentMode.HTML);
		ftSortOptionLayout.addComponent(label4);
	}

	private VerticalLayout getFtDomainSearchOptionLayout() {
		if (null == ftDomainSearchOptionLayout) {
			setFtDomainSearchOptionLayout();
		}
		return ftDomainSearchOptionLayout;
	}

	private void setFtDomainSearchOptionLayout() {
		ftDomainSearchOptionLayout = new VerticalLayout();
		Label label1 = new Label("FTDomainSearchOption");
		label1.setStyleName(ValoTheme.LABEL_H3);
		Label label2 = new Label("Used by " + FactoryUtils.addCodeString("Database.FTDomainSearch()")
				+ " methods. Normally those methods takes an integer, but the overloaded methods allows developers to use an enum. "
				+ "There are more options available than " + FactoryUtils.addCodeString("Database.FTSearchOption") + ".Options are:<ul>");
		label2.setContentMode(ContentMode.HTML);
		ftDomainSearchOptionLayout.addComponents(label1, label2);
		for (FTDomainSearchOption elem : FTDomainSearchOption.values()) {
			Label label3 = new Label("<li>" + elem + " - " + elem.getValue() + "</li>", ContentMode.HTML);
			ftDomainSearchOptionLayout.addComponent(label3);
		}
		Label label4 = new Label("</ul>", ContentMode.HTML);
		ftDomainSearchOptionLayout.addComponent(label4);
	}

	private VerticalLayout getFtDomainSortOptionLayout() {
		if (null == ftDomainSortOptionLayout) {
			setFtDomainSortOptionLayout();
		}
		return ftDomainSortOptionLayout;
	}

	private void setFtDomainSortOptionLayout() {
		ftDomainSortOptionLayout = new VerticalLayout();
		Label label1 = new Label("FTDomainSortOption");
		label1.setStyleName(ValoTheme.LABEL_H3);
		Label label2 = new Label("Used by " + FactoryUtils.addCodeString("Database.FTDomainSearch()")
				+ " methods. Normally those methods takes an integer, but the overloaded methods allows developers to use an enum. "
				+ "There are fewer options than " + FactoryUtils.addCodeString("Database.FTSortOption") + ". Options are:<ul>");
		label2.setContentMode(ContentMode.HTML);
		ftDomainSortOptionLayout.addComponents(label1, label2);
		for (FTDomainSortOption elem : FTDomainSortOption.values()) {
			Label label3 = new Label("<li>" + elem + " - " + elem.getValue() + "</li>", ContentMode.HTML);
			ftDomainSortOptionLayout.addComponent(label3);
		}
		Label label4 = new Label("</ul>", ContentMode.HTML);
		ftDomainSortOptionLayout.addComponent(label4);
	}

}
