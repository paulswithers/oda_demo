package org.openntf.domino.demoApp.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openntf.domino.demoApp.DemoUI;
import org.openntf.domino.events.EnumEvent;
import org.openntf.domino.events.IDominoEvent;
import org.openntf.domino.events.IDominoListener;
import org.openntf.domino.ext.Database.Events;

public class DocumentListener implements IDominoListener {
	private String filePath;

	public DocumentListener(String filePath_) {
		setFilePath(filePath_);
	}

	// Required method, triggered when Listener fires
	public boolean eventHappened(IDominoEvent event) {
		try {
			if (event.getEvent().equals(Events.AFTER_CREATE_DOCUMENT)) {
				return incrementCreateCount();
			}
			if (event.getEvent().equals(Events.AFTER_UPDATE_DOCUMENT)) {
				return incrementUpdateCount();
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	// Required method, lists events to attach listeners for
	public List<EnumEvent> getEventTypes() {
		ArrayList<EnumEvent> eventList = new ArrayList<EnumEvent>();
		eventList.add(Events.AFTER_CREATE_DOCUMENT);
		eventList.add(Events.AFTER_UPDATE_DOCUMENT);
		return eventList;
	}

	private boolean incrementCreateCount() {
		try {
			Integer docsCreated;
			// Update a "session-scoped" variable
			Map<String, Integer> createdDocs = DemoUI.get().getCreatedDocs();
			if (createdDocs.containsKey(getFilePath())) {
				docsCreated = createdDocs.get(getFilePath()) + 1;
			} else {
				docsCreated = 1;
			}
			DemoUI.get().getCreatedDocs().put(getFilePath(), docsCreated);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private boolean incrementUpdateCount() {
		try {
			Integer docsUpdated;
			// Update a "session-scoped" variable
			Map<String, Integer> updatedDocs = DemoUI.get().getUpdatedDocs();
			if (updatedDocs.containsKey(getFilePath())) {
				docsUpdated = updatedDocs.get(getFilePath()) + 1;
			} else {
				docsUpdated = 1;
			}
			DemoUI.get().getCreatedDocs().put(getFilePath(), docsUpdated);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
