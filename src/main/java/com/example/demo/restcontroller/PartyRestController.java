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
import com.example.demo.models.Party;
import com.example.demo.service.PartyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("party")
@RequiredArgsConstructor
public class PartyRestController {

	private final PartyService partyserv;
	
	@PostMapping("/")
	public ResponseEntity<ResponseDto> saveParty(@RequestBody Party party) {
		partyserv.saveParty(party);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto("Party "+party.getParty_name()+" is created successfully",HttpStatus.CREATED));		
	}
	
	@GetMapping("/")
	public  ResponseEntity<List<Party>> viewParties() {
		List<Party> partyList = partyserv.getAllParties();		 
		return new ResponseEntity<List<Party>>(partyList, HttpStatus.OK);		 
	}
	
	@GetMapping("/{id}")
	public  ResponseEntity<Party> getPartyById(@PathVariable Integer id) {
		
		Party party = partyserv.getpartyById(id);
		return new ResponseEntity<Party>(party, HttpStatus.OK);
		
	}
	
	@PutMapping("/")
	public ResponseEntity<List<Party>> updateParty(@RequestBody Party party) {
		
		partyserv.updateParty(party);
		return new ResponseEntity<List<Party>>(partyserv.getAllParties(), HttpStatus.OK);			
	}
	
}
