package com.myweb.bookswap.dao;

import com.myweb.bookswap.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListRepository extends JpaRepository<WishList,Integer> {


}
