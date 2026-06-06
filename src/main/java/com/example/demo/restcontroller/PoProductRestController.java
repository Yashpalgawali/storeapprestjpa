package com.example.demo.restcontroller;

import java.util.List;

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
import com.example.demo.models.PoProducts;
import com.example.demo.service.PoProductService;

import lombok.RequiredArgsConstructor;

@RequestMapping("poproducts")
@RestController
@RequiredArgsConstructor
public class PoProductRestController {

	private final PoProductService poprodserv;

	@PostMapping("/")
	public ResponseEntity<ResponseDto> savePoProducts(@RequestBody PoProducts poprods) {
		poprodserv.savePoProductsList(poprods);
		return ResponseEntity.status(HttpStatus.CREATED).body(
				new ResponseDto("Po product " + poprods.getProd_name() + " is saved successfully", HttpStatus.CREATED));

	}

	@GetMapping("/")
	public ResponseEntity<List<PoProducts>> getAllPoProducts() {

		List<PoProducts> allPoProductList = poprodserv.getAllPoProductList();
		return new ResponseEntity<List<PoProducts>>(allPoProductList, HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<PoProducts> getPoProductById(@PathVariable Integer id) {

		PoProducts poProd = poprodserv.getPoProductById(id);
		return new ResponseEntity<PoProducts>(poProd, HttpStatus.OK);
	}

	@PutMapping("/")
	public ResponseEntity<ResponseDto> updatePoProducts(@RequestBody PoProducts poprods) {
		poprodserv.updatePoProductsList(poprods);
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseDto("Po product " + poprods.getProd_name() + " is updated successfully", HttpStatus.OK));

	}
}
