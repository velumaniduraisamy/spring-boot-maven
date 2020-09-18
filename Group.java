package com.zyter.thermal.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "GROUP_LIST")
public class Group {

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "NAME", nullable = false)
    private String name;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_GROUPS", joinColumns=@JoinColumn(name="GROUP_ID"), inverseJoinColumns=@JoinColumn(name="USER_ID"))
    private List<User> users;
    
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
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
}
