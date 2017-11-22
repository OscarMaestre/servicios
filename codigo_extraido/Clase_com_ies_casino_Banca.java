public class Banca {

    protected long saldo;

    protected boolean enBancarrota;

    protected Random generador;

    protected boolean sePuedenHacerApuestas;

    protected int numeroGanador;

    public enum Estado {

        INICIO, ACEPTANDO_APUESTAS, RULETA_GIRANDO, PAGANDO_APUESTAS, EN_BANCARROTA
    }

    ;

    private Estado estadoRuleta;

    private ArrayList<Jugador> apostadores;

    public Banca(long saldoInicial) {
        saldo = saldoInicial;
        enBancarrota = false;
        estadoRuleta = Estado.INICIO;
        generador = new Random();
        apostadores = new ArrayList<Jugador>();
    }

    public synchronized boolean enBancarrota() {
        return enBancarrota;
    }

    public synchronized void sumarSaldo(long cantidad) {
        saldo = saldo + cantidad;
    }

    public synchronized void restarSaldo(long cantidad) {
        if (saldo - cantidad <= 0) {
            saldo = 0;
            estadoRuleta = Estado.EN_BANCARROTA;
            return;
        }
        saldo = saldo - cantidad;
    }

    public synchronized void aceptarApuesta(Jugador jugador) {
        if (estadoRuleta == Estado.ACEPTANDO_APUESTAS) {
            apostadores.add(jugador);
        }
    }

    public synchronized boolean aceptaApuestas() {
        if (estadoRuleta == Estado.ACEPTANDO_APUESTAS) {
            return true;
        }
        return false;
    }

    public void comunicarNumeroGanador(int numero) {
        /* Al pasar el número a los jugadores, ellos nos
		 * irán restando el saldo que les corresponda por haber ganado */
        int numApostadores = apostadores.size();
        for (Jugador apostador : apostadores) {
            apostador.comunicarNumero(numeroGanador);
        }
        /* Una vez comunicadas todas las apuestas, borramos
		 * el vector. La partida va a volver a empezar */
        apostadores.clear();
    }

    public void girarRuleta() throws InterruptedException {
        int segundosAzar;
        System.out.println("Empieza el juego!");
        while (estadoRuleta != Estado.EN_BANCARROTA) {
            estadoRuleta = Estado.ACEPTANDO_APUESTAS;
            /* Se eligen unos milisegundos al azar para que los jugadores
			 * elijan, aunque quizá no todos puedan llegar a apostar
			 */
            segundosAzar = 1 + generador.nextInt(3);
            System.out.println("Hagan juego, tienen Vds " + segundosAzar + " segundos");
            Thread.sleep(1000 * segundosAzar);
            System.out.println("Ya no va más, señores. ¡Girando!");
            estadoRuleta = Estado.RULETA_GIRANDO;
            Thread.sleep(3000);
            numeroGanador = generador.nextInt(37);
            System.out.println("El número ganador es el :" + numeroGanador);
            estadoRuleta = Estado.PAGANDO_APUESTAS;
            this.comunicarNumeroGanador(numeroGanador);
            System.out.println("El saldo de la banca es ahora:" + saldo);
        }
    }

    public void simular(int jugadoresPar, int jugadoresMartingala, int jugadoresClasicos) throws InterruptedException {
        Thread[] hilosJugadoresPares = new Thread[jugadoresPar];
        for (int i = 0; i < jugadoresPar; i++) {
            JugadorParImpar jugador = new JugadorParImpar(1000, this);
            hilosJugadoresPares[i] = new Thread(jugador);
            hilosJugadoresPares[i].setName("Apostador par/impar " + i);
            hilosJugadoresPares[i].start();
        }
        Thread[] hilosJugadoresMartingala = new Thread[jugadoresMartingala];
        for (int i = 0; i < jugadoresMartingala; i++) {
            JugadorMartingala jugador = new JugadorMartingala(1000, this);
            hilosJugadoresMartingala[i] = new Thread(jugador);
            hilosJugadoresMartingala[i].setName("Apostador martingala " + i);
            hilosJugadoresMartingala[i].start();
        }
        Thread[] hilosJugadoresClasico = new Thread[jugadoresClasicos];
        for (int i = 0; i < jugadoresClasicos; i++) {
            JugadorClasico jugador = new JugadorClasico(1000, this);
            hilosJugadoresClasico[i] = new Thread(jugador);
            hilosJugadoresClasico[i].setName("Apostador clasico " + i);
            hilosJugadoresClasico[i].start();
        }
        this.girarRuleta();
    }

    public static void main(String[] args) throws InterruptedException {
        Banca b = new Banca(50000);
        b.simular(5, 5, 5);
    }
}
