package raf.undergraduate.dissertation.markomatkovic.jobfinder.entity;

import org.neo4j.ogm.annotation.*;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.model.Branch;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represent a worker, it is equivalent to domain object in database.
 * Worker represent one of the users of JobFineder social network.
 */
@NodeEntity
public class Worker{

    @Id @GeneratedValue
    private Long id;

    @Relationship(type = "MY_CREDENTIALS",direction = Relationship.UNDIRECTED)
    private User credentials;

    @Property("name")
    private String firstName;

    @Property("surname")
    private String secondName;

    @Property
    private Branch branch;

    @Property
    private String degree;

    @Property
    private String CVLink;

    @Relationship(type = "FRIEND_WITH", direction = Relationship.OUTGOING)
    private List<Worker> friends;

    @Relationship(type = "FOLLOW", direction = Relationship.OUTGOING)
    private List<Employer> followedEmployers;

    @Relationship(type = "REVIEWED", direction = Relationship.INCOMING)
    private List<ReviewedRelationship> reviews;

    @Relationship(type = "SEEN", direction = Relationship.OUTGOING)
    private List<NotificationRelationship> notifications;

    //constructor
    public Worker() {
    }

    //geters and setters
    public Long getId() {
        return id;
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
    public Branch getBranch() {
        return branch;
    }
    public void setBranch(Branch branch) {
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
    public List<Worker> getFriends() {
        return friends;
    }
    public void setFriends(List<Worker> friends) {
        this.friends = friends;
    }
    public List<Employer> getFollowedEmployers() {
        return followedEmployers;
    }
    public void setFollowedEmployers(List<Employer> followedEmployers) {
        this.followedEmployers = followedEmployers;
    }
    public List<ReviewedRelationship> getReviews() {
        return reviews;
    }
    public void setReviews(List<ReviewedRelationship> reviews) {
        this.reviews = reviews;
    }
    public List<NotificationRelationship> getNotifications() {
        return notifications;
    }
    public void setNotifications(List<NotificationRelationship> notifications) {
        this.notifications = notifications;
    }

    /**
     * Add new friend to the list of friends.
     * @param friend we want to add
     */
    public void addFriend(Worker friend){
        if(friends == null){
            friends = new ArrayList<>();
        }
        friends.add(friend);
    }

    /**
     * Add new employer to the list of followed employers.
     * @param employer we want to add
     */
    public void followEmployer(Employer employer){
        if(followedEmployers == null){
            followedEmployers = new ArrayList<>();
        }
        followedEmployers.add(employer);
    }

    /**
     * Add review to the list of reviews written by specific employer about my service.
     * Review can by added after this worker finish his work for that employer,
     * or while this worker is still working for that employer.
     * @param review we want to add to this worker
     */
    public void addReview(ReviewedRelationship review){
        if(reviews == null){
            reviews = new ArrayList<>();
        }
        reviews.add(review);
    }

    /**
     * Add new notification to the list of this worker notifications.
     * This is called whenever employer, followed by this worker, adds new joboffer.
     * @param notification
     */
    public void addNotification(NotificationRelationship notification) {
        if (this.notifications == null){
            this.notifications = new ArrayList<>();
        }
        this.notifications.add(notification);
    }
    
}
