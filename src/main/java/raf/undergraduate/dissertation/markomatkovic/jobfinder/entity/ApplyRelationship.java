package raf.undergraduate.dissertation.markomatkovic.jobfinder.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.*;

import java.time.LocalDateTime;

@RelationshipEntity(type = "APPLIED")
public class ApplyRelationship {

    @Id @GeneratedValue
    private Long id;

    @Property
    private LocalDateTime appliedDateAndTime;

    @StartNode
    private Worker worker;

    @JsonIgnore
    @EndNode
    private JobOffer jobOffer;

    public ApplyRelationship() {
    }

    public ApplyRelationship(LocalDateTime appliedDateAndTime, Worker worker, JobOffer jobOffer) {
        this.appliedDateAndTime = appliedDateAndTime;
        this.worker = worker;
        this.jobOffer = jobOffer;
    }

    public Long getId() {
        return id;
    }
    public LocalDateTime getAppliedDateAndTime() {
        return appliedDateAndTime;
    }
    public void setAppliedDateAndTime(LocalDateTime appliedDateAndTime) {
        this.appliedDateAndTime = appliedDateAndTime;
    }
    public Worker getWorker() {
        return worker;
    }
    public void setWorker(Worker worker) {
        this.worker = worker;
    }
    public JobOffer getJobOffer() {
        return jobOffer;
    }
    public void setJobOffer(JobOffer jobOffer) {
        this.jobOffer = jobOffer;
    }
}
