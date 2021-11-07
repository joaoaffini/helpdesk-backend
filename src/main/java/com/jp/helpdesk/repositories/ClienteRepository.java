package com.jp.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jp.helpdesk.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
