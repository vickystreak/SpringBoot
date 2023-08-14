package com.example.jdbc.service;

import org.springframework.stereotype.Service;
import com.example.jdbc.model.Response;
import com.example.jdbc.model.SignUpModel;

@Service
public interface SignUpService {
	
	public Response createUser(SignUpModel values);

	public Response getUser();

	public Response getOneUser(String sno);

	public Response updateUser(String sno, String email);

	public Response deleteUser(String sno);
	
	public Response login(String email, String pword);
}
