package com.sf.marathon.pentakill.server.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sf.marathon.pentakill.server.domain.Test;

public interface ITestDao extends JpaRepository<Test, Long> {

}
