package com.personal.multidb.model;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

/**
 * @author bimal on 10/4/21
 * @project multi-db
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "client_db_conn_detail")
public class ClientDBConnectionDetail {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "client_id", nullable = false, unique = true)
    private int clientId;

    @Column(name = "db_username", nullable = false)
    private String dbUsername;

    @Column(name = "db_password", nullable = false)
    private String dbPassword;

    @Column(name = "db_driver", nullable = false)
    private String dbDriver;

    @Column(name = "db_url", nullable = false)
    private String dbUrl;

    @Column(name = "db_name", nullable = false)
    private String dbName;

}
