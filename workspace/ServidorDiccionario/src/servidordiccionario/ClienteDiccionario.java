package servidordiccionario;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClienteDiccionario {
    public static String cualVaAntes(
            String pal1, String pal2) throws IOException
    {
        String resultado="";
        InetSocketAddress direccionServidor;
        direccionServidor=new InetSocketAddress(
            "127.0.0.1", 9876);
        Socket conexion=new Socket();
        conexion.connect(direccionServidor);
        /*Obtenemos flujos*/
        PrintWriter salida;
        salida=Utilidades.getPrintWriter(conexion);
        BufferedReader entrada;
        entrada=Utilidades.getBufferedReader(conexion);
        /* Y actuamos*/
        
        salida.println(pal1);
        salida.println(pal2);
        salida.flush();
        resultado=entrada.readLine();
        
        return resultado;
        
    }
    public static void main(String[] args) {

    }
}
