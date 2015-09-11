package org.openntf.domino.demoApp;

import java.util.concurrent.Callable;

import org.openntf.domino.utils.Factory;
import org.openntf.domino.utils.Factory.SessionType;
import org.openntf.domino.xots.Tasklet;

public class XotsTests {

	@Tasklet(session = Tasklet.Session.CLONE)
	public static class SessionCallable implements Callable<String> {

		public SessionCallable() {

		}

		public String call() {
			try {
				System.out.println("Checking user");
				String name = Factory.getSession(SessionType.CURRENT).getEffectiveUserName();
				System.out.println("Got user " + name);
				return name;
			} catch (Throwable t) {
				t.printStackTrace();
				return t.getMessage();
			}
		}
	}

}
