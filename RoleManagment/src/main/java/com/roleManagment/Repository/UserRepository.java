package com.roleManagment.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.roleManagment.Entity.User;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	 
    @Query("SELECT u FROM User u WHERE u.username = :username")
    public User getUserByUsername(@Param("username") String username);
    @Query("SELECT u FROM User u WHERE u.id = :id")
    public Optional<User> findById(Long id);
    public List<User> findAll();
}
