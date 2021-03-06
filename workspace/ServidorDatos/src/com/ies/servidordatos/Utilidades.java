package com.ies.servidordatos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Utilidades {
	/* Obtiene un flujo de escritura
	a partir de un socket*/
	public static 
	   PrintWriter getFlujoEscritura
		(Socket s) throws IOException{
		OutputStream os;
		os=s.getOutputStream();
		PrintWriter pw;
		pw=new PrintWriter(os);
		return pw;		
	}
	/* Obtiene un flujo de lectura
	a partir de un socket*/
	public static 
	   BufferedReader 
		getFlujoLectura(Socket s) 
				throws IOException{
		InputStream is;
		is=s.getInputStream();
		InputStreamReader isr;
		isr=new InputStreamReader(is);
		BufferedReader bfr;
		bfr=new BufferedReader(isr);
		return bfr;
	}
}