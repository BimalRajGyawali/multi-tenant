package com.personal.multidb.repo;

import com.personal.multidb.model.ClientDBConnectionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author bimal on 10/4/21
 * @project multi-db
 */
@Repository
public interface ClientDBConnectionDetailRepo extends JpaRepository<ClientDBConnectionDetail, Integer> {
    Optional<ClientDBConnectionDetail> findByClientId(int clientId);
}
