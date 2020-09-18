package com.zyter.thermal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zyter.thermal.entity.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
	Group findByName(String name);
	List<Group> findByIdIn(List<Long> ids);
}
