package br.jotas.sc.model;

import java.util.ArrayList;
import java.util.List;

public enum StatusExemplarEnum {

	DISPONIVEL(0), LOCADO(1), INDISPONIVEL(2);

	final int codigo;
	
	StatusExemplarEnum(int codigo){
		this.codigo = codigo;
				
	}
	
	public int getCodigo(){
		return codigo;
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
