package com.ht.oa.model.domain.system.response;

import com.ht.oa.model.domain.system.Permission;
import com.ht.oa.model.domain.system.Role;
import com.ht.oa.model.domain.system.User;
import lombok.Getter;
import lombok.Setter;
import org.crazycake.shiro.AuthCachePrincipal;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;

@Getter
@Setter
public class ProfileResult implements AuthCachePrincipal, Serializable {


    private String name;
    private String city;
    private String level;
    private Map<String, Object> roles = new HashMap<>();


    public ProfileResult(User user, List<Permission> list) {
        this.city = user.getCity();
        this.level = user.getLevel();
        this.name = user.getName();
        Set<String> permissions = new HashSet<>();

        for (Permission perm : list) {
            String name = perm.getName();
            permissions.add(name);
        }
        this.roles.put("permissions", permissions);
    }

    public ProfileResult(User user) {
        this.city = user.getCity();
        this.level = user.getLevel();
        this.name = user.getName();
        Set<Role> roles = user.getRoles();
        Set<String> permissions = new HashSet<>();
        for (Role role : roles) {
            Set<Permission> perms = role.getPermissions();
            for (Permission permission : perms) {
                String name = permission.getName();
                permissions.add(name);
            }
        }
        this.roles.put("permissions", permissions);
    }



    @Override
    public String getAuthCacheKey() {
        return null;
    }
}
