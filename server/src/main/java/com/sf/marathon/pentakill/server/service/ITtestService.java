package com.sf.marathon.pentakill.server.service;

import com.sf.marathon.pentakill.server.domain.Test;

public interface ITtestService {

	public Test getById(Long id);

	public Test save(Test test);
}
