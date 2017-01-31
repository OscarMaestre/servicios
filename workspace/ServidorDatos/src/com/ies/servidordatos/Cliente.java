package com.ies.servidordatos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Random;

public class Cliente {
	public void interactuar(
			BufferedReader bfr,
			PrintWriter pw) throws IOException
	{
		
	}
	public void crearCliente(
			BufferedReader bfr,
			PrintWriter pw) throws IOException
	{
		System.out.println("Escribe un codigo ");
		String id;
		id=System.console().readLine();
		System.out.println("Escribe un nombre ");
		String nombre;
		nombre=System.console().readLine();
		pw.println("CREATE");
		pw.println(id);
		pw.println(nombre);
		pw.flush();
	}
	public void consultar(
			BufferedReader bfr,
			PrintWriter pw) throws IOException
	{
		System.out.println("Recuperando datos");
		String num=bfr.readLine();
		Integer nEmpleados=Integer.parseInt(num);
		for (int i=0; i<nEmpleados; i++){
			String id=bfr.readLine();
			String nombre=bfr.readLine();
			System.out.println(id+": "+nombre);
		}
	}
	public static void main(String[] args) {
		Cliente cliente;
		cliente=new Cliente();
		InetSocketAddress direccion;
		direccion=new InetSocketAddress(
				"10.13.0.100", 9876);
		Socket conexion;
		conexion=new Socket();
		try {
			
			conexion.connect(direccion);
			
			BufferedReader bfr;
			bfr=Utilidades.getFlujoLectura(
					conexion);
			PrintWriter pw;
			pw=Utilidades.getFlujoEscritura(conexion);
			cliente.interactuar(bfr, pw);
			pw.close();
			bfr.close();
			conexion.close();			
			
		} catch (IOException e) {
			//Quiza el servidor no está encendido
			//Quizá lo esté pero su cortafuegos
			//impide conexiones
			//...
		}
		
		

	}

}
