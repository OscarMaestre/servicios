public class Cliente implements Runnable {

    Socket conexion;

    Random generador;

    BufferedReader bfr;

    PrintWriter pw;

    String[] palabras = { "Hola", "mundo", "Java", "hilo" };

    int numHilo;

    /* Esta variable se puede comprobar por
	 * parte del lanzador para ver si hubo un fallo
	 */
    boolean algoFallo = false;

    public Cliente() {
        generador = new Random();
        InetSocketAddress direccion;
        direccion = new InetSocketAddress("localhost", 9876);
        Socket conexion;
        conexion = new Socket();
        try {
            conexion.connect(direccion);
            bfr = Utilidades.getFlujoLectura(conexion);
            pw = Utilidades.getFlujoEscritura(conexion);
        } catch (IOException e) {
            algoFallo = true;
        }
    //Fin del catch
    }

    public int getNumHilo() {
        return numHilo;
    }

    public void setNumHilo(int numHilo) {
        this.numHilo = numHilo;
    }

    public boolean servidorFunciona() {
        /* Elegimos una palabra al azar*/
        String palabra = palabras[generador.nextInt(palabras.length)];
        String eco;
        try {
            /* Si no pudimos obtener un flujo
			 * de lectura o escritura con
			 * el servidor es que estaba
			 * colapsado
			 */
            if ((bfr == null) || (pw == null)) {
                /* Indicamos que algo fallo*/
                algoFallo = true;
                /* Y decimos que este
				 * metodo no ha funcionado
				 */
                return false;
            }
            pw.println(palabra);
            pw.flush();
            eco = bfr.readLine();
            if (eco.equals(palabra)) {
                System.out.println("Hilo " + numHilo + " recibio bien:" + eco);
                return true;
            }
        //Fin del if
        } catch (IOException e) {
            return false;
        }
        /*Si se llega a este punto es porque
		 *la palabra devuelta no fue la
		 *que enviamos, o sea que el servidor falló
		*/
        return false;
    }

    @Override
    public void run() {
        if (!servidorFunciona()) {
            /* Imprimimos un mensaje y 
			 * además cambiamos la variable
			 * que indica que hubo un fallo
			 */
            System.out.println("Fallo en el hilo " + numHilo);
            algoFallo = true;
        }
    }

    //Fin del run
    public boolean huboFallo() {
        return algoFallo;
    }
}
