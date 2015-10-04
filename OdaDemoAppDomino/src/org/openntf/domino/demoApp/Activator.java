package org.openntf.domino.demoApp;

import org.openntf.domino.utils.Factory;
import org.openntf.domino.utils.Factory.SessionType;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

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

import lotus.domino.NotesThread;

public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
	 * )
	 */
	@Override
	public void start(BundleContext bundleContext) throws Exception {
		// startDominoThread();
		Activator.context = bundleContext;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		// stopDominoThread();
		Activator.context = null;
	}

	public static void startDominoThread() {

		if (!Factory.isStarted()) { // Wait for ODA Factory to beready
			int timeout = 30; // Maximum wait time for Factory startup;
			while (!Factory.isStarted() && timeout > 0) {
				try {
					System.out.print(".");
					timeout--;
					Thread.sleep(1000);
				} catch (final InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("Starting factory thread...");
		Factory.initThread(Factory.STRICT_THREAD_CONFIG);

		// Override the default session factory.
		System.out.println("Overriding session factory");
		Factory.setSessionFactory(Factory.getSessionFactory(SessionType.NATIVE), SessionType.CURRENT);

		System.out.println("Initialising Notes Thread");
		NotesThread.sinitThread();

	}

	public static void stopDominoThread() {

		NotesThread.stermThread();
		Factory.termThread();

	}

}
