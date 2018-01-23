
package servidordiccionario;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorDiccionario {
    public static void servir(int puerto) throws IOException{
        ServerSocket socketServidor;
        socketServidor=new ServerSocket(puerto);
        while (true){
            Socket conexionEntrante;
            conexionEntrante=socketServidor.accept();
            HiloPeticion p;
            p=new HiloPeticion(conexionEntrante);
            Thread hilo;
            hilo=new Thread(p);
            hilo.start();
        }
    }

    public static void main(String[] args) {

    }
    
}
