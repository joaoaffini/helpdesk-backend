package com.jp.helpdesk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jp.helpdesk.domain.Chamado;
import com.jp.helpdesk.domain.dtos.ChamadoDTO;
import com.jp.helpdesk.services.ChamadoService;

@RestController
@RequestMapping("/chamados")
public class ChamadoResource {
	
	@Autowired
	private ChamadoService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id) {
		
		Chamado chamado = service.findByID(id);
		
		return ResponseEntity.ok().body(new ChamadoDTO(chamado));
	}
	
	@GetMapping
	public ResponseEntity<List<ChamadoDTO>> findAll() {
		
		List<Chamado> chamados = service.findAll();
		
		return ResponseEntity.ok().body(chamados.stream().map(c -> new ChamadoDTO(c)).collect(Collectors.toList()));
	}
	
	@PostMapping
	public ResponseEntity<ChamadoDTO> create(@RequestBody @Valid ChamadoDTO dto) {
		
		Chamado chamado = service.create(dto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(chamado.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ChamadoDTO> update(@PathVariable Integer id, @RequestBody @Valid ChamadoDTO dto) {
		
		Chamado chamado = service.update(id, dto);
		
		return ResponseEntity.ok().body(new ChamadoDTO(chamado));
	}

}
