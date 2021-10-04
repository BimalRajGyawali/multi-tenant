package com.personal.multidb.service.impl;

import com.personal.multidb.adapter.DataSourceAdapter;
import com.personal.multidb.dto.Account;
import com.personal.multidb.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

/**
 * @author bimal on 10/4/21
 * @project multi-db
 */
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final DataSourceAdapter dataSourceAdapter;

    @Override
    public List<Account> getAccounts(int clientId) {
        EntityManagerFactory emf = dataSourceAdapter.getEntityManager(clientId);

        EntityManager entityManager = emf.createEntityManager();

        Query query = entityManager.createNativeQuery("select * from accounts", Account.class);
        List<Account> accounts = query.getResultList();

        entityManager.close();

        return accounts;
    }
}
