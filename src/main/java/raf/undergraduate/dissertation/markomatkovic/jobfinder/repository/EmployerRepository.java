package raf.undergraduate.dissertation.markomatkovic.jobfinder.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.Employer;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.Worker;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployerRepository extends Neo4jRepository<Employer,Long> {

    @Query("MATCH (u:User {email:{0}})<-[c:MY_CREDENTIALS]-(e:Employer) RETURN *")
    Optional<Employer> findByEmail(String employerEmail);

    @Query("MATCH (e:Employer)-[c:MY_CREDENTIALS]->(u:User) WHERE e.name =~ {0} RETURN *")
    List<Employer> searchForEmployers(String search);

    @Query("MATCH (u:User {email:{0}})<-[:MY_CREDENTIALS]-(w:Worker) " +
            "MATCH (w)-[f:FOLLOW]->(e:Employer) " +
            "WHERE id(e)={1} " +
            "RETURN e")
    Optional<Employer> followCheck(String email, Integer id);

    @Query("MATCH (u:User {email:{0}})<-[:MY_CREDENTIALS]-(e:Employer) " +
            "MATCH (w)-[f:FOLLOW]->(e:Employer) " +
            "RETURN w")
    List<Worker> getAllFollowers(String employerEmail);
}
