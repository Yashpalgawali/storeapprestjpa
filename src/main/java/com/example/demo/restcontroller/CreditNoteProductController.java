package com.example.demo.restcontroller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CreditNoteProductDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.models.CreditNoteProduct;
import com.example.demo.service.ICreditNoteProductService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("creditnoteproduct")
@RequiredArgsConstructor
public class CreditNoteProductController {

	private final ICreditNoteProductService creditnoteprodserv;
	
	@PostMapping("/")
	public ResponseEntity<ResponseDto> saveCreditNoteProduct(@RequestBody CreditNoteProductDto creditnoteprod, HttpServletRequest request )
	{
		creditnoteprodserv.saveCreditNoteProduct(creditnoteprod, request);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto( "Credit Note product saved successfully" ,HttpStatus.CREATED )); 
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<List<CreditNoteProduct>> getAllCreditNoteProductsByOrderId(@PathVariable Integer id){
		
		List<CreditNoteProduct> prodList = creditnoteprodserv.getCreditNoteProductsByOrderId(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(prodList);
	}
}
