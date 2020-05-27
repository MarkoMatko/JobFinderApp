package raf.undergraduate.dissertation.markomatkovic.jobfinder.config.auth;

import io.jsonwebtoken.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import raf.undergraduate.dissertation.markomatkovic.jobfinder.entity.User;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtil implements Serializable {

    public String getUsernameFromToken(String token) {
        final Claims claims = getAllClaimsFromToken(token);
        System.out.println("CLAIMOVI: "+claims);
        return claims.getSubject();
    }

    public Date getExpirationDateFromToken(String token) {
        final Claims claims = getAllClaimsFromToken(token);
        return claims.getExpiration();
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(AuthConstants.SIGNING_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T getClaimFromToken(String token, Function claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return  (T) claimsResolver.apply(claims);
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(User user) {
        return doGenerateToken(user.getEmail(), user.getRoles());
    }

    private String doGenerateToken(String subject, Set<String> roles) {

        Claims claims = Jwts.claims().setSubject(subject);
        List<SimpleGrantedAuthority> authRoles = new ArrayList<>();
        for (String role : roles) {
            authRoles.add(new SimpleGrantedAuthority(role));
        }
        claims.put("scopes", authRoles);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuer("mmatkovic15")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + AuthConstants.ACCESS_TOKEN_VALIDITY_SECONDS*1000))
                .signWith(SignatureAlgorithm.HS256, AuthConstants.SIGNING_KEY)
                .compact();
    }

    public String doGenerateTokenWithRoles(Authentication authentication) {

        final String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));


        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AuthConstants.AUTHORITIES_KEY, authorities)
                .setIssuer("mmatkovic15")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + AuthConstants.ACCESS_TOKEN_VALIDITY_SECONDS*1000))
                .signWith(SignatureAlgorithm.HS256, AuthConstants.SIGNING_KEY)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (
                username.equals(userDetails.getUsername())
                        && !isTokenExpired(token));
    }

    UsernamePasswordAuthenticationToken getAuthentication(final String token, final Authentication existingAuth, final UserDetails userDetails) {

        final JwtParser jwtParser = Jwts.parser().setSigningKey(AuthConstants.SIGNING_KEY);

        final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);

        final Claims claims = claimsJws.getBody();

        final Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AuthConstants.AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }
}
