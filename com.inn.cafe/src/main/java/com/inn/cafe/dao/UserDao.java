package com.inn.cafe.dao;

import com.inn.cafe.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserDao extends JpaRepository<User,Integer> {
    //JpaRepository<User,Integer>
    // User(first parameter) : means This represents the type of the entity that the repository will manage.
    // Typically, this would be a Java class that maps to a table in a database.

    //Integer (the second type parameter):
    // This represents the type of the ID field of the entity.
    // In this case, the entity's ID field is of type Integer.
    // This means the primary key of the entity (the identifier) is an integer.
    User findByEmailId(@Param("email") String email);

}
