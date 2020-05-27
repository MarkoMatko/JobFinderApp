package raf.undergraduate.dissertation.markomatkovic.jobfinder.model;

public class FriendshipRequest {

    private String firstWorkerEmail;
    private Long secondWorkerId;

    public FriendshipRequest(){}

    public String getFirstWorkerEmail() {
        return firstWorkerEmail;
    }
    public void setFirstWorkerEmail(String firstWorkerEmail) {
        this.firstWorkerEmail = firstWorkerEmail;
    }
    public Long getSecondWorkerId() {
        return secondWorkerId;
    }
    public void setSecondWorkerId(Long secondWorkerId) {
        this.secondWorkerId = secondWorkerId;
    }
}
