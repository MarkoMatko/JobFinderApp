package raf.undergraduate.dissertation.markomatkovic.jobfinder.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.Employer;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.EvaluateRelationship;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.model.EvaluateRequest;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.model.FollowCheckRequest;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.service.EmployerService;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = ResourceConstants.CLIENT_URL)
public class EmployerResource {

    @Autowired
    private EmployerService employerService;

    @PostMapping("/employers")
    public ResponseEntity<Employer> saveEmployer(@RequestBody Employer employer){
        return new ResponseEntity<>(employerService.saveNewEmployer(employer),HttpStatus.CREATED);
    }

    @GetMapping("/employers/{id}")
    public ResponseEntity<Employer> findEmployerWithSpecificId(@PathVariable Long id){
        return new ResponseEntity<>(employerService.findEmployerById(id), HttpStatus.OK);
    }

    @GetMapping("/employers/find/{email}")
    public ResponseEntity<Employer> findEmployerWithSpecificId(@PathVariable String email){
        return new ResponseEntity<>(employerService.findEmployerByEmail(email), HttpStatus.OK);
    }


    @GetMapping("/employers/search/{search}")
    public ResponseEntity<List<Employer>> searchForEmployers(@PathVariable String search) {
        return new ResponseEntity<>(employerService.searchForEmployers(search), HttpStatus.OK);
    }

    @PutMapping("/employers/evaluate")
    public ResponseEntity<EvaluateRelationship> evaluateEmployer(@RequestBody EvaluateRequest evaluateRequest){
        EvaluateRelationship e = employerService.createEvaluation(evaluateRequest);
        return new ResponseEntity<>(e, HttpStatus.CREATED);
    }

    @GetMapping("/employers/evaluations/{id}")
    public ResponseEntity<List<EvaluateRelationship>> getAllEvaluationsForEmployerId(@PathVariable Integer id){
        return new ResponseEntity<>(this.employerService.getAllEvaluationsForEmloyerId(id), HttpStatus.OK);
    }

    @PostMapping("/employers/follow_check")
    public ResponseEntity<Boolean> followCheck(@RequestBody FollowCheckRequest followCheckRequest) {
        return new ResponseEntity<>(employerService.followCheck(followCheckRequest), HttpStatus.OK);
    }

}
