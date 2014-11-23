package br.jotas.sc.model;

import java.util.ArrayList;
import java.util.List;

public enum StatusExemplarEnum {

	DISPONIVEL(0,"Disponível"), LOCADO(1,"Locado"), INDISPONIVEL(2,"Indisponível");

	final int codigo;
	final String descricao;
	
	StatusExemplarEnum(int codigo, String descricao){
		this.codigo = codigo;
		this.descricao = descricao;				
	}
	
	public int getCodigo(){
		return codigo;
	}
	
	public String descricao(){
		return descricao;
	}
	
	public static StatusExemplarEnum getValue(int codigo){
		for (StatusExemplarEnum s : StatusExemplarEnum.getValues()) {
			if(s.getCodigo() == codigo){
				return s;
			}
		}
		return null;
	}
	
	public static List<StatusExemplarEnum> getValues(){
		List<StatusExemplarEnum> values = new ArrayList<StatusExemplarEnum>();
		for (StatusExemplarEnum statusExemplarEnum : values()) {
			values.add(statusExemplarEnum);
		}
		return values;
	}
}
