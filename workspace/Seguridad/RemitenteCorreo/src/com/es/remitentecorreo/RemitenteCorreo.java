package com.es.remitentecorreo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

/* Para poder usar esta clase debes disponer 
del JAR de la biblioteca Apache Commons EMail y 
las extensiones JavaMail así como configurar tu entorno 
para que encuentre correctamente dichas bibliotecas. 
Puedes encontrar el JAR en https://commons.apache.org/email/
*/
public class RemitenteCorreo {
    private String          servidorSMTP;
    private String          usuarioRemitente;
    private String          claveRemitente;
    private MultiPartEmail  email;

    public RemitenteCorreo(String servidorSMTP, 
            String usuarioRemitente, String claveRemitente) {
        this.servidorSMTP       =   servidorSMTP;
        this.usuarioRemitente   =   usuarioRemitente;
        this.claveRemitente     =   claveRemitente;
        this.email              =   null;
    } //Fin del constructor
    
    //Método usado solo en el interior de la clase
    private void iniciarConexionEmail(){
        email=new MultiPartEmail();
        /*Se indica el servidor del remitente*/
        email.setHostName(servidorSMTP);
        /*Habitualmente el puerto 465 se usa para SMTPS,
        en el que la encriptación se inicia antes de enviar nada*/
        email.setSmtpPort(465);
        
        /*Se configura la autenticación*/
        DefaultAuthenticator sistemaAutenticacion;
        sistemaAutenticacion=new DefaultAuthenticator(
                this.usuarioRemitente,
                this.claveRemitente );
        email.setAuthenticator(sistemaAutenticacion);
        
        /*Se indica que vamos a usar el cifrado
        al inicio de la conexión    */
        email.setSSLOnConnect(true);
    }
    
    private  void configurarParametrosBasicos (
        String asunto, String textoEmail, String destinatario,
            String[] destinatariosCC, String[] destinatariosBCC) throws EmailException 
    {
        /*Se indica el asunto*/
        email.setSubject(asunto);
        /*Se indica el remitente*/
        email.setFrom(this.usuarioRemitente+"@"+this.servidorSMTP);
        /*Se pasa el texto*/
        email.setMsg(textoEmail);
        /*Se configura el destinatario principal*/
        email.addTo(destinatario);
        /*Y se configuran otros posibles destinatarios*/
        if (destinatariosCC!=null){
            email.addCc(destinatariosCC);            
        } 
        if (destinatariosBCC!=null){
            email.addBcc(destinatariosBCC);
        }
    }
    public void enviarMensaje(String asunto, String textoEmail, String destinatario,
            String[] destinatariosCC, String[] destinatariosBCC) 
            throws EmailException
    {
        iniciarConexionEmail();
        this.configurarParametrosBasicos(asunto, textoEmail, 
                destinatario, destinatariosCC, destinatariosBCC);
        
        /*Y se envía el mensaje ;) */
        email.send();
    }
    
    public void enviarMensajeConAdjuntos(String asunto, String textoEmail, String destinatario,
            String[] destinatariosCC, String[] destinatariosBCC, 
            String[] listaRutasArchivo) 
            throws EmailException, FileNotFoundException
    {
        /*Se configura lo basico*/
        iniciarConexionEmail();
        this.configurarParametrosBasicos(asunto, textoEmail, 
                destinatario, destinatariosCC, destinatariosBCC);
        /*Y añadimos los adjuntos*/
        for (String ruta : listaRutasArchivo){
            File fichero=new File(ruta);
            this.email.attach(fichero);
        }
        /*Y se envía el mensaje ;) */
        email.send();
    }
}
