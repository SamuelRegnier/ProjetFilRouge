package com.filrouge.ProjetFilRouge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filrouge.ProjetFilRouge.entity.Classroom;
import com.filrouge.ProjetFilRouge.entity.Session;
import com.filrouge.ProjetFilRouge.service.ClassroomService;
import com.filrouge.ProjetFilRouge.service.SessionService;

@RestController
@RequestMapping("/api")
public class ClassroomController {
	
	@Autowired
	private ClassroomService classroomService;
	private SessionService sessionService;

	public ClassroomController(ClassroomService classroomService, SessionService sessionService) {
		this.classroomService = classroomService;
		this.sessionService = sessionService;
	}
	
	@GetMapping("/classrooms")
	public List<Classroom> findAll() {
		return classroomService.getAll();
	}
	
	@GetMapping("/classrooms/{classroomId}")
	public Classroom getMessageById(@PathVariable int classroomId) {
		Classroom classroom = classroomService.get(classroomId);
		if (classroom == null) {
			throw new RuntimeException("Message id not found - " + classroomId);
		}
		return classroom;
	}
	
	@GetMapping("/classrooms/findClassrooms/{sessionId}")
	public List<Classroom> findAllFreeClassrooms(@PathVariable int sessionId) {
		Session session = sessionService.get(sessionId);
		if (session == null) {
			throw new RuntimeException("Message id not found - " + sessionId);
		}
		return classroomService.getAllFreeClassrooms(session);
	}
	
	@PostMapping(value = "/classrooms")
	public Classroom addClassroom(@RequestBody Classroom classroom) {
		// Une salle de cours lorsqu'elle est insérée doit toujours être libre.
		classroom.setId(null);
		classroomService.add(classroom);
		return classroom;
	}
	
	@PutMapping("/classrooms")
	public Classroom updateClassroom(@RequestBody Classroom classroom) {
		classroomService.update(classroom);
		return classroom;
	}
	
	@DeleteMapping("/classrooms/{classroomId}")
	public String deleteClassroom(@PathVariable int classroomId) {
		Classroom tempClassroom = classroomService.get(classroomId);
		if (tempClassroom == null) {
			throw new RuntimeException("His message ID not found - " + classroomId);
		}
		classroomService.delete(tempClassroom);
		return "Deleted classroom with id - " + classroomId;
	}
	
	@GetMapping("/classrooms/sessions/{sessionId}")
	public Classroom getClassroomBySession(@PathVariable int sessionId) {
		Classroom classroom = classroomService.getClassroomForSession(sessionService.get(sessionId));
		if (classroom == null) {
			throw new RuntimeException("Message id not found - " + sessionId);
		}
		return classroom;
	}
}
