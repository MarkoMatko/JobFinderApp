package raf.undergraduate.dissertation.markomatkovic.jobfinder.service;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.Employer;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.JobOffer;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.ReviewedRelationship;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.model.FollowCheckRequest;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.model.FollowRequest;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.model.FriendshipRequest;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.Worker;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.model.ReviewRequest;

import java.util.Collection;
import java.util.List;

public interface WorkerService {

    Collection<Worker> findAllWorkers();
    Worker saveNewWorker(Worker worker);
    void deleteWorkerById(Long id);
    Worker findWorkerById(Long id);
    void createFriendship(FriendshipRequest friendship);
    void createFollowConnection(FollowRequest follow);
    ReviewedRelationship createReview(ReviewRequest review);
    List<Worker> searchForWorkers(String search);
    List<ReviewedRelationship> getAllReviewsForWorkerId(Integer id);
    boolean friendsCheck(FriendshipRequest friendsCheckRequest);
    Worker findWorkerByEmail(String email);
    List<Worker> findFriendsForSpecificWorker(String email);
    List<Employer> findFollowedEmployersForSpecificWorker(String email);
    boolean unfollowEmployer(FollowCheckRequest unfollowRequest);
    boolean unfriend(FriendshipRequest unfriendRequest);
    List<JobOffer> getNewNotifications(String email);
    List<JobOffer> getAlreadySeenNotifications(String email, Integer limit);
    boolean markJobOfferAsSeenByThisWorker(String email, Integer id);
}
