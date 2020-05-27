package raf.undergraduate.dissertation.markomatkovic.jobfinder.model;

public class FollowRequest {

    private String workerEmail;
    private Long employerId;

    public FollowRequest(){}

    public String getWorkerEmail() {
        return workerEmail;
    }
    public void setWorkerEmail(String workerEmail) {
        this.workerEmail = workerEmail;
    }
    public Long getEmployerId() {
        return employerId;
    }
    public void setEmployerId(Long secondId) {
        this.employerId = secondId;
    }
}
