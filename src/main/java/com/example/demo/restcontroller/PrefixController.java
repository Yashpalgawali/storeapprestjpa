package com.example.demo.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseDto;
import com.example.demo.models.Prefix;
import com.example.demo.service.PrefixService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("prefix")
@RequiredArgsConstructor
public class PrefixController {

	private final PrefixService prefixserv;

	@GetMapping("/{id}")
	public ResponseEntity<Prefix> getPrefixById(@PathVariable Integer id) {
		Prefix prefix = prefixserv.getPrefixById(id);

		return new ResponseEntity<Prefix>(prefix, HttpStatus.OK);

	}

	@PutMapping("/")
	public ResponseEntity<ResponseDto> updatePrefixById(@RequestBody Prefix prefix) {
		prefixserv.updatePrefixById(prefix);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseDto(
						"Prefix " + prefix.getPrefix() + "-" + prefix.getFin_year() + " is updated successfully",
						HttpStatus.OK));

	}

	@PostMapping("/")
	public ResponseEntity<ResponseDto> savePrefix(@RequestBody Prefix prefix) {
		prefixserv.savePrefix(prefix);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseDto(
						"Prefix " + prefix.getPrefix() + "-" + prefix.getFin_year() + " is created successfully",
						HttpStatus.CREATED));

	}

	@GetMapping("/")
	public ResponseEntity<Prefix> getAllPrefixes() {
		Prefix prefixList = prefixserv.getAllPrefixes();
		return new ResponseEntity<Prefix>(prefixList, HttpStatus.OK);
	}
}
