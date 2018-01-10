package transmisionficheros;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.io.FileOutputStream;
import java.net.ServerSocket;

public class ReceptorFicheros {
    public static void recibir(String fichero, Socket conexion) throws IOException{
        InputStream is=conexion.getInputStream();
        final int TAM_BUFFER=8192;
        FileOutputStream fos=new FileOutputStream(fichero);
        byte[] bytesRecibidos=new byte[TAM_BUFFER];
        System.out.println("Recibiendo...");
        int bytes_recibidos=is.read(bytesRecibidos);
        IndicadorProgreso indicador=new IndicadorProgreso();
        while (bytes_recibidos!=-1){
            System.out.print("\b"+indicador.siguienteCaracter());
            fos.write(bytesRecibidos, 0, bytes_recibidos);
            bytes_recibidos=is.read(bytesRecibidos);
        };
        
    }
    public static void main(String[] args) throws IOException{
        ServerSocket conexionesEntrantes;
        conexionesEntrantes=new ServerSocket(9876);
        Socket conexion=conexionesEntrantes.accept();
        recibir("fichero.deb", conexion);
    }
}
