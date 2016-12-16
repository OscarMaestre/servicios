package com.ies.examenhilos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Utilidades {
	/* Usado para generar números aleatorios*/
	private static Random generador=new Random();
	
	/* Convierte fechas a una cadena como "2020-12-09 16:04:28"*/
	private static SimpleDateFormat formateadorFechas=
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
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
	
	/**
	 * Dado un número como 3, lo convierte a una cadena en la que
	 * haya ceros por la izquierda hasta que ocupe seis posiciones.
	 * Es decir 3 pasa a ser "000003"
	 * @param numero Numero a formatear
	 * @return
	 */
	public static String anadirCeros(int numero){
		return String.format("%06d", numero);
	}
	/**
	 * Dado un nombre de fichero devuelve un objeto BufferedReader
	 * para poder leer líneas de él
	 * @param nombreFichero Nombre del fichero. Se pueden incluir rutas completas
	 * @return
	 * @throws FileNotFoundException
	 */
	public static BufferedReader getBufferedReader(String nombreFichero) throws FileNotFoundException{
		FileReader 		fr		= new FileReader	 ( nombreFichero );
		BufferedReader 	bfr		= new BufferedReader ( fr );
		return bfr;
	}
	
	/**
	 * Dado un nombre de fichero devuelve un objeto PrintWriter
	 * para poder escribir líneas en él
	 * @param nombreFichero Nombre del fichero. Se pueden incluir rutas completas
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException
	 */
	public static PrintWriter getPrintWriter(String nombreFichero) throws IOException{
		System.out.println("Creando el fichero "+nombreFichero);
		PrintWriter pw			=	new PrintWriter 	( nombreFichero, "UTF-8" );
		return pw;
	}
	
	/**
	 * Dado un nombre de fichero lo escribe en pantalla
	 * @param nombreFichero Nombre del fichero. Se pueden incluir rutas completas
	 * @throws IOException
	 */
	public static void mostrarFicheroEnPantalla(String nombreFichero) throws IOException{
		BufferedReader lector=getBufferedReader(nombreFichero);
		String linea;
		linea=lector.readLine();
		while (linea!=null){
			System.out.println(linea);
			linea=lector.readLine();
		}
	}
	
	/**
	 *  Escribe un mensaje en un PrintWriter asociado a un fichero 
	 * @param pw Fichero asociado
	 * @param pw Numero de evento anotado por un recurso compartido
	 * @param mensaje Texto a escribir. Irá precedido de la fecha (lo
	 * que será muy útil a la hora de ordenar los eventos que se han
	 * apuntado en el fichero)
	 */
	public static void escribirMensaje(PrintWriter pw, String nombreHilo, String mensaje){
		Date instanteActual=new Date();
		String cadenaFecha=formateadorFechas.format(instanteActual);
		
		pw.println(nombreHilo + "----" + cadenaFecha + " --> "+ mensaje );
	}	
	
	/**
	 * Dado un fichero en el que se han apuntado mensajes, ordena
	 * las líneas por orden de fecha y reescribe el fichero
	 * con los eventos ordenados por fecha
	 * @param nombreFichero Nombre del fichero que deseamos ordenar. Se 
	 * presupone que en cada línea la fecha va al comienzo
	 * @throws IOException
	 */
	public static void ordenarLineasFichero(String nombreFichero) throws IOException{
		ComparadorCadenas c=new ComparadorCadenas();
		ArrayList<String> lineas=new ArrayList<String>();
		/* Leemos todas las lineas del fichero y las almacenamos*/
		BufferedReader bfr=getBufferedReader(nombreFichero);
		
		/* Cargamos todas las lineas del fichero en el vector líneas*/
		String linea=bfr.readLine();
		while (linea!=null){
			lineas.add(linea);
			linea=bfr.readLine();
		}
		/* Ordenamos las líneas y borramos el fichero*/
		lineas.sort(c);
		bfr.close();
		
		
		/* Y ahora borramos el fichero escribiendo encima las líneas ordenadas*/
		PrintWriter pw=getPrintWriter(nombreFichero);
		for (String lineaNueva: lineas){
			pw.println(lineaNueva);
		}
		pw.close();
		
	}
}
