package raf.undergraduate.dissertation.markomatkovic.jobfinder.model;

import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.User;

public class WorkerRequest {
    private String firstName;
    private String secondName;
    private String branch;
    private String degree;
    private String CVLink;
    private User credentials;

    public WorkerRequest(String firstName, String secondName, String branch, String degree, String CVLink, User credentials) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.branch = branch;
        this.degree = degree;
        this.CVLink = CVLink;
        this.credentials = credentials;
    }

    public WorkerRequest() {
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getSecondName() {
        return secondName;
    }
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
    public String getBranch() {
        return branch;
    }
    public void setBranch(String branch) {
        this.branch = branch;
    }
    public String getDegree() {
        return degree;
    }
    public void setDegree(String degree) {
        this.degree = degree;
    }
    public String getCVLink() {
        return CVLink;
    }
    public void setCVLink(String CVLink) {
        this.CVLink = CVLink;
    }
    public User getCredentials() {
        return credentials;
    }
    public void setCredentials(User credentials) {
        this.credentials = credentials;
    }
}
