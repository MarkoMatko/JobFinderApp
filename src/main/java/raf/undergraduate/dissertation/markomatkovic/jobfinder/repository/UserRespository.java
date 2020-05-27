package raf.undergraduate.dissertation.markomatkovic.jobfinder.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.User;

import java.util.Collection;
import java.util.Optional;

/**
 * Repository for managing logging and registrating and deleting new user with his credentials.
 */
@Repository
public interface UserRespository extends Neo4jRepository<User,Long> {

    /**
     * Custom query method for fetching all user's with their credentials.
     * @return Collection of users with their credentials(username and password)
     */
    @Query("MATCH (u:User) RETURN u")
    Collection<User> findAll();

    @Query("MATCH (u:User {email:{0}}) RETURN u")
    Optional<User> findByEmail(String email);
}
