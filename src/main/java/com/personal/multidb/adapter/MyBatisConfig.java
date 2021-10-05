package com.personal.multidb.adapter;

import com.personal.multidb.dto.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author bimal on 10/5/21
 * @project multi-db
 */
@Component
@RequiredArgsConstructor
public class MyBatisConfig {

    private final Map<Integer, SqlSessionFactory> sqlSessionFactoryMap = new HashMap<>();
    private final DataSourceAdapter dataSourceAdapter;

    public SqlSessionFactory getSqlSessionFactory(int clientId){
        if(sqlSessionFactoryMap.containsKey(clientId)){
            return sqlSessionFactoryMap.get(clientId);
        }

        DataSource dataSource = dataSourceAdapter.getDataSourceOfClient(clientId);
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment devEnv = new Environment("dev", transactionFactory, dataSource);

        Configuration configuration = new Configuration(devEnv);

        //Add MyBatis Mapper Classes here
        configuration.addMapper(AccountMapper.class);

        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = builder.build(configuration);

        sqlSessionFactoryMap.put(clientId, sqlSessionFactory);

        return sqlSessionFactory;
    }

}
