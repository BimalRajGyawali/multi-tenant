package com.personal.multidb.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author bimal on 10/4/21
 * @project multi-db
 */

/* Mappers for JPA */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Account {
    @Id
    private int id;

    @Column(name = "accnumber")
    private String accountNumber;
}
