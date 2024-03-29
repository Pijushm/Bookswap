package com.myweb.bookswap.entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
public class PasswordResetToken {

	private static final int EXPIRATION = 60 * 24;
	 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tokenid;
 
    private String token;
 
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
 
    private Date expiryDate;
    

	
	
	public PasswordResetToken() {
		super();
		
	}


	public PasswordResetToken(final String token) {
        
        
        this.token = token;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
        
    }
	
	
	public PasswordResetToken(final String token,User user) {
        this.user = user;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
        this.token = token;
    }


	

	  public Long getTokenid() {
		return tokenid;
	}


	public void setTokenid(Long tokenid) {
		this.tokenid = tokenid;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Date getExpiryDate() {
		return expiryDate;
	}


	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}


	private Date calculateExpiryDate(final int expiryTimeInMinutes) {
	        final Calendar cal = Calendar.getInstance();
	        cal.setTimeInMillis(new Date().getTime());
	        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
	        return new Date(cal.getTime().getTime());
	    }
	
    
}
