package com.ies.casino;

import java.util.Random;

public class Banca {
	protected 	long	saldo;
	protected 	boolean	enBancarrota;
	protected	Random	generador;
	protected	boolean	sePuedenHacerApuestas;
	protected	int		numeroGanador;
	public Banca(long saldoInicial) {
		saldo=saldoInicial;
		enBancarrota=false;
		sePuedenHacerApuestas=true;
	}
	public synchronized boolean sePuedenHacerApuestas(){
		return sePuedenHacerApuestas;
	}
	public synchronized void girarRuleta() throws InterruptedException{
		int segundosAzar;
		while (true){
			sePuedenHacerApuestas=false;
			System.out.println("Ya no va más, señores. ¡Girando!");
			Thread.sleep(3000);
			numeroGanador=generador.nextInt(37);
			System.out.println("El número ganador es el :"+numeroGanador);
			sePuedenHacerApuestas=true;
			/* Se eligen unos milisegundos al azar para que los jugadores
			 * elijan, aunque quizá no todos puedan llegar a apostar
			 */
			segundosAzar=generador.nextInt(3);
			System.out.println("Hagan juego, tienen Vds "+segundosAzar+" segundos");
			Thread.sleep(1000*segundosAzar);
		}
	}
	
	

}
