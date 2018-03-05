package com.es.remitentecorreo;
/* Para poder usar esta clase debes disponer del JAR de la biblioteca
Apache Commons EMail y configurar tu entorno para que la encuentre
correctamente. Puedes encontrar el JAR en 
https://commons.apache.org/email/
*/
public class RemitenteCorreo {
    String   servidorSMTP;
    String   direccionRemitente;
    String   claveRemitente;

    public RemitenteCorreo(String servidorSMTP, String direccionRemitente, String claveRemitente) {
        this.servidorSMTP = servidorSMTP;
        this.direccionRemitente = direccionRemitente;
        this.claveRemitente = claveRemitente;
    }
    public void enviarMensaje(String texto, String destinatario){
        
    }
}
