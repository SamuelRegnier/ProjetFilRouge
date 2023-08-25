package com.filrouge.ProjetFilRouge.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filrouge.ProjetFilRouge.entity.Training;
import com.filrouge.ProjetFilRouge.service.TrainingService;

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
}
