package com.ies.casino;

import java.util.Random;

public abstract class Jugador implements Runnable{
	protected	long 	saldo;
	protected	boolean enBancarrota;
	protected	long 	cantidadApostada;
	protected 	boolean	apuestaRealizada;
	protected	Banca	banca;
	protected	String	nombreHilo;
	protected	Random	generador;
	public Jugador(long saldoInicial, Banca b){
		saldo=saldoInicial;
		banca=b;
		apuestaRealizada=false;
		nombreHilo=Thread.currentThread().getName();
		generador=new Random();
	}
	public void sumarSaldo(long cantidad){
		saldo = saldo + cantidad;
	}
	public void restarSaldo(long cantidad){
		if (saldo - cantidad <= 0){
			saldo = 0;
			enBancarrota=true;
			return ;
		}
		saldo = saldo - cantidad;
	}
	public boolean enBancarrota(){
		return enBancarrota;
	}
	
	/* Las formas de jugar se implementan 
	 * de distinta forma en función
	 * de los tipos de jugador*/
	public void jugar() throws InterruptedException{
		while (saldo>0){
			int msAzar;
			/* Mientras la ruleta no acepte apuestas, dormimos un 
			 * periodo al azar */
			while (!banca.aceptaApuestas()){
				msAzar=this.generador.nextInt(500);
				Thread.currentThread().sleep(msAzar);
			}
			hacerApuesta();
		}
		String nombre=Thread.currentThread().getName();
		System.out.println(nombre+": ¡¡Me arruiné!!");
	}
	/* Lo usa la banca para comunicarnos el número*/
	public abstract void comunicarNumero(int numero);
	

	public abstract void hacerApuesta();
	
	/* Todos los jugadores hacen lo mismo:
	 * Mientras no estemos en bancarrota ni la
	 * banca tampoco, hacemos apuestas. La banca
	 * nos dirá el número que haya salido y en 
	 * ese momento (y si procede) incrementaremos
	 * nuestro saldo
	 */
	@Override
	public void run() {
		while ( (!banca.enBancarrota) && (!enBancarrota) ){
			hacerApuesta();
		}
		
	}
}
