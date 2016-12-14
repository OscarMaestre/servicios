package com.ies.examenhilos;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Utilidades {
	/* Usado para generar números aleatorios*/
	private static Random generador=new Random();
	
	/* Convierte fechas a una cadena como "2020-12-09 16:04:28"*/
	private static SimpleDateFormat formateadorFechas=
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/** 
	 * Espera un tiempo al azar entre 0 y 3000 milisegundos
	 */
	public static void esperarTiempoAlAzar(){
		try {
			int msAzar=Utilidades.generador.nextInt(3000);
			Thread.sleep(msAzar);
		} catch (InterruptedException e) {
			//Alguien interrumpió el hilo
			System.out.println("Se interrumpió la ejecución del hilo, saliendo...");
			return ;
		}
	}
	public static void escribirMensaje(PrintWriter pw, String mensaje){
		Date instanteActual=new Date();
		
		String cadenaFecha=formateadorFechas.format(instanteActual);
		
		pw.println(cadenaFecha + " --> " + mensaje );
	}
	
	
}
