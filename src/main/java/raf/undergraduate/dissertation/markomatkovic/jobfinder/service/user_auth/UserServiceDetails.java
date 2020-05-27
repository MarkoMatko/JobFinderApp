package raf.undergraduate.dissertation.markomatkovic.jobfinder.service.user_auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.User;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.repository.UserRespository;

@Service
public class UserServiceDetails implements UserDetailsService {

    private UserRespository userRespository;

    @Autowired
    public UserServiceDetails(UserRespository userRepository) {
        this.userRespository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRespository.findByEmail(email).get();
        if(user == null){
            throw new UsernameNotFoundException(email);
        }
        return new UserDetailsImpl(user);
    }
}
