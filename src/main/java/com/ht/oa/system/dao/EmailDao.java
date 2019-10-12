package com.ht.oa.system.dao;


import com.ht.oa.model.domain.system.Email;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface EmailDao extends JpaRepository<Email,String>, JpaSpecificationExecutor<Email> {
}
