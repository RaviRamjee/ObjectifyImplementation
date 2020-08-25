package com.obj;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.googlecode.objectify.ObjectifyService;
import com.obj.bean.User;
import com.obj.model.UserImpl;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		/**
	    getting the string value from the html form 
	    if==> comparing the string which is comming from the html form  for update action     
	    else if ==> comparing the string which is comming from the html form for delete action 
	    else ==> fetching user data 
	 */
		String req=request.getParameter("put");
		if(req.equals("update")) 
		{
			this.doPut(request, response); //calling doPut method for update
		}
		else if(req.equals("delete")) 
		{
			this.doDelete(request, response); //calling doDolete method for delete user data
		}else
		{
			/**
		      extracting the user details from datastore
		 */
		User u=new User();
		String id=request.getParameter("Id");
		UserImpl uImpl = new UserImpl();
		u=uImpl.getUserData(id);
		
		PrintWriter out=response.getWriter();
		out.print(u);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

		Datastore datastore = DatastoreOptions.newBuilder().setProjectId("bike-rent-system").build().getService();

		UserImpl uImpl = new UserImpl();

		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String phone =request.getParameter("phoneNo");
		String address = request.getParameter("address");

		String myHash = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] digest = md.digest();
			myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
			System.out.println(myHash);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		User user = new User();
		user.setUserId(id);
		user.setUserName(name);
		user.setUserEmail(email);
		user.setUserPassword(myHash);
		user.setUserPhoneNo(phone);
		user.setUserAddress(address);
		uImpl.save(user);
		PrintWriter out=response.getWriter();
		out.print("Inserted succesfully");

	}
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String id = request.getParameter("Id");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String phone = request.getParameter("phoneNo");
		String address = request.getParameter("address");
		
		String myHash = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] digest = md.digest();
			myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
			System.out.println(myHash);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		User user = ofy().load().type(User.class).id(id).now();
			user.setUserName(name);
			user.setUserEmail(email);
			user.setUserPassword(myHash);
			user.setUserPhoneNo(phone);
			user.setUserAddress(address);
			
		ofy().save().entity(user).now();
		
		PrintWriter out=response.getWriter();
		out.print("Updated succesfully");
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		String id=request.getParameter("Id");
		UserImpl uImpl = new UserImpl();
		
		uImpl.deleteUserdata(id);
		
		PrintWriter out=response.getWriter();
		out.print("Deleted succesfully");
		
	}

}
