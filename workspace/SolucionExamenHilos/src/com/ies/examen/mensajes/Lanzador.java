package com.ies.examen.mensajes;

import java.io.IOException;

public class Lanzador {

	public static void main(String[] args) throws IOException, InterruptedException {
		String FICHERO="trazas.txt";
		int NUM_HILOS=100;
		ProcesadorMensajes procesadorMensajes;
		procesadorMensajes=new ProcesadorMensajes(
				FICHERO	);
		
		Thread[] hilos=new Thread[NUM_HILOS];
		for (int i=0; i<NUM_HILOS;i++){
			GeneradorMensajes gm;
			gm=new GeneradorMensajes(
					procesadorMensajes
			);
			hilos[i]=new Thread(gm);
			hilos[i].setName("Generador "+i);
			hilos[i].start();
		}
		
		try {
			for (int i=0; i<NUM_HILOS;i++){
				hilos[i].join();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		procesadorMensajes.cerrarFichero();

	}

}
