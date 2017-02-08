public class JugadorClasico extends Jugador {

    int numeroElegido;

    public JugadorClasico(long saldoInicial, Banca b) {
        super(saldoInicial, b);
    }

    @Override
    public void comunicarNumero(int numero) {
        /* No hace falta comprobar si el numero es
		 * 0, ya que este jugador no lo elige nunca */
        if (numero == numeroElegido) {
            System.out.println(nombreHilo + ": �Gana 36 veces lo jugado, 360 euros");
            banca.restarSaldo(360);
            sumarSaldo(360);
        }
        System.out.println(nombreHilo + " queda con un saldo de " + saldo);
        apuestaRealizada = false;
    }

    @Override
    public void hacerApuesta() {
        if (!banca.aceptaApuestas())
            return;
        if (apuestaRealizada)
            return;
        /* Elegimos del 1 al 36 (no se puede elegir el 0*/
        numeroElegido = 1 + generador.nextInt(36);
        banca.sumarSaldo(10);
        restarSaldo(10);
        apuestaRealizada = true;
        banca.aceptarApuesta(this);
    }
}
