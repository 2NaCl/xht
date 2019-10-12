package com.ht.oa.system.dao;

import com.ht.oa.model.domain.system.Role;
import com.ht.oa.model.domain.system.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

public interface UserDao extends JpaSpecificationExecutor<User>, JpaRepository<User,String> {

    @Query(value = "select id,name,city,age,sex,dept,company,level from user where id = :id",nativeQuery = true)
    List<User> findAllById(@Param("id") String id);

    User findByName(String username);

    @Query(value = "select * from user", nativeQuery = true)
    List<User> findAllUser();


}
