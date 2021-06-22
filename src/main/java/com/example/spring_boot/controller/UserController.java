package com.example.spring_boot.controller;

import com.example.spring_boot.controller.entity.UserEntity;
import com.example.spring_boot.exceptions.UserAlreadyExistException;
import com.example.spring_boot.exceptions.UserNotFoundException;
import com.example.spring_boot.repository.UserRepo;
import com.example.spring_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public  ResponseEntity registration(@RequestBody UserEntity user){
		try {
			userService.registration(user);
			return ResponseEntity.ok("User saved");

		}catch (UserAlreadyExistException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}catch (Exception e){
			return ResponseEntity.badRequest().body("Error message");
		}
	}

	@GetMapping
	public ResponseEntity getOneUser(@RequestParam Long id){

		try {
			return ResponseEntity.ok(userService.getOne(id));
		}catch (UserNotFoundException e){
			return ResponseEntity.badRequest().body("User not found");
		}
		catch (Exception e){
			return ResponseEntity.badRequest().body("Error message");
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity deleteUser(@PathVariable Long id){

		try {
			return ResponseEntity.ok(userService.delete(id));
		}catch (Exception e){
			return ResponseEntity.badRequest().body("Error message");
		}
	}

}
