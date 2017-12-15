package com.sf.marathon.pentakill.server.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sf.marathon.pentakill.server.domain.Group;

public interface IGroupDao extends JpaRepository<Group, Long>{

}
