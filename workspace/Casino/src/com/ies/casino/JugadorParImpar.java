package com.ies.casino;

import java.util.Random;

public class JugadorParImpar extends Jugador{
	public JugadorParImpar(long saldoInicial, Banca b) {
		super(saldoInicial, b);
		// TODO Auto-generated constructor stub
	}

	protected boolean jugamosAPares;
	public void apostar(){
		Random generador=new Random();
		if (generador.nextBoolean()==true){
			jugamosAPares=true;
		} else {
			jugamosAPares=false;
		}
	}

	public boolean esGanador(int num) {
		if (num==0){ 
			return false;
		} else {
			if ((num%2==0 ) && (jugamosAPares))
			{
				return true;
			}
		} //Fin del else externo
		return false;
	//Fin de esGanador
	}

	@Override
	public void hacerApuesta() {
		// TODO Auto-generated method stub
		
	} 
}
