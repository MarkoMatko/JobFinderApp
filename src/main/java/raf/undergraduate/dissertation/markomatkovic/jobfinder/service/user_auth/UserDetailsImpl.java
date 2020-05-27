package raf.undergraduate.dissertation.markomatkovic.jobfinder.service.user_auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.User;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * This class is implementation of  UserDetails interface (interface form spring security framework).
 *  This class represent user informations that are later encapsulated into Authentication objects.
 *  This class represent non-security related user information (such as email addresses, telephone numbers etc)
 *  to be stored in a convenient location.
 */
public class UserDetailsImpl implements UserDetails {

    /**
     * Our user from database
     */
    private User user;
    public UserDetailsImpl(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        Set<String> roles = this.user.getRoles();
        for (String role : roles) {
            System.out.println("OVO SU ROLES: " + role);
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
