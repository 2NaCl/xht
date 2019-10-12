package com.ht.oa.system.controller;

import com.ht.oa.common.entity.Result;
import com.ht.oa.common.entity.ResultCode;
import com.ht.oa.common.utils.IdWorker;
import com.ht.oa.model.domain.system.Permission;
import com.ht.oa.model.domain.system.Role;
import com.ht.oa.model.domain.system.response.RoleResult;
import com.ht.oa.system.dao.RoleDao;
import com.ht.oa.system.service.PermissionService;
import com.ht.oa.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private RoleService roleService;


    @Autowired
    private PermissionService permissionService;

    /**
     * 添加角色
     */
    @PostMapping("/add")
    public Result addRole(@RequestBody Role role) {
        role.setId(idWorker.nextId()+"");
        roleService.add(role);
        return new Result(ResultCode.SUCCESS);
    }


    /**
     * 删除角色
     */
    @DeleteMapping("/del/{id}")
    public Result deleteByRole(@PathVariable("id") String id) {
        roleService.deleteRole(id);
        return new Result(ResultCode.SUCCESS);
    }


    /**
     * 更新角色
     */
    @PutMapping("/update")
    public Result update(@RequestBody Role role) {
        roleService.updateRole(role);
        return new Result(ResultCode.SUCCESS);
    }


    /**
     * 根据姓名查找角色
     */
    @GetMapping("/{id}")
    public Result findByName(@PathVariable("id") String id) {
        Role roleById = roleService.findRoleById(id);
        return new Result(ResultCode.SUCCESS, roleById);
    }


    /**
     * 展示出所有角色
     */
    @GetMapping("/all")
    public Result findAll() {
        List<Role> all = roleService.findAll();
        return new Result(ResultCode.SUCCESS, all);
    }


    /**
     * 给角色赋予权限
     */
    @PostMapping("/{roleId}/assignPerm")
    public Result assignRoles(@PathVariable("roleId") String roleId,@RequestBody Map<String,String> perms) {
        List<String> permList = new ArrayList<>(perms.values());
        roleService.assignPermissions(roleId, permList);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 给用户分配角色
     */
    @PostMapping("/{userId}/assignRole")
    public Result assignUsers(@PathVariable("userId") String userId, @RequestBody Map<String, String> roles) {
        List<String> roleList = new ArrayList<>(roles.values());
        roleService.assignRoles(userId, roleList);
        return new Result(ResultCode.SUCCESS);

    }

//    @GetMapping("/di")
//    public Result f(String id) {
//        List<String> roleName = roleService.findRoleName(id);
//        return new Result(ResultCode.SUCCESS, roleName);
//    }

    /**
     * 根据roleId查询权限
     */
    @GetMapping("/perm/{id}")
    public Result findPermsByRoleId(@PathVariable("id") String roleId) {
        Role roleById = roleService.findRoleById(roleId);
        RoleResult roleResult = new RoleResult(roleById);
        return new Result(ResultCode.SUCCESS, roleResult);
    }

}
