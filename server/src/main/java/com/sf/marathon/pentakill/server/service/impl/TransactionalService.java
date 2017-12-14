package com.sf.marathon.pentakill.server.service.impl;

import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Throwable.class) // 默认只对RunTimeException回滚
public class TransactionalService {

}
