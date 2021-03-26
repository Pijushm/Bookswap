package com.myweb.bookswap.entity;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class ConfirmationToken {
	private static final int EXPIRATION = 1;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="token_id")
	private long tokenid;
	
	@Column(name="confimation_token")
	private String confirmationToken;
	
	
	private Date expiryDate;
	
	
	@OneToOne(targetEntity = User.class,fetch=FetchType.EAGER)
	@JoinColumn(nullable = false,name="user_id")
	private User user;
	
	
	
	
	
	public ConfirmationToken() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ConfirmationToken(final String token) {
        
        
        this.confirmationToken = token;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
        
    }
	
	
	public ConfirmationToken(final String token,User user) {
        this.user = user;
        expiryDate = calculateExpiryDate(EXPIRATION);
        confirmationToken = token;
    }


	public long getTokenid() {
		return tokenid;
	}


	public void setTokenid(long tokenid) {
		this.tokenid = tokenid;
	}


	public String getConfirmationToken() {
		return confirmationToken;
	}


	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}


	


	public Date getExpiryDate() {
		return expiryDate;
	}


	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	

	  private Date calculateExpiryDate(final int expiryTimeInMinutes) {
	        final Calendar cal = Calendar.getInstance();
	        cal.setTimeInMillis(new Date().getTime());
	        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
	        return new Date(cal.getTime().getTime());
	    }
	
	
	
}
