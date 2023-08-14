package com.jpa.jpacrud.service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jpa.jpacrud.jparepo.JPARepository;
import com.jpa.jpacrud.modal.JPAModal;
import com.jpa.jpacrud.modal.Response;

@Service
public class JPAService {

	@PersistenceContext
	private EntityManager entityManager;

	Response res = new Response();

	@Autowired
	private JPARepository jparepo;

//	private String password;

	public Response createUser(JPAModal values) {

		String uuid = UUID.randomUUID().toString();
		values.setsNo(uuid);
		values.setCreatedBy(uuid);
		values.setUpdatedBy(uuid);

		Date date = new Date(Calendar.getInstance().getTime().getTime());
		values.setCreatedDate(date);
		values.setUpdateDate(date);
		values.setIsActive(1);

		try {

			jparepo.save(values);
			res.setData("User created successsfully");
			res.setResponseCode(200);
			res.setResponseMessage("success");

		}

		catch (Exception e) {

			e.printStackTrace();
			res.setData("Cannot create User");
			res.setResponseCode(500);
			res.setResponseMessage("error");

		}

		return res;

	}

	@SuppressWarnings("unchecked")
	public Response getUser() {

		try {

			List<JPAModal> list = jparepo.findAll();
			JSONArray jarray = new JSONArray();

			for (JPAModal values : list) {

				JSONObject jobj = new JSONObject();
				jobj.put("sNo", values.getsNo());
				jobj.put("firstName", values.getFirstName());
				jobj.put("lastName", values.getLastName());
				jobj.put("email", values.getEmail());
				jobj.put("dob", values.getDob());
				jobj.put("gender", values.getGender());
				jobj.put("phoneNumber", values.getPhoneNumber());
				jobj.put("password", values.getPasword());

				jarray.add(jobj);

			}

			res.setData("All Users had been Fetched");
			res.setResponseCode(200);
			res.setjData(jarray);
			res.setResponseMessage("success");

		} catch (Exception e) {

			e.printStackTrace();
			res.setData("Failed to Fetch Users");
			res.setResponseCode(500);
			res.setResponseMessage("error");

		}

		return res;

	}

	@SuppressWarnings("unchecked")
	public Response getOneUser(String sno) {

		try {

			Optional<JPAModal> optObj = jparepo.findById(sno);
			JSONObject jsonObj = new JSONObject();

			if (optObj.isPresent()) {

				JPAModal jpaObj = optObj.get();

				jsonObj.put("sNo", jpaObj.getsNo());
				jsonObj.put("firstName", jpaObj.getFirstName());
				jsonObj.put("lastName", jpaObj.getLastName());
				jsonObj.put("email", jpaObj.getEmail());
				jsonObj.put("dob", jpaObj.getDob());
				jsonObj.put("gender", jpaObj.getGender());
				jsonObj.put("phoneNumber", jpaObj.getPhoneNumber());
				jsonObj.put("password", jpaObj.getPasword());

			} else {

				res.setData("No Such User");
				res.setResponseCode(500);
				res.setResponseMessage("error");

			}

			res.setjData(jsonObj);
			res.setData("User had Fetched Successfully");
			res.setResponseCode(200);
			res.setResponseMessage("success");

		} catch (Exception e) {

			e.printStackTrace();
			res.setData("Failed to Fetch User");
			res.setResponseCode(500);
			res.setResponseMessage("error");

		}

		return res;

	}

	public Response deleteUser(String sno) {

		try {

			Optional<JPAModal> optObj = jparepo.findById(sno);

			if (optObj.isPresent()) {

				JPAModal del = optObj.get();
				jparepo.delete(del);

				res.setData("User Deleted Succesfully");
				res.setResponseCode(200);
				res.setResponseMessage("success");

			} else {

				res.setData("No Such User");
				res.setResponseCode(500);
				res.setResponseMessage("error");

			}

		} catch (Exception e) {

			e.printStackTrace();
			res.setData("Failed to Delete User");
			res.setResponseCode(500);
			res.setResponseMessage("error");

		}

		return res;

	}

	public Response updateUser(String sno, String email) {

		try {

			Optional<JPAModal> optObj = jparepo.findById(sno);

			if (optObj.isPresent()) {

				JPAModal update = optObj.get();
				update.setEmail(email);
				jparepo.save(update);

				res.setData("Email had been Updated");
				res.setResponseCode(200);
				res.setResponseMessage("success");

			} else {

				res.setData("No Such User");
				res.setResponseCode(200);
				res.setResponseMessage("error");

			}

		} catch (Exception e) {

			e.printStackTrace();
			res.setData("Failed to Update User Email");
			res.setResponseCode(500);
			res.setResponseMessage("error");

		}

		return res;

	}

	public Response scam(String sno) {

		try {

			Optional<JPAModal> optObj = jparepo.findById(sno);

			if (optObj.isPresent()) {

				JPAModal update = optObj.get();
				update.setIsActive(0);
				jparepo.save(update);

				res.setData("User Soft Deleted Successfully");
				res.setResponseCode(200);
				res.setResponseMessage("success");

			} else {

				res.setData("No Such User");
				res.setResponseCode(200);
				res.setResponseMessage("error");

			}

		} catch (Exception e) {

			e.printStackTrace();
			res.setData("Unable to Soft Delete the User");
			res.setResponseCode(500);
			res.setResponseMessage("error");

		}

		return res;
	}

	@SuppressWarnings("unchecked")
	public Response login(String email, String password) {

		try {

			Query query = entityManager
					.createQuery("SELECT u FROM JPAModal u WHERE u.email = :email AND u.password = :password");
			query.setParameter("email", email);
			query.setParameter("password", password);

			List<JPAModal> value = query.getResultList();

			if (value.isEmpty()) {

				res.setData("No Such User");
				res.setResponseCode(200);
				res.setResponseMessage("success");

			} else {

				res.setData("Existing User Login Successful");
				res.setResponseCode(200);
				res.setResponseMessage("success");
			}

		} catch (Exception e) {

			e.printStackTrace();
			res.setData("Login Failed");
			res.setResponseCode(500);
			res.setResponseMessage("error");

		}

		return res;
	}

}
