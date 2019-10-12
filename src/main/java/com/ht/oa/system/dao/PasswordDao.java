package com.ht.oa.system.dao;

import com.ht.oa.model.domain.system.Password;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

public interface PasswordDao extends JpaSpecificationExecutor<Password>, JpaRepository<Password,String> {
}
