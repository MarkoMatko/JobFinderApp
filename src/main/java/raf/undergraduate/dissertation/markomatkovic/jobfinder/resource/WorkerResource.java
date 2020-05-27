package raf.undergraduate.dissertation.markomatkovic.jobfinder.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.Employer;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.JobOffer;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.ReviewedRelationship;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.model.*;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.Worker;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.service.WorkerService;

import java.util.List;

/**
 * Rest endpoint for worker.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = ResourceConstants.CLIENT_URL)
public class WorkerResource {

    @Autowired
    private WorkerService workerService;

    @PostMapping("/workers")
    public ResponseEntity<Worker> saveWorker(@RequestBody Worker worker){
        return new ResponseEntity<>(workerService.saveNewWorker(worker), HttpStatus.CREATED);
    }

    @GetMapping("/workers/{id}")
    public ResponseEntity<Worker> findWorkerBySpecificId(@PathVariable Long id){
        return new ResponseEntity<>(workerService.findWorkerById(id), HttpStatus.OK);
    }

    @GetMapping("/workers/search/{search}")
    public ResponseEntity<List<Worker>> searchForWorkers(@PathVariable String search) {
        return new ResponseEntity<>(workerService.searchForWorkers(search), HttpStatus.OK);
    }

    @PutMapping("/workers/create_friendship")
    public ResponseEntity<Void> createFriendship(@RequestBody FriendshipRequest friendship){
        workerService.createFriendship(friendship);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/workers/follow")
    public ResponseEntity<Void> createFollowConnection(@RequestBody FollowRequest follow){
        workerService.createFollowConnection(follow);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PutMapping("/workers/review")
    public ResponseEntity<ReviewedRelationship> rateWorker(@RequestBody ReviewRequest review){
        ReviewedRelationship r = workerService.createReview(review);
        return new ResponseEntity<>(r, HttpStatus.CREATED);
    }

    @GetMapping("/workers/reviews/{id}")
    public ResponseEntity<List<ReviewedRelationship>> getAllReviewsForWorkerId(@PathVariable Integer id){
        return new ResponseEntity<>(workerService.getAllReviewsForWorkerId(id), HttpStatus.OK);

    }

    @PostMapping("/workers/friends_check")
    public ResponseEntity<Boolean> friendsCheck(@RequestBody FriendshipRequest friendsCheckRequest) {
        return new ResponseEntity<>(workerService.friendsCheck(friendsCheckRequest), HttpStatus.OK);
    }

    @GetMapping("/workers/find/{email}")
    public ResponseEntity<Worker> findWorkerWithSpecificId(@PathVariable String email){
        return new ResponseEntity<>(workerService.findWorkerByEmail(email), HttpStatus.OK);
    }

    @GetMapping("/workers/friends/{email}")
    public ResponseEntity<List<Worker>> findFriendsForSpecificWorker(@PathVariable String email){
        return new ResponseEntity<>(workerService.findFriendsForSpecificWorker(email), HttpStatus.OK);
    }

    @GetMapping("/workers/followed_employers/{email}")
    public ResponseEntity<List<Employer>> findFollowedEmployersForSpecificWorker(@PathVariable String email){
        return new ResponseEntity<>(workerService.findFollowedEmployersForSpecificWorker(email), HttpStatus.OK);
    }

    @PutMapping("/workers/unfollow")
    public ResponseEntity<Boolean> unfollowEmployer(@RequestBody FollowCheckRequest unfollowRequest){
        return new ResponseEntity<>(workerService.unfollowEmployer(unfollowRequest), HttpStatus.OK);
    }

    @PutMapping("/workers/unfriend")
    public ResponseEntity<Boolean> unfriend(@RequestBody FriendshipRequest unfriendRequest) {
        return new ResponseEntity<>(workerService.unfriend(unfriendRequest), HttpStatus.OK);
    }

    @GetMapping("/workers/notification/{email}")
    public ResponseEntity<List<JobOffer>> getNewNotifications(@PathVariable String email) {
        return new ResponseEntity<>(workerService.getNewNotifications(email), HttpStatus.OK);
    }

    @GetMapping("/workers/notification/{email}/{limit}")
    public ResponseEntity<List<JobOffer>> getAlreadySeenNotifications(@PathVariable String email, @PathVariable  Integer limit) {
        return new ResponseEntity<>(workerService.getAlreadySeenNotifications(email,limit), HttpStatus.OK);
    }

    @PutMapping("workers/mark_as_seen")
    public ResponseEntity<Boolean> markJobOfferAsSeenByThisWorker(@RequestBody ApplyCheckRequest request){
        return new ResponseEntity<>(workerService.markJobOfferAsSeenByThisWorker(request.getWorkerEmail(), request.getJobOfferId().intValue()), HttpStatus.OK);
    }
}
