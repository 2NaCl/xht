package com.ht.oa.system.dao;

import com.ht.oa.model.domain.system.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao extends JpaSpecificationExecutor<Role>, JpaRepository<Role,String> {

    @Query(value = "select * from role where name = :name",nativeQuery = true)
    Role findByName(@Param("name") String name);

//    @Query(value = "select p.name from role r " +
//            "inner join user_role ur on (r.id=ur.role_id)" +
//            "inner join user u on (u.id=ur.user_id)" +
//            "inner join role_permission rp on (rp.role_id=r.id)" +
//            "inner join permission p on (p.id=rp.permission_id)" +
//            "where u.id = :id",nativeQuery = true)
//    List<String> findRoleNameByInner(@Param("id") String id);



}
