package com.jp.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jp.helpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}
