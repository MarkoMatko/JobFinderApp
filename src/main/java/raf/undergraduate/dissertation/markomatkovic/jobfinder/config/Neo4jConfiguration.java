package raf.undergraduate.dissertation.markomatkovic.jobfinder.config;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableNeo4jRepositories
@EnableTransactionManagement
public class Neo4jConfiguration {

    @Bean
    public SessionFactory sessionFactory() {
        return new SessionFactory(configuration(), "raf.undergraduate.dissertation.markomatkovic.jobfinder.entity" );
    }

    @Bean
    public org.neo4j.ogm.config.Configuration configuration() {
        org.neo4j.ogm.config.Configuration configuration = new org.neo4j.ogm.config.Configuration.Builder()
                .uri("bolt://localhost")
                .credentials("neo4j", "jobfinder")
                .build();
        return configuration;
    }
    @Bean
    public Neo4jTransactionManager transactionManager() {
        return new Neo4jTransactionManager(sessionFactory());
    }
}
