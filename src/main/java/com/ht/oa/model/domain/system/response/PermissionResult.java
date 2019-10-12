package com.ht.oa.model.domain.system.response;

import com.ht.oa.model.domain.system.Permission;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

@Getter
@Setter
public class PermissionResult implements Serializable {

    private String id;

    private String name;

    private String type;

    private String code;

    public PermissionResult(Permission permission) {
        BeanUtils.copyProperties(permission,this);
    }

}
