package raf.undergraduate.dissertation.markomatkovic.jobfinder.model;

public class ApplyCheckRequest {

    private String workerEmail;
    private Long jobOfferId;

    public ApplyCheckRequest(String workerEmail, Long jobOfferId) {
        this.workerEmail = workerEmail;
        this.jobOfferId = jobOfferId;
    }

    public ApplyCheckRequest() {
    }

    public String getWorkerEmail() {
        return workerEmail;
    }
    public void setWorkerEmail(String workerEmail) {
        this.workerEmail = workerEmail;
    }
    public Long getJobOfferId() {
        return jobOfferId;
    }
    public void setJobOfferId(Long jobOfferId) {
        this.jobOfferId = jobOfferId;
    }
}
