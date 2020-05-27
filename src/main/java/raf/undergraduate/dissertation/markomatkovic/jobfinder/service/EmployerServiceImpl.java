package raf.undergraduate.dissertation.markomatkovic.jobfinder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.Employer;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.EvaluateRelationship;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.Worker;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.model.EvaluateRequest;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.model.FollowCheckRequest;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.repository.EmployerRepository;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.repository.EvaluateRelationshipRepository;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.repository.WorkerRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Service class for reading,deleting,adding,updating,... Employers.
 */
@Service
public class EmployerServiceImpl implements EmployerService {

    private EmployerRepository employerRepository;
    private WorkerRepository workerRepository;
    private EvaluateRelationshipRepository evaluateRelationshipRepository;

    @Autowired
    public EmployerServiceImpl(EmployerRepository employerRepository,
                               WorkerRepository workerRepository,
                               EvaluateRelationshipRepository evaluateRelationshipRepository){
        this.employerRepository = employerRepository;
        this.workerRepository = workerRepository;
        this.evaluateRelationshipRepository = evaluateRelationshipRepository;
    }

    //TODO NEED TO BE IMPLEMENTED
    @Override
    @Transactional
    public Collection<Employer> findAllEmployers() {
        return null;
    }

    /**
     * Saves new employer to database.
     * @param employer to be saved
     * @return saved employer with his database id
     */
    @Override
    @Transactional
    public Employer saveNewEmployer(Employer employer) {
        return employerRepository.save(employer);
    }

    /**
     * Delete employer with id.
     * @param id of employer to be deleted
     */
    @Override
    @Transactional
    public void deleteEmployerById(Long id) {
        employerRepository.deleteById(id);
    }

    /**
     * Find employer with specific id.
     * @param id of employer
     * @return employer with specific id or null if employer with specific id doesn't exist
     */
    @Override
    @Transactional
    public Employer findEmployerById(Long id) {
        Optional<Employer> employerOpt = employerRepository.findById(id);

        //check weather result is present
        if(employerOpt.isPresent()){
            return employerOpt.get();
        }
        //TODO HANDLOVATI EXCEPTION U SUPROTNOM
        return null;
    }

    /**
     * Create EvaluateRelationship object using EvaluateRequest object and after that, create
     * evaluate relation between specified worker and specified employer
     * @param evaluateRequest represent object that contains ids of both sides of relation and other properties
     *               We use this object to construct real relationship object
     */
    @Override
    @Transactional
    public EvaluateRelationship createEvaluation(EvaluateRequest evaluateRequest) {
        Optional<Worker> workerOpt = workerRepository.findByEmail(evaluateRequest.getWorkerEmail());
        Optional<Employer> employerOpt = employerRepository.findById(evaluateRequest.getEmployerId());

        if(workerOpt.isPresent() && employerOpt.isPresent()){
            EvaluateRelationship evaluateRelationship = new EvaluateRelationship(evaluateRequest.getWorkerExperience(),
                    evaluateRequest.getRecommendation(),evaluateRequest.getEmployerRate(),
                    evaluateRequest.isStillWorkingForThisEmployer(),workerOpt.get(),employerOpt.get());
            employerOpt.get().addEvaluation(evaluateRelationship);
            employerRepository.save(employerOpt.get());
            return evaluateRelationship;
        }
        else {
            //TODO U SLUCAJU GRESKE
            return null;
        }
    }

    @Override
    @Transactional
    public List<Employer> searchForEmployers(String search) {
        String regExSearch = "(?i).*" + search + ".*";
        List<Employer> searchedEmployers = this.employerRepository.searchForEmployers(regExSearch);
        if(searchedEmployers.isEmpty()) {
            System.out.println("Nije nista nasao");
            return null;
        }
        System.out.println(searchedEmployers);
        return searchedEmployers;
    }

    @Override
    @Transactional
    public List<EvaluateRelationship> getAllEvaluationsForEmloyerId(Integer id) {
        List<EvaluateRelationship> evaluations = this.evaluateRelationshipRepository.getAllEvaluationsForEmployerId(id);
        if(evaluations.isEmpty())
            return null;
        return evaluations;
    }

    @Override
    @Transactional
    public boolean followCheck(FollowCheckRequest followCheckRequest) {
        Optional<Employer> e = employerRepository.followCheck(followCheckRequest.getWorkerEmail(), followCheckRequest.getEmployerId().intValue());
        if (e.isPresent()) return true;
        return false;
    }

    /**
     * Find employer with specific email.
     * @param email of employer
     * @return employer with specific email or null if employer with specific email doesn't exist
     */
    @Override
    @Transactional
    public Employer findEmployerByEmail(String email) {
        Optional<Employer> employerOpt = employerRepository.findByEmail(email);

        //check weather result is present
        if(employerOpt.isPresent()){
            return employerOpt.get();
        }
        //TODO HANDLOVATI EXCEPTION U SUPROTNOM
        return null;
    }

}
