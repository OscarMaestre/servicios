package com.ies.sumasverificacion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class HiloConexion implements Runnable{

	BufferedReader bfr;
	PrintWriter pw;
	Socket socket;
	public HiloConexion(Socket socket){
		this.socket=socket;
	}
	public void procesarLineas() 
			throws IOException{
		String lineaNumero=bfr.readLine();
		Integer nLineas=Integer.parseInt(lineaNumero);
		String[] lineas;
		int[]    resultados;
		lineas=new String[nLineas];
		resultados=new int[nLineas];
		for (int i=0;i<nLineas;i++){
			String linea=bfr.readLine();
			lineas[i]=linea;
			resultados[i]=Sumador.sumaSimple(linea);
		}
		for (int i=0;i<nLineas;i++){
			pw.println(resultados[i]);
			pw.flush();
		}
		
		
	}
	public void run() {
		try {
			
			bfr=Utilidades.getFlujoLectura(
					this.socket);
			pw=Utilidades.getFlujoEscritura(
					this.socket);
			procesarLineas();
			
			
		} catch (IOException e) {
			System.out.println(
				"Hubo una interrupción");
		}
		
	}
	

}
