package org.openntf.domino.demoApp.application;

import java.io.Serializable;

public class ContactSummary implements Serializable, Comparable {
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String email;
	private String city;
	private String metaversalId;
	private String state;
	private String dbName;

	public ContactSummary() {

	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getMetaversalId() {
		return metaversalId;
	}

	public void setMetaversalId(String metaversalId) {
		this.metaversalId = metaversalId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	@Override
	public int compareTo(Object other) {
		if (this.equals(other)) {
			return 0;
		} else if (this.hashCode() < other.hashCode()) {
			return -1;
		} else if (this.hashCode() > other.hashCode()) {
			return 1;
		}
		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((dbName == null) ? 0 : dbName.hashCode());
		result = prime * result + ((metaversalId == null) ? 0 : metaversalId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ContactSummary other = (ContactSummary) obj;
		if (firstName == null) {
			if (other.firstName != null) {
				return false;
			}
		} else if (!firstName.equals(other.firstName)) {
			return false;
		}
		if (lastName == null) {
			if (other.lastName != null) {
				return false;
			}
		} else if (!lastName.equals(other.lastName)) {
			return false;
		}
		if (city == null) {
			if (other.city != null) {
				return false;
			}
		} else if (!city.equals(other.city)) {
			return false;
		}
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (state == null) {
			if (other.state != null) {
				return false;
			}
		} else if (!state.equals(other.state)) {
			return false;
		}
		if (dbName == null) {
			if (other.dbName != null) {
				return false;
			}
		} else if (!dbName.equals(other.dbName)) {
			return false;
		}
		if (metaversalId == null) {
			if (other.metaversalId != null) {
				return false;
			}
		} else if (!metaversalId.equals(other.metaversalId)) {
			return false;
		}
		return true;
	}
}