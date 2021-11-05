package com.jp.helpdesk.domain.enums;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

public enum Status {

	ABERTO(0, "ABERTO"), ANDAMENTO(1, "ANDAMENTO"), ENCERRADO(2, "ENCERRADO");
	
	private Integer codigo;
	private String descricao;
	
	
	private Status(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public static Status toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		Optional<Status> perfil = Arrays.asList(Status.values()).stream().filter(p -> p.getCodigo().equals(cod)).findFirst(); 
		
		return perfil.orElseThrow(() -> new IllegalArgumentException("Status inválido"));
	}
	
}
