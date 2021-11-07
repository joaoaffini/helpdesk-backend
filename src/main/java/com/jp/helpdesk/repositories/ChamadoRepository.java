package com.jp.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jp.helpdesk.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {

}
