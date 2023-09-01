package com.filrouge.ProjetFilRouge.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filrouge.ProjetFilRouge.entity.Training;
import com.filrouge.ProjetFilRouge.erreur.ErreurResponse;
import com.filrouge.ProjetFilRouge.exception.StringException;
import com.filrouge.ProjetFilRouge.service.TrainingService;
import com.filrouge.ProjetFilRouge.validation.IntegerValidation;
import com.filrouge.ProjetFilRouge.validation.StringValidation;

@RestController
@RequestMapping("/api")
public class TrainingController {
	
	private TrainingService trainingService;
	
	public TrainingController(TrainingService trainingService) {
		this.trainingService = trainingService;
	}
	
	@GetMapping("/trainings")
	public List<Training> findAll(){
		return trainingService.getAll();
	}
	
	@GetMapping("/trainings/{trainingId}")
	public Training findById(@PathVariable int trainingId) {
		if (trainingService.get(trainingId) == null) {
			throw new RuntimeException("Id " + trainingId + " inexistant.");
		}
		return trainingService.get(trainingId);
	}
	
	@PostMapping(value = "/trainings")
	public Training add(@RequestBody Training training) {
		training.setId(null);
		List<String> erreurs = StringValidation.erreurNom(training.getNom());
		erreurs.addAll(IntegerValidation.ErreurInt(training.getDuree()));
		erreurs.addAll(IntegerValidation.ErreurInt(training.getNbParticipants()));
		if (!erreurs.isEmpty()) {
			throw new StringException(erreurs);
		}
		trainingService.add(training);
		return training;
	}
	
	@PutMapping("/trainings")
	public Training update(@RequestBody Training training) {
		trainingService.update(training);
		return training;
	}
	
	@DeleteMapping("trainings/{trainingId}")
	public String delete(@PathVariable int trainingId) {
		if (trainingService.get(trainingId) == null) {
			throw new RuntimeException("Id " + trainingId + " innexistant.");
		}
		trainingService.delete(trainingService.get(trainingId));
		return "Formation " + trainingId + " supprimée avec succès.";
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
