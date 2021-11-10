package com.jp.helpdesk.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jp.helpdesk.domain.Cliente;
import com.jp.helpdesk.domain.Pessoa;
import com.jp.helpdesk.domain.dtos.ClienteDTO;
import com.jp.helpdesk.repositories.ClienteRepository;
import com.jp.helpdesk.repositories.PessoaRepository;
import com.jp.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.jp.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Cliente findById(Integer id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado com o id: "+id));
	}

	public List<ClienteDTO> findAll() {
		
		return repository.findAll().stream().map(cli -> new ClienteDTO(cli)).collect(Collectors.toList());
	}

	public Cliente create(ClienteDTO dto) {
		dto.setId(null);
		this.validaPorCPFEmail(dto);
		Cliente Cliente = new Cliente(dto);
		
		return repository.save(Cliente);
		
	}

	private void validaPorCPFEmail(ClienteDTO dto) {
		
		Optional<Pessoa> cpf = pessoaRepository.findByCpf(dto.getCpf());
		
		if(cpf.isPresent() && cpf.get().getId() != dto.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
		}
		
		Optional<Pessoa> email = pessoaRepository.findByEmail(dto.getEmail());
		
		if(email.isPresent() && email.get().getId() != dto.getId()) {
			throw new DataIntegrityViolationException("Email já cadastrado no sistema");
		}
		
	}

	public Cliente update(Integer id, ClienteDTO dto) {
		dto.setId(id);
		Cliente clienteBD = this.findById(id);
		//this.validaPorCPFEmail(dto);
		
		clienteBD = new Cliente(dto);
		return repository.save(clienteBD);
	}

	public void delete(Integer id) {
		
		Cliente Cliente = this.findById(id);
		if(Cliente.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Técnico possui ordens de serviço e não pode ser deletado!");
		}
		
		repository.deleteById(id);
		
	}

}
