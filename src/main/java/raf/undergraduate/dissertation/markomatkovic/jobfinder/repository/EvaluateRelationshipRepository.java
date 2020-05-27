package raf.undergraduate.dissertation.markomatkovic.jobfinder.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.EvaluateRelationship;

import java.util.List;

@Repository
public interface EvaluateRelationshipRepository extends Neo4jRepository<EvaluateRelationship, Long> {

    @Query("MATCH (e:Employer)<-[r:EVALUATE]-(w:Worker) WHERE id(e)={0} RETURN *")
    List<EvaluateRelationship> getAllEvaluationsForEmployerId(Integer id);
}
