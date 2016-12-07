package com.ies.casino;

import java.util.Random;

public class JugadorParImpar extends Jugador{
	public JugadorParImpar(long saldoInicial, Banca b) {
		super(saldoInicial, b);
		// TODO Auto-generated constructor stub
	}

	protected boolean jugamosAPares;
	@Override
	public void hacerApuesta() {
		
		
		if (generador.nextBoolean()==true){
			System.out.println(nombreHilo+" elige apostar a par");
			jugamosAPares=true;
		} else {
			System.out.println(nombreHilo+" elige apostar a impar");
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
	public void comunicarNumero(int numero) {
		boolean elNumeroEsPar=(numero%2)==0;
		if ( (jugamosAPares) && (elNumeroEsPar) ) {
			System.out.print(nombreHilo + " ganó 20 euros por acertar par");
			/*Ganamos y cogemos a la banca 20 euros*/
			banca.restarSaldo(20);
			this.sumarSaldo(20);
		}
		/* Si no jugábamos a pares (es decir, jugabamos
		 * a impares) y el número no es par, también 
		 * ganamos y cogemos 20 euros */
		if ( ( ! jugamosAPares) && ( ! elNumeroEsPar) ) {
			/*Ganamos y cogemos a la banca 20 euros*/
			System.out.print(nombreHilo + " ganó 20 euros por acertar impar");
			banca.restarSaldo(20);
			this.sumarSaldo(20);
		}
	}

 
}
