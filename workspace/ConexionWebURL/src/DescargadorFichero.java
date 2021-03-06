import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class DescargadorFichero {
	
	public void descargarFichero(String urlDescarga, String nombreFichero) throws IOException{
		URL url=new URL(urlDescarga);
		InputStream is=url.openStream();
		FileOutputStream fos=new FileOutputStream(nombreFichero);
		byte[] buffer=new byte[1024];
		int bytesLeidos=is.read(buffer, 0, 1024);
		while (bytesLeidos!=0){
			fos.write(buffer, 0, bytesLeidos);
			bytesLeidos=is.read(buffer, 0, 1024);
		}
		fos.close();
	}
	public static void main(String[] args) throws IOException {
		DescargadorFichero d=new DescargadorFichero();
		d.descargarFichero("http://www.google.es", "pagina.html");
	}

}
