package com.journalManagment.Repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.journalManagment.Entity.Journal;

public interface JournalRepository extends JpaRepository<Journal, Long>{

}
