package com.zyter.thermal.service.impl;

import com.zyter.thermal.constants.Constants;
import com.zyter.thermal.entity.Group;
import com.zyter.thermal.entity.Notification;
import com.zyter.thermal.entity.Role;
import com.zyter.thermal.entity.User;
import com.zyter.thermal.repository.GroupRepository;
import com.zyter.thermal.repository.NotificationRepository;
import com.zyter.thermal.repository.RoleRepository;
import com.zyter.thermal.repository.UserRepository;
import com.zyter.thermal.service.ThermalService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service(value = "userService")
public class ThermalServiceImpl<T> implements UserDetailsService, ThermalService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private GroupRepository groupRepository;
	
	@Autowired
	private NotificationRepository notificationRepository;
	
	private static final Logger LOGGER = LogManager.getLogger(ThermalServiceImpl.class);

	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		User user = userRepository.findByUsernameAndSchemaName(userId, Constants.CURRENT_TENANT);
		if (user == null) {
			LOGGER.error("Invalid username or password.");
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		Set<GrantedAuthority> grantedAuthorities = getAuthorities(user);
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
	}
	
	private Set<GrantedAuthority> getAuthorities(User user) {
        List<Role> roleByUserId = user.getRoles();
        final Set<GrantedAuthority> authorities = roleByUserId.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName().toString().toUpperCase())).collect(Collectors.toSet());
        return authorities;
    }
	
	public User createOrUpdateUser(User user) {
		return userRepository.save(user);
	}
	
	public User getUser(Long id) {
		return userRepository.findOne(id);
	}
	
	public User getUser(String username) {
		return userRepository.findByUsernameAndSchemaName(username, Constants.CURRENT_TENANT);
	}
	
	public List<User> getAllUsers() {
		return userRepository.findBySchemaName(Constants.CURRENT_TENANT);
	}
	
	public List<User> getAllUsers(List<String> usernames) {
		return userRepository.findBySchemaNameAndUsernameIn(Constants.CURRENT_TENANT, usernames);
	}
	
	public void deleteUsers(List<User> users) {
		userRepository.deleteInBatch(users);
	}
	
	public Group createOrUpdateGroup(Group group) {
		return groupRepository.save(group);
	}
	
	public Group getGroup(Long id) {
		return groupRepository.findOne(id);
	}
	
	public Group getGroup(String name) {
		return groupRepository.findByName(name);
	}
	
	public List<Group> getAllGroups() {
		return groupRepository.findAll();
	}
	
	public List<Group> getAllGroups(List<Long> ids) {
		return groupRepository.findByIdIn(ids);
	}
	
	public void deleteGroups(List<Group> groups) {
		groupRepository.deleteInBatch(groups);
	}
	
	public List<Notification> getAllNotifications() {
		return notificationRepository.findAll();
	}
	
	public List<Notification> getAllNotifications(List<Integer> ids) {
		return notificationRepository.findByIdIn(ids);
	}
	
	public List<Role> getAllRoles() {
		return roleRepository.findAll();
	}
	
	public List<Role> getRoles(List<Integer> ids) {
		return roleRepository.findByIdIn(ids);
	}
	
	public List<Group> getUserGroupIds(String username) {
		return userRepository.findUserGroupIds(username);
	}
}
