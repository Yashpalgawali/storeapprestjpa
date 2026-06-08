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

import com.example.demo.dto.ResponseDto;
import com.example.demo.models.CreditNote;
import com.example.demo.service.CreditNoteService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("creditnote")
@RequiredArgsConstructor
public class CreditNoteRestController {

	private final CreditNoteService creditnoteserv;
	
	@PostMapping("/")
	public ResponseEntity<ResponseDto> saveCreditNote(@RequestBody CreditNote creditNote,HttpServletRequest request) {
		
		System.err.println("In controller Credit note Object "+creditNote.toString());
		
		HttpSession sess =request.getSession();
		
		System.err.println("Orrder ID in session is "+sess.getAttribute("temp_id"));
		creditnoteserv.saveCreditNote(creditNote);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto("Credit Note is created successfully",HttpStatus.CREATED) );
	}

	@GetMapping("/")
	public ResponseEntity<List<CreditNote>> getAllCreditNotes() {
		List<CreditNote> credNoteList = creditnoteserv.getAllCreditNotes();
		return new ResponseEntity<List<CreditNote>>(credNoteList, HttpStatus.OK);		 
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CreditNote> getCreditNote(@PathVariable Integer id) {
		CreditNote credNote= creditnoteserv.getCreditNotebyId(id);
		return new ResponseEntity<CreditNote>(credNote, HttpStatus.OK);		 
	}
}
