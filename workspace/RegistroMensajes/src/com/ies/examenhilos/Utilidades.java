package com.ies.examenhilos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Utilidades {
	/* Usado para generar números aleatorios*/
	private static Random generador=new Random();
	
	/* Convierte fechas a una cadena como "2020-12-09 16:04:28"*/
	private static SimpleDateFormat formateadorFechas=
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
	
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
	 * Dado un nombre de fichero como d:\carpeta\fich.txt nos
	 * devuelve d:\\carpeta\\fich.txt. 
	 * @param nombreFichero
	 * @return
	 */
	public static String incluirBackslashes(String nombreFichero){
		return nombreFichero.replace("\\", "\\\\");
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
	 * @param mensaje Texto a escribir. Irá precedido de la fecha
	 */
	public static void escribirMensaje(PrintWriter pw, String mensaje){
		Date instanteActual=new Date();
		String cadenaFecha=formateadorFechas.format(instanteActual);		
		pw.println(cadenaFecha + " --> " + mensaje );
	}	
	
	public static void ordenarLineasFichero(String nombreFichero) throws IOException{
		ComparadorCadenas c=new ComparadorCadenas();
		ArrayList<String> lineas=new ArrayList<String>();
		/* Leemos todas las lineas del fichero y las almacenamos*/
		BufferedReader bfr=getBufferedReader(nombreFichero);
		
		String linea=bfr.readLine();
		while (linea!=null){
			lineas.add(linea);
			linea=bfr.readLine();
		}
		lineas.sort(c);
		bfr.close();
		
		
		/* Y ahora borramos el fichero y escribimos encima las líneas ordenadas*/
		PrintWriter pw=getPrintWriter(nombreFichero);
		for (String lineaNueva: lineas){
			pw.println(lineaNueva);
		}
		pw.close();
		
	}
}
