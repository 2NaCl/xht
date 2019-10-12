package com.ht.oa.model.domain.system.response;

import com.ht.oa.model.domain.system.Role;
import com.ht.oa.model.domain.system.User;
import lombok.Getter;
import lombok.Setter;
import org.crazycake.shiro.AuthCachePrincipal;
import org.springframework.beans.BeanUtils;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class UserResult implements Serializable, AuthCachePrincipal {

    @Id
    private String id;

    private String name;

    private String city;

    private String age;

    private String sex;

    private String dept;

    private String company;

    private String level;

    private List<String> roleNames = new ArrayList<>();

    public UserResult(User user) {
        BeanUtils.copyProperties(user,this);
        for (Role role : user.getRoles()) {
            this.roleNames.add(role.getName());
        }
    }

    @Override
    public String getAuthCacheKey() {
        return null;
    }
}
