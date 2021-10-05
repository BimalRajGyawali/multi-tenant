package com.personal.multidb.adapter;

import com.personal.multidb.model.ClientDBConnectionDetail;
import com.personal.multidb.repo.ClientDBConnectionDetailRepo;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author bimal on 10/5/21
 * @project multi-db
 */
@Component
@RequiredArgsConstructor
public class MyBatisConfig {

    private final Map<Integer, SqlSessionFactory> sqlSessionFactoryMap = new HashMap<>();
    private final ClientDBConnectionDetailRepo clientDBConnectionDetailRepo;

    public SqlSessionFactory getSqlSessionFactory(int clientId) {
        if (sqlSessionFactoryMap.containsKey(clientId)) {
            return sqlSessionFactoryMap.get(clientId);
        }
        ClientDBConnectionDetail connectionDetail = clientDBConnectionDetailRepo
                .findByClientId(clientId)
                .orElseThrow(RuntimeException::new);

        SqlSessionFactory factory = null;

        try {
            Reader reader = Resources.getResourceAsReader("mybatis_config.xml");

            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

            Properties properties = new Properties();
            properties.put("driver", connectionDetail.getDbDriver());
            properties.put("url", connectionDetail.getDbUrl());
            properties.put("username", connectionDetail.getDbUsername());
            properties.put("password", connectionDetail.getDbPassword());

            factory = builder.build(reader, properties);

            sqlSessionFactoryMap.put(clientId, factory);


        } catch (IOException e) {
            e.printStackTrace();
        }


        return factory;
    }

}
