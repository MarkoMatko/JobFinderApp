package raf.undergraduate.dissertation.markomatkovic.jobfinder.model;

import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.User;

public class EmployerRequest {
    private String name;
    private String branch;
    private String description;
    private String siteLink;
    private User credentials;
    private String location;
    private String logoImgPath;

    public EmployerRequest(String name, String branch, String description, String siteLink,
                           User credentials, String location, String logoImgPath) {
        this.name = name;
        this.branch = branch;
        this.description = description;
        this.siteLink = siteLink;
        this.credentials = credentials;
        this.location = location;
        this.logoImgPath = logoImgPath;
    }

    public EmployerRequest() {
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getBranch() {
        return branch;
    }
    public void setBranch(String branch) {
        this.branch = branch;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getSiteLink() {
        return siteLink;
    }
    public void setSiteLink(String siteLink) {
        this.siteLink = siteLink;
    }
    public User getCredentials() {
        return credentials;
    }
    public void setCredentials(User credentials) {
        this.credentials = credentials;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getLogoImgPath() {
        return logoImgPath;
    }
    public void setLogoImgPath(String logoImgPath) {
        this.logoImgPath = logoImgPath;
    }

    @Override
    public String toString() {
        return "EmployerRequest{" +
                "name='" + name + '\'' +
                ", branch='" + branch + '\'' +
                ", description='" + description + '\'' +
                ", siteLink='" + siteLink + '\'' +
                ", credentials=" + credentials +
                ", location='" + location + '\'' +
                ", logoImgPath='" + logoImgPath + '\'' +
                '}';
    }
}
