package raf.undergraduate.dissertation.markomatkovic.jobfinder.service;

import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.Employer;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.EvaluateRelationship;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.model.EvaluateRequest;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.model.FollowCheckRequest;

import java.util.Collection;
import java.util.List;

public interface EmployerService {

    Collection<Employer> findAllEmployers();
    Employer saveNewEmployer(Employer employer);
    void deleteEmployerById(Long id);
    Employer findEmployerById(Long id);
    EvaluateRelationship createEvaluation(EvaluateRequest evaluateRequest);
    List<Employer> searchForEmployers(String search);
    List<EvaluateRelationship> getAllEvaluationsForEmloyerId(Integer id);
    boolean followCheck(FollowCheckRequest followCheckRequest);
    Employer findEmployerByEmail(String email);
}
