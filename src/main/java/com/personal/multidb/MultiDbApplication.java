package com.personal.multidb;

import com.personal.multidb.model.ClientDBConnectionDetail;
import com.personal.multidb.repo.ClientDBConnectionDetailRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class MultiDbApplication implements CommandLineRunner {

    private final ClientDBConnectionDetailRepo clientDBConnectionDetailRepo;

    public static void main(String[] args) {
        SpringApplication.run(MultiDbApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        String dbUrl = "jdbc:postgresql://localhost:5432/";
//        ClientDBConnectionDetail connectionDetail =
//                ClientDBConnectionDetail.builder()
//                        .clientId(1)
//                        .dbUsername("postgres")
//                        .dbPassword("postgres")
//                        .dbDriver("org.postgresql.Driver")
//                        .dbUrl(dbUrl+"client1")
//                        .dbName("client1")
//                        .build();
//
//        ClientDBConnectionDetail connectionDetail2 =
//                ClientDBConnectionDetail.builder()
//                        .clientId(2)
//                        .dbUsername("postgres")
//                        .dbPassword("postgres")
//                        .dbDriver("org.postgresql.Driver")
//                        .dbUrl(dbUrl+"client2")
//                        .dbName("client2")
//                        .build();
//
//
//        clientDBConnectionDetailRepo.save(connectionDetail);
//        clientDBConnectionDetailRepo.save(connectionDetail2);

    }
}
