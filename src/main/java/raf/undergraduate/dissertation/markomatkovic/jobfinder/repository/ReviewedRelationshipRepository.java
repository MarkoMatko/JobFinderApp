package raf.undergraduate.dissertation.markomatkovic.jobfinder.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.ReviewedRelationship;

import java.util.List;

@Repository
public interface ReviewedRelationshipRepository extends Neo4jRepository<ReviewedRelationship, Long> {

    @Query("MATCH (w:Worker)<-[r:REVIEWED]-(e:Employer) WHERE id(w)={0} RETURN *")
    List<ReviewedRelationship> getAllReviewsForWorkerId(Integer id);
}
