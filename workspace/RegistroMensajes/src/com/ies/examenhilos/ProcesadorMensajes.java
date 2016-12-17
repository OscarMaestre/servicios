package com.ies.examenhilos;

import java.io.IOException;
import java.io.PrintWriter;

public class ProcesadorMensajes  {
	private PrintWriter pwFicheroTrazas;
	int numeroDeMensaje=0;
	
	
	public ProcesadorMensajes(String ficheroTrazas) throws IOException {
		
		pwFicheroTrazas=Utilidades.getPrintWriter(ficheroTrazas);
	}
	
	public synchronized void escribirTraza(String mensaje){
		Utilidades.escribirMensaje(this.pwFicheroTrazas,  mensaje);
		System.out.println(numeroDeMensaje);
		numeroDeMensaje++;
	}
	public void cerrarFicheros(){
		this.pwFicheroTrazas.flush();
		this.pwFicheroTrazas.close();
	}
}
