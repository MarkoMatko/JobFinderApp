package raf.undergraduate.dissertation.markomatkovic.jobfinder.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.model.AuthTokenWithUserInfo;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.model.LoginUser;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.service.user_auth.UserService;

//@CrossOrigin(origins = ResourceConstants.CLIENT_URL, maxAge = 360)
@RestController
@RequestMapping(ResourceConstants.AUTH_RESOURCE)
@CrossOrigin(origins = "*", maxAge = 360)
public class AuthResource {

    @Autowired
    private UserService userService;

    @PostMapping("/generate-token")
    public ResponseEntity<AuthTokenWithUserInfo> logInUser(@RequestBody LoginUser loginUser) throws AuthenticationException {

        AuthTokenWithUserInfo toReturn = userService.logInUser(loginUser);
        if(toReturn == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        else {
            return new ResponseEntity<AuthTokenWithUserInfo>(toReturn, HttpStatus.OK);
        }

    }


}
