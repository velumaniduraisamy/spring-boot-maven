package com.zyter.thermal.service;

import java.util.List;

import com.zyter.thermal.entity.Group;
import com.zyter.thermal.entity.Notification;
import com.zyter.thermal.entity.Role;
import com.zyter.thermal.entity.User;

public interface ThermalService {
	User createOrUpdateUser(User user);
	List<User> getAllUsers();
	List<Role> getAllRoles();
	List<Notification> getAllNotifications();
	List<User> getAllUsers(List<String> usernames);
	void deleteUsers(List<User> user);
	Group createOrUpdateGroup(Group group);
	List<Group> getAllGroups();
	void deleteGroups(List<Group> groups);
	List<Group> getAllGroups(List<Long> ids);
	List<Role> getRoles(List<Integer> ids);
	List<Notification> getAllNotifications(List<Integer> ids);
	User getUser(Long id);
	User getUser(String username);
	List<Group> getUserGroupIds(String email);
	Group getGroup(Long id);
	Group getGroup(String name);
}
