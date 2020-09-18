package com.zyter.thermal.model;

import java.util.List;

public class GroupVO {

    private long id;

    private String groupName;
    
    private String admin;
    
    private String adminEmail;
    
    private String adminMobile;
    
    private String totalMembers;
    
    private String groupIds;
    
    private String userNames;
    
    private List<UserVO> users;
    
    /*@Column(name = "CREATED_ON")
    private Long createdOn;
    
    @Column(name = "UPDATED_ON")
    private Long updatedOn;*/
	
    public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public String getAdminMobile() {
		return adminMobile;
	}
	public void setAdminMobile(String adminMobile) {
		this.adminMobile = adminMobile;
	}
	public String getTotalMembers() {
		return totalMembers;
	}
	public void setTotalMembers(String totalMembers) {
		this.totalMembers = totalMembers;
	}
	public String getGroupIds() {
		return groupIds;
	}
	public void setGroupIds(String groupIds) {
		this.groupIds = groupIds;
	}
	
	public String getAdminEmail() {
		return adminEmail;
	}
	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}
	public List<UserVO> getUsers() {
		return users;
	}
	public void setUsers(List<UserVO> users) {
		this.users = users;
	}
	public String getUserNames() {
		return userNames;
	}
	public void setUserNames(String userNames) {
		this.userNames = userNames;
	}
	
	/*public Long getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Long createdOn) {
		this.createdOn = createdOn;
	}
	public Long getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Long updatedOn) {
		this.updatedOn = updatedOn;
	}*/
}
