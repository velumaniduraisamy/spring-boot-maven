package com.zyter.thermal.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import com.zyter.thermal.constants.Constants;
import com.zyter.thermal.entity.Group;
import com.zyter.thermal.entity.Notification;
import com.zyter.thermal.entity.User;
import com.zyter.thermal.exception.ThermalException;
import com.zyter.thermal.model.GroupVO;
import com.zyter.thermal.model.SuccessResponse;
import com.zyter.thermal.model.UserVO;
import com.zyter.thermal.service.ThermalService;

@RestController
@RequestMapping("/Group")
public class GroupController {
	private static final Logger LOGGER = LogManager.getLogger(GroupController.class);
	
	@Autowired
	private ThermalService thermalService;
	
	/**
     * Create / Update Group.
     *
	 * @throws ThermalException 
     * 
     */
	@Secured({Constants.ROLE_ADMIN})
	@PostMapping(value = "/CreateOrUpdateGroup")
	public ResponseEntity<SuccessResponse> createGroup(@RequestBody GroupVO groupVo) throws ThermalException {
		LOGGER.info("inside CreateOrUpdateGroup");
		SuccessResponse response = new SuccessResponse();
		try{
			if(groupVo.getGroupName() == null || groupVo.getGroupName().isEmpty()) {
				throw new ThermalException("Group name cannot be null");
			}else if(groupVo.getUserNames() == null || groupVo.getUserNames().isEmpty()) {
				throw new ThermalException("Atleast one username is required");
			}
			Group group;
			if(groupVo.getId() > 0) {
				group = thermalService.getGroup(groupVo.getId());
				if(group == null) {
					throw new ThermalException("Group is not available");
				}
			}else {
				group = thermalService.getGroup(groupVo.getGroupName());
				if(group != null) {
					throw new ThermalException("Duplicate entry");
				}
			}
			group = new Group();
			List<String> list = Stream.of(groupVo.getUserNames().split(",")).map(String::toString).collect(Collectors.toList());
			List<User> users = thermalService.getAllUsers(list);
			
			group.setId(groupVo.getId());
			group.setUsers(users);
			group.setName(groupVo.getGroupName());
			group = thermalService.createOrUpdateGroup(group);
			
			response.setMessage(Constants.SUCCESS);
		} catch (ThermalException ex) {
			LOGGER.error(ex.getMessage());
			throw new ThermalException(ex.getErrorMessage());
		} catch(Exception ex){
			LOGGER.error(ex);
			throw new ThermalException("Internal Server Error");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/**
     * If list of Ids are passed in the request, those related groups only will be retrieved.
     * otherwise all groups will be retrieved
     *
	 * @throws ThermalException 
     * 
     */
	@RequestMapping(value = "/GetAllGroups", method = RequestMethod.POST)
	public ResponseEntity<List<GroupVO>> getAllGroups(@RequestBody(required = false) GroupVO groupVo) throws ThermalException {
		LOGGER.info("inside getAllGroups");
		List<Group> groups = null;
		List<GroupVO> groupsVO = new ArrayList<>();
		try{
			if(groupVo == null || groupVo.getGroupIds() == null || groupVo.getGroupIds().isEmpty()) {
				groups = thermalService.getAllGroups();
			}else {
				LOGGER.info("Group Ids : " + groupVo.getGroupIds());
				List<Long> longList = Stream.of(groupVo.getGroupIds().split(",")).map(Long::parseLong).collect(Collectors.toList());
				groups = thermalService.getAllGroups(longList);
			}
			
			for(Group group : groups) {
				groupVo = new GroupVO();
				groupVo.setId(group.getId());
				groupVo.setGroupName(group.getName());
				List<User> users = group.getUsers();
				
				if(users!=null && !users.isEmpty()) {
					groupVo.setAdminEmail(users.get(0).getEmail());
					groupVo.setAdmin(users.get(0).getFirstName() + ", " + users.get(0).getLastName());
					groupVo.setAdminMobile(users.get(0).getMobile());
					UserVO userVo = new UserVO();
					List<UserVO> list = new ArrayList<UserVO>();
					for(User user : group.getUsers()) {
						userVo = new UserVO();
						userVo.setUserName(user.getUsername());
						userVo.setEmail(user.getEmail());
						userVo.setMobile(user.getMobile());
						userVo.setFirstName(user.getFirstName());
						userVo.setLastName(user.getLastName());
						String notifiNames = user.getNotifications().stream().map(Notification::getName).collect(Collectors.joining(","));
						userVo.setNotifications(notifiNames);						
						list.add(userVo);
					}
					groupVo.setUsers(list);
				}
				groupVo.setTotalMembers(String.valueOf(group.getUsers().size()));
				groupsVO.add(groupVo);
			}
		} catch(Exception ex){
			LOGGER.error(ex);
			throw new ThermalException("Internal Server Error");
		}
		return new ResponseEntity<>(groupsVO, HttpStatus.OK);
	}
	
	/**
     * Delete the groups.
     *
	 * @throws ThermalException 
     * 
     */
	@Secured({Constants.ROLE_ADMIN})
	@RequestMapping(value = "/DeleteGroups", method = RequestMethod.POST)
	public ResponseEntity<SuccessResponse> deleteGroups(@RequestBody GroupVO groupVo) throws ThermalException {
		SuccessResponse response = new SuccessResponse();
		LOGGER.info("inside deleteGroups");
		try{
			if(groupVo == null || groupVo.getGroupIds() == null || groupVo.getGroupIds().isEmpty()) {
				throw new ThermalException("Group Ids cannot be empty");
			}
			List<Long> list = Stream.of(groupVo.getGroupIds().split(",")).map(Long::parseLong).collect(Collectors.toList());
			List<Group> groups = thermalService.getAllGroups(list);
			thermalService.deleteGroups(groups);
			response.setMessage(Constants.SUCCESS);
		} catch(Exception ex){
			LOGGER.error(ex);
			throw new ThermalException("Internal Server Error");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@Secured({Constants.ROLE_ADMIN, Constants.ROLE_USER})
	@RequestMapping(value = "/GetUserGroupIds", method = RequestMethod.POST)
	public ResponseEntity<List<Long>> GetUserGroupIds(@RequestBody UserVO userVO) throws ThermalException {
		LOGGER.info("inside GetUserGroupIds");
		
		List<Long> response = new ArrayList<>();
		
		try{
			
			if(userVO.getUserName()==null || userVO.getUserName().trim().length() <=0){
				
				throw new ThermalException("User's username is required");
			}
			
			List<Group> groups  = thermalService.getUserGroupIds(userVO.getUserName());
			
			if(groups!=null && groups.size() > 0){
				
				for(Group group : groups){
					response.add(group.getId());
				}
			}
			
		} catch(ThermalException ex){
			LOGGER.error(ex);
			ex.printStackTrace();
			throw new ThermalException(ex.getMessage());
		} catch(Exception ex){
			LOGGER.error(ex);
			throw new ThermalException("Internal Server Error");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}