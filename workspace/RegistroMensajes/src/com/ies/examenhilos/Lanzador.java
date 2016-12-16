package com.ies.examenhilos;

import java.io.IOException;
import java.io.PrintWriter;

public class Lanzador {
	public static void main(String[] args) throws IOException, InterruptedException{
		/* Si no te deja escribir en este fichero, modifícalo.  */
		String rutaFicheroErrores="errores.txt";
		String rutaFicheroTrazas="trazas.txt";
		final int MAX_HILOS = 500;
		
		ProcesadorMensajes p=new ProcesadorMensajes(rutaFicheroTrazas, rutaFicheroErrores);
		Thread[] hilos=new Thread[MAX_HILOS];
		for (int i=0; i<MAX_HILOS; i++){
			hilos[i]=new Thread(new GeneradorMensajes(p));
			hilos[i].setName("Generador "+i);
			hilos[i].start();
		}
		
		System.out.println("Hilos lanzados, esperando la finalización");
		for (int i=0; i<MAX_HILOS; i++){
			hilos[i].join();
		}
		p.cerrarFicheros();
		System.out.println("Hilos finalizados. Compruebe el fichero");
	}
}
