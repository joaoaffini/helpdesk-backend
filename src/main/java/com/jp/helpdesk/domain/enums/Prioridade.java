package com.jp.helpdesk.domain.enums;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

public enum Prioridade {

	BAIXA(0, "BAIXA"), MEDIA(1, "BAIXA"), ALTA(2, "ALTA");
	
	private Integer codigo;
	private String descricao;
	
	
	private Prioridade(Integer codigo, String descricao) {
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
	
	public static Prioridade toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		Optional<Prioridade> perfil = Arrays.asList(Prioridade.values()).stream().filter(p -> p.getCodigo().equals(cod)).findFirst(); 
		
		return perfil.orElseThrow(() -> new IllegalArgumentException("Prioridade inválido"));
	}
	
}
