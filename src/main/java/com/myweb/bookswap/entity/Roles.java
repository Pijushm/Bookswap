package com.myweb.bookswap.entity;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "authorities")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;


    @ManyToOne
    @JoinColumn(name = "username")
    User buser;



    private String role="ROLE_USER";

   
    public Roles()
    {
    	
    }
    
    public Roles(String role) {
      this.role=role;
    }

    public User getBuser() {
        return buser;
    }

    public void setBuser(User buser) {
        this.buser = buser;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
