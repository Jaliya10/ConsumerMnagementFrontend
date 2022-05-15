<%@page import="model.Consumer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<% 
// SAVE
/*
	if (request.getParameter("id") != null) {			
		Consumer consumer = new Consumer();
		String statusMsg = "";
		/*
		//String id = request.getParameter("id");
		String nic = request.getParameter("regNo");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String regDate = request.getParameter("regDate"); //date type
		String power = request.getParameter("power_usage");
		String type = request.getParameter("consumer_type");
*/
		
	/*	if (request.getParameter("hidConsumerIDSave") == "") {
			statusMsg = consumer.insertConsumer(
			request.getParameter("regNo"), 
			request.getParameter("name"), 
			request.getParameter("address"),
			request.getParameter("phone"),
			request.getParameter("regDate"), 
			request.getParameter("power_usage"),
			request.getParameter("consumer_type"));
		} else {
			statusMsg = consumer.updateConsumers(request.getParameter("hidConsumerIDSave"),
			//request.getParameter("id"),
			request.getParameter("regNo"),
			request.getParameter("name"), 
			request.getParameter("address"),
			request.getParameter("phone"),
			request.getParameter("regDate"), 
			request.getParameter("power_usage"),
			request.getParameter("consumer_type"));
		}	
		session.setAttribute("statusMsg", statusMsg);
	}*/
/*
	// DELETE
	if (request.getParameter("hidConsumerIDDelete") != null) {
		Consumer consumer = new Consumer();
		String statusMsg = consumer.deleteConsumer(request.getParameter("hidConsumerIDDelete"));
		session.setAttribute("statusMsg", statusMsg);
	}*/
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Consumer Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery.min.js"></script>
<script src="Components/main.js"></script>
<body>
	<div class="container">
		<div class="row">
			<div class="col-8">
				<br>
				<h1>Consumer Management</h1>
				<br>
				<form id="formItem" name="formItem" method="post" action="cosumer.jsp">
					<h3>Insert Consumer Details</h3>
					<!-- Registration Number -->
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="lblName">Registration Number: </span><br>
						</div>
						<input type="text" id="txtReg" name="txtReg">
					</div>

					<!-- NAME -->
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="lblName">Name: </span><br>
						</div>
						<input type="text" id="txtName" name="txtName">
					</div>

					<!-- Address -->
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="lblName">Address: </span><br>
						</div>
						<input type="text" id="txtAddress" name="txtAddress">
					</div>

					<!-- Phone -->
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="lblName">Phone: </span><br>
						</div>
						<input type="text" id="txtPhone" name="txtPhone">
					</div>

					<!-- Reg Date -->
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="lblName">Registration Date: </span><br>
						</div>
						<input type="text" id="txtRegDate" name="txtRegDate">
					</div>

					<!-- Usage -->
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="lblName">Power Usage: </span>
						</div>
						<input type="text" id="txtUsage" name="txtUsage">
					</div>

					<!-- Consumer Type -->
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="lblName">Consumer Type:
							</span><br>
						</div>
						<input type="text" id="txtType" name="txtType">
					</div>
					<br> 
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary"> 
					
					<input id="hidItemIDSave" name="hidItemIDSave" type="hidden" value="">
				</form>
				<br>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>

				<div id="divItemsGrid">
					<h3>Consumer Details</h3>
					<%
					Consumer consumer = new Consumer();
					out.print(consumer.readConsumers());
					%>
				</div>
			</div>
		</div>
		<br>
	</div>
</body>
</html>