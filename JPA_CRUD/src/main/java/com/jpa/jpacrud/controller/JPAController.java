package com.jpa.jpacrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.jpacrud.modal.JPAModal;
import com.jpa.jpacrud.modal.Response;
import com.jpa.jpacrud.service.JPAService;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class JPAController {
	
	
	@Autowired
	JPAService jpa;
	
	@PostMapping("/add")
	public ResponseEntity<Response> createUser(@RequestBody JPAModal values) {
		return ResponseEntity.ok(jpa.createUser(values));
	}

	@GetMapping("/get")
	public ResponseEntity<Response> getUser() {
		return ResponseEntity.ok(jpa.getUser());
	}

	@GetMapping("/getOne/{sno}")
	public ResponseEntity<Response> getOneUser(@PathVariable String sno) {
		return ResponseEntity.ok(jpa.getOneUser(sno));
	}

	@PutMapping("/update")
	public ResponseEntity<Response> updateUser(@RequestParam String sno,String email) {
		return ResponseEntity.ok(jpa.updateUser(sno,email));
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Response> deleteUser(@RequestParam String sno) {
		return ResponseEntity.ok(jpa.deleteUser(sno));
	}

	@PutMapping("/scam")
	public ResponseEntity<Response> scam(@RequestParam String sno) {
		return ResponseEntity.ok(jpa.scam(sno));
	}
	@PostMapping("/login")
	public ResponseEntity<Response> login(@RequestParam String email,@RequestParam String password) {
		return ResponseEntity.ok(jpa.login(email,password));
	}

}











