package raf.undergraduate.dissertation.markomatkovic.jobfinder.resource;

import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.ApplyRelationship;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.JobOffer;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.Worker;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.model.ApplyCheckRequest;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.model.ApplyRequest;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.model.JobOfferRequest;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.service.JobOfferService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = ResourceConstants.CLIENT_URL)
public class JobOfferResource {

    @Autowired
    private JobOfferService jobOfferService;

    @PostMapping("/job_offers")
    public ResponseEntity<JobOffer> saveJobOffer(@RequestBody JobOfferRequest jobOffer){
        return new ResponseEntity<>(jobOfferService.saveNewJobOffer(jobOffer), HttpStatus.CREATED);
    }

    @PutMapping("/job_offers/apply")
    public ResponseEntity<Void> applyWorker(@RequestBody ApplyRequest apply){
        boolean success = jobOfferService.applyWorker(apply);
        if(success) return new ResponseEntity<>(HttpStatus.CREATED);
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    //@PreAuthorize("hasAnyRole('WORKER')")
    @GetMapping("/job_offers")
    public ResponseEntity<Collection<JobOffer>> getAllOffers(){
        return new ResponseEntity<>(jobOfferService.getAllOffers(), HttpStatus.OK);
    }

    @PostMapping("/job_offers/apply_check")
    public ResponseEntity<Boolean> applyCheck(@RequestBody ApplyCheckRequest applyCheckRequest) {
        return new ResponseEntity<>(jobOfferService.applyCheck(applyCheckRequest), HttpStatus.OK);
    }

    @GetMapping("/job_offers/published_by_specific_employer/{id}")
    public ResponseEntity<List<JobOffer>> findAllPublishedBySpecificEmployer(@PathVariable Integer id) {
        return new ResponseEntity<>(jobOfferService.findAllPublishedBySpecificEmployer(id), HttpStatus.OK);
    }

    @GetMapping("/job_offers/appliers/{id}")
    public ResponseEntity<List<ApplyRelationship>> findAllAppliersForSpecificJobOffer(@PathVariable Integer id) {
        return new ResponseEntity<>(jobOfferService.findAllAppliersForSpecificJobOffer(id), HttpStatus.OK);
    }

    @GetMapping("/job_offers/applications/{email}")
    public ResponseEntity<List<JobOffer>> findAllAplicationsForWorker(@PathVariable String email) {
        return new ResponseEntity<>(jobOfferService.findAllAplicationsForWorker(email), HttpStatus.OK);
    }

    @DeleteMapping("/job_offers/{id}/{email}")
    public ResponseEntity<Void> removeApplication(@PathVariable Integer id, @PathVariable String email){
        jobOfferService.removeApplication(id, email);
        return new ResponseEntity<Void>( HttpStatus.OK);
    }

}
