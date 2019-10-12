package com.ht.oa.system.shiro.realm;

import com.ht.oa.model.domain.system.Permission;
import com.ht.oa.model.domain.system.Role;
import com.ht.oa.model.domain.system.User;
import com.ht.oa.model.domain.system.response.ProfileResult;
import com.ht.oa.system.dao.PasswordDao;
import com.ht.oa.system.dao.UserDao;
import com.ht.oa.system.service.PasswordService;
import com.ht.oa.system.service.PermissionService;
import com.ht.oa.system.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.*;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //获取到安全数据
        ProfileResult result = (ProfileResult) principalCollection.getPrimaryPrincipal();
        //获取角色信息
        Set<String> roles = (Set<String>)result.getRoles().get("roles");
        //获取权限信息
//        Set<String> perms = (Set<String>) result.getPermissions().get("permissions");

        //构造权限数据
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roles);
//        info.setStringPermissions(perms);

//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        User user = (User) principalCollection.getPrimaryPrincipal();
//        for (Role role : user.getRoles()) {
//            info.addRoles(Collections.singleton(role.getName()));
//            for (Permission permission : role.getPermissions()) {
//                info.addStringPermissions(Collections.singleton(permission.getName()));
//            }
//        }
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取到用户的名字和密码
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username = usernamePasswordToken.getUsername();
        String password = new String(usernamePasswordToken.getPassword());
        System.out.println(password);
        //根据名字查询用户
        User user = userService.findByName(username);
        //根据用户对象获取到密码
        String byId = passwordService.findById(user.getId());
        //判断用户是否存在
        if (user != null && password.equals(byId)) {
            //构造安全数据并且返回
            ProfileResult result = new ProfileResult(user);
            return new SimpleAuthenticationInfo(result, byId, getName());//这里的东西将会被存储到SessionId里
        }
        //用户名和密码不匹配
        return null;
    }
}
