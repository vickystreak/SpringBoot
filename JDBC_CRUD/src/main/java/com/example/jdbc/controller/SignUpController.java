package com.example.jdbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.jdbc.dao.SignUpDao;
import com.example.jdbc.model.Response;
import com.example.jdbc.model.SignUpModel;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class SignUpController {
	
	@Autowired
	SignUpDao dao;

	@PostMapping("/add")
	public ResponseEntity<Response> createUser(@RequestBody SignUpModel values) {
		return ResponseEntity.ok(dao.createUser(values));
	}

	@GetMapping("/select")
	public ResponseEntity<Response> getUser() {
		return ResponseEntity.ok(dao.getUser());
	}

	@GetMapping("/getone")
	public ResponseEntity<Response> getOneUser(@RequestParam String sno) {
		return ResponseEntity.ok(dao.getOneUser(sno));
	}

	@PutMapping("/update")
	public ResponseEntity<Response> updateUser(@RequestParam String sno, @RequestParam String email) {
		return ResponseEntity.ok(dao.updateUser(sno, email));
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Response> deleteUser(@RequestParam String sno) {
		return ResponseEntity.ok(dao.deleteUser(sno));
	}

	@PutMapping("/scam")
	public ResponseEntity<Response> scam(@RequestParam String sno) {
		return ResponseEntity.ok(dao.scam(sno));
	}
	@PostMapping("/login")
	public ResponseEntity<Response> login(@RequestParam String email, String pword) {
		return ResponseEntity.ok(dao.login(email,pword));
	}
}
