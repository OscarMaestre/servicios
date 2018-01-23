package servidordiccionario;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HiloPeticion implements Runnable{
    Socket conexionEntrante;
    public HiloPeticion(Socket conexion){
        conexionEntrante=conexion;
    }

    @Override
    public void run() {
        BufferedReader entrada;
        PrintWriter salida;
        try {
            entrada=Utilidades.getBufferedReader(
                    conexionEntrante);
            salida=Utilidades.getPrintWriter(
                    conexionEntrante);
            String l1=entrada.readLine();
            String l2=entrada.readLine();
            
            int resultado=l1.compareTo(l2);
            if (resultado==-1){
                salida.println(l1);
                salida.flush();
            }
            if (resultado==0){
                salida.println(l1);
                salida.flush();
            }
            
            if (resultado==1){
                salida.println(l2);
                salida.flush();
            }
        } catch (IOException ex) {
            Logger.getLogger(HiloPeticion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
