package com.dbserver.agenda.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.dbserver.agenda.models.AgendaModel;

public interface AgendaRepository extends CrudRepository<AgendaModel, String> {
	void deleteById(Long id);

	Iterable<AgendaModel> findById(Long id);
	
	@Query("SELECT ag FROM AgendaModel ag WHERE ag.nome LIKE %?1%")
	Iterable<AgendaModel> findByName(String name);
}