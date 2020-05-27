package raf.undergraduate.dissertation.markomatkovic.jobfinder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.Employer;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.JobOffer;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.ReviewedRelationship;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.model.FollowCheckRequest;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.model.FollowRequest;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.model.FriendshipRequest;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.Worker;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.model.ReviewRequest;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.repository.EmployerRepository;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.repository.ReviewedRelationshipRepository;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.repository.WorkerRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Service class for reading,deleting,adding,updating,... Workers.
 */
@Service
public class WorkerServiceImpl implements WorkerService {


    private WorkerRepository workerRepository;
    private EmployerRepository employerRepository;
    private ReviewedRelationshipRepository reviewedRelationshipRepository;

    @Autowired
    public WorkerServiceImpl(WorkerRepository workerRepository,
                             EmployerRepository employerRepository,
                             ReviewedRelationshipRepository reviewedRelationshipRepository){
        this.workerRepository = workerRepository;
        this.employerRepository = employerRepository;
        this.reviewedRelationshipRepository = reviewedRelationshipRepository;
    }

    /**
     * Saves new worker to database.
     * @param worker to be saved
     * @return saved worker with his database id
     */
    @Transactional
    @Override
    public Worker saveNewWorker(Worker worker) {
        return workerRepository.save(worker);
    }

    //TODO NEED TO BE IMPLEMENTED
    @Transactional
    @Override
    public Collection<Worker> findAllWorkers() {
        return null;
    }

    /**
     * Delete worker with id.
     * @param id of worker to be deleted
     */
    @Transactional
    @Override
    public void deleteWorkerById(Long id) {
        workerRepository.deleteById(id);
    }

    /**
     * Find worker with specific id.
     * @param id of worker
     * @return worker with specific id or null if worker with specific id doesn't exist
     */
    @Transactional
    @Override
    public Worker findWorkerById(Long id) {
        Optional<Worker> workerOptional = workerRepository.findById(id);

        //check weather result is present
        if(workerOptional.isPresent()){
            return workerOptional.get();
        }
        //TODO HANDLOVATI EXCEPTION U SUPROTNOM
        return null;
    }


    /**
     * Create friendship between two workers.
     * @param friendship Object that contains id of domain object in data
     *                   base for first and second worker, between we create friendship.
     */
    @Override
    @Transactional
    public void createFriendship(FriendshipRequest friendship) {

        Optional<Worker> firstWorkerOpt = workerRepository.findByEmail(friendship.getFirstWorkerEmail());
        Optional<Worker> secondWorkerOpt = workerRepository.findById(friendship.getSecondWorkerId());

        if(firstWorkerOpt.isPresent() && secondWorkerOpt.isPresent()){
            firstWorkerOpt.get().addFriend(secondWorkerOpt.get());
            secondWorkerOpt.get().addFriend(firstWorkerOpt.get());
            workerRepository.save(firstWorkerOpt.get());
            workerRepository.save(secondWorkerOpt.get());
        }
        else{
            //TODO HANDLOVATI EXCEPTION U SUPROTNOM
        }


    }

    /**
     * Create follow relation between worker and employer.
     * @param follow Object that contains id of domain object in database for worker and id of domain object
     *               , in database for employer, between we create follow relation.
     */
    @Override
    @Transactional
    public void createFollowConnection(FollowRequest follow) {

        Optional<Worker> workerOpt = workerRepository.findByEmail(follow.getWorkerEmail());
        Optional<Employer> employerOpt = employerRepository.findById(follow.getEmployerId());

        if(workerOpt.isPresent() && employerOpt.isPresent()){
            workerOpt.get().followEmployer(employerOpt.get());
            workerRepository.save(workerOpt.get());
        }
        else{
            //TODO HANDLOVATI EXCEPTION U SUPROTNOM
        }
    }

    /**
     * Create ReviewedRelationship object using ReviewRequest object and after that, create
     * review relation between specified employer and specified worker
     * @param review represent object that contains ids of both sides of relation and other properties
     *               We use this object to construct real relationship object
     */
    @Override
    @Transactional
    public ReviewedRelationship createReview(ReviewRequest review) {

        Optional<Worker> workerOpt = workerRepository.findById(review.getWorkerId());
        Optional<Employer> employerOpt = employerRepository.findByEmail(review.getEmployerEmail());

        if(workerOpt.isPresent() && employerOpt.isPresent()){
            ReviewedRelationship reviewedRelationship =
                    new ReviewedRelationship(review.getCritics(),review.getRecommendation(),
                            review.getWorkerRate(),review.isStillWorkingForThisEmployer(),
                            employerOpt.get(),workerOpt.get());
            workerOpt.get().addReview(reviewedRelationship);
            workerRepository.save(workerOpt.get());
            return reviewedRelationship;

        }
        else {
            //TODO HANDLOVATI EXCEPTION U SUPROTNOM
            return null;
        }
    }

    @Override
    @Transactional
    public List<Worker> searchForWorkers(String search) {
        String regExSearch = "(?i).*" + search + ".*";
        List<Worker> searchedWorkers = this.workerRepository.searchForWorkers(regExSearch);
        if(searchedWorkers.isEmpty()) {
            System.out.println("Nije nasao ni jednog workera");
            return null;
        }
        return searchedWorkers;
    }

    @Override
    @Transactional
    public List<ReviewedRelationship> getAllReviewsForWorkerId(Integer id) {
        List<ReviewedRelationship> reviews = this.reviewedRelationshipRepository.getAllReviewsForWorkerId(id);
        if(reviews.isEmpty())
            return null;
        return reviews;
    }

    @Override
    @Transactional
    public boolean friendsCheck(FriendshipRequest friendsCheckRequest) {
        Optional<Worker> w = workerRepository.friendsCheck(friendsCheckRequest.getFirstWorkerEmail(), friendsCheckRequest.getSecondWorkerId().intValue());
        if (w.isPresent()) return true;
        return false;
    }

    /**
     * Find worker with specific email.
     * @param email of worker
     * @return worker with specific email or null if worker with specific email doesn't exist
     */
    @Override
    @Transactional
    public Worker findWorkerByEmail(String email) {
        Optional<Worker> workerOpt = workerRepository.findByEmail(email);

        //check weather result is present
        if(workerOpt.isPresent()){
            return workerOpt.get();
        }
        //TODO HANDLOVATI EXCEPTION U SUPROTNOM
        return null;
    }

    @Override
    @Transactional
    public List<Worker> findFriendsForSpecificWorker(String email) {
        List<Worker> friends = this.workerRepository.findFriendsForSpecificWorker(email);
        if(friends.isEmpty())
            return null;
        return friends;
    }

    @Override
    @Transactional
    public List<Employer> findFollowedEmployersForSpecificWorker(String email) {
        List<Employer> followedEmployers = this.workerRepository.findFollowedEmployersForSpecificWorker(email);
        if(followedEmployers.isEmpty())
            return null;
        return followedEmployers;
    }

    @Override
    @Transactional
    public boolean unfollowEmployer(FollowCheckRequest unfollowRequest) {
        this.workerRepository.unfollowEmployer(unfollowRequest.getWorkerEmail(), unfollowRequest.getEmployerId().intValue());
        //TODO PROVERITI DA LI JE USPESNO OBRISAO
        return true;
    }

    @Override
    @Transactional
    public boolean unfriend(FriendshipRequest unfriendRequest) {
        this.workerRepository.unfriend(unfriendRequest.getFirstWorkerEmail(), unfriendRequest.getSecondWorkerId().intValue());
        //TODO PROVERITI DA LI JE USPESNO OBRISAO
        return true;
    }

    @Override
    @Transactional
    public List<JobOffer> getNewNotifications(String email) {
        List<JobOffer> newNotifications = this.workerRepository.getNewNotifications(email);
        if(newNotifications.isEmpty()) return null;
        return newNotifications;
    }

    @Override
    @Transactional
    public List<JobOffer> getAlreadySeenNotifications(String email, Integer limit) {
        List<JobOffer> seenNotifications = this.workerRepository.getNotifications(email, limit.intValue());
        if(seenNotifications.isEmpty()) return null;
        return seenNotifications;
    }

    @Override
    @Transactional
    public boolean markJobOfferAsSeenByThisWorker(String email, Integer id) {
        Optional<Boolean> seen = this.workerRepository.markJobOfferAsSeenByThisWorker(email, id.intValue());
        if (seen.isPresent()) return seen.get();
        return false;
    }
}
