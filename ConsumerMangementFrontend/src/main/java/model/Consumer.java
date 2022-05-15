package model;

import java.sql.*;

import com.google.gson.JsonObject;

public class Consumer {
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ElectroGrid", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	// insert
	public String insertConsumer(String nic, String name, String address, String phone,
			String date /* changed setDate into setString */, String power, String type) {
		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}

			// create a prepared statement
			String query = " insert into consumers(`id`,`regNo`,`name`,`address`,`phone`,`regDate`,`power_usage`,`consumer_type`)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, nic);
			preparedStmt.setString(3, name);
			preparedStmt.setString(4, address);
			preparedStmt.setString(5, phone);
			preparedStmt.setString(6, date); // changed setDate into setString
			preparedStmt.setString(7, power);
			//preparedStmt.setInt(7, Integer.parseInt(power)); // changed setInt into setString
			preparedStmt.setString(8, type);

			// execute the statement
			preparedStmt.execute();
			con.close();
			// output = "Inserted successfully";

			String newConsumer = readConsumers();
			output = "{\"status\":\"success\", \"data\": \"" + newConsumer + "\"}";
		} catch (Exception e) {
			// output = "Error while inserting the item.";
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the consumer.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// retrieve
	public String readConsumers() {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed
			output = "<table border='1'>" + "<tr>" + "<th>NIC/Reg_No</th>" + "<th>Consumer Name</th>"
					+ "<th>Consumer Address</th>" + "<th>Contact</th>" + "<th>Registered Date</th>"
					+ "<th>Power Consumnption</th>" + "<th>Consumer Type</th>" + "<th>Update</th>" + "<th>Remove</th>"
					+ "</tr>";

			String query = "select * from consumers";
			// String query = "SELECT `regNo`, `name`, `address`, `phone`, `regDate`,
			// `power_usage`, `consumer_type` FROM `consumers`";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				String id = Integer.toString(rs.getInt("id"));
				String nic = rs.getString("regNo");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String phone = rs.getString("phone");
				String regDate = rs.getString("regDate");
				// String power = rs.getString("power_usage");
				String power = Integer.toString(rs.getInt("power_usage"));
				String type = rs.getString("consumer_type");

				// Add into the html table
				output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value='"+id+"'>" + nic + "</td>";
				//output += "<tr><td>" + nic + "</td>";
				output += "<td>" + name + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + phone + "</td>";
				output += "<td>" + regDate + "</td>";
				output += "<td>" + power + "</td>";
				output += "<td>" + type + "</td>";

				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' data-id='" + id + "'></td>"
						+ "<td><input name='btnRemove' type='button' value='Delete' class='btnRemove btn btn-danger' data-id='"+ id + "'></td></tr>";
						/*
						 + "<form method='post' action='consumer.jsp'>"
						 + "<input name='btnRemove' type='submit' value='Remove'  class='btn btn-danger'>"
						 + "<input name='hidItemIDDelete' type='hidden' value='" + id + "'>"
						 + "</form>"
						 + "</td>"
						+ "</tr>";*/
			}
			con.close();

			// Complete the html table
			output += "</table>";
		}

		catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// update
	public String updateConsumers(String id, String nic, String name, String address, String phone, String date,
			String power, String type) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE consumers SET regNo=?, name=?, address=?, phone=?, regDate=?, power_usage=?, consumer_type=? WHERE id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, nic);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, address);
			preparedStmt.setString(4, phone);
			preparedStmt.setString(5, date);
			preparedStmt.setString(6, power);
			//preparedStmt.setInt(6, Integer.parseInt(power));
			preparedStmt.setString(7, type);
			preparedStmt.setInt(8, Integer.parseInt(id));

			// execute the statement
			preparedStmt.execute();
			con.close();
			
			// output = "Updated successfully";
			String newConsumer = readConsumers();
			output = "{\"status\":\"success\", \"data\": \"" + newConsumer + "\"}";
		} catch (Exception e) {
			// output = "Error while updating the item.";
			output = "{\"status\":\"error\", \"data\": \"Error while updating the consumer.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// delete
	public String deleteConsumer(String id) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from consumers where id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(id));

			// execute the statement
			preparedStmt.execute();
			con.close();
			
			// output = "Deleted successfully";
			String newConsumer = readConsumers();
			output = "{\"status\":\"success\", \"data\": \"" + newConsumer + "\"}";
		} catch (Exception e) {
			// output = "Error while deleting the item.";
			output = "{\"status\":\"error\", \"data\":  \"Error while deleting the item.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}

	/* Reading complaints by ID */
	public JsonObject readComp(String id) {
		JsonObject output = null;

		try {
			Connection con = connect();
			if (con == null) {
				output = new JsonObject();
				output.addProperty("MESSAGE", "Database connection failed for reading consumer data.");
				// return "Database connection failed for reading data.";
			}

			String query = "select * from consumers where id='" + id + "'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				JsonObject dbObject = new JsonObject();
				dbObject.addProperty("Consumer Name", rs.getString("name"));
				dbObject.addProperty("Consumer Address", rs.getString("address"));

				output = dbObject;
			}
			con.close();
		} catch (Exception e) {
			output = new JsonObject();
			output.addProperty("MESSAGE", "Error while reading the complaint details.");
			// output = "Error while reading the complaint details.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
