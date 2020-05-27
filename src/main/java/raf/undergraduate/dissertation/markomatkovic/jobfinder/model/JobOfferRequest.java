package raf.undergraduate.dissertation.markomatkovic.jobfinder.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class JobOfferRequest {

    private String employerEmail;
    private LocalDateTime published;
    private LocalDate expired;

    private String jobDescription;
    private Set<String> offer = new HashSet<>();
    private Set<String> jobResponsibilities = new HashSet<>();
    private Set<String> experienceRequired = new HashSet<>();
    private String imgPath;

    public JobOfferRequest() {
    }

    public String getEmployerEmail() {
        return employerEmail;
    }
    public void setEmployerEmail(String employerEmail) {
        this.employerEmail = employerEmail;
    }
    public LocalDateTime getPublished() {
        return published;
    }
    public void setPublished(LocalDateTime published) {
        this.published = published;
    }
    public LocalDate getExpired() {
        return expired;
    }
    public void setExpired(LocalDate expired) {
        this.expired = expired;
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
    public String getImgPath() {
        return imgPath;
    }
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
