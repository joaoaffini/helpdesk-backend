package com.jp.helpdesk.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jp.helpdesk.domain.Tecnico;
import com.jp.helpdesk.domain.dtos.TecnicoDTO;
import com.jp.helpdesk.services.TecnicoService;

@RestController
@RequestMapping("/tecnicos")
public class TecnicoResource {

	@Autowired
	private TecnicoService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
		
		Tecnico tecnico = service.findById(id);
		return ResponseEntity.ok().body(new TecnicoDTO(tecnico));
	}
	
	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> findAll() {
		
		 List<TecnicoDTO> tecnicos = service.findAll();
		 
		 return ResponseEntity.ok().body(tecnicos);
	}
	
	@PostMapping
	public ResponseEntity<TecnicoDTO> create(@RequestBody @Valid TecnicoDTO dto) {
		
		Tecnico obj = service.create(dto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}

}
