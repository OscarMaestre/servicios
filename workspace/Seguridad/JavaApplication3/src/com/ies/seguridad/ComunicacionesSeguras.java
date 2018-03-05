package com.ies.seguridad;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

public class ComunicacionesSeguras {
   
    public static SSLSocket getSSLSocket
        (String rutaAlmacen, String clave) throws KeyStoreException,
                FileNotFoundException,
                IOException,
                NoSuchAlgorithmException,
                CertificateException,
                CertificateException,
                KeyManagementException
    {
        SSLSocket socketSeguro=null;
       
        //Paso 1
        KeyStore almacen=KeyStore.getInstance(KeyStore.getDefaultType());
        FileInputStream fis=new FileInputStream(rutaAlmacen);
        almacen.load(fis, clave.toCharArray());
       
       
        //Paso 2: como el cliente se conecta a alguien en quien
        //se supone que confiamos, se necesitan objetos
        //que puedan procesar esos elementos distintos del almacen
        //(los certificados no son como las parejas de claves)
        TrustManagerFactory fabricaGestoresConfianza;
        fabricaGestoresConfianza=TrustManagerFactory.getInstance(
                TrustManagerFactory.getDefaultAlgorithm()
        );
        fabricaGestoresConfianza.init(almacen);
       
        //Paso 3: contexto SSL
        SSLContext contextoSSL=SSLContext.getInstance("TLS");
        contextoSSL.init(null,
                fabricaGestoresConfianza.getTrustManagers()
                , null);
       
        //Paso 4: crear los sockets
        SSLSocketFactory fabricaSockets=(SSLSocketFactory)
                SSLSocketFactory.getDefault();
        socketSeguro=(SSLSocket) fabricaSockets.createSocket();
        return socketSeguro;
    }
   
    public static SSLServerSocket getSSLServerSocket
        (String rutaAlmacen, String clave) throws KeyStoreException, FileNotFoundException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException, KeyManagementException
    {
        SSLServerSocket socketServidorSeguro=null;
       
        //Paso 1:Creamos un objeto almacén que se refiera
        //al almacen pasado.
        KeyStore almacen;
        almacen=KeyStore.getInstance( KeyStore.getDefaultType() );
        FileInputStream ficheroAlmacen;
        ficheroAlmacen=new FileInputStream(rutaAlmacen);
        almacen.load ( ficheroAlmacen, clave.toCharArray());
       
        //Paso 2: necesitamos un objeto que administre las claves
        //que hay dentro de ese almacen
        KeyManager administradorClaves;
        KeyManagerFactory fabricaAdministradores;
        fabricaAdministradores=KeyManagerFactory.getInstance(
                KeyManagerFactory.getDefaultAlgorithm()
        );
        fabricaAdministradores.init(almacen, clave.toCharArray());
       
       
        //Paso 3: Construir un contexto SSL
        SSLContext contextoSSL;
        contextoSSL=SSLContext.getInstance("TLS");
        contextoSSL.init(fabricaAdministradores.getKeyManagers(),
                null, null);
       
       
        //Paso 4: obtener sockets seguros (de server o normales)
        SSLServerSocketFactory fabricaSockets;
        fabricaSockets=(SSLServerSocketFactory)
                SSLServerSocketFactory.getDefault();
        socketServidorSeguro=(SSLServerSocket)
                fabricaSockets.createServerSocket(9876);     
        return socketServidorSeguro;
       
    }
       
       

   
    public static void main(String[] args) throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, FileNotFoundException, CertificateException, UnrecoverableKeyException, KeyManagementException {
        String ruta="C:\\users\\ogomez\\documents\\almacen.ks";
        String clave="123456";
       
        SSLServerSocket socketSeguro;
        socketSeguro=getSSLServerSocket(ruta, clave);
    }
   
}
