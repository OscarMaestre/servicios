package com.ies.seguridad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLServerSocket;

public class Servidor {
     public void escuchar(String rutaAlmacen, String clave)
        throws KeyManagementException, UnrecoverableKeyException,
            KeyStoreException, NoSuchAlgorithmException,
            CertificateException, IOException
    {
            SSLServerSocket socketServidor=ComunicacionesSeguras.getSSLServerSocket(rutaAlmacen, clave);
            
            BufferedReader entrada;
            PrintWriter salida;
            while (true){
                    Socket connRecibida=socketServidor.accept();
                    System.out.println("Conexion segura recibida");
                    entrada=
                        new BufferedReader(
                        new InputStreamReader(connRecibida.getInputStream()));
                    salida=
                        new PrintWriter(
                            new OutputStreamWriter(
                            connRecibida.getOutputStream()));
                    String linea=entrada.readLine();
                    salida.println(linea.length());
                    salida.flush();
            }
    }
     public static void main(String[] a){
         try {
             String rutaAlmacen="/home/usuario/repos/borrar/almacen.ks";
             String clave="123456";
             Servidor s=new Servidor();
             s.escuchar(rutaAlmacen, clave);
         } catch (KeyManagementException ex) {
             Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
         } catch (UnrecoverableKeyException ex) {
             Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
         } catch (KeyStoreException ex) {
             Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
         } catch (NoSuchAlgorithmException ex) {
             Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
         } catch (CertificateException ex) {
             Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
}
