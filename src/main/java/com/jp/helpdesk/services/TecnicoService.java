package com.jp.helpdesk.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jp.helpdesk.domain.Tecnico;
import com.jp.helpdesk.domain.dtos.TecnicoDTO;
import com.jp.helpdesk.repositories.TecnicoRepository;
import com.jp.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository repository;
	
	public Tecnico findById(Integer id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado com o id: "+id));
	}

	public List<TecnicoDTO> findAll() {
		
		return repository.findAll().stream().map(tec -> new TecnicoDTO(tec)).collect(Collectors.toList());
	}

}
