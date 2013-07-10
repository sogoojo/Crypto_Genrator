package org.etz.core;

public class NVPair {

	private String key;
	private String value;

	public NVPair(String key, String value)
	{
		this.key = key;
		this.value = value;
	}
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return value;
	}

	public boolean equals(Object obj) {
		if (obj instanceof NVPair) {
			return ((NVPair) obj).getKey().equalsIgnoreCase(this.key);
		}
		return false;
	}

}
