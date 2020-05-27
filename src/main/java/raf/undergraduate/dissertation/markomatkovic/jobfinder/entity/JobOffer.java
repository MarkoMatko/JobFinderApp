package raf.undergraduate.dissertation.markomatkovic.jobfinder.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.neo4j.ogm.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@NodeEntity
public class JobOffer {

    @Id @GeneratedValue
    private Long id;

    @Property
    private String jobDescription;

    @Property
    private Set<String> offer = new HashSet<>();

    @Property
    private Set<String> jobResponsibilities = new HashSet<>();

    @Property
    private Set<String> experienceRequired = new HashSet<>();

    @Property
    private String imgPath;

    @Relationship(type = "PUBLISH", direction = Relationship.INCOMING)
    private JobOfferRelationship jobOfferRelationship;

    @Relationship(type = "APPLIED", direction = Relationship.INCOMING)
    private List<ApplyRelationship> workersWhoApplied;

    public JobOffer() {
    }

    public JobOffer(String jobDescription, Set<String> offer, Set<String> jobResponsibilities,
                    Set<String> experienceRequired, String imgPath) {
        this.jobDescription = jobDescription;
        this.offer = offer;
        this.jobResponsibilities = jobResponsibilities;
        this.experienceRequired = experienceRequired;
        this.imgPath = imgPath;
    }

    public String getJobDescription() {
        return jobDescription;
    }
    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }
    public Set<String> getOffer() {
        return offer;
    }
    public void setOffer(Set<String> offer) {
        this.offer = offer;
    }
    public Set<String> getJobResponsibilities() {
        return jobResponsibilities;
    }
    public void setJobResponsibilities(Set<String> jobResponsibilities) {
        this.jobResponsibilities = jobResponsibilities;
    }
    public Set<String> getExperienceRequired() {
        return experienceRequired;
    }
    public void setExperienceRequired(Set<String> experienceRequired) {
        this.experienceRequired = experienceRequired;
    }
    public Long getId() {
        return id;
    }
    public JobOfferRelationship getJobOfferRelationship() {
        return jobOfferRelationship;
    }
    public void setJobOfferRelationship(JobOfferRelationship jobOfferRelationship) {
        this.jobOfferRelationship = jobOfferRelationship;
    }
    public List<ApplyRelationship> getWorkersWhoApplied() {
        return workersWhoApplied;
    }
    public void setWorkersWhoApplied(List<ApplyRelationship> workersWhoApplied) {
        this.workersWhoApplied = workersWhoApplied;
    }
    public String getImgPath() {
        return imgPath;
    }
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public String toString() {
        return "JobOffer{" +
                "id=" + id +
                ", jobDescription='" + jobDescription + '\'' +
                ", offer='" + offer + '\'' +
                ", jobResponsibilities='" + jobResponsibilities + '\'' +
                ", experienceRequired='" + experienceRequired + '\'' +
                ", jobOfferRelationship=" + jobOfferRelationship.getId() +
                '}';
    }

    public void addApplier(ApplyRelationship applier){
        if(workersWhoApplied == null){
            workersWhoApplied = new ArrayList<>();
        }
        workersWhoApplied.add(applier);
    }
}
