import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorCalculo {

	
	public static void main(String[] args) {
		ServerSocket socket;
		Socket cliente;
		final int PUERTO=9876;
		try {
			socket=new ServerSocket(PUERTO);
			while (true){
				cliente=socket.accept();
				BufferedReader bfr;
				PrintWriter pw;
				
				InputStream is=cliente.getInputStream();
				OutputStream os=cliente.getOutputStream();
				
				bfr=ClienteCalculo.getBufferedReader(is);
				pw=ClienteCalculo.getPrintWriter(os);
				String op=bfr.readLine();
				String n1=bfr.readLine();
				String n2=bfr.readLine();
				System.out.println(n1 + op + n2);	
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
