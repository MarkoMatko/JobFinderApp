package raf.undergraduate.dissertation.markomatkovic.jobfinder.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.ApplyRelationship;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.Worker;

import java.util.List;

public interface ApplyRelationshipRepository extends Neo4jRepository<ApplyRelationship, Long> {

    @Query("MATCH (w:Worker)-[c:MY_CREDENTIALS]->(u:User)" +
            "MATCH (w)-[a:APPLIED]->(j:JobOffer) WHERE id(j)={id} RETURN *")
    List<ApplyRelationship> findAllAppliersForSpecificJobOffer(Integer id);
}
