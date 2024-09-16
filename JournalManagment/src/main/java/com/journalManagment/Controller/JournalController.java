package com.journalManagment.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.journalManagment.Entity.Journal;
import com.journalManagment.Repositry.JournalRepository;

@RestController
public class JournalController {
	@Autowired
	private JournalRepository journalRepository;

	public JournalController(JournalRepository journalRepository) {
		this.journalRepository = journalRepository;
	}

	@GetMapping
	public List<Journal> getAllJournals() {
		return journalRepository.findAll();
	}
}
