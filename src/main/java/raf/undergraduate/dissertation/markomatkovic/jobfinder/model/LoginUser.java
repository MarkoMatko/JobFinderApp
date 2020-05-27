package raf.undergraduate.dissertation.markomatkovic.jobfinder.model;

/**
 * This class represent informations that we receive from login form from client (frontend).
 */
public class LoginUser {

    private String email;
    private String password;



    public LoginUser() {
        super();
    }
    public LoginUser(String email, String password) {
        super();
        this.email = email;
        this.password = password;
    }
    public String getEmail() {	return email;}
    public void setEmail(String email) {	this.email = email;}
    public String getPassword() {	return password;}
    public void setPassword(String password) {this.password = password;}



}
