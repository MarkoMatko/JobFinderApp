package raf.undergraduate.dissertation.markomatkovic.jobfinder.service.user_auth;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.Employer;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.User;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.Worker;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.model.AuthTokenWithUserInfo;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.model.EmployerRequest;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.model.LoginUser;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.model.WorkerRequest;

import java.util.Collection;

public interface UserService {

    Collection<User> findAllUsers();
    User saveNewUser(User user);
    void deleteUserById( Long id);
    AuthTokenWithUserInfo logInUser(LoginUser loginUser) throws AuthenticationException;
    Worker registerWorker(User user, WorkerRequest workerRequest);
    Employer registerEmployer(User user, EmployerRequest employerRequest);
}
