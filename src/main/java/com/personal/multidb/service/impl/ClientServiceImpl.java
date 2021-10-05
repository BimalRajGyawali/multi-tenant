package com.personal.multidb.service.impl;

import com.personal.multidb.adapter.DataSourceAdapter;
import com.personal.multidb.adapter.MyBatisConfig;
import com.personal.multidb.dto.AccountMapper;
import com.personal.multidb.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
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

    private final MyBatisConfig myBatisConfig;

    @Override
    public List<AccountMapper> getAccounts(int clientId) {

        SqlSessionFactory factory = myBatisConfig.getSqlSessionFactory(clientId);

        SqlSession session = factory.openSession();
        List<AccountMapper> accountMyBatis = session.selectList("getAccounts");

        session.close();
        return accountMyBatis;
    }
}
