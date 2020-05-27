package raf.undergraduate.dissertation.markomatkovic.jobfinder.entity;

import org.neo4j.ogm.annotation.*;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.model.Branch;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represent a employer, it is equivalent to domain object in database.
 * Employer represent one of the users of JobFineder social network and he is the one who publish posts.
 */
@NodeEntity
public class Employer {

    @Id @GeneratedValue
    private Long id;

    @Relationship(type = "MY_CREDENTIALS",direction = Relationship.UNDIRECTED)
    private User credentials;

    @Property
    private String name;

    @Property
    private Branch branch;

    @Property
    private String description;

    @Property
    private String siteLink;

    @Property
    private String location;

    @Property
    private String logoImgPath;

    @Relationship(type = "EVALUATE", direction = Relationship.INCOMING)
    private List<EvaluateRelationship> evaluations;

    public Employer() {
    }

    //getters and setters
    public User getCredentials() {
        return credentials;
    }
    public void setCredentials(User credentials) {
        this.credentials = credentials;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Branch getBranch() {
        return branch;
    }
    public void setBranch(Branch branch) {
        this.branch = branch;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getSiteLink() {
        return siteLink;
    }
    public void setSiteLink(String siteLink) {
        this.siteLink = siteLink;
    }
    public List<EvaluateRelationship> getEvaluations() {
        return evaluations;
    }
    public void setEvaluations(List<EvaluateRelationship> evaluations) {
        this.evaluations = evaluations;
    }
    public Long getId() {
        return id;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getLogoImgPath() {
        return logoImgPath;
    }
    public void setLogoImgPath(String logoImgPath) {
        this.logoImgPath = logoImgPath;
    }


    /**
     * Add evaluation to the list of evaluations written by specific worker about my service.
     * @param evaluation we want to add to this employer
     */
    public void addEvaluation(EvaluateRelationship evaluation) {
        if(evaluations == null){
            evaluations = new ArrayList<>();
        }
        evaluations.add(evaluation);
    }
}
