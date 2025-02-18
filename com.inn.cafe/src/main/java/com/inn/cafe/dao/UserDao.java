package com.inn.cafe.dao;

import com.inn.cafe.pojo.User;
import jakarta.persistence.NamedQuery;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDao extends JpaRepository<User,Integer> {
    User findByEmailId(@Param("email") String email);
    @Query("UPDATE User u SET u.password = :password WHERE u.email = :email")
    @Transactional
    @Modifying
    void updateUserPassword(@Param("password") String password,@Param("email") String email);

}
