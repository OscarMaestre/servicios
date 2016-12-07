package com.ies.casino;

import java.util.ArrayList;
import java.util.Random;


public class Banca {
	protected 	long	saldo;
	protected 	boolean	enBancarrota;
	protected	Random	generador;
	protected	boolean	sePuedenHacerApuestas;
	protected	int		numeroGanador;
	public 		enum 	Estado {
		ACEPTANDO_APUESTAS, RULETA_GIRANDO, PAGANDO_APUESTAS, EN_BANCARROTA
	};
	private Estado estadoRuleta;
	
	private ArrayList<Jugador> apostadores;

	public Banca(long saldoInicial) {
		saldo=saldoInicial;
		enBancarrota=false;
		sePuedenHacerApuestas=true;
	}
	public synchronized void sumarSaldo(long cantidad){
		saldo = saldo + cantidad;
	}
	public synchronized void restarSaldo(long cantidad){
		if (saldo - cantidad <= 0){
			saldo=0;
			estadoRuleta=Estado.EN_BANCARROTA;
			return ;
		}
		saldo = saldo - cantidad;
	}
	
	public synchronized void aceptarApuesta(Jugador jugador){
		if (estadoRuleta == Estado.ACEPTANDO_APUESTAS ) {
			apostadores.add(jugador);
		}
	}
	public void comunicarNumeroGanador (int numero){
		/* Al pasar el número a los jugadores, ellos nos
		 * irán restando el saldo que les corresponda por haber ganado */
		for (Jugador apostador: apostadores){
			apostador.comunicarNumero(numeroGanador);
		}
	}
	public synchronized void girarRuleta() throws InterruptedException{
		int segundosAzar;
		while (estadoRuleta!=Estado.EN_BANCARROTA){
			estadoRuleta=Estado.ACEPTANDO_APUESTAS;
			/* Se eligen unos milisegundos al azar para que los jugadores
			 * elijan, aunque quizá no todos puedan llegar a apostar
			 */
			segundosAzar=1+generador.nextInt(3);
			System.out.println("Hagan juego, tienen Vds "+segundosAzar+" segundos");
			Thread.sleep(1000*segundosAzar);
			
			
			System.out.println("Ya no va más, señores. ¡Girando!");
			estadoRuleta=Estado.RULETA_GIRANDO;
			Thread.sleep(3000);
			numeroGanador=generador.nextInt(37);
			System.out.println("El número ganador es el :"+numeroGanador);
			estadoRuleta=Estado.PAGANDO_APUESTAS;
			this.comunicarNumeroGanador(numeroGanador);
		}
	}
	
	public void simular(int jugadoresPar) throws InterruptedException{
		Thread[] hilosJugadores=new Thread[jugadoresPar];
		for (int i=0; i<jugadoresPar; i++){
			JugadorParImpar jugador=new JugadorParImpar(1000, this);
			hilosJugadores[i]=new Thread ( jugador );
			hilosJugadores[i].start();
		}
		this.girarRuleta();
	}
	public static void main(String[] args){
		Banca b=new Banca(50000);
		b.simular(5);
	}
}
