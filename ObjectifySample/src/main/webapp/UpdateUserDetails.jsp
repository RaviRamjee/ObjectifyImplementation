<%@ page import="com.obj.bean.User"%>
<%@ page import="com.obj.model.UserImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
	background-color: #333;
}

li {
	float: left;
}

li a {
	display: block;
	color: white;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
}

li a:hover:not (.active ) {
	background-color: #111;
}

.active {
	background-color: #4CAF50;
}

.contacts-form {
	background: grey;
	border-radius: 10px;
	-moz-border-radius: 10px;
	-webkit-border-radius: 10px;
	-o-border-radius: 10px;
	-ms-border-radius: 10px;
	padding: 70px 585px;
}

.form-control {
	padding: 10px 10px;
	margin: 3px;
}
</style>

</head>
<body>
	<div class="switcher-wrapper">
		<div class="demo_changer">
			<div class="demo-icon customBgColor">
				<i class="fa fa-cog fa-spin fa-2x"></i>
			</div>
			<div class="form_holder">
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="predefined_styles"></div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<ul>
		<li><a href="./UserDetails.html">Show Users</a></li>
		<li><a href="./UpdateUser.html">Update User</a></li>
		<li><a href="./DeleteUser.html">Delete User</a></li>
	</ul>
	<section class="contacts">

	<div class="container">
		<div class="row">
			<div class="contacts-find">
				<div class="col-md-5 col-md-offset-3">
					<div class="contacts-form"
						style="background-color: rgba(80, 54, 84, 0.6); height: 100%">

						<h1 style="font-size: 200%; color: White" class="blog-title">Update
							data</h1>
						<div id="success"></div>
						<%   
                      User u=new User();
              		String id=request.getParameter("Id");
              		UserImpl uImpl = new UserImpl();
              		u=uImpl.getUserData(id);
              		
                      %>
						<form action="./UserServlet" method="get" id="contactForm">

							<div class="comment-form__input">
								<input type="hidden" class="form-control" name="put"
									id="user-name" placeholder="Id" value="update" />
							</div>
							<div class="comment-form__input">
								<input type="text" class="form-control" name="Id" id="user-name"
									placeholder="Id" value="<%= u.getUserId() %>" />
							</div>
							<div class="comment-form__input">
								<input type="text" class="form-control" name="name"
									id="user-name" placeholder="Full Name"
									value="<%= u.getUserName() %>" />
							</div>
							<div class="comment-form__input">
								<input type="text" class="form-control" name="email"
									id="user-email" placeholder="Email"
									value="<%= u.getUserEmail() %>" />
							</div>
							<div class="comment-form__input">
								<input type="text" class="form-control" name="password"
									id="user-website" placeholder="Password"
									value="<%= u.getUserPassword() %>" />
							</div>
							<div class="comment-form__input">
								<input type="text" class="form-control" name="phoneNo"
									id="user-website" placeholder="Phone No"
									value="<%= u.getUserPhoneNo() %>" />
							</div>
							<div class="comment-form__input">
								<input type="text" class="form-control" name="address"
									id="user-website" placeholder="address"
									value="<%= u.getUserAddress() %>" />
							</div>
							<br>
							<center>
								<button type="submit"
									class="btn button button--red triangle triangle--12"
									value="SignUp">Update</button>
							</center>

						</form>
					</div>
				</div>
			</div>
		</div>
	</section>





</body>
</html>