
package transmisionficheros;

public class IndicadorProgreso {
    int num_caracter=0;
    String caracteres="\\|/-";
    char siguienteCaracter(){
        char caracterADevolver=this.caracteres.charAt(num_caracter);
        this.num_caracter=(this.num_caracter+1) % this.caracteres.length();
        return caracterADevolver;
    }
}
