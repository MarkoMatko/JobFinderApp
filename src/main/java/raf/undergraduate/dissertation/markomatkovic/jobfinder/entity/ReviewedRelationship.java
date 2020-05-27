package raf.undergraduate.dissertation.markomatkovic.jobfinder.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.*;

/**
 * This class represent reviewed relationship where employer sends review for specific worker.
 * This class represent object mapping from domain object (relationship from database).
 */
@RelationshipEntity(type = "REVIEWED")
public class ReviewedRelationship {

    @Id @GeneratedValue
    private Long id;

    @Property
    private String critics;

    @Property
    private String recommendation;

    @Property
    private Integer workerRate;

    @Property
    private boolean stillWorkingForThisEmployer;

    @StartNode
    private Employer employer;

    @JsonIgnore
    @EndNode
    private Worker worker;


    public ReviewedRelationship() {
    }

    public ReviewedRelationship(String critics, String recommendation, Integer workerRate,
                                boolean stillWorkingForThisEmployer, Employer employer, Worker worker) {
        this.critics = critics;
        this.recommendation = recommendation;
        this.workerRate = workerRate;
        this.stillWorkingForThisEmployer = stillWorkingForThisEmployer;
        this.employer = employer;
        this.worker = worker;
    }

    //getters and setters
    public String getCritics() {
        return critics;
    }
    public void setCritics(String critics) {
        this.critics = critics;
    }
    public Integer getWorkerRate() {
        return workerRate;
    }
    public void setWorkerRate(Integer workerRate) {
        this.workerRate = workerRate;
    }
    public Worker getWorker() {
        return worker;
    }
    public void setWorker(Worker worker) {
        this.worker = worker;
    }
    public Employer getEmployer() {
        return employer;
    }
    public void setEmployer(Employer employer) {
        this.employer = employer;
    }
    public Long getId() {
        return id;
    }
    public String getRecommendation() {
        return recommendation;
    }
    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }
    public boolean isStillWorkingForThisEmployer() {
        return stillWorkingForThisEmployer;
    }
    public void setStillWorkingForThisEmployer(boolean stillWorkingForThisEmployer) {
        this.stillWorkingForThisEmployer = stillWorkingForThisEmployer;
    }

}
