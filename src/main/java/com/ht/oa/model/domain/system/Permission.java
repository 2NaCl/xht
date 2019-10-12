package com.ht.oa.model.domain.system;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Entity(name = "permission")
@Table(name = "permission")
@AllArgsConstructor
@NoArgsConstructor
@Data
@DynamicInsert(true)
@DynamicUpdate(true)
public class Permission implements Serializable {

    @Id
    private String id;

    /**
     * 权限名
     */
    private String name;

    private String type;

    private String code;

    private String description;

    public Permission(String name, String type, String code, String description) {
        this.name = name;
        this.type = type;
        this.code = code;
        this.description = description;
    }

}
