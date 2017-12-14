package com.sf.marathon.pentakill.server.controller.dto.req;

import java.io.Serializable;

public class TestReq implements Serializable {

	private static final long serialVersionUID = 4640385112600759930L;

	private String name;

	private String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
