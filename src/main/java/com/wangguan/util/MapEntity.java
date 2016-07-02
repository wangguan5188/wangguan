package com.wangguan.util;

public class MapEntity implements java.io.Serializable {

	private static final long serialVersionUID = -6112107339899260046L;

	private String key;
	private Object value;

	public MapEntity() {
		super();
	}

	public MapEntity(String key, Object value) {
		super();
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "MapEntity [key=" + key + ", value=" + value + "]";
	}

}
