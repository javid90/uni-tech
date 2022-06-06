package az.unibank.unitech.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import az.unibank.unitech.dto.response.RestResponse;
import az.unibank.unitech.service.CurrencyService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("utility")
@RestController
public class UtilityController {

	private final CurrencyService service;

	@GetMapping("currency/rate")
	public ResponseEntity<?> currencyRate(@RequestParam String currencyPair) {

		return new ResponseEntity<>(RestResponse.ok().setData(service.getCurrencyRate(currencyPair)), HttpStatus.OK);

	}

}
