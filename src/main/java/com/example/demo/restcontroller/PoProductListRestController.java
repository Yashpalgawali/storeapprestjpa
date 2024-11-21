package com.example.demo.restcontroller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.PoProductsList;
import com.example.demo.service.PoProductListServImpl;

@RequestMapping("poprodlist")
@RestController
@CrossOrigin("*")
public class PoProductListRestController {

	private PoProductListServImpl poprodserv;
	
	public PoProductListRestController(PoProductListServImpl poprodserv) {
		this.poprodserv = poprodserv;
	}
	
	@PostMapping("/")
	public ResponseEntity<PoProductsList> savePoProducts(@RequestBody PoProductsList poprods)
	{
		PoProductsList poProd = poprodserv.savePoProductsList(poprods);
		if(poProd!=null) {
			return new ResponseEntity<PoProductsList>(poProd , HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<PoProductsList>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<List<PoProductsList>> getAllPoProducts() {
		
		List<PoProductsList> allPoProductList = poprodserv.getAllPoProductList();
		if(allPoProductList.size()>0) {
			return new ResponseEntity<List<PoProductsList>> (allPoProductList , HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<PoProductsList>> (HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PoProductsList> getPoProductById(@PathVariable Integer id) {
		
		PoProductsList poProd = poprodserv.getPoProductById(id);
		if(poProd!=null) {
			return new ResponseEntity<PoProductsList> (poProd , HttpStatus.OK);
		}
		else {
			return new ResponseEntity<PoProductsList> (HttpStatus.NO_CONTENT);
		}
	}
	
	
	@PutMapping("/")
	public ResponseEntity<List<PoProductsList>> updatePoProducts(@RequestBody PoProductsList poprods)
	{
		int result  = poprodserv.updatePoProductsList(poprods);
		if(result > 0) {
			return new ResponseEntity<List<PoProductsList>>(poprodserv.getAllPoProductList() , HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<PoProductsList>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
