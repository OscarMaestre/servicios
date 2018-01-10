package es.maestredam.sockets.http;

import java.io.BufferedReader;
import java.io.IOException;

public class Lanzador {

	public static void main(String[] args) throws IOException {
		ExploradorHTTP explorador=new ExploradorHTTP("www.marca.com",80 );
		System.out.println("Enviando GET /");
		explorador.enviarGET();
		BufferedReader flujoLectura=explorador.obtenerFlujoLectura();
		String linea=flujoLectura.readLine();
		while (linea!=null) {
			System.out.println(linea);
			linea=flujoLectura.readLine();
		}
	}

}
