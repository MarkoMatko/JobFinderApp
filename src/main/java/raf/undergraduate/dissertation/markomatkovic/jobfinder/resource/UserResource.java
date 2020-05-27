package raf.undergraduate.dissertation.markomatkovic.jobfinder.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.Employer;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.User;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.Worker;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.model.EmployerRequest;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.model.WorkerRequest;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.service.user_auth.UserService;

import java.util.Collection;

/**
 * Rest endpoint for user with his credentials.
 */
@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = ResourceConstants.CLIENT_URL)
@CrossOrigin(origins = "*", maxAge = 360)
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public Collection<User> getUsers(){
        return userService.findAllUsers();
    }

    @PostMapping("/user/register_worker")
    public ResponseEntity<Worker> registerWorker(@RequestBody WorkerRequest workerRequest){

        Worker worker = userService.registerWorker(workerRequest.getCredentials(), workerRequest);
        if(worker == null) return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(null);
        if(worker.getFirstName() == null) return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        return new ResponseEntity<>(worker, HttpStatus.CREATED);
    }

    @PostMapping("/user/register_employer")
    public ResponseEntity<Employer> registerEmployer(@RequestBody EmployerRequest employerRequest){

        Employer employer = userService.registerEmployer(employerRequest.getCredentials(), employerRequest);
        if(employer == null) return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(null);
        if(employer.getName() == null)  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        return new ResponseEntity<>(employer, HttpStatus.CREATED);
    }
    @PostMapping("/users")
    public ResponseEntity<User> saveNewUser(@RequestBody User user){

        User u = userService.saveNewUser(user);
        if(u != null) return new ResponseEntity<>(u, HttpStatus.CREATED);
        else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
