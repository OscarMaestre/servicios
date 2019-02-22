public class Cliente {

    public void verificarCadenas(BufferedReader bfr, PrintWriter pw) throws IOException {
        pw.println(2);
        pw.println("ABC");
        pw.println("ZZ");
        pw.flush();
        String suma1 = bfr.readLine();
        String suma2 = bfr.readLine();
        System.out.println(suma1);
        System.out.println(suma2);
    }

    public static void main(String[] args) {
        Cliente cliente;
        cliente = new Cliente();
        InetSocketAddress direccion;
        direccion = new InetSocketAddress("10.13.0.100", 9876);
        Socket conexion;
        conexion = new Socket();
        try {
            conexion.connect(direccion);
            BufferedReader bfr;
            bfr = Utilidades.getFlujoLectura(conexion);
            PrintWriter pw;
            pw = Utilidades.getFlujoEscritura(conexion);
            cliente.verificarCadenas(bfr, pw);
            pw.close();
            bfr.close();
            conexion.close();
        } catch (IOException e) {
        //Quiza el servidor no está encendido
        //Quizá lo esté pero su cortafuegos
        //impide conexiones
        //...
        }
    }
}
