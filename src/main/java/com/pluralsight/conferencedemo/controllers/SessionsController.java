package com.pluralsight.conferencedemo.controllers;

import com.pluralsight.conferencedemo.models.Session;
import com.pluralsight.conferencedemo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionsController {

	@Autowired
	private SessionRepository sessionRepository;

	// GET all Sessions
	@GetMapping
	public List<Session> list() {
		return sessionRepository.findAll();
	}

	// GET one Session by id
	@GetMapping("{id}")
//	@RequestMapping("{id}")
	public Session get(@PathVariable long id) {
		return sessionRepository.getOne(id);
	}

	// POST a new Session
	@PostMapping
	public Session create(@RequestBody final Session session) {
		return sessionRepository.saveAndFlush(session);
	}

	// DELETE Session by id
	@DeleteMapping("{id}")
//	@RequestMapping(value = "{id}")
	public void delete(@PathVariable Long id) {
		//TODO: Also need to check for children records
		sessionRepository.deleteById(id);
	}

	// PUT session by id
	@PutMapping("{id}")
	public Session update(@PathVariable Long id, @RequestBody Session session) {
		// TODO: Add validation that all attributes are passed in, otherwise return a 400 bad payload
		Session existingSession = sessionRepository.getOne(id);
		BeanUtils.copyProperties(session, existingSession, "session_id");

		return sessionRepository.saveAndFlush(existingSession);
	}


}
