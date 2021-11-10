package com.jp.helpdesk.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jp.helpdesk.domain.Pessoa;
import com.jp.helpdesk.domain.Tecnico;
import com.jp.helpdesk.domain.dtos.TecnicoDTO;
import com.jp.helpdesk.repositories.PessoaRepository;
import com.jp.helpdesk.repositories.TecnicoRepository;
import com.jp.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.jp.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Tecnico findById(Integer id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado com o id: "+id));
	}

	public List<TecnicoDTO> findAll() {
		
		return repository.findAll().stream().map(tec -> new TecnicoDTO(tec)).collect(Collectors.toList());
	}

	public Tecnico create(TecnicoDTO dto) {
		dto.setId(null);
		this.validaPorCPFEmail(dto);
		Tecnico tecnico = new Tecnico(dto);
		
		return repository.save(tecnico);
		
	}

	private void validaPorCPFEmail(TecnicoDTO dto) {
		
		Optional<Pessoa> cpf = pessoaRepository.findByCpf(dto.getCpf());
		
		if(cpf.isPresent() && cpf.get().getId() != dto.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
		}
		
		Optional<Pessoa> email = pessoaRepository.findByEmail(dto.getEmail());
		
		if(email.isPresent() && email.get().getId() != dto.getId()) {
			throw new DataIntegrityViolationException("Email já cadastrado no sistema");
		}
		
	}

	public Tecnico update(Integer id, TecnicoDTO dto) {
		dto.setId(id);
		Tecnico tecnicoBD = this.findById(id);
		//this.validaPorCPFEmail(dto);
		
		tecnicoBD = new Tecnico(dto);
		return repository.save(tecnicoBD);
	}

}
