package servidordiccionario;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Utilidades {
    public static BufferedReader getBufferedReader(Socket conexion) throws IOException{
        InputStream         is  =   conexion.getInputStream();
        InputStreamReader   isr =   new InputStreamReader(is);
        BufferedReader      bfr =   new BufferedReader(isr);
        return bfr;
    }
    public static PrintWriter getPrintWriter(Socket conexion) throws IOException{
        OutputStream        os   =   conexion.getOutputStream();
        OutputStreamWriter  osw  =   new OutputStreamWriter(os);
        PrintWriter         pw   =   new PrintWriter(osw);
        return pw;
    }
}
