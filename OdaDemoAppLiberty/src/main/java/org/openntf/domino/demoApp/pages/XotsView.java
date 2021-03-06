package org.openntf.domino.demoApp.pages;

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
import org.openntf.domino.demoApp.components.TargetSelector;
import org.openntf.domino.demoApp.components.TargetSelector.Target;
import org.openntf.domino.demoApp.subpages.xots.Xots_CallableExample;
import org.openntf.domino.demoApp.subpages.xots.Xots_RunnableExample;
import org.openntf.domino.demoApp.subpages.xots.Xots_Summary;
import org.openntf.domino.demoApp.subpages.xots.Xots_Tasklets;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

public class XotsView extends BaseView {
	private static final long serialVersionUID = 1L;
	public static String VIEW_NAME = "XOTS";
	public static String VIEW_LABEL = "XOTS";
	private Xots_Summary summaryDetails = new Xots_Summary(this);
	private Xots_CallableExample threadExample = new Xots_CallableExample(this);
	private Xots_RunnableExample runnableExample = new Xots_RunnableExample(this);
	private Xots_Tasklets taskletDetails = new Xots_Tasklets(this);
	private XotsSubPage currentPage;
	private SourceCodeType currentSourcePage;

	private enum SourceCodeType {
		RUNNABLE, CALLABLE;
	}

	public enum XotsSubPage {
		SUMMARY_DETAILS("Summary Details", Target.BOTH, SourceCodeType.RUNNABLE), CALLABLE_EXAMPLE("Callable Example",
				Target.BOTH, SourceCodeType.CALLABLE), RUNNABLE_EXAMPLE("Runnable Example", Target.BOTH,
						SourceCodeType.RUNNABLE), TASKLETS("Tasklets", Target.BOTH, SourceCodeType.RUNNABLE);

		private String value_;
		private Target target_;
		private SourceCodeType sourcePage_;

		private XotsSubPage(String subPage, Target target, SourceCodeType sourcePage) {
			value_ = subPage;
			target_ = target;
			sourcePage_ = sourcePage;
		}

		public String getValue() {
			return value_;
		}

		public Target getTarget() {
			return target_;
		}

		public SourceCodeType getSourcePage() {
			return sourcePage_;
		}
	}

	public XotsView() {
		setShowNavigation(true);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		super.enter(event);
		getRightSliderContent().setSelectedTab(getSourceTab());
	}

	@Override
	public void loadContent() {
		loadContent(getCurrentPage());
	}

	public void loadContent(XotsSubPage subPage) {
		if (!getCurrentPage().equals(subPage)) {
		}
		switch (subPage) {
		case SUMMARY_DETAILS:
			summaryDetails.load();
			getContentPanel().setContent(summaryDetails);
			break;
		case CALLABLE_EXAMPLE:
			threadExample.load();
			getContentPanel().setContent(threadExample);
			loadCallableSource();
			break;
		case RUNNABLE_EXAMPLE:
			runnableExample.load();
			getContentPanel().setContent(runnableExample);
			break;
		case TASKLETS:
			taskletDetails.load();
			getContentPanel().setContent(taskletDetails);
			break;
		default:
			getContentPanel().setContent(new Label("<b>NO CONTENT SET FOR THIS PAGE</b>", ContentMode.HTML));
		}
		if (!subPage.getSourcePage().equals(getCurrentSourcePage())) {
			switch (subPage.getSourcePage()) {
			case CALLABLE:
				loadCallableSource();
				break;
			default:
				loadRunnableSource();
			}
			setCurrentSourcePage(subPage.getSourcePage());
		}
	}

	@Override
	public void loadNavigation() {
		getSubNavigation().removeAllComponents();

		TargetSelector target1 = new TargetSelector(this);
		getSubNavigation().addComponent(target1);
		Target currTarget = DemoUI.get().getAppTarget();

		for (final XotsSubPage subPage : XotsSubPage.values()) {
			if (Target.BOTH.equals(currTarget) || Target.BOTH.equals(subPage.getTarget())
					|| currTarget.equals(subPage.getTarget())) {
				Button button1 = new Button(subPage.getValue());
				button1.addStyleName(ValoTheme.BUTTON_LINK);
				button1.addStyleName(ValoTheme.BUTTON_SMALL);
				button1.addStyleName("navigation-button");
				button1.addClickListener(new ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						// Reset relevant page
						loadContent(subPage);
					}

				});
				getSubNavigation().addComponents(button1);
			}
		}
	}

	@Override
	public void loadMethodList() {
		return;
	}

	@Override
	public void loadSource() {
		loadRunnableSource();
	}

	public void loadRunnableSource() {
		loadSimpleSource("xotsRunnable");
	}

	public void loadCallableSource() {
		loadSimpleSource("xotsCallable");
	}

	public XotsSubPage getCurrentPage() {
		if (null == currentPage) {
			currentPage = XotsSubPage.SUMMARY_DETAILS;
		}
		return currentPage;
	}

	public void setCurrentPage(XotsSubPage currentPage) {
		this.currentPage = currentPage;
	}

	public SourceCodeType getCurrentSourcePage() {
		if (null == currentSourcePage) {
			setCurrentSourcePage(SourceCodeType.RUNNABLE);
		}
		return currentSourcePage;
	}

	public void setCurrentSourcePage(SourceCodeType currentSourcePage) {
		this.currentSourcePage = currentSourcePage;
	}

}
