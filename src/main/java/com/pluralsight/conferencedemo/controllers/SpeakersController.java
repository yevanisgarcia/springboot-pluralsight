package com.pluralsight.conferencedemo.controllers;

import com.pluralsight.conferencedemo.models.Speaker;
import com.pluralsight.conferencedemo.repositories.SpeakerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/speakers")
public class SpeakersController {
	@Autowired
	private SpeakerRepository speakerRepository;

	@GetMapping
	public List<Speaker> list() {
		return speakerRepository.findAll();
	}

	@GetMapping("{id}")
	public Speaker get(@PathVariable Long id) {
		return speakerRepository.getOne(id);
	}

	@PutMapping
	public Speaker create(@RequestBody final Speaker speaker) {
		return speakerRepository.saveAndFlush(speaker);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		// TODO: Check for children records
		speakerRepository.deleteById(id);
	}

	@PutMapping("{id}")
	public Speaker update(@PathVariable Long id, @RequestBody Speaker speaker) {
		Speaker existingSpeaker = speakerRepository.getOne(id);
		BeanUtils.copyProperties(speaker, existingSpeaker, "speaker_id");

		return speakerRepository.saveAndFlush(existingSpeaker);
	}
}
