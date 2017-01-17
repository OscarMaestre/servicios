package com.ies.examen.mensajes;

public class GeneradorMensajes implements Runnable {

	private ProcesadorMensajes procMensajes;
	private int numMensajes;
	private String nombre;
	public GeneradorMensajes(ProcesadorMensajes procMensajes) 
	{
		this.procMensajes = procMensajes;
		this.numMensajes = 0;
		
	}
	public void tareaCompleja(){
		
		procMensajes.escribirTraza(
			nombre + 
			" Ini:"+
		Utilidades.anadirCeros(numMensajes)
		);
		numMensajes++;
		procMensajes.escribirTraza(
				nombre +
				" Med:"+
			Utilidades.anadirCeros(numMensajes)
			);
		int mensajesTotales=procMensajes.getNumMensajes();
		if (mensajesTotales%2!=0){
			procMensajes.escribirTraza("Voy por un numero impar");
		}
		
		
		numMensajes++;
		procMensajes.escribirTraza(
				nombre +
				" Fin:"+
			Utilidades.anadirCeros(numMensajes)
			);
		
		numMensajes++;
	}
	
	public void tareaCompleja2(){
		int mensajesTotales;
		String mensaje;
		for (int i=0; i<1000; i++){
			mensajesTotales=procMensajes.getNumMensajes();
			mensaje="Soy el hilo "+ nombre;
			mensaje+="y he leido el ";
			mensaje+="numMensaje ";
			mensaje+=mensajesTotales;
			procMensajes.escribirTraza(
					mensaje
			);
		}
	}
	
	@Override
	public void run() {
		nombre=Thread.currentThread().getName();
		for (int i=0; i<10; i++){
			tareaCompleja();
		}
	}

}
