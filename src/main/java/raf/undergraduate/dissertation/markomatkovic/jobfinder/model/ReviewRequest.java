package raf.undergraduate.dissertation.markomatkovic.jobfinder.model;


public class ReviewRequest {

    private Long workerId;
    private String employerEmail;
    private String critics;
    private String recommendation;
    private Integer workerRate;
    private boolean stillWorkingForThisEmployer;

    public ReviewRequest() {
    }

    public Long getWorkerId() {
        return workerId;
    }
    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }
    public String getEmployerEmail() {
        return employerEmail;
    }
    public void setEmployerEmail(String employerEmail) {
        this.employerEmail = employerEmail;
    }
    public String getCritics() {
        return critics;
    }
    public void setCritics(String critics) {
        this.critics = critics;
    }
    public String getRecommendation() {
        return recommendation;
    }
    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }
    public Integer getWorkerRate() {
        return workerRate;
    }
    public void setWorkerRate(Integer workerRate) {
        this.workerRate = workerRate;
    }
    public boolean isStillWorkingForThisEmployer() {
        return stillWorkingForThisEmployer;
    }
    public void setStillWorkingForThisEmployer(boolean stillWorkingForThisEmployer) {
        this.stillWorkingForThisEmployer = stillWorkingForThisEmployer;
    }
}
