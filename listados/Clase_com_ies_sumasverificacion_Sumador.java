public class Sumador {

    public static int sumaSimple(String cad) {
        int suma = 0;
        for (int i = 0; i < cad.length(); i++) {
            suma += cad.codePointAt(i);
        }
        return suma;
    }
}
