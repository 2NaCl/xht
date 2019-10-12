package com.ht.oa.system.controller;


import com.ht.oa.common.entity.Result;
import com.ht.oa.common.entity.ResultCode;
import com.ht.oa.common.utils.IdWorker;
import com.ht.oa.model.domain.system.Permission;
import com.ht.oa.model.domain.system.User;
import com.ht.oa.model.domain.system.response.ProfileResult;
import com.ht.oa.model.domain.system.response.UserResult;
import com.ht.oa.system.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.soap.Addressing;
import java.awt.color.ProfileDataException;
import java.util.List;
import java.util.Map;
import java.util.Stack;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 添加员工
     */
    @RequiresPermissions("boss")
    @PutMapping("/add")
    public Result add(@RequestBody Map map) {
        userService.add(map);

        return new Result(ResultCode.SUCCESS);
    }


    /**
     * 删除员工
     */
    @DeleteMapping("/del/{id}")
    public Result del(@PathVariable("id") String id) {
        userService.delete(id);
        return new Result(ResultCode.SUCCESS);
    }


    /**
     * 更改员工部门
     */
    @PutMapping("/update")
    public Result update(@RequestBody  User user) {
        userService.updateDeptById(user);
        return new Result(ResultCode.SUCCESS);
    }


    /**
     * 查看员工详细信息
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable("id") String id) {
        User byId = userService.findById(id);
        return new Result(ResultCode.SUCCESS,byId);
    }


    /**
     * 查看全部部门员工
     */
    @RequiresRoles("admin   ")
    @GetMapping("/all")
    public Result findAll() {
        List<User> all = userService.findAllUser();
        return new Result(ResultCode.SUCCESS, all);
    }

    /**
     * 注册
     */
    @PostMapping("/register")
    public Result register(@RequestBody Map<String,Object> map) {
        if (userService.findByName((String) map.get("name")) != null) {
            userService.register(map);
            return new Result(ResultCode.SUCCESS);
        } else {
            return new Result(ResultCode.UNAUTHORISE);
        }
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public Result login(@RequestBody Map<String,Object> map) {

        String name = String.valueOf(map.get("name"));
        String password = String.valueOf(map.get("password"));
        try {
            //1.构造登录令牌
            //加密密码
//            password = new Md5Hash(password,name,3).toString();
            UsernamePasswordToken token = new UsernamePasswordToken(name, password);
            //获取subject
            Subject subject = SecurityUtils.getSubject();
            //调用login方法完成认证
            subject.login(token);
            //获取SessionId
            String sessionId = (String) subject.getSession().getId();
            return new Result(ResultCode.SUCCESS,sessionId);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.MOBILEORPASSWORDERROR);
        }
    }

    /**
     * 登录授权
     */
    @PostMapping("/profile")
    public Result profile() {
        //获取Session中的安全数据
        Subject subject = SecurityUtils.getSubject();
        //获取所有的安全数据集合
        PrincipalCollection principalCollection = subject.getPrincipals();
        //获取安全数据
        ProfileResult result = (ProfileResult) principalCollection.getPrimaryPrincipal();
        return new Result(ResultCode.SUCCESS, result);
    }
    /**
     * test
     */
    @GetMapping("/t")
    public Result test(String id) {
        List<Permission> permissionsByName = userService.findPermissionsByName(id);
        return new Result(ResultCode.SUCCESS, permissionsByName);
    }

    /**
     * 根据id查询角色
     */
    @GetMapping("/role/{id}")
    public Result findRoleByUserId(@PathVariable("id") String id) {
        User byId = userService.findById(id);
        UserResult userResult = new UserResult(byId);
        return new Result(ResultCode.SUCCESS, userResult);
    }
}
