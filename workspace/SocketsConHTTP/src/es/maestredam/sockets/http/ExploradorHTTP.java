package es.maestredam.sockets.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ExploradorHTTP {
	String 			direccion;
	int 			puerto;
	Socket 			conexion;
	BufferedReader 	flujoLectura;
	PrintWriter		flujoEscritura;
	
	public ExploradorHTTP(String direccion, int puerto)  {
		super();
		this.direccion 			= 	direccion;
		this.puerto 			= 	puerto;
		this.conexion			=	null;
		this.flujoEscritura		=	null;
		this.flujoLectura		=	null;
	}
	private void conectar() throws IOException {
		if (this.conexion==null) {
			InetSocketAddress direccionInet=new InetSocketAddress(direccion, puerto);
			this.conexion=new Socket();
			this.conexion.connect(direccionInet);
		}
	}
	public BufferedReader obtenerFlujoLectura() {
		if (this.flujoLectura!=null) {
			return this.flujoLectura;
		}
		try {
			this.conectar();
			InputStream 		is	= this.conexion.getInputStream();
			InputStreamReader	isr	= new InputStreamReader(is);
			this.flujoLectura		= new BufferedReader(isr);
		} catch (IOException e) {
			this.imprimirMensajeErrorIO();
			e.printStackTrace();
		}
		return this.flujoLectura;
	}
	public PrintWriter obtenerFlujoEscritura() {
		if (this.flujoEscritura!=null) {
			return this.flujoEscritura;
		}
		try {
			this.conectar();
			OutputStream		os	=	this.conexion.getOutputStream();
			OutputStreamWriter	osw =	new OutputStreamWriter(os);
			this.flujoEscritura		=	new PrintWriter(osw);
		} catch (IOException e) {
			this.imprimirMensajeErrorIO();
			e.printStackTrace();
		}
		return this.flujoEscritura;
	}
	private void imprimirMensajeErrorIO() {
		String mensaje=
				"Hubo un error de I/O. Algunas posibles causas son:\n" +
				"1. No hay conexión a la red.\n"+
				"2. Aunque haya conexión quizá sea demasiado lenta.\n"+
				"3. La red funciona bien pero hay un fallo en el programa\n";
		System.out.println(mensaje);
	}
	public void enviarGET() {
		try {
			this.conectar();
			this.flujoEscritura=this.obtenerFlujoEscritura();
			this.flujoEscritura.println("GET / HTTP/1.1");
			this.flujoEscritura.println("Host: "+this.direccion);
			this.flujoEscritura.println();
			this.flujoEscritura.flush();
		} catch (IOException e) {
			this.imprimirMensajeErrorIO();
			e.printStackTrace();
		}
		
	}
	
	
}
