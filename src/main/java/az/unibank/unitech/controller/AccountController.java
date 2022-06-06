package az.unibank.unitech.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.unibank.unitech.dto.request.AccountTransferRequestDto;
import az.unibank.unitech.dto.response.RestResponse;
import az.unibank.unitech.service.AccountService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("accounts")
@RestController
public class AccountController {

	private final AccountService service;

	@PostMapping
	public ResponseEntity<?> createAccount() {

		return new ResponseEntity<>(RestResponse.ok().setData(service.createAccount()), HttpStatus.OK);

	}

	@GetMapping
	public ResponseEntity<?> accounts() {

		return new ResponseEntity<>(RestResponse.ok().setData(service.getAccounts()), HttpStatus.OK);

	}

	@PutMapping("transfer")
	public ResponseEntity<?> transfer(@RequestBody AccountTransferRequestDto request) {

		service.transfer(request);
		return new ResponseEntity<>(RestResponse.ok().setData("The amount transfered successfully!"), HttpStatus.OK);

	}

}
