package com.example.demo.restcontroller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Prefix;
import com.example.demo.service.PrefixService;

@RestController
@RequestMapping("prefix")
@CrossOrigin
public class PrefixController {

	private PrefixService prefixserv;

	public PrefixController(PrefixService prefixserv) {
		super();
		this.prefixserv = prefixserv;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Prefix> getPrefixById(@PathVariable Integer id) {
		Prefix prefix = prefixserv.getPrefixById(id);
		if(prefix!=null) {
			return new ResponseEntity<Prefix>(prefix, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Prefix>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/")
	public ResponseEntity<Prefix> updatePrefixById(@RequestBody Prefix prefix) {
		int res = prefixserv.updatePrefixById(prefix);
		if(res > 0 ) {
			return new ResponseEntity<Prefix>(prefixserv.getPrefixById(prefix.getSetting_id()) ,HttpStatus.OK);	
		}
		else {
			return new ResponseEntity<Prefix>(HttpStatus.NOT_MODIFIED);
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Prefix>> getAllPrefixes(){
		List<Prefix> prefixList = prefixserv.getAllPrefixes();
		if(prefixList.size()>0) {
			return new ResponseEntity<List<Prefix>>(prefixList, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Prefix>>(HttpStatus.NO_CONTENT);
		}
	}
}
