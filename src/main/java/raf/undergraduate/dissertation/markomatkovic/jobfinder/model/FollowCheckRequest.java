package raf.undergraduate.dissertation.markomatkovic.jobfinder.model;

public class FollowCheckRequest {

    private String workerEmail;
    private Long employerId;

    public FollowCheckRequest() {
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
}
