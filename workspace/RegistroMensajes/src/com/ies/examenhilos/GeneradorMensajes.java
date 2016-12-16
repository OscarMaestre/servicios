package com.ies.examenhilos;

import java.util.Random;

public class GeneradorMensajes implements Runnable {

	Random generadorNumeros=new Random();
	String nombreHilo;
	ProcesadorMensajes procesadorMensajes;
	public GeneradorMensajes(ProcesadorMensajes p) {
		this.procesadorMensajes=p;
	}
	
	public void tareaCompleja(){
		
		//Utilidades.esperarTiempoAlAzar();
		boolean esError=generadorNumeros.nextBoolean();
		if (esError){
			procesadorMensajes.escribirError(nombreHilo+":Empieza mi mensaje de error");
			procesadorMensajes.escribirError(nombreHilo+":Tuve un error");
			procesadorMensajes.escribirError(nombreHilo+":Fin de mi error");
		} else {
			procesadorMensajes.escribirTraza(nombreHilo+":Empieza mi mensaje de traza");
			procesadorMensajes.escribirTraza(nombreHilo+":Mensaje de traza");
			procesadorMensajes.escribirTraza(nombreHilo+":Fin de mi traza");
		}
		System.out.println(nombreHilo+" acaba de hacer algo");
	}
	@Override
	public void run() {
		nombreHilo=Thread.currentThread().getName();
		for (int i=0; i<=10; i++){
			tareaCompleja();
		}

	}

}
