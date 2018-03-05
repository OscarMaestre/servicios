/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.es.remitentecorreo;

import java.io.FileNotFoundException;
import org.apache.commons.mail.EmailException;

/**
 *
 * @author usuario
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws EmailException, FileNotFoundException {
        String[] cc={"oscar761001@gmail.com", "ogomezgarcia@gmail.com"};
        String[] bcc={"oscar761001@hotmail.com", "o_gom_gar@hotmail.com"};
        RemitenteCorreo rc=new RemitenteCorreo("smtp.googlemail.com",
        "profesor.oscar.gomez", "atlantis_3031");
        String[] adjuntos={"/home/usuario/repos/servicios/textos/tema5.rst", 
            "/home/usuario/repos/servicios/textos/tema4.rst"};
        rc.enviarMensaje("Probando", "Hola prueba", 
                "profesor.oscar.gomez@gmail.com", cc, bcc);
        rc.enviarMensajeConAdjuntos("Probando", "Hola prueba", 
                "profesor.oscar.gomez@gmail.com", cc, bcc, adjuntos);
    }
    
}
