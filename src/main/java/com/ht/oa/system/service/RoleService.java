package com.ht.oa.system.service;


import com.ht.oa.common.utils.IdWorker;
import com.ht.oa.model.domain.system.Permission;
import com.ht.oa.model.domain.system.Role;
import com.ht.oa.model.domain.system.User;
import com.ht.oa.system.dao.PermissionDao;
import com.ht.oa.system.dao.RoleDao;
import com.ht.oa.system.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private PermissionDao permissionDao;

    /**
     * 添加角色
     */
    public void add(Role role){
        role.setId(String.valueOf(idWorker.nextId()+""));
        roleDao.save(role);
    }

    /**
     * 删除角色
     */
    public void deleteRole(String id) {
        roleDao.deleteById(id);
    }

    /**
     * 更新角色
     */
    public void updateRole(Role role) {
        Role role1 = roleDao.getOne(role.getId());
        role1.setName(role.getName());
        role1.setDescription(role.getDescription());
        roleDao.save(role1);
    }

    /**
     * 根据姓名查找角色
     */
    public Role findRoleById(String id) {
        return roleDao.findById(id).get();
    }

    /**
     * 展示出所有角色
     */
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    /**
     * 给角色赋予权限
     */
    public void assignPermissions(String roleId, List<String> permissions) {
        Role role = roleDao.findById(roleId).get();
        List<Permission> set = new ArrayList<>();
        for (String perm:permissions) {
            Permission byName = permissionDao.findByName(perm);
            set.add(byName);
        }
        role.setPermissions(set);
        roleDao.save(role);
    }

    /**
     * 给用户分配权限
     */
    public void assignRoles(String userId, List<String> roles) {
        User user = userDao.findById(userId).get();
        List<Role> set = new ArrayList<>();
        for (String role: roles) {
            Role byName = roleDao.findByName(role);
            set.add(byName);
        }
        user.setRoles(set);
        userDao.save(user);
    }

    /**
     * 查询角色
     */
//    public List<String> findRoleName(String id){
//        List<String> roleNameByInner = roleDao.findRoleNameByInner(id);
//        return roleNameByInner;
//    }

    /**
     * 通过roleName获取Role对象
     */
    public Role findByName(String name) {
        return roleDao.findByName(name);
    }




}
