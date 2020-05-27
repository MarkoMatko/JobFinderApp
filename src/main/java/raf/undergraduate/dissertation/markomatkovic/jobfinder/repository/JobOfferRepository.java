package raf.undergraduate.dissertation.markomatkovic.jobfinder.repository;

import org.springframework.data.neo4j.annotation.Depth;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.ApplyRelationship;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.JobOffer;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.Worker;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface JobOfferRepository extends Neo4jRepository<JobOffer,Long> {

    /**
     * Custom query method for fetching all job offers.
     * @return Collection of all job offers
     */
    @Query("MATCH (j:JobOffer) RETURN j")
    Collection<JobOffer> findAll();

    @Query("MATCH (u:User {email:{0}})<-[:MY_CREDENTIALS]-(w:Worker) " +
            "MATCH (w)-[a:APPLIED]->(j:JobOffer) " +
            "WHERE id(j)={1} " +
            "RETURN w")
    Optional<Worker> applyCheck(String email, Integer id);

    @Query("MATCH (e:Employer)-[r:PUBLISH]->(j:JobOffer) WHERE id(e)={id} RETURN *")
    List<JobOffer> findAllPublishedBySpecificEmployer(Integer id);

    @Query("MATCH (u:User {email:{0}})<-[c:MY_CREDENTIALS]-(w:Worker) " +
            "MATCH (w)-[a:APPLIED]->(j:JobOffer) " +
            "MATCH (j)<-[p:PUBLISH]-(e:Employer)" +
            "RETURN *")
    List<JobOffer> findAllAplicationsForWorker(String email);

    @Query("MATCH (u:User {email:{1}})<-[c:MY_CREDENTIALS]-(w:Worker) " +
            "MATCH (w)-[a:APPLIED]->(j:JobOffer) WHERE id(j)={0}" +
            "DELETE a")
    void removeApplication(Integer id, String email);
}
