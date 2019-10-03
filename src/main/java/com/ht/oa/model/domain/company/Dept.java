package com.ht.oa.model.domain.company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "dept")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("unchecked")
public class Dept {

    @Id
    private String did;

    private String dname;

    private String dpnum;

    private String dmsg;

    private String ddate;

}
