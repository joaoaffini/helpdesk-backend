package com.jp.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jp.helpdesk.domain.Chamado;
import com.jp.helpdesk.domain.Cliente;
import com.jp.helpdesk.domain.Tecnico;
import com.jp.helpdesk.domain.dtos.ChamadoDTO;
import com.jp.helpdesk.domain.enums.Prioridade;
import com.jp.helpdesk.domain.enums.Status;
import com.jp.helpdesk.repositories.ChamadoRepository;
import com.jp.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class ChamadoService {
	
	@Autowired
	private ChamadoRepository repository;
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@Autowired
	private ClienteService clienteService; 
	
	public Chamado findByID(Integer id) {
		Optional<Chamado> chamado = repository.findById(id);
		
		return chamado.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado: "+id));
	}

	public List<Chamado> findAll() {
		return repository.findAll();
	}

	public Chamado create(@Valid ChamadoDTO dto) {
		return repository.save(this.newChamado(dto));
	}
	
	private Chamado newChamado(ChamadoDTO dto) {
		
		Tecnico tecnico = tecnicoService.findById(dto.getTecnico());
		Cliente cliente = clienteService.findById(dto.getCliente());
		
		Chamado chamado = new Chamado();
		
		if(dto.getId() != null) {
			chamado.setId(dto.getId());
		}
		
		chamado.setTecnico(tecnico);
		chamado.setCliente(cliente);
		chamado.setPrioridade(Prioridade.toEnum(dto.getPrioridade()));
		chamado.setStatus(Status.toEnum(dto.getStatus()));
		chamado.setTitulo(dto.getTitulo());
		chamado.setObservacoes(dto.getObservacoes());
		
		return chamado;
	}

}
