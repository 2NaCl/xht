package com.ht.oa.model.domain.system;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "password")
@Entity
public class Password {

    @Id
    private String id;

    private String pwd;

}
