package com.example.demo.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Docket;
import com.example.demo.service.DocketService;
import com.example.demo.service.PartyService;

@RestController
@RequestMapping("docket")
@CrossOrigin("*")
public class DocketRestController {

	
	private DocketService dockserv;
	
	private PartyService partyserv;

	@Autowired
	public DocketRestController(PartyService partyserv,DocketService dockserv) {
		this.partyserv=partyserv;
		this.dockserv=dockserv;
	}
	
	@RequestMapping("/savedocket")
	public ResponseEntity<Docket> saveDocket(@RequestBody Docket dock) {
		Docket docket = dockserv.saveDocket(dock);
		if(docket!=null) {
			return new ResponseEntity<Docket>(docket , HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Docket>> viewDocket() {
		List<Docket> dlist = dockserv.getAllDocketsWithJoin();
		return new ResponseEntity<List<Docket>>(dlist,HttpStatus.OK);
	}
	
	
	@PutMapping("/")
	public ResponseEntity<Docket> updateDocket(@RequestBody Docket dock ) {
		int value = dockserv.updateDocket(dock);
		
		if(value > 0 ) {
			return new ResponseEntity<Docket>(dock,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}
	}
	
}