package raf.undergraduate.dissertation.markomatkovic.jobfinder.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.*;

/**
 * This class represent evaluate relationship where worker sends evaluation for specific employer.
 * This class represent object mapping from domain object (relationship from database).
 */
@RelationshipEntity(type = "EVALUATE")
public class EvaluateRelationship {

    @Id @GeneratedValue
    private Long id;

    @Property
    private String workerExperience;

    @Property
    private String recommendation;

    @Property
    private Integer employerRate;

    @Property
    private boolean stillWorkingForThisEmployer;

    @StartNode
    private Worker worker;

    @JsonIgnore
    @EndNode
    private Employer employer;

    public EvaluateRelationship(String workerExperience, String recommendation,
                                Integer employerRate, boolean stillWorkingForThisEmployer, Worker worker, Employer employer) {
        this.workerExperience = workerExperience;
        this.recommendation = recommendation;
        this.employerRate = employerRate;
        this.stillWorkingForThisEmployer = stillWorkingForThisEmployer;
        this.worker = worker;
        this.employer = employer;
    }

    public EvaluateRelationship() {
    }

    public String getWorkerExperience() {
        return workerExperience;
    }
    public void setWorkerExperience(String workerExperience) {
        this.workerExperience = workerExperience;
    }
    public String getRecommendation() {
        return recommendation;
    }
    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }
    public Integer getEmployerRate() {
        return employerRate;
    }
    public void setEmployerRate(Integer employerRate) {
        this.employerRate = employerRate;
    }
    public boolean isStillWorkingForThisEmployer() {
        return stillWorkingForThisEmployer;
    }
    public void setStillWorkingForThisEmployer(boolean stillWorkingForThisEmployer) {
        this.stillWorkingForThisEmployer = stillWorkingForThisEmployer;
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

}
