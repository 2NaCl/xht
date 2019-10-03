package com.ht.oa.model.domain.system;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "email")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email {

    @Id
    private String dname;

    private String dept;

    private String msg;

    private String ddate;

}
