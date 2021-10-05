package com.personal.multidb.service.impl;

import com.personal.multidb.adapter.DataSourceAdapter;
import com.personal.multidb.adapter.MyBatisConfig;
import com.personal.multidb.dto.Account;
import com.personal.multidb.dto.AccountMyBatis;
import com.personal.multidb.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bimal on 10/4/21
 * @project multi-db
 */
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final DataSourceAdapter dataSourceAdapter;
    private final MyBatisConfig myBatisConfig;

    @Override
    public List<Account> getAccounts(int clientId) {

        SqlSessionFactory factory = myBatisConfig.getSqlSessionFactory(clientId);

        factory.openSession().selectList("select * from account", AccountMyBatis.class);
        return List.of();
    }
}
