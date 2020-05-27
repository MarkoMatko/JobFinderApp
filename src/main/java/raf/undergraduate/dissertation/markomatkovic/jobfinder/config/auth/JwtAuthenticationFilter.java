package raf.undergraduate.dissertation.markomatkovic.jobfinder.config.auth;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    @Qualifier("userServiceDetails")
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtToken;


    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        String header = req.getHeader(AuthConstants.HEADER_STRING);
        String email = null;
        String authToken = null;

        if(header != null && header.startsWith(AuthConstants.TOKEN_PREFIX)) {
            authToken = header.replace(AuthConstants.TOKEN_PREFIX,"");

            try {
                email = jwtToken.getUsernameFromToken(authToken);
            } catch (IllegalArgumentException e) {
                logger.error("an error occured during getting email from token", e);
            } catch (ExpiredJwtException e) {
                logger.warn("the token is expired and not valid anymore", e);
            } catch(SignatureException e){
                logger.error("Authentication Failed. Email or Password not valid.");
            }
        }
        else{
            logger.warn("couldn't find bearer string, will ignore the header");
        }
        if(email != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);

            if(jwtToken.validateToken(authToken, userDetails)) {

                UsernamePasswordAuthenticationToken authentication =
                        jwtToken.getAuthentication(authToken, SecurityContextHolder.getContext().getAuthentication(), userDetails);

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                logger.info("authenticated user " + email + ", setting security context");
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(req,res);
    }
}
