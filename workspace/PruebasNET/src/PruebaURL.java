import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;

public class PruebaURL {

	public static void main(String[] args) {
		URL url;
		String direccion="https://www.youtube.com/results?search_query=cristiano+ronaldo";
		
		try {
			url=new URL(direccion);
			InputStream is=url.openStream();
			InputStreamReader isr;
			isr=new InputStreamReader(is);
			BufferedReader bfr;
			bfr=new BufferedReader(isr);
			String linea;
			FileWriter fw=new FileWriter("cr.html");
			PrintWriter pw=new PrintWriter(fw);
			while ((linea=bfr.readLine())!=null){
				pw.print(linea);
				
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//El fichero está descargado
		

	}

}
