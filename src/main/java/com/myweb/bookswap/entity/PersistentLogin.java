package com.myweb.bookswap.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
//This combination of randomly generated series and token are persisted, making a brute force attack very unlikely.
@Entity
@Table(name="persistent_logins")
public class PersistentLogin {

	@Id
	@Column(name = "SERIES")
	private String series;

	@Column(name = "USERNAME", nullable = false)
	private String username;

	@Column(name = "TOKEN", nullable = false)
	private String token;

	@Column(name = "LAST_USED", nullable = false)
	private Date lastUsed;

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getLastUsed() {
		return lastUsed;
	}

	public void setLastUsed(Date lastUsed) {
		this.lastUsed = lastUsed;
	}

}
