package com.ht.oa.company.dao;

import com.ht.oa.model.domain.company.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DeptDao extends JpaRepository<Dept,String>, JpaSpecificationExecutor<String> {

    @Query(value = "select did from dept where dname=:dname",nativeQuery = true)
    String findDidByDname(@Param("dname") String dname);

}
