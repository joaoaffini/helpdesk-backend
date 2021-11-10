package com.jp.helpdesk.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jp.helpdesk.domain.Cliente;
import com.jp.helpdesk.domain.dtos.ClienteDTO;
import com.jp.helpdesk.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id) {
		
		Cliente tecnico = service.findById(id);
		return ResponseEntity.ok().body(new ClienteDTO(tecnico));
	}
	
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll() {
		
		 List<ClienteDTO> tecnicos = service.findAll();
		 
		 return ResponseEntity.ok().body(tecnicos);
	}
	
	@PostMapping
	public ResponseEntity<ClienteDTO> create(@RequestBody @Valid ClienteDTO dto) {
		
		Cliente obj = service.create(dto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ClienteDTO> update(@PathVariable Integer id, @RequestBody @Valid ClienteDTO dto) {
		
		Cliente tecnico = service.update(id, dto);
		
		return ResponseEntity.ok().body(new ClienteDTO(tecnico));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ClienteDTO> delete(@PathVariable Integer id) {
		
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}

}
