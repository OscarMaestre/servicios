package com.ies.seguridad;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;


public class PruebasSeguridad {
    public static boolean hayGestorSeguridad(){
        SecurityManager gestorSeguridad;
        gestorSeguridad=System.getSecurityManager();
        /* Si es null es que no hay ningún 
        gestor de seguridad en el sistema*/
        if (gestorSeguridad==null) return false;
        /*Si llegamos aquí es que sí hay un gestor*/
        return true;
    }
    
    public static void leerFicheroConSecretos(String ruta) 
            throws FileNotFoundException, IOException
    {
        BufferedReader bfr=new BufferedReader(new FileReader(ruta));
        String linea=bfr.readLine();
        while (linea!=null){
            System.out.println(linea);
            linea=bfr.readLine();
        }
    }
    
    public static void conectarConGoogle() throws IOException{
        Socket socket=new Socket();
        InetSocketAddress direccion=new InetSocketAddress(
                "www.google.es", 80);
        socket.connect(direccion);
    }
    
    public static void main(String[] args) throws IOException{
        leerFicheroConSecretos("../secretos.txt");
        conectarConGoogle();
    }
    
}
