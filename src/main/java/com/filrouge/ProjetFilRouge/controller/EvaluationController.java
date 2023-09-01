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

import com.filrouge.ProjetFilRouge.entity.Evaluation;
import com.filrouge.ProjetFilRouge.exception.NotFoundException;
import com.filrouge.ProjetFilRouge.exception.StringException;
import com.filrouge.ProjetFilRouge.service.EvaluationService;
import com.filrouge.ProjetFilRouge.validation.IntegerValidation;
import com.filrouge.ProjetFilRouge.validation.StringValidation;

@RestController
@RequestMapping("/api")
public class EvaluationController {

	private EvaluationService evaluationService;
	
	public EvaluationController(EvaluationService evaluationService) {
		this.evaluationService = evaluationService;
	}
	
	@GetMapping("/evaluations")
	public List<Evaluation> findAll(){
		return evaluationService.getAll();
	}
	
	@GetMapping("/evaluations/findSession/{sessionId}")
	public List<Evaluation> findBySession(@PathVariable int sessionId){
		return evaluationService.getEvaluationBySession(sessionId);
	}
	
	@GetMapping("/evaluations/{evaluationId}")
	public Evaluation findById(@PathVariable int evaluationId) {
		if (evaluationService.get(evaluationId) == null) {
			throw new NotFoundException("L'id de l'évaluation " + evaluationId + " n'est pas trouvé.");
		}
		return evaluationService.get(evaluationId);
	}
	
	@PostMapping(value=("/evaluations"))
	public Evaluation add(@RequestBody Evaluation evaluation) {
		evaluation.setId(null);
		List<String> erreurs = StringValidation.erreurCommentaire(evaluation.getCommentaires());
		erreurs.addAll(IntegerValidation.ErreurInt(evaluation.getNote()));
		if (!erreurs.isEmpty()) {
			throw new StringException(erreurs);
		}
		evaluationService.add(evaluation);
		return evaluation;
	}
	
	@DeleteMapping("/evaluations/{evaluationId}")
	public String delete(@PathVariable int evaluationId) {
		Evaluation evaluation = evaluationService.get(evaluationId);
		if(evaluation == null) {
			throw new NotFoundException("L'id de l'évaluation " + evaluationId + " n'est pas trouvé.");
		}
		evaluationService.delete(evaluation);
		return "L'évaluation " + evaluationId + " a été supprimée avec succès.";
	}
	
	@PutMapping("/evaluations")
	public Evaluation update(@RequestBody Evaluation evaluation) {
		evaluationService.update(evaluation);
		return evaluation;
	}
}
