package com.personal.multidb.adapter;

import com.personal.multidb.model.ClientDBConnectionDetail;
import com.personal.multidb.repo.ClientDBConnectionDetailRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author bimal on 10/4/21
 * @project multi-db
 */
@Component
@RequiredArgsConstructor
public class DataSourceAdapter {

    private final Map<Integer, DataSource> dataSourceMap = new HashMap<>();
    Map<Integer, EntityManagerFactory> entityManagerFactoryMap = new HashMap<>();

    private final ClientDBConnectionDetailRepo clientDBConnectionDetailRepo;

    public DataSource getDataSourceOfClient(int clientId) {
        if (dataSourceMap.containsKey(clientId)) {
            return dataSourceMap.get(clientId);
        }
        ClientDBConnectionDetail connectionDetail = clientDBConnectionDetailRepo
                .findByClientId(clientId)
                .orElseThrow(RuntimeException::new);

        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.username(connectionDetail.getDbUsername());
        dataSourceBuilder.password(connectionDetail.getDbPassword());
        dataSourceBuilder.url(connectionDetail.getDbUrl());

        DataSource dataSource = dataSourceBuilder.build();
        dataSourceMap.put(clientId, dataSource);

        return dataSource;
    }

    public EntityManagerFactory getEntityManager(int clientId) {

        if(entityManagerFactoryMap.containsKey(clientId)){
            return entityManagerFactoryMap.get(clientId);
        }
        Properties jpaProperties = new Properties();

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setJpaProperties(jpaProperties);
        factory.setPackagesToScan("com.personal.multidb");
        factory.setDataSource(getDataSourceOfClient(clientId));
        factory.afterPropertiesSet();

        EntityManagerFactory entityManagerFactory = factory.getObject();
        entityManagerFactoryMap.put(clientId, entityManagerFactory);

        return entityManagerFactory;

    }
}
