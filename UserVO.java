package com.zyter.thermal.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zyter.thermal.entity.Notification;

public class UserVO {
	
	private long id;
	 
	private String name;
	
	private String firstName;
	    
	private String lastName;
	
	private String userName;

	private String password;
	
	private String email;
	
	private String mobile;
	
	private String profilePhoto;
	
	private String userNames;
	
	private String roleIds;
	
	private String role;

	private String groupIds;
	
	private String groupNames;
	
	private String notificationIds;
	
	private String notifications;
	
	private boolean includeProfilePhoto=true;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getGroupNames() {
		return groupNames;
	}

	public void setGroupNames(String groupNames) {
		this.groupNames = groupNames;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNotifications() {
		return notifications;
	}

	public void setNotifications(String notifications) {
		this.notifications = notifications;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getGroupIds() {
		return groupIds;
	}

	public void setGroupIds(String groupIds) {
		this.groupIds = groupIds;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getNotificationIds() {
		return notificationIds;
	}

	public void setNotificationIds(String notificationIds) {
		this.notificationIds = notificationIds;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNames() {
		return userNames;
	}

	public void setUserNames(String userNames) {
		this.userNames = userNames;
	}

	public String getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

	public boolean isIncludeProfilePhoto() {
		return includeProfilePhoto;
	}

	public void setIncludeProfilePhoto(boolean includeProfilePhoto) {
		this.includeProfilePhoto = includeProfilePhoto;
	}
}