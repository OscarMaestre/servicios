public class HiloConexion implements Runnable {

    BufferedReader bfr;

    PrintWriter pw;

    Socket socket;

    public HiloConexion(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            /* Nuestro hilo se limita a
			 * recibir una línea y reenviarla
			 * de vuelta al cliente
			 */
            bfr = Utilidades.getFlujoLectura(this.socket);
            pw = Utilidades.getFlujoEscritura(this.socket);
            String lineaRecibida;
            lineaRecibida = bfr.readLine();
            System.out.print(Thread.currentThread().getName());
            System.out.println(" recibio:" + lineaRecibida);
            pw.println(lineaRecibida);
            pw.flush();
        } catch (IOException e) {
            System.out.println("Hubo un fallo al enviar/recibir datos");
        }
    }
    //Fin del run
}
