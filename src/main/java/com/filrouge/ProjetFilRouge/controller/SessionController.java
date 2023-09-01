package com.filrouge.ProjetFilRouge.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.http.ResponseEntity;

import com.filrouge.ProjetFilRouge.service.SessionService;
import com.filrouge.ProjetFilRouge.validation.IntegerValidation;
import com.filrouge.ProjetFilRouge.entity.Session;
import com.filrouge.ProjetFilRouge.erreur.ErreurResponse;
import com.filrouge.ProjetFilRouge.exception.NotFoundException;
import com.filrouge.ProjetFilRouge.exception.StringException;

@RestController
@RequestMapping("/api")
public class SessionController {

	//on a supprimer Autowired , il est remplacer par le constructeur
	private SessionService sessionService;
	private IntegerValidation integerValidation;
	
	
	public SessionController (SessionService sessionService, IntegerValidation integerValidation) {
		this.sessionService = sessionService;
        this.integerValidation= integerValidation;
	}
	
	@GetMapping("/sessions")
	public List<Session>findAll(){
		return sessionService.getAll();
	}
	
	
	@GetMapping ("/session/{sessionId}")
	public Session getSessionById(@PathVariable int sessionId) {
		//1. recuperer la sessionId via sessionService 
		Session session = sessionService.get(sessionId);
		
		//2. si la session n'existe pas , on met le if et on lance une exception=> throw new exception
		if (session == null) { 
			throw new NotFoundException ("Session id not found - " + sessionId);
		}
		
	// 3. retourner la session recuperée
	return session;	
	}
	
	@ExceptionHandler
	public ResponseEntity<ErreurResponse> handleException(NotFoundException e) {
	ErreurResponse error = new ErreurResponse();
	error.setStatus(HttpStatus.NOT_FOUND.value());
	error.setMessage(e.getMessage());
	error.setTimeStamp(System.currentTimeMillis());
	return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	// ajouter un autre exception handler pour gérer les autres exceptions (catch all)
	@ExceptionHandler
	public ResponseEntity<ErreurResponse> handleException(Exception e){
	ErreurResponse error = new ErreurResponse();
	error.setStatus(HttpStatus.BAD_REQUEST.value());
	error.setMessage(e.getMessage());
	error.setTimeStamp(System.currentTimeMillis());
	return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	@DeleteMapping ("session/{sessionId}")
	public String deleteSession (@PathVariable int sessionId) {
		Session session = sessionService.get(sessionId);
		
		if (session == null) {
			throw new NotFoundException ("Session id not found - " + sessionId);
		}
		sessionService.delete(session);
		return "Delete Session id = " + sessionId; 
	}
	
	@PutMapping("/session")
	public Session updateSession(@RequestBody Session session) {
		sessionService.update(session);
		return session; 
	}
	
	@PostMapping(value="/session")
	public Session addSession (@RequestBody Session session) {
		List <String> listErreurs = IntegerValidation.ErreurInt(session.getNbParticipant());
		
		if (!listErreurs.isEmpty()) {
			 throw new StringException (listErreurs);
        }

        session.setId(null);
        sessionService.add(session);
		return session;

	}

	@ExceptionHandler
	public ResponseEntity<ErreurResponse> handleException(StringException e) {
	ErreurResponse error = new ErreurResponse();
	error.setStatus(HttpStatus.NOT_FOUND.value());
	error.setMessage(e.getMessage());
	error.setTimeStamp(System.currentTimeMillis());
	return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
}
	


