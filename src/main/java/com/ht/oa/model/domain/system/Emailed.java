package com.ht.oa.model.domain.system;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "emailed")
@AllArgsConstructor
@NoArgsConstructor
public class Emailed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String dname;

    private String dept;

    private String msg;

    private String ddate;

    private String condition;

}
