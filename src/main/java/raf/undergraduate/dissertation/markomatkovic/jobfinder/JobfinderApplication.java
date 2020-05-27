package raf.undergraduate.dissertation.markomatkovic.jobfinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories
public class JobfinderApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobfinderApplication.class, args);
	}

}
