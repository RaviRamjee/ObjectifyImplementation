package com.obj.model;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.obj.bean.User;

public class UserImpl {

	// saving user data
	public void save(User u) {
		ofy().save().entity(u).now();
	}
	public void save() {
        User user = new User();
        user.setUserId("one");
		user.setUserName("Ravi");
		user.setUserEmail("ravi@gmail.com");
		user.setUserPassword("12345");
		user.setUserPhoneNo("8318498844");
		user.setUserAddress("Banglore");
		ofy().save().entity(user).now();
	}

	// extracting user data
	public User getUserData(String id) {
		User getUser = ofy().load().type(User.class).id(id).now();
		System.out.println(getUser.toString());
		return getUser;
	}
	
	public void getUserByFilter(User u)
	{
		//User filter = ofy().load().type(User.class).filter("userId =",u.getUserId()).first().now();
		//System.out.println(filter);
	}

	// updating some data
	public void upadateUserData(User u) {
		
		ofy().save().entity(u).now();
	}

	// deleting user data
	public void deleteUserdata(String id) {
		User u = ofy().load().type(User.class).id(id).now();
		ofy().delete().entity(u).now();
	}

}
