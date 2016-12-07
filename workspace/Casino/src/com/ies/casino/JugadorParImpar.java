package com.ies.casino;

import java.util.Random;

public class JugadorParImpar extends Jugador{
	protected boolean jugamosAPares;
	public JugadorParImpar(long saldoInicial) {
		super(saldoInicial);
	}
	public void apostar(){
		Random generador=new Random();
		if (generador.nextBoolean()==true){
			jugamosAPares=true;
		} else {
			jugamosAPares=false;
		}
	}

	@Override
	public boolean ganador(int num) {
		if (num==0){
			//Perdemos 
		} else {
			if ((num%2==0 ) && (jugamosAPares)){
				//Ganamos
			} else {
				//Perdemos
			}
		}
	}

}
