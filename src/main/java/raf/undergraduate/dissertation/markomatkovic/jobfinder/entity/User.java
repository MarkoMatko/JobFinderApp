package raf.undergraduate.dissertation.markomatkovic.jobfinder.entity;

import org.neo4j.ogm.annotation.*;

import java.util.HashSet;
import java.util.Set;

/**
 * This class represent credentials for every user of JobFinder app.
 */
@NodeEntity
public class User {

    @Id @GeneratedValue
    private Long id;

    @Property
    private String username;

    @Property
    private String password;

    @Property
    private String email;

    @Property
    private Set<String> roles = new HashSet<>();

    public User() {

    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public Long getId() {
        return id;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Set<String> getRoles() {
        return roles;
    }
    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public boolean addRole(String role){
        for (String r :
                this.roles) {
            if(r.equals(role)) return false;
        }
        this.roles.add(role);
        return true;
    }
}
