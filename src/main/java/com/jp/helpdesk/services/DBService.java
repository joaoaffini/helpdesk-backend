package com.jp.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jp.helpdesk.domain.Chamado;
import com.jp.helpdesk.domain.Cliente;
import com.jp.helpdesk.domain.Tecnico;
import com.jp.helpdesk.domain.enums.Perfil;
import com.jp.helpdesk.domain.enums.Prioridade;
import com.jp.helpdesk.domain.enums.Status;
import com.jp.helpdesk.repositories.ChamadoRepository;
import com.jp.helpdesk.repositories.ClienteRepository;
import com.jp.helpdesk.repositories.TecnicoRepository;

@Service
public class DBService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ChamadoRepository chamadoRepository;

	public void instanciaDB() {
		Tecnico tec1 = new Tecnico(null, "João Paulo", "12759073009", "joao@mail.com", "123");
		tec1.addPerfil(Perfil.ADMIN);
		
		
		Cliente cli1 = new Cliente(null, "Linus Torvalds", "57844947080", "torvalds@mail.com", "123");
		
		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1, cli1);
		
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));
	}
}
