package com.ies.examenhilos;

import java.io.IOException;

public class Lanzador {
	public static void main(String[] args) throws IOException, InterruptedException{
		/* Si no te deja escribir en este fichero, modifícalo.  */
		String rutaFicheroTrazas="trazas.txt";
		String rutaFicheroTrazasOrdenadas="trazas_ordenadas.txt";
		final int MAX_HILOS = 500;
		
		ProcesadorMensajes p=new ProcesadorMensajes(rutaFicheroTrazas);
		Thread[] hilos=new Thread[MAX_HILOS];
		for (int i=0; i<MAX_HILOS; i++){
			hilos[i]=new Thread(new GeneradorMensajes(p));
			hilos[i].setName("Generador "+Utilidades.anadirCeros(i));
			hilos[i].start();
		}
		
		System.out.println("Hilos lanzados, esperando la finalización");
		for (int i=0; i<MAX_HILOS; i++){
			hilos[i].join();
		}
		p.cerrarFicheros();
		System.out.println("Hilos finalizados. Compruebe el fichero");
		Utilidades.ordenarLineasFichero(rutaFicheroTrazas, rutaFicheroTrazasOrdenadas);
		
	}
}
