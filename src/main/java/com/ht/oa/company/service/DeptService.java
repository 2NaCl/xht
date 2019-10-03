package com.ht.oa.company.service;


import com.ht.oa.common.utils.IdWorker;
import com.ht.oa.company.dao.DeptDao;
import com.ht.oa.model.domain.company.Dept;
import com.ht.oa.model.domain.system.User;
import com.ht.oa.system.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class DeptService {

    @Autowired
    private DeptDao deptDao;

    @Autowired
    private UserDao userDao;
    /**
     * 列举出部门信息总览
     */
    public List<Dept> findAll() {
        return deptDao.findAll();
    }

    /**
     * 列举出自己部门的同僚
     */
    public List<User> findById(String id) {
        return userDao.findAllById(id);
    }

    /**
     * 添加部门
     * @return
     */
    public void add(Map map) {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY:MM:DD hh:mm:ss");
        String format = sdf.format(new Date());
        Dept dept = new Dept();
        dept.setDid(String.valueOf(new IdWorker().nextId()));
        dept.setDdate(format);
        dept.setDmsg(String.valueOf(map.get("dmsg")));
        dept.setDname(String.valueOf(map.get("dname")));
        dept.setDpnum(String.valueOf(map.get("dpnum")));
        deptDao.save(dept);
    }

    /**
     * 删除部门
     */
    public void deleteDept(String id){
        deptDao.deleteById(id);
    }

}
