package transmisionficheros;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class EmisorFicheros {
    public static void emitir(String nombreFichero,
            Socket conexionSalida) throws FileNotFoundException, IOException{
        final int TAM_BUFFER=8192;
        byte[] buffer=new byte[TAM_BUFFER];
        IndicadorProgreso indicador=new IndicadorProgreso();
        FileInputStream fis=new FileInputStream(nombreFichero);
        OutputStream flujoSalida=conexionSalida.getOutputStream();
        int bytes_leidos=fis.read(buffer);
        System.out.println("Enviando...");
        while (bytes_leidos!=-1){
            System.out.print("\b"+indicador.siguienteCaracter());
            flujoSalida.write(buffer, 0, bytes_leidos);
            flujoSalida.flush();
            bytes_leidos=fis.read(buffer);
        }   
    }
    public static void main(String[] args) throws IOException{
        String destinatario="127.0.0.1";
        int puerto=9876;
        String fichero="/home/usuario/repos/linux-tools-common_4.8.0-59.64-oscar_all.deb";
        InetSocketAddress inetAddress=new InetSocketAddress(
                destinatario, puerto);
        Socket conexion=new Socket();
        conexion.connect(inetAddress);
        emitir(fichero, conexion);
        conexion.close();
    }
}
