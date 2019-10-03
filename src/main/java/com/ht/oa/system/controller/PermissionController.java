package com.ht.oa.system.controller;

import com.ht.oa.common.entity.Result;
import com.ht.oa.common.entity.ResultCode;
import com.ht.oa.common.utils.BeanMapUtils;
import com.ht.oa.common.utils.IdWorker;
import com.ht.oa.model.domain.system.Permission;
import com.ht.oa.system.service.PermissionService;
import com.ht.oa.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/perm")
public class PermissionController {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RoleService roleService;

    /**
     * 添加权限
     */
    @PostMapping("/add")
    public Result addPerm(@RequestBody Map map) throws Exception {
        permissionService.addPermission(map);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 删除权限
     */
    @DeleteMapping("/del/{id}")
    public Result deletePerm(@PathVariable("id") String id) {
        permissionService.deletePermission(id);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 查看所有权限
     */
    @GetMapping("/all")
    public Result findAll() {
        List<Permission> all = permissionService.findAll();
        return new Result(ResultCode.SUCCESS, all);
    }


    /**
     * 修改权限
     */
    @PutMapping("/update")
    public Result updatePermissions(@RequestBody Permission permission) throws Exception {
        Permission permission1 = permissionService.updatePermission(permission);
        return new Result(ResultCode.SUCCESS, permission1);
    }


}
