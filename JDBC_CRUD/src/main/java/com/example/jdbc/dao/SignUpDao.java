package com.example.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.UUID;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;
import com.example.jdbc.model.Response;
import com.example.jdbc.model.SignUpModel;
import com.example.jdbc.service.SignUpService;

@Component
public class SignUpDao implements SignUpService {
	Response res = new Response();
	String url = "jdbc:mysql://127.0.0.1:3306/kgm";
	String user = "root";
	String password = "Vicky#27";

	public Response createUser(SignUpModel values) {
		String uuid = UUID.randomUUID().toString();
		values.setsNo(uuid);
		values.setCreatedBy(uuid);
		values.setUpdatedBy(uuid);

		Date date = new Date(Calendar.getInstance().getTime().getTime());
		values.setCreatedDate(date);
		values.setUpdatedDate(date);
		values.setIsActive(1);

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conn = DriverManager.getConnection(url, user, password);
					Statement st = conn.createStatement()) {

				String insertQuery = "INSERT INTO user_details(s_no,first_name,last_name,email,dob,gender,phoneNumber,password,created_by,created_date,updated_by,updated_date,is_active) "
						+ "VALUES('" + values.getsNo() + "','" + values.getFirstName() + "','" + values.getLastName()
						+ "','" + values.getEmail() + "','" + values.getDob() + "','" + values.getGender() + "',"
						+ values.getPhoneNumber() + ",'" + values.getPassword() + "','" + values.getCreatedBy() + "','"
						+ values.getCreatedDate() + "','" + values.getUpdatedBy() + "','" + values.getUpdatedDate()
						+ "','" + values.getIsActive() + "')";

				st.executeUpdate(insertQuery);

				res.setData("User created Successfully!");
				res.setResponseCode(200);
				res.setResponseMessage("success");

			} catch (Exception e) {
				e.printStackTrace();
				res.setData("Cannot create User!");
				res.setResponseCode(500);
				res.setResponseMessage("error");

			}

		} catch (Exception e) {
			e.printStackTrace();
			res.setData("Driver class Error!");
			res.setResponseCode(500);
			res.setResponseMessage("error");

		}

		return res;
	}

	@SuppressWarnings("unchecked")
	public Response getUser() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String selectQuery = "select * from user_details where is_active=1";
			JSONArray jarray = new JSONArray();
			try (Connection conn = DriverManager.getConnection(url, user, password);
					Statement st = conn.createStatement();
					ResultSet rs = st.executeQuery(selectQuery);) {

				while (rs.next()) {
					JSONObject jobj = new JSONObject();
					jobj.put("sNo", rs.getObject("s_no"));
					jobj.put("firstName", rs.getObject("first_name"));
					jobj.put("lastName", rs.getObject("last_name"));
					jobj.put("firstName", rs.getObject("first_name"));
					jobj.put("email", rs.getObject("email"));
					jobj.put("dob", rs.getObject("dob"));
					jobj.put("gender", rs.getObject("gender"));
					jobj.put("phoneNumber", rs.getObject("phoneNumber"));
					jobj.put("password", rs.getObject("password"));
					jarray.add(jobj);
				}

				res.setData("Data Fetched Successfully!");
				res.setResponseCode(200);
				res.setResponseMessage("success");
				res.setjData(jarray);

			} catch (Exception e) {
				e.printStackTrace();
				res.setData("Data does'nt Fetched!");
				res.setResponseCode(500);
				res.setResponseMessage("error");
			}

		} catch (Exception e) {
			e.printStackTrace();
			res.setData("Driver class Error!");
			res.setResponseCode(500);
			res.setResponseMessage("error");
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	public Response getOneUser(String sno) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String selectQuery = "select * from user_details where s_no='" + sno + "'";
			JSONObject jobj = new JSONObject();
			try (Connection conn = DriverManager.getConnection(url, user, password);
					Statement st = conn.createStatement();
					ResultSet rs = st.executeQuery(selectQuery)) {
				while (rs.next()) {

					jobj.put("sNo", rs.getObject("s_no"));
					jobj.put("firstName", rs.getObject("first_name"));
					jobj.put("lastName", rs.getObject("last_name"));
					jobj.put("firstName", rs.getObject("first_name"));
					jobj.put("email", rs.getObject("email"));
					jobj.put("dob", rs.getObject("dob"));
					jobj.put("gender", rs.getObject("gender"));
					jobj.put("phoneNumber", rs.getObject("phoneNumber"));
					jobj.put("password", rs.getObject("password"));
				}

				res.setData("Data Fetched Successfully!");
				res.setResponseCode(200);
				res.setResponseMessage("success");
				res.setjData(jobj);

			} catch (Exception e) {
				e.printStackTrace();
				res.setData("Data does'nt Fetched!");
				res.setResponseCode(500);
				res.setResponseMessage("error");
			}

		} catch (Exception e) {
			e.printStackTrace();
			res.setData("Driver class Error!");
			res.setResponseCode(500);
			res.setResponseMessage("error");
		}
		return res;
	}

	public Response updateUser(String sno, String email) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			try (Connection conn = DriverManager.getConnection(url, user, password);
					Statement st = conn.createStatement();) {

				String updateQuery = "update kgm.user_details set email='" + email + "'where s_no='" + sno + "';";
				st.executeUpdate(updateQuery);

				res.setData("Email Updated Successfully!");
				res.setResponseCode(200);
				res.setResponseMessage("success");

			} catch (Exception e) {
				e.printStackTrace();
				res.setData("Email does'nt Updated!");
				res.setResponseCode(500);
				res.setResponseMessage("error");
			}

		} catch (Exception e) {
			e.printStackTrace();
			res.setData("Driver class Error!");
			res.setResponseCode(500);
			res.setResponseMessage("error");
		}
		return res;
	}

	public Response deleteUser(String sno) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conn = DriverManager.getConnection(url, user, password);
					Statement st = conn.createStatement();) {

				String deleteQuery = "delete from kgm.user_details where s_no='" + sno + "';";
				st.executeUpdate(deleteQuery);

				res.setData("Data deleted Successfully!");
				res.setResponseCode(200);
				res.setResponseMessage("success");
			} catch (Exception e) {
				e.printStackTrace();
				res.setData("Data does'nt Deleted!");
				res.setResponseCode(500);
				res.setResponseMessage("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setData("Driver class Error!");
			res.setResponseCode(500);
			res.setResponseMessage("error");
		}
		return res;
	}

	public Response scam(String sno) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conn = DriverManager.getConnection(url, user, password);
					Statement st = conn.createStatement();) {

				String scamQuery = "update kgm.user_details set is_active=0 where s_no='" + sno + "'";
				st.executeUpdate(scamQuery);

				res.setData("User has been Soft Deleted!");
				res.setResponseCode(200);
				res.setResponseMessage("success");
			} catch (Exception e) {
				e.printStackTrace();
				res.setData("Unable to Soft Delete the User!");
				res.setResponseCode(500);
				res.setResponseMessage("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setData("Driver class Error!");
			res.setResponseCode(500);
			res.setResponseMessage("error");
		}
		return res;
	}

	public Response login(String email, String pword) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String selectQuery = "select * from kgm.user_details where email='" + email + "' and password='" + pword
					+ "'";
			
			try (Connection conn = DriverManager.getConnection(url, user, password);
					PreparedStatement pst=conn.prepareStatement(selectQuery);
					ResultSet rs=pst.executeQuery();) {

				String result;
				
				if(rs.next()) {
					result = "Existing user";
				}else {
					result = "No such user Found";
				}
						
						
				res.setData(result);
				res.setResponseCode(200);
				res.setResponseMessage("success");

			} catch (Exception e) {
				e.printStackTrace();
				res.setData("Unable to Login!");
				res.setResponseCode(500);
				res.setResponseMessage("error");
			}

		} catch (Exception e) {
			e.printStackTrace();
			res.setData("Driver class Error!");
			res.setResponseCode(500);
			res.setResponseMessage("error");
		}
		return res;
	}

}
