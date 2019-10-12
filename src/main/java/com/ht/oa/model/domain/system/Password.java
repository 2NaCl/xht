package com.ht.oa.model.domain.system;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "password")
@Entity(name = "password")
public class Password implements Serializable {

    @Id
    private String id;

    private String pwd;

}
