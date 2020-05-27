package raf.undergraduate.dissertation.markomatkovic.jobfinder.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RelationshipEntity(type = "PUBLISH")
public class JobOfferRelationship {


    @Id
    @GeneratedValue
    private Long id;

    @Property
    private LocalDateTime publish;

    @Property
    private LocalDate expired;

    @StartNode
    private Employer employer;

    @JsonIgnore
    @EndNode
    private JobOffer jobOffer;

    public JobOfferRelationship() {
    }

    public JobOfferRelationship(LocalDateTime publish, LocalDate expired, Employer employer, JobOffer jobOffer) {
        this.publish = publish;
        this.expired = expired;
        this.employer = employer;
        this.jobOffer = jobOffer;
    }

    public LocalDateTime getPublish() {
        return publish;
    }
    public void setPublish(LocalDateTime publish) {
        this.publish = publish;
    }
    public LocalDate getExpired() {
        return expired;
    }
    public void setExpired(LocalDate expired) {
        this.expired = expired;
    }
    public Employer getEmployer() {
        return employer;
    }
    public void setEmployer(Employer employer) {
        this.employer = employer;
    }
    public JobOffer getJobOffer() {
        return jobOffer;
    }
    public void setJobOffer(JobOffer jobOffer) {
        this.jobOffer = jobOffer;
    }
    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "JobOfferRelationship{" +
                "id=" + id +
                ", publish=" + publish +
                ", expired=" + expired +
                ", employer=" + employer.getName() +
                ", jobOffer=" + jobOffer.getOffer() +
                '}';
    }
}
