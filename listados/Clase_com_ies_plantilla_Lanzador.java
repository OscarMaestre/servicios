public class Lanzador {

    public Lanzador() {
        /* Poner aqu� todo el c�digo que
        	 * se necesite para inicializar los atributos
        	 */
    }

    /* Nadie de fuera puede llamar a este m�todo. Ni el main*/
    protected void crearRecursosCompartidos() {
        /* Crear aqu� los recursos compartidos. Esto se
        	 * har� ANTES DE LANZAR LOS HILOS */
    }

    /* Nadie de fuera puede llamar a este m�todo. Ni el main*/
    protected void crearHilos() {
        /* Crear aqu� los hilos. Esto se ejecutar�
        	 * DESPUES DE CREAR LOS RECURSOS COMPARTIDOS */
    }

    /* Este m�todo pone en marcha todo el proceso...*/
    public void lanzar() {
        /* Para ello PRIMERO CREA LOS RECURSOS COMPARTIDOS*/
        crearRecursosCompartidos();
        /* Y despues crea los hilos, pas�ndoles, si es necesario
         * el o los objetos compartidos */
        crearHilos();
    }

    public static void main(String[] args) {
        Lanzador lanzador = new Lanzador();
        lanzador.lanzar();
    }
}
