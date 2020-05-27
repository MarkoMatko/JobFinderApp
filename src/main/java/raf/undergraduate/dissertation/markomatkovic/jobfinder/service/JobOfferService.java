package raf.undergraduate.dissertation.markomatkovic.jobfinder.service;


import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.ApplyRelationship;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.JobOffer;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.Worker;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.model.ApplyCheckRequest;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.model.ApplyRequest;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.model.JobOfferRequest;

import java.util.Collection;
import java.util.List;

public interface JobOfferService {

    Collection<JobOffer> findAllJobOffers();
    JobOffer saveNewJobOffer(JobOfferRequest jobOffer);
    void deleteJobOfferById(Long id);
    boolean applyWorker(ApplyRequest apply);
    Collection<JobOffer> getAllOffers();
    Boolean applyCheck(ApplyCheckRequest applyCheckRequest);
    List<JobOffer> findAllPublishedBySpecificEmployer(Integer id);
    List<ApplyRelationship> findAllAppliersForSpecificJobOffer(Integer id);
    List<JobOffer> findAllAplicationsForWorker(String email);
    void removeApplication(Integer id, String email);
}
