package com.ht.oa.model.domain.system;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.lang.annotation.DeclareAnnotation;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "role")
@Table(name = "role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {

    @Id
    private String id;

    private String name;

    private String description;

//    @JsonIgnore
    @ManyToMany
    private List<User> users = new ArrayList<>();

//    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "role_permission",
    joinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "permission_id",referencedColumnName = "id")})
    private List<Permission> permissions = new ArrayList<>();


}
