package com.myweb.bookswap.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "bswapuser")
public class User {

    @Id
    @Column(name = "username")

    String userid;
    @Column(name="userno", unique=true)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int userno;
    String firstname;
    String lastname;
    @Column(name="email")
    //@Email add later
    String uemail;
    String district;
    int Gender;
    String password;
    private boolean enabled;


    @OneToMany(mappedBy = "bookowner", cascade = CascadeType.ALL)
    List<Book> owned_books;


    @OneToMany(mappedBy = "buser",cascade = CascadeType.ALL)
    List<Roles> roles;

    public User() {
    }

    
    
    
    
    
    public String getUserid() {
		return userid;
	}



	public void setUserid(String userid) {
		this.userid = userid;
	}






	public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

  

    public String getUemail() {
		return uemail;
	}


	public void setUemail(String uemail) {
		this.uemail = uemail;
	}



	public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getGender() {
        return Gender;
    }

    public void setGender(int gender) {
        Gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Book> getOwned_books() {
        return owned_books;
    }

    public void setOwned_books(List<Book> owned_books) {
        this.owned_books = owned_books;
    }

    public String getfullName() {
        return this.firstname + " " + this.lastname;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

	public int getUserno() {
		return userno;
	}

	public void setUserno(int userno) {
		this.userno = userno;
	}


	public List<Roles> getRoles() {
		return roles;
	}


	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}
    
    
	
    
}
