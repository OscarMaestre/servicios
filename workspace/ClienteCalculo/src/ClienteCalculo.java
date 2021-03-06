import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClienteCalculo {

	public static BufferedReader 
		getBufferedReader (InputStream is)
	{
		InputStreamReader isr;
		isr=new InputStreamReader(is);
		BufferedReader bfr;
		bfr=new BufferedReader(isr);
		return bfr;
		
	}
	public static PrintWriter
		getPrintWriter(OutputStream os){
		OutputStreamWriter osw;
		osw=new OutputStreamWriter(os);
		PrintWriter pw;
		pw=new PrintWriter(osw);
		return pw;
	}
	public static void main(String[] args) 
	{
		String ipServidor="10.13.0.3";
		int puerto=9876;
		InetSocketAddress direccion;
		direccion=new InetSocketAddress(
				ipServidor, puerto);
		Socket socket=new Socket();
		try {
			socket.connect(direccion);
			InputStream is=socket.getInputStream();
			OutputStream os=socket.getOutputStream();
			byte[] op="+\n".getBytes();
			byte[] num1="12\n".getBytes();
			byte[] num2="21\n".getBytes();
			os.write(op);
			os.write(num1);
			os.write(num2);
			os.flush();	

			BufferedReader bfr;
			bfr=getBufferedReader(is);
			String linea=bfr.readLine();
			System.out.println(linea);
			
		} catch (IOException e) {
			System.out.println(
				"No se estableció conexión " +
				"con el servidor en "+ipServidor
			);
		}

	}

}
