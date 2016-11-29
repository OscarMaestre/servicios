public static ArrayList<String> getLineasFichero(String nombreFichero) throws IOException {
    ArrayList<String> lineas = new ArrayList<String>();
    BufferedReader bfr = getBufferedReader(nombreFichero);
    //Leemos l�neas del fichero...
    String linea = bfr.readLine();
    while (linea != null) {
        //Y las a�adimos al array
        lineas.add(linea);
        linea = bfr.readLine();
    }
    //Fin del bucle que lee l�neas
    return lineas;
}
