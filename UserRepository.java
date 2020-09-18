package com.zyter.thermal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.zyter.thermal.entity.Group;
import com.zyter.thermal.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findBySchemaName(String schemaName);
	
    User findByUsernameAndSchemaName(String username, String schemaName);
    
    List<User> findBySchemaNameAndUsernameIn(String schemaName, List<String> usernames);
    
    @Query("SELECT us.groups FROM User us where us.username = :username") 
	public List<Group> findUserGroupIds(@Param("username") String username);
}
