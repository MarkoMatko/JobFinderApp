package raf.undergraduate.dissertation.markomatkovic.jobfinder.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * This class represent seen relationship where worker who is following some emoloyer gets
 * notified whenever that employer add new joboffer.
 * This class also has some properties to determine did this worker see this notification.
 * This class represent object mapping from domain object (relationship from database).
 */
@RelationshipEntity(type = "SEEN")
public class NotificationRelationship {
    @Id
    @GeneratedValue
    private Long id;

    @Property
    private LocalDateTime dateAndTimeWhenSeen;

    @Property
    private boolean seen;

    @JsonIgnore
    @StartNode
    private Worker worker;

    @EndNode
    private JobOffer jobOffer;

    public NotificationRelationship() {
    }

    public NotificationRelationship(LocalDateTime dateAndTimeWhenSeen, boolean seen, Worker worker, JobOffer jobOffer) {
        this.dateAndTimeWhenSeen = dateAndTimeWhenSeen;
        this.seen = seen;
        this.worker = worker;
        this.jobOffer = jobOffer;
    }

    public LocalDateTime getDateAndTimeWhenSeen() {
        return dateAndTimeWhenSeen;
    }
    public void setDateAndTimeWhenSeen(LocalDateTime dateAndTimeWhenSeen) {
        this.dateAndTimeWhenSeen = dateAndTimeWhenSeen;
    }
    public boolean isSeen() {
        return seen;
    }
    public void setSeen(boolean seen) {
        this.seen = seen;
    }
    public Worker getWorker() {
        return worker;
    }
    public void setWorker(Worker worker) {
        this.worker = worker;
    }
    public JobOffer getJobOffer() {
        return jobOffer;
    }
    public void setJobOffer(JobOffer jobOffer) {
        this.jobOffer = jobOffer;
    }
    public Long getId() {
        return id;
    }
}
