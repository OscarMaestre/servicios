package com.ies.seguridad;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.rmi.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;

public class OtroCliente {
    
    SSLSocket conexion;
    public OtroCliente(String almacen, String clave, String ip, int puerto)
                    throws UnknownHostException, IOException,
                    KeyManagementException, NoSuchAlgorithmException,
                    KeyStoreException, FileNotFoundException, CertificateException
   {

            conexion=ComunicacionesSeguras.getSSLSocket(almacen, clave);
            InetSocketAddress direccion=new InetSocketAddress(ip, puerto);
            conexion.connect(direccion);
    }
    /* Envía un mensaje de prueba para verificar que la conexión
     * SSL es correcta */
    public void conectar() throws IOException{
            System.out.println("Iniciando..");
            BufferedReader entrada;
            PrintWriter salida;
            entrada=new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            salida=new PrintWriter(new OutputStreamWriter(conexion.getOutputStream()));
            /* De esta linea se intenta averiguar la longitud*/
            salida.println("1234567890");
            salida.flush();
            /* Si todo va bien, el servidor nos contesta el numero*/
            String num=entrada.readLine();
            int longitud=Integer.parseInt(num);
            System.out.println("La longitud devuelta es:"+longitud);

    }
     public static void main(String[] a){
         
        try {
            String rutaAlmacen="/home/usuario/repos/borrar/almacen.ks";
            String clave="123456";
            OtroCliente o=new OtroCliente(rutaAlmacen, clave, "127.0.0.1", 9876);
            o.conectar();
        } catch (KeyManagementException ex) {
            Logger.getLogger(OtroCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyStoreException ex) {
            Logger.getLogger(OtroCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(OtroCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CertificateException ex) {
            Logger.getLogger(OtroCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OtroCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
}