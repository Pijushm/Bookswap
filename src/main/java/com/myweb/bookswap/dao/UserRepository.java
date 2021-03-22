package com.myweb.bookswap.dao;

import com.myweb.bookswap.entity.Roles;
import com.myweb.bookswap.entity.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,String> {

	
	User findByUserno(int userno);
	Optional<User> findByUemail(String email);
	Optional<User> findByUseridOrUemail(String userid,String email);
	 @Query("select r from Roles r where r.buser.userid=:userId")
	List<Roles> getUserRoles(@Param("userId")String userId);
}
