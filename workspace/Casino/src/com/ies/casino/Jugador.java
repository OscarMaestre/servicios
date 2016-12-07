package com.ies.casino;

public abstract class Jugador {
	protected	long 	saldo;
	protected	boolean enBancarrota;
	protected	long 	cantidadApostada;
	
	public Jugador(long saldoInicial, Banca b){
		saldo=saldoInicial;
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
	public void jugar(){
		while (saldo>0){
			hacerApuesta();
		}
		String nombre=Thread.currentThread().getName();
		System.out.println(nombre+": ¡¡Me arruiné!!");
	}
	public abstract void hacerApuesta();
}
