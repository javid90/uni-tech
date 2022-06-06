package az.unibank.unitech.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.unibank.unitech.dto.request.UserLoginRequestDto;
import az.unibank.unitech.dto.request.UserRegisterRequestDto;
import az.unibank.unitech.dto.response.RestResponse;
import az.unibank.unitech.dto.response.UserRegisterResponseDto;
import az.unibank.unitech.service.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("users")
@RestController
public class UserController {

	private final UserService service;

	@PostMapping("register")
	public ResponseEntity<?> register(@RequestBody UserRegisterRequestDto request) {

		return new ResponseEntity<>(RestResponse.ok().setData(service.register(request)), HttpStatus.CREATED);

	}

	@PostMapping("login")
	public ResponseEntity<?> login(@RequestBody UserLoginRequestDto request) {
		
		service.login(request);
		return new ResponseEntity<>(RestResponse.ok().setData("The user logged in successfully!"), HttpStatus.OK);

	}

}
