package com.myweb.bookswap.entity;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "authorities")
public class Roles {

    @Id
    int id;


    @ManyToOne
    @JoinColumn(name = "username")
    User buser;



    private String role="ROLE_ADMIN";

    public Roles() {
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
