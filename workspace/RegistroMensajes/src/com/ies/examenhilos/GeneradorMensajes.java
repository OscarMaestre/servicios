package com.ies.examenhilos;

import java.util.Random;

public class GeneradorMensajes implements Runnable {

	Random generadorNumeros=new Random();
	String nombreHilo;
	int numMensaje=0;
	ProcesadorMensajes procesadorMensajes;
	public GeneradorMensajes(ProcesadorMensajes p) {
		this.procesadorMensajes=p;
	}
	
	public void escribirTraza(String mensaje){
		String cadNumMensaje;
		cadNumMensaje=Utilidades.anadirCeros(numMensaje);
		procesadorMensajes.escribirTraza(nombreHilo+" Mensaje " + cadNumMensaje +" --> "+ mensaje);
		numMensaje++;
	}
	public void tareaCompleja(){
		
		this.escribirTraza(" Empieza mi mensaje de traza ");
		this.escribirTraza(" Mensaje de traza            ");
		this.escribirTraza(" Fin de mi traza             ");		
		
		System.out.println(nombreHilo+" acaba de hacer algo");
	}
	@Override
	public void run() {
		nombreHilo=Thread.currentThread().getName();
		for (int i=0; i<10; i++){
			tareaCompleja();
		}

	}

}
