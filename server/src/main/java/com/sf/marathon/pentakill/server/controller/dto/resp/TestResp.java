package com.sf.marathon.pentakill.server.controller.dto.resp;

import java.io.Serializable;

public class TestResp implements Serializable {

	private static final long serialVersionUID = 2680475764858336342L;
	private Long id;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
