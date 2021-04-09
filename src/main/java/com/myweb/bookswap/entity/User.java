package com.myweb.bookswap.entity;

import javax.persistence.*;




import javax.validation.GroupSequence;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.myweb.bookswap.validation.AdvanceValidation;
import com.myweb.bookswap.validation.UserEmailExists;
import com.myweb.bookswap.validation.UserIdExists;
import com.myweb.bookswap.validation.ValidEmail;
import com.myweb.bookswap.validation.ValidatePassword;

import java.util.List;





@Entity
@Table(name = "bswapuser")
@ValidatePassword(groups = AdvanceValidation.class)
@GroupSequence({ User.class, AdvanceValidation.class })
public class User {

	@Id
	@Column(name = "username")

	@NotBlank(message = "Please Provide a User Id")
	@UserIdExists(groups = AdvanceValidation.class)
	String userid;
	//https://www.bookcrossing.com/join
	@Column(name = "userno", unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int userno;
	@NotBlank(message = "Firstname is needed")
	String firstname;//fulllname instead of firstname and lastname
	String lastname;
	@Column(name = "email")
	// @Email add later
	@ValidEmail(groups = AdvanceValidation.class)
	//https://github.com/mailcheck/mailcheck can be user later
	@NotEmpty(message = "Please Provide a email")
	@UserEmailExists(message="Account already registered with this email",groups = AdvanceValidation.class)
	String uemail;
	String district;
	int Gender;

	@NotEmpty(message = "Please Enter password")
	@Column(length = 100) 
	String password;
	private boolean enabled;

	@Transient
	@NotEmpty(message = "Please Confirm password",groups=AdvanceValidation.class )
	private String confirmpassword;

	@OneToMany(mappedBy = "bookowner", cascade = CascadeType.ALL)
	List<Book> owned_books;

	@OneToMany(mappedBy = "buser", cascade = CascadeType.ALL)
	List<Roles> roles;


	@OneToMany(mappedBy = "buser", cascade = CascadeType.ALL)
	List<WishList> wishes;

	String loginuserid;

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
		this.uemail = uemail.toLowerCase();
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

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}

	public String getLoginuserid() {
		return loginuserid;
	}

	public void setLoginuserid(String loginuserid) {
		this.loginuserid = loginuserid;
	}

	public List<WishList> getWishes() {
		return wishes;
	}

	public void setWishes(List<WishList> wishes) {
		this.wishes = wishes;
	}
}
