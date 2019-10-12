package com.ht.oa.system.service;

import com.ht.oa.common.utils.IdWorker;
import com.ht.oa.company.dao.DeptDao;
import com.ht.oa.model.domain.system.Password;
import com.ht.oa.model.domain.system.Permission;
import com.ht.oa.model.domain.system.Role;
import com.ht.oa.model.domain.system.User;
import com.ht.oa.system.dao.PasswordDao;
import com.ht.oa.system.dao.PermissionDao;
import com.ht.oa.system.dao.RoleDao;
import com.ht.oa.system.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private PasswordDao passwordDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private DeptDao deptDao;

    /**
     * 添加员工
     */
    public void add(Map map) {
        User user = new User();
        user.setAge(String.valueOf(map.get("age")));
        user.setCity(String.valueOf(map.get("city")));
        user.setCompany("TGU");
        user.setId(String.valueOf(idWorker.nextId()));
        user.setLevel("3");
        user.setDept(String.valueOf(map.get("dept")));
        user.setDeptid(deptDao.findDidByDname(String.valueOf(map.get("dept"))));
        user.setSex(String.valueOf(map.get("sex")));
        user.setName(String.valueOf(map.get("name")));
        userDao.save(user);
    }

    /**
     * 删除员工
     */
    public void delete(String id) {
        userDao.deleteById(id);
    }

    /**
     * 更改员工部门
     */

    public void updateDeptById(User u) {
        User user = userDao.findById(u.getId()).get();
        user.setDept(u.getDept());
        userDao.save(user);

    }

    /**
     * 查看员工详细信息
     */

    public User findById(String id) {
        return userDao.findById(id).get();
    }

    /**
     * 查看全部部门员工
     */
    public List<User> findAllUser() {
        return userDao.findAllUser();
    }

    /**
     * 根据username查询到员工对象
     */
    public User findByName(String username) {
        User byName = userDao.findByName(username);
        return byName;
    }

    /**
     * 注册
     */
    public void register(Map map) {
        User user = new User();
        Password password = new Password();
        String id = idWorker.nextId() + "";
        user.setId(id);
        password.setId(id);
        password.setPwd(String.valueOf(map.get("password")));
        user.setDept((String) map.get("dept"));
        user.setDeptid(deptDao.findDidByDname(String.valueOf(map.get("dept"))));
        user.setName((String) map.get("name"));
        user.setSex((String) map.get("sex"));
        user.setLevel("1");
        user.setAge((String) map.get("age"));
        user.setCompany("TGU");
        user.setCity((String) map.get("city"));
        userDao.save(user);
        passwordDao.save(password);
    }

    /**
     * 通过用户name查询其对应的权限
     */
    public List<Permission> findPermissionsByName(String id) {

        System.out.println(id);
        User byName = userDao.findById(id).get();
        List<Role> roles = byName.getRoles();
        Set<Permission> permSet = new HashSet<>();
        if (roles!=null) {
            for (Role role : roles) {
                permSet.addAll(role.getPermissions());
            }
            System.out.println(permSet);
            List<Permission> permissionList = new ArrayList<>(permSet);
            return permissionList;
        }else {
            return null;
        }

    }


}
