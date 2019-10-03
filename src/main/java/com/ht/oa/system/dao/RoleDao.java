package com.ht.oa.system.dao;

import com.ht.oa.model.domain.system.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

public interface RoleDao extends JpaSpecificationExecutor<Role>, JpaRepository<Role,String> {

    Role findByName(String roleName);

}
