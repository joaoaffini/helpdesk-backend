package com.jp.helpdesk.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jp.helpdesk.domain.Chamado;
import com.jp.helpdesk.repositories.ChamadoRepository;
import com.jp.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class ChamadoService {
	
	@Autowired
	private ChamadoRepository repository;
	
	public Chamado findByID(Integer id) {
		Optional<Chamado> chamado = repository.findById(id);
		
		return chamado.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado: "+id));
	}

}
