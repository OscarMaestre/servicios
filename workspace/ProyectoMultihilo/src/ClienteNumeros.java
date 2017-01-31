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

public class ClienteNumeros {
	public void preguntarPorAlgo(
			BufferedReader bfr,
			PrintWriter pw) throws IOException
	{
		Random generador=new Random();
		int numero=generador.nextInt();
		pw.println(numero);
		pw.flush();
		String respuestaServidor;
		respuestaServidor=bfr.readLine();
		System.out.println("Pregunté por "+
				numero + " y el servidor dijo "+
				respuestaServidor);
		
	}
	public static void main(String[] args) {
		ClienteNumeros cliente;
		cliente=new ClienteNumeros();
		InetSocketAddress direccion;
		direccion=new InetSocketAddress(
				"10.13.0.100", 9876);
		Socket conexion;
		conexion=new Socket();
		try {
			conexion.connect(direccion);
			InputStream is;
			is=conexion.getInputStream();
			InputStreamReader isr;
			isr=new InputStreamReader(is);
			BufferedReader bfr;
			bfr=new BufferedReader(isr);
			
			OutputStream os;
			os=conexion.getOutputStream();
			OutputStreamWriter osw;
			osw=new OutputStreamWriter(os);
			PrintWriter pw;
			pw=new PrintWriter(osw);
			
			
			cliente.preguntarPorAlgo(bfr, pw);
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
