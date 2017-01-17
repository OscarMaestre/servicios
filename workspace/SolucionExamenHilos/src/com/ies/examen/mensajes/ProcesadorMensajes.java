package com.ies.examen.mensajes;

import java.io.IOException;
import java.io.PrintWriter;

public class ProcesadorMensajes {
	private int numMensajes;
	private PrintWriter pw;
	public ProcesadorMensajes(
		String nombreFichero) throws IOException{
		pw=Utilidades.getPrintWriter(
				nombreFichero);
		numMensajes=0;
	}
	public synchronized 
		void escribirTraza(String msg){
		//Se almacena el mensaje
		pw.println(msg); 
		//Hilo 1
		
		//Leer numMensajes
		//Incrementar numMensajes
		//Almacenar numMensajes 
		numMensajes=numMensajes+1;
	}
	
	public int getNumMensajes(){
		//Hilo 2
		return numMensajes;
	}
	
	public void cerrarFichero(){
		pw.close();
	}
}
