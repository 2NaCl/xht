package com.ht.oa.system.dao;

import com.ht.oa.model.domain.system.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface PermissionDao extends JpaSpecificationExecutor<Permission>, JpaRepository<Permission,String> {

    Permission findByName(String name);

}
