package com.ht.oa.model.domain.system.response;

import com.ht.oa.model.domain.system.Permission;
import com.ht.oa.model.domain.system.Role;
import lombok.Getter;
import lombok.Setter;
import org.crazycake.shiro.AuthCachePrincipal;
import org.springframework.beans.BeanUtils;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RoleResult implements Serializable, AuthCachePrincipal {

    @Id
    private String id;

    private String name;

    private String description;

    private List<String> permsNames = new ArrayList<>();

    public RoleResult(Role role) {
        BeanUtils.copyProperties(role,this);
        for (Permission perm : role.getPermissions()) {
            this.permsNames.add(perm.getName());
        }
    }

    @Override
    public String getAuthCacheKey() {
        return null;
    }
}
