package com.ht.oa.system.dao;

import com.ht.oa.model.domain.system.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserDao extends JpaSpecificationExecutor<User>, JpaRepository<User,String> {

    @Query(value = "select id,name,city,age,sex,dept,company,level from user where id = #{#id}",nativeQuery = true)
    List<User> findAllById(@Param("id") String id);

    User findByName(String username);

}
