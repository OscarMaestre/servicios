package com.ies.examenhilos;

public class Pruebas implements PruebasMBean {
	int num;
	public Pruebas() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public int getEscrituras() {
		// TODO Auto-generated method stub
		return num;
	}
	@Override
	public void setEscrituras(int num) {
		// TODO Auto-generated method stub
		this.num=num;
	}

}
