package raf.undergraduate.dissertation.markomatkovic.jobfinder.model;

import java.time.LocalDateTime;

public class ApplyRequest {

    private String workerEmail;
    private Long jobOfferId;
    private LocalDateTime applied;

    public ApplyRequest() {
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
    public LocalDateTime getApplied() {
        return applied;
    }
    public void setApplied(LocalDateTime applied) {
        this.applied = applied;
    }
}
