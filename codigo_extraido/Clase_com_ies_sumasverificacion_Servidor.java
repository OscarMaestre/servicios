public class Servidor {

    public void servir() {
        ServerSocket serverSocket;
        final int PUERTO = 9876;
        try {
            serverSocket = new ServerSocket(PUERTO);
            while (true) {
                Socket conexion;
                conexion = serverSocket.accept();
                HiloConexion hiloConexion;
                hiloConexion = new HiloConexion(conexion);
                Thread hilo = new Thread(hiloConexion);
                hilo.start();
            }
        } catch (IOException e) {
        //No se pudo crear el server
        //socket porque no tenemos permisos
        //Se pudo crear pero no fuimos
        //capaces de enviar o recibir nada
        //Todo funcionaba pero el usuario
        //interrumpió
        }
    }

    public static void main(String[] args) {
        Servidor servidor;
        servidor = new Servidor();
        servidor.servir();
    }
}
