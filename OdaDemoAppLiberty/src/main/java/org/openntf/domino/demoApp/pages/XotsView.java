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
	private boolean isShowingRunnable = true;

	public enum XotsSubPage {
		SUMMARY_DETAILS("Summary Details", Target.BOTH), CALLABLE_EXAMPLE("Callable Example",
				Target.BOTH), RUNNABLE_EXAMPLE("Runnable Example", Target.BOTH), TASKLETS("Tasklets", Target.BOTH);

		private String value_;
		private Target target_;

		private XotsSubPage(String subPage, Target target) {
			value_ = subPage;
			target_ = target;
		}

		public String getValue() {
			return value_;
		}

		public Target getTarget() {
			return target_;
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
		if (getCurrentPage().equals(subPage)) {
			return;
		}
		if (!isShowingRunnable) {
			loadRunnableSource();
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
			isShowingRunnable = false;
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
		getSourceCode().removeAllComponents();
		Label label1 = new Label("<div class=\"domino-code\">@Tasklet(session = Tasklet.Session.CLONE)<br/>"
				+ "public static class SessionRunnable implements AbstractXotsRunnable<String> {<br/><br/>"
				+ "&nbsp;&nbsp;public SessionRunnable() {<br/><br/>&nbsp;&nbsp;}<br/><br/>&nbsp;&nbsp;public void run() {"
				+ "<br/>&nbsp;&nbsp;&nbsp;&nbsp;try {<br/>&nbsp;&nbsp;&nbsp;&nbsp;String name = Factory.getSession(SessionType.CURRENT). getEffectiveUserName();"
				+ "<br/>&nbsp;&nbsp;&nbsp;&nbsp;return name;<br/>&nbsp;&nbsp;} catch (Throwable t) {<br/>&nbsp;&nbsp;&nbsp;&nbsp;t.printStackTrace();<br/>"
				+ "&nbsp;&nbsp;&nbsp;&nbsp;system.out.println(t.getMessage());<br/>&nbsp;&nbsp;&nbsp;&nbsp;}<br/>&nbsp;&nbsp;}<br/>}<br/><br/>"
				+ "Xots.getService().submit(new SessionRunnable());</div>");
		label1.setContentMode(ContentMode.HTML);
		getSourceCode().addComponent(label1);
	}

	public void loadCallableSource() {
		getSourceCode().removeAllComponents();
		Label label1 = new Label("<div class=\"domino-code\">@Tasklet(session = Tasklet.Session.CLONE)<br/>"
				+ "public static class SessionCallable implements AbstractXotsCallable<String> {<br/>private int threadNo;<br/>"
				+ "&nbsp;&nbsp;public SessionCallable(int threadNo) {<br/>this.threadNo = threadNo;<br/>&nbsp;&nbsp;}<br/><br/>&nbsp;&nbsp;public String call() {"
				+ "<br/>&nbsp;&nbsp;&nbsp;&nbsp;try {<br/>&nbsp;&nbsp;&nbsp;&nbsp;String name = Factory.getSession(SessionType.CURRENT). getEffectiveUserName();"
				+ "<br/>&nbsp;&nbsp;&nbsp;&nbsp;return \"Hello \" + name + \" from thread \" + threadNo;<br/>&nbsp;&nbsp;} catch (Throwable t) {<br/>&nbsp;&nbsp;&nbsp;&nbsp;t.printStackTrace();<br/>"
				+ "&nbsp;&nbsp;&nbsp;&nbsp;return t.getMessage();<br/>&nbsp;&nbsp;&nbsp;&nbsp;}<br/>&nbsp;&nbsp;}<br/>}<br/><br/>"
				+ "List<Future<String>> results = new ArrayList<Future<String>>();<br/>for (int i = 0; i < 10; i++) {<br/>&nbsp;&nbsp;results.add(Xots.getService().submit(new SessionCallable(i)));<br/>}"
				+ "<br/>for (Future<String> f : results) {<br/>&nbsp;&nbsp;try {<br/>&nbsp;&nbsp;&nbsp;&nbsp;System.out.println(f.get());<br/>&nbsp;&nbsp;} "
				+ "catch (InterruptedException e) {<br/>&nbsp;&nbsp;&nbsp;&nbsp;e.printStackTrace();<br/>&nbsp;&nbsp;} catch (ExecutionException e) {<br/>&nbsp;&nbsp;&nbsp;&nbsp;e.printStackTrace();<br/>"
				+ "&nbsp;&nbsp;}<br/></div>}");
		label1.setContentMode(ContentMode.HTML);
		getSourceCode().addComponent(label1);
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

}
