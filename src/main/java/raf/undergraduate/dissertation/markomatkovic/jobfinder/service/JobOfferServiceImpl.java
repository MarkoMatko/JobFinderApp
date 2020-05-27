package raf.undergraduate.dissertation.markomatkovic.jobfinder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.*;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.model.ApplyCheckRequest;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.model.ApplyRequest;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.model.JobOfferRequest;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.repository.ApplyRelationshipRepository;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.repository.EmployerRepository;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.repository.JobOfferRepository;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.repository.WorkerRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class JobOfferServiceImpl implements JobOfferService {

    private JobOfferRepository jobOfferRepository;
    private EmployerRepository employerRepository;
    private WorkerRepository workerRepository;
    private ApplyRelationshipRepository applyRelationshipRepository;

    @Autowired
    public JobOfferServiceImpl(JobOfferRepository jobOfferRepository,
                               EmployerRepository employerRepository,
                               WorkerRepository workerRepository,
                               ApplyRelationshipRepository applyRelationshipRepository){

        this.jobOfferRepository = jobOfferRepository;
        this.employerRepository = employerRepository;
        this.workerRepository = workerRepository;
        this.applyRelationshipRepository = applyRelationshipRepository;
    }

    //TODO NEED TO BE IMPLEMENTED
    @Override
    @Transactional
    public Collection<JobOffer> findAllJobOffers() {
        return null;
    }

    @Override
    @Transactional
    public JobOffer saveNewJobOffer(JobOfferRequest jobOffer) {
        Optional<Employer> employerOpt = employerRepository.findByEmail(jobOffer.getEmployerEmail());
        if(employerOpt.isPresent()){

            //ADDING JOB OFFER
            JobOffer newJobOffer = new JobOffer(jobOffer.getJobDescription(),jobOffer.getOffer(),
                    jobOffer.getJobResponsibilities(),jobOffer.getExperienceRequired(), jobOffer.getImgPath());

            JobOfferRelationship jobOfferRelationship = new JobOfferRelationship(jobOffer.getPublished(),jobOffer.getExpired(),
                    employerOpt.get(),newJobOffer);

            newJobOffer.setJobOfferRelationship(jobOfferRelationship);
            JobOffer toReturn = jobOfferRepository.save(newJobOffer);

            //CREATING SEEN RELATIONSHIP FOR ALL WORKER WHO ARE FOLLOWING THIS EMPLOYER
            List<Worker> workersWhoFollowEmployer = employerRepository.getAllFollowers(jobOffer.getEmployerEmail());
            for (Worker worker : workersWhoFollowEmployer) {
                NotificationRelationship notification = new NotificationRelationship(jobOffer.getPublished(), false, worker, toReturn);
                worker.addNotification(notification);
                workerRepository.save(worker);
            }

           return toReturn;
        }
        else{
            //TODO NEED TO BE IMPLEMENTED
            return null;
        }
    }

    @Override
    @Transactional
    public void deleteJobOfferById(Long id) {
        jobOfferRepository.deleteById(id);
    }

    @Override
    @Transactional
    public boolean applyWorker(ApplyRequest apply) {
        Optional<Worker> workerOpt = workerRepository.findByEmail(apply.getWorkerEmail());
        Optional<JobOffer> jobOfferOpt = jobOfferRepository.findById(apply.getJobOfferId());

        if(workerOpt.isPresent() && jobOfferOpt.isPresent()){
            jobOfferOpt.get().addApplier(new ApplyRelationship(apply.getApplied(),workerOpt.get(),jobOfferOpt.get()));
            jobOfferRepository.save(jobOfferOpt.get());
            return true;
        }
        return false;
    }


    @Override
    @Transactional
    public List<JobOffer> getAllOffers() {
        Iterable<JobOffer> theOffers =  jobOfferRepository.findAll(1);
        List<JobOffer> toReturn = new ArrayList<>();
        for (JobOffer jobOffer : theOffers) {
            if (jobOffer.getJobOfferRelationship().getExpired().isAfter(LocalDate.now())){
                toReturn.add(jobOffer);
            }
        }
        return toReturn;
    }


    @Override
    @Transactional
    public Boolean applyCheck(ApplyCheckRequest applyCheckRequest) {
        if (applyCheckRequest.getJobOfferId() < 0 || applyCheckRequest.getWorkerEmail().equals(null)) return null;
        Optional<Worker> relationship = jobOfferRepository.applyCheck(applyCheckRequest.getWorkerEmail(), applyCheckRequest.getJobOfferId().intValue());
        if (relationship.isPresent()) return true;
          return false;
    }

    @Override
    @Transactional
    public List<JobOffer> findAllPublishedBySpecificEmployer(Integer id) {
        List<JobOffer> jobOffers = jobOfferRepository.findAllPublishedBySpecificEmployer(id);
        if (jobOffers.isEmpty()) return null;
        else return jobOffers;
    }

    @Override
    @Transactional
    public List<ApplyRelationship> findAllAppliersForSpecificJobOffer(Integer id) {
        List<ApplyRelationship> appliedWorkers = applyRelationshipRepository.findAllAppliersForSpecificJobOffer(id);
        if (appliedWorkers.isEmpty()) return null;
        else return appliedWorkers;
    }

    @Override
    @Transactional
    public List<JobOffer> findAllAplicationsForWorker(String email) {
        List<JobOffer> applications = jobOfferRepository.findAllAplicationsForWorker(email);
        if (applications.isEmpty()) return null;
        else return applications;
    }

    @Override
    @Transactional
    public void removeApplication(Integer id, String email) {
        jobOfferRepository.removeApplication(id, email);
        //TODO STA AKO BACI ERROR
    }
}
