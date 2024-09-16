package com.journalManagment.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.journalManagment.Entity.Journal;
import com.journalManagment.Repositry.JournalRepository;

@Service
public class KafkaConsumerService {
	@Autowired
	private JournalRepository journalRepository;

	public KafkaConsumerService(JournalRepository journalRepository) {
		this.journalRepository = journalRepository;
	}

	@KafkaListener(topics = "user-events", groupId = "journal-group")
	public void listen(String message) {
		Journal journal = new Journal();
		journal.setMessage(message);
		journalRepository.save(journal);
	}
}
