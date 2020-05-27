package raf.undergraduate.dissertation.markomatkovic.jobfinder.model;

public class EvaluateRequest {

    private String workerEmail;
    private Long employerId;
    private String workerExperience;
    private String recommendation;
    private Integer employerRate;
    private boolean stillWorkingForThisEmployer;

    public EvaluateRequest() {
    }

    public String getWorkerEmail() {
        return workerEmail;
    }
    public void setWorkerEmail(String workerEmail) {
        this.workerEmail = workerEmail;
    }
    public Long getEmployerId() {
        return employerId;
    }
    public void setEmployerId(Long employerId) {
        this.employerId = employerId;
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
}
