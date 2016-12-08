package com.ies.casino;

public class JugadorMartingala extends Jugador {
	private int cantidadAApostar;
	private int numeroElegido;
	public JugadorMartingala(long saldoInicial, Banca b) {
		super(saldoInicial, b);
		cantidadAApostar=1;
	}

	@Override
	public void comunicarNumero(int numero) {
		if (numero==0){
			System.out.println(nombreHilo + " pierde "+cantidadAApostar);
			cantidadAApostar=cantidadAApostar*2;
			return ;
		}
		if (numeroElegido==numero){
			int beneficios=cantidadAApostar * 36;
			banca.restarSaldo(beneficios);
			sumarSaldo(beneficios);
			cantidadAApostar=1;
		}
		if (numeroElegido!=numero){
			//System.out.println(nombreHilo + " pierde "+cantidadAApostar);
			cantidadAApostar=cantidadAApostar * 2;
		}
		System.out.println(nombreHilo + " queda con un saldo de " + saldo);
		apuestaRealizada=false;
		
	}

	@Override
	public void hacerApuesta() {
		if (!banca.aceptaApuestas()) return ;
		if (apuestaRealizada) return ;
		/* Elegimos del 1 al 36 (no se puede elegir el 0*/
		numeroElegido=1 + generador.nextInt(36);
		
		banca.sumarSaldo(cantidadAApostar);
		restarSaldo(cantidadAApostar);
		apuestaRealizada=true;
		banca.aceptarApuesta(this);
	}

}
