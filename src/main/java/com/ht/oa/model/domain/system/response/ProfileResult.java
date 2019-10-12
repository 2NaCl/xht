package com.ht.oa.model.domain.system.response;

import com.ht.oa.model.domain.system.Permission;
import com.ht.oa.model.domain.system.Role;
import com.ht.oa.model.domain.system.User;
import com.ht.oa.system.service.RoleService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.crazycake.shiro.AuthCachePrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.*;

@Getter
@Slf4j
@Setter
public class ProfileResult implements AuthCachePrincipal, Serializable {

    @Autowired
    private RoleService roleService;

    private String name;
    private String city;
    private String level;
    private String id;
    private Map<String, Object> roles = new HashMap<>();
    private Map<String, Object> permissions = new HashMap<>();


    public ProfileResult(User user)  {

        this.id = user.getId();
        this.city = user.getCity();
        this.level = user.getLevel();
        this.name = user.getName();

        Set<String> permissions = new HashSet<>();

        //获取角色
        UserResult userResult = new UserResult(user);
        List<String> roleNames = userResult.getRoleNames();
        Set<String> roles = new HashSet<>();
        for (String roleName : roleNames) {
            roles.add(roleName);
        }
        this.roles.put("roles", roles);
//        //获取角色对应的权限
//        for (String roleName : roleNames) {
//            System.out.println(roleName);
//                //TODO 有bug
//            Role byName = roleService.findByName(roleName);
//            System.out.println(byName.getName());
//
//            RoleResult roleResult = new RoleResult(byName);
//            String name = roleResult.getName();
//
//            permissions.add(name);
//        }
//        this.permissions.put("permissions", permissions);
    }

//    public ProfileResult(User user) {
//        this.city = user.getCity();
//        this.level = user.getLevel();
//        this.name = user.getName();
//        Set<Role> roles = user.getRoles();
//        Set<String> permissions = new HashSet<>();
//        for (Role role : roles) {
//            Set<Permission> perms = role.getPermissions();
//            for (Permission permission : perms) {
//                String name = permission.getName();
//                permissions.add(name);
//            }
//        }
//        this.permissions.put("permissions", permissions);
//    }



    @Override
    public String getAuthCacheKey() {
        return null;
    }
}
