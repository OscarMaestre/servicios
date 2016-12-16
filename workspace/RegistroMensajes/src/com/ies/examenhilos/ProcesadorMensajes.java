package com.ies.examenhilos;

import java.io.IOException;
import java.io.PrintWriter;

public class ProcesadorMensajes  {
	private PrintWriter pwFicheroTrazas, pwFicheroErrores;
	int numeroDeEvento=0;
	
	
	public ProcesadorMensajes(String ficheroTrazas, String ficheroErrores) throws IOException {
		
		pwFicheroTrazas=Utilidades.getPrintWriter(ficheroTrazas);
		pwFicheroErrores=Utilidades.getPrintWriter(ficheroErrores);
	}
	public synchronized void escribirError(String mensaje){
		Utilidades.escribirMensaje(this.pwFicheroErrores, numeroDeEvento, mensaje);
		System.out.println(numeroDeEvento);
		numeroDeEvento++;
	}
	public synchronized void escribirTraza(String mensaje){
		Utilidades.escribirMensaje(this.pwFicheroTrazas, numeroDeEvento, mensaje);
		System.out.println(numeroDeEvento);
		numeroDeEvento++;
	}
	public void cerrarFicheros(){
		this.pwFicheroErrores.flush();
		this.pwFicheroTrazas.flush();
		this.pwFicheroErrores.close();
		this.pwFicheroTrazas.close();
	}
}
