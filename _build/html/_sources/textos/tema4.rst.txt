Generación de servicios en red
===============================


Una vez vistas las comunicaciones en Java a través de sockets podemos utilizar dicho conocimiento para dar un paso más allá y acceder a servicios de red habituales. En las siguientes secciones desglosamos el funcionamiento de los protocoles y proporcionamos algunas clases básicas para realizar dichos accesos.


Telnet/SSH
----------------

Aunque hoy está prácticamente obsoleto, Telnet fue un servicio de administración remota que permitía conectarse a máquinas UNIX desde un sitio remoto, permitiendo enviar comandos y ver el resultado de dichos comandos en nuestro terminal *como si estuviésemos sentados en la máquina administrada*

El gran problema de Telnet fue **la seguridad**. Telnet no enviaba datos cifrados por lo que cualquier persona con un *sniffer* podía capturar el tráfico de una sesión y ver no solamente los comandos sino también los usuarios y contraseñas enviados a través de la red. Por ello, Telnet prácticamente no se usa hoy en día y ha sido sustituido "de facto" por SSH, que hace lo mismo pero cifrando la comunicación con criptografía de clave pública.

FTP
----------------

FTP significa "File Transfer Protocol" y es un protocolo orientado a comandos pensado para descargar ficheros. Podría decirse que también tiende a desaparecer, ya que cada vez más a menudo se usa el navegador (con HTTP) para descargar ficheros. En realidad, algunos navegadores, como Mozilla Firefox siguen implementando el protocolo FTP, permitiendo así el descargar ficheros como si en realidad estuviésemos usando comandos.

Cabe destacar que FTP tiene dos modalidades de uso: pasivo y activo. La diferencia entre activo y pasivo está en quien inicia las conexiones. Por defecto, los router no suelen permitir la entrada de conexiones iniciadas desde el exterior (aunque sí permiten que entren datos del exterior si desde el interior hemos iniciado previamente la conexion). Cuando se definió FTP como protocolo, se daba por sentado que todo el mundo aceptaría conexiones siempre, por lo cual en el FTP normal (el activo) se permite que el servidor inicie una conexión con nosotros para enviarnos un fichero. En FTP hay dos conexiones, una para enviar comandos (que inician los cliente) y otra (que inicia el servidor) para recibir el archivo. Podría decirse que en el FTP normal/activo "no se descarga" sino que "el servidor envía un fichero".

Cuando la seguridad empezó a preocupar a todo el mundo los fabricantes y compañías telefónicas empezaron a distribuir routers en los que por defecto no se aceptaban conexiones empezadas fuera de nuestra red, así que de repente el FTP empezó mostrar un comportamiento peculiar que podría resumirse en la frase  "se pueden enviar comandos pero no descargar ficheros". Ese error tiene su lógica porque la conexión de comandos la inicia el cliente pero el servidor inicia la conexión (mejor dicho, lo intenta) para enviar el fichero al cliente. Debido a estos problemas  se desarrolló el  "modo pasivo", que consiste en que ahora el cliente inicia una conexión para enviar comandos e inicia otra conexión para descargar el fichero. Como la conexión de descarga se inicia "dentro de la red", el router autoriza la salida, toma nota de la conexión y cuando llega la respuesta, la deja pasar (porque observa que la conexión no se ha iniciado fuera, sino que es la respuesta a una conexión iniciada dentro). Como puede deducirse se llama pasivo porque el servidor ya no es activo, sino que se limita a recibir conexiones.

HTTP
----------------
El protocolo para la transferencia de hipertexto (HyperText Transfer Protocol) es un protocolo que en su momento se diseñó para que los navegadores (clientes web) se conectasen a servidores y descargasen archivos HTML. Sin embargo, su uso se ha popularizado en otros ámbitos como son la creación de aplicaciones web, es decir aplicaciones pensadas para ser manejadas desde un navegador.

Desde mayo de 2015, HTTP va por su versión 2.0 que incluye mejoras en la latencia de los tiempos de respuesta y mejoras en el empaquetado de los datos.

HTTP es un protocolo basado en texto.  A modo de ejemplo se incluye a continuación un ejemplo de petición (copiada de Wikipedia):

.. code-block:: none

    GET /index.html HTTP/1.1
    Host: www.example.com
    Referer: www.google.com
    User-Agent: Mozilla/5.0 (X11; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0
    Connection: keep-alive
    [Línea en blanco]
    
Y a continuación se muestra una respuesta (ejemplo copiado también de Wikipedia):

.. code-block:: none

    HTTP/1.1 200 OK
    Date: Fri, 31 Dec 2003 23:59:59 GMT
    Content-Type: text/html
    Content-Length: 1221
    
    <html lang="es">
    <head>
    <meta charset="utf-8">
    <title>Título del sitio</title>
    </head>
    <body>
    <h1>Página </h1>
        ...
    </body>
    </html>

POP3
------------

POP3 significa "Post Office Protocol version 3" y **está pensando fundamentalmente** para descargar cliente desde un servidor de correo al ordenador de un cliente que habitualmente utiliza Outlook, Mozilla Thunderbird o algún otro cliente de correo. Las versiones más antiguas no usaban mecanismos de cifrado, pero al igual que Telnet, POP3 ha necesitado cambiar para manejar correctamente la seguridad (aunque Telnet ha sido sustituido por otro protocolo y POP3 lo que ha hecho ha sido incorporar extensiones).


SMTP
-----------

Al contrario que POP3, está pensado sobre todo para **enviar correo**. Esto significa que quienes usan SMTP son máquinas que están en una de estas situaciones.

* Cliente de correo (que supongamos que tiene el usuario "pepe") de un servidor de correo (por ejemplo "hotmail.es") y que quiere enviar un mensaje a otro usuario "john" que tiene su cuenta en "gmail.com". Esto significa que si "pepe" escribe un mensaje en su Outlook y quiere enviarlo a "john@gmail.com" primero tiene que enviar el mensaje desde su ordenador a "hotmail.es" y pedirle que lo entregue. La subida del correo desde el ordenador de "pepe" a "hotmail.es" **se hace mediante SMTP**.

* Servidor de correo que quiere enviar mensaje a otro servidor. En el ejemplo anterior "hotmail.es" recibe un correo que debe entregar a su destinatario, pero como dicho destinatario está en otro servidor debe entregarlo a "gmail.com" que comprobará si tiene un usuario "john" y si es así recibirá el mensaje. **Todo este proceso también se hace mediante SMTP**

Ejemplo de base: supongamos que deseamos crear un programa Java que al ser lanzado permita enviar un mensaje con un fichero del cual nos dan la ruta y que en el cuerpo del mensaje indique el nombre del archivo. El programa debe ser capaz de enviar el email a varios destinatarios y debe ser capaz también de enviar varios adjuntos a la vez.

.. WARNING::
   Si utilizamos este código con un servidor público (GMail, Yahoo, Hotmail) debemos asegurarnos de que configuramos nuestra cuenta para permitir el acceso a aplicaciones desconocidas por dichos servidores. La forma de configurarlo varía de unos servicios a otros
   
.. DANGER::
   Una vez activado el acceso a aplicaciones desconocidas GMail permitirá a nuestra aplicación enviar y recibir emails con nuestra cuenta de correo, pero existe el riesgo de que nuestra cuenta se marque como "generadora de spam". Se recomienda usar cuentas de prueba con estos programas.
   

Librerías de clases y componentes.
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Para crear este programa vamos a usar las siguientes bibliotecas de libre distribución:

* La biblioteca JavaMail. Curiosamente, no se distribuye con el JDK, sino que debe descargarse el JAR por separado. 
* La biblioteca Apache Commons Email. Es una biblioteca de libre distribución que facilita el desarrollo de aplicaciones que necesiten enviar o recibir email.

Utilización de objetos predefinidos.
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

El objeto fundamental es la clase ``Email``, proporcionado por Apache Commons Email. Esta clase no tiene propiedades de interés y no está orientada a eventos, sin embargo tiene algunos métodos interesantes que además son bastante autoexplicativos.

* El método ``setHostname`` permite configurar el servidor SMTP de nuestro proveedor de correo.
* El método ``setSmtpPort`` permite indicar el número de puerto en el que el servidor de correo escucha. Para conexiones SMTPS (SMTP seguro) el puerto es el 465.
* El método ``setAuthenticator`` permite indicar el objeto que se encargará de configurar la autenticación. Suele usar un objeto básico de la clase ``DefaultAuthenticator``.
* El método ``setSslOnConect`` se usa para indicar si se establece una conexión SSL (o TLS) en el momento de conectar


Establecimiento y finalización de conexiones.
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

La biblioteca proporciona de manera transparente el proceso correcto para el establecimiento y finalización de conexiones.

Transmisión de información.
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Toda la transmisión de información es controlada por la biblioteca, liberando al programador de tareas de control.


Programación de aplicaciones cliente.
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

La clase ``Cliente.java`` que se adjunta a continuación presenta un interfaz con dos métodos que permiten enviar correos electrónicos con adjuntos. El código es mejorable pero se ha primado la legibilidad e inteligibilidad del mismo.

.. code-block:: java

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


Programación de servidores.
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Implementación de comunicaciones simultáneas.
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Documentación.
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Depuración.
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Monitorización de tiempos de respuesta.
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
