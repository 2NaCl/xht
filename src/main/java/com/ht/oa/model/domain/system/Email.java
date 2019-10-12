package com.ht.oa.model.domain.system;


import lombok.*;
import org.crazycake.shiro.AuthCachePrincipal;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "email")
@Table(name = "email")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email implements Serializable, AuthCachePrincipal {

    @Id
    private String dname;

    private String dept;

    private String msg;

    private String ddate;

    @Override
    public String getAuthCacheKey() {
        return null;
    }
}
