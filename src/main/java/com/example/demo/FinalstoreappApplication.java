package com.example.demo;

import javax.activation.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.transaction.PlatformTransactionManager;

@SpringBootApplication
public class FinalstoreappApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinalstoreappApplication.class, args);
	}
}


//@Configuration
//@EnableJdbcHttpSession
//class JdbcHttpSessionConfig {
//
//    @Bean
//    public DataSource dataSource() {
//    	
//        return (DataSource) new EmbeddedDatabaseBuilder()
//                .setType(EmbeddedDatabaseType.H2)
//                .addScript("org/springframework/session/jdbc/schema-h2.sql")
//                .build();
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager(javax.sql.DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//}
