package raf.undergraduate.dissertation.markomatkovic.jobfinder.repository;

import org.springframework.data.neo4j.annotation.Depth;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.Employer;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.JobOffer;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.ReviewedRelationship;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.Worker;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkerRepository extends Neo4jRepository<Worker,Long> {

    @Query("MATCH (u:User {email:{0}})<-[c:MY_CREDENTIALS]-(w:Worker) RETURN *")
    Optional<Worker> findByEmail(String workerEmail);

    @Query("MATCH (w:Worker)-[c:MY_CREDENTIALS]->(u:User) WHERE w.name =~ {0} OR w.surname =~ {0} RETURN *")
    List<Worker> searchForWorkers(String search);

    @Query("MATCH (u:User {email:{0}})<-[:MY_CREDENTIALS]-(w1:Worker) " +
            "MATCH (w1)-[f:FRIEND_WITH]->(w2:Worker) " +
            "WHERE id(w2)={1} " +
            "RETURN w1")
    Optional<Worker> friendsCheck(String firstWorkerEmail, int secondWorkerId);

    @Query("MATCH (u:User {email:{0}})<-[c:MY_CREDENTIALS]-(w1:Worker)" +
            "MATCH (w1)-[f:FRIEND_WITH]->(w2:Worker)" +
            "RETURN w2")
    List<Worker> findFriendsForSpecificWorker(String email);

    @Query("MATCH (u:User {email:{0}})<-[c:MY_CREDENTIALS]-(w:Worker)" +
            "MATCH (w)-[f:FOLLOW]->(e:Employer)" +
            "RETURN e")
    List<Employer> findFollowedEmployersForSpecificWorker(String email);

    @Query("MATCH (u:User {email:{0}})<-[c:MY_CREDENTIALS]-(w:Worker)" +
            "MATCH (w)-[f:FOLLOW]->(e:Employer) WHERE id(e)={1}" +
            "DELETE f")
    void unfollowEmployer(String workerEmail, int employerId);

    @Query("MATCH (u:User {email:{0}})<-[c:MY_CREDENTIALS]-(w1:Worker)" +
            "MATCH (w1)-[f:FRIEND_WITH]->(w2:Worker) WHERE id(w2)={1}" +
            "DELETE f")
    void unfriend(String firstWorkerEmail, int secondWorkerId);

    @Query("MATCH (u:User {email:{0}})<-[c:MY_CREDENTIALS]-(w:Worker)" +
            "MATCH (w)-[s:SEEN]->(j:JobOffer) " +
            "MATCH (j)<-[p:PUBLISH]-(e:Employer) " +
            "WHERE s.seen=false " +
            "RETURN * " +
            "ORDER BY p.publish DESC")
    List<JobOffer> getNewNotifications(String email);

    @Query("MATCH (u:User {email:{0}})<-[c:MY_CREDENTIALS]-(w:Worker)" +
            "MATCH (w)-[s:SEEN]->(j:JobOffer) " +
            "MATCH (j)<-[p:PUBLISH]-(e:Employer) " +
            "WHERE s.seen=true " +
            "RETURN * " +
            "ORDER BY p.publish DESC " +
            "LIMIT {1}")
    List<JobOffer> getNotifications(String email, int limit);

    @Query("MATCH (u:User {email:{0}})<-[c:MY_CREDENTIALS]-(w:Worker)" +
            "MATCH (w)-[s:SEEN]->(j:JobOffer) " +
            "WHERE id(j)={1} " +
            "SET s.seen=true " +
            "RETURN s.seen")
    Optional<Boolean> markJobOfferAsSeenByThisWorker(String email, int id);
}
