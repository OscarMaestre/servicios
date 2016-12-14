package com.ies.examenhilos;

import java.io.IOException;
import java.io.PrintWriter;

public class Lanzador {
	public static void main(String[] args) throws IOException{
		/* Si no te deja escribir en este fichero, modifícalo.  */
		String rutaFichero="c:\\Users\\ogomez\\Downloads\\eclipse\\fich.txt";
		
		
		PrintWriter pwFichero=Utilidades.getPrintWriter(rutaFichero);
		Utilidades.escribirMensaje(pwFichero, "Hola");
		
		/* Recuerda cerrar ficheros en tu aplicación*/
		pwFichero.flush();
		pwFichero.close();
	}
}
