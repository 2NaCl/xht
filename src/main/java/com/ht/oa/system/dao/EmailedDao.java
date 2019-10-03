package com.ht.oa.system.dao;

import com.ht.oa.model.domain.system.Emailed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


public interface EmailedDao extends JpaRepository<Emailed,String>, JpaSpecificationExecutor<Emailed> {
}
