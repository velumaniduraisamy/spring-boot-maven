package com.zyter.thermal.entity;

import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    
    @Column(name = "USERNAME", nullable = false)
    private String username;
   
    @Column(name = "PASSWORD", nullable = false)
    private String password;
    
    @Column(name = "FIRSTNAME", nullable = false)
    private String firstName;
    
    @Column(name = "LASTNAME", nullable = false)
    private String lastName;
    
    @Column(name = "EMAIL", nullable = true)
    private String email;
    
    @Column(name = "MOBILE", nullable = true)
    private String mobile;
    
    @Column(name = "PROFILE_PHOTO", nullable = true)
    private String profilePhoto;
    
    @Column(name = "SCHEMA_NAME", nullable = false)
    private String schemaName;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLES", joinColumns=@JoinColumn(name="USER_ID"), inverseJoinColumns=@JoinColumn(name="ROLE_ID"))
    private List<Role> roles;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_NOTIFICATIONS", joinColumns=@JoinColumn(name="USER_ID"), inverseJoinColumns=@JoinColumn(name="NOTIFICATION_ID"))
    private List<Notification> notifications;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(name = "USER_GROUPS", joinColumns=@JoinColumn(name="USER_ID"), inverseJoinColumns=@JoinColumn(name="GROUP_ID"))
    private List<Group> groups;
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}
}
