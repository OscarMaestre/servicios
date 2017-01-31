/* Clase que ejecuta una tarea permitiendo
 * el paralelismo con otros procesos */
public class Hilo implements Runnable {

    public Hilo() {
        /* Inicializar aqu� todos los atributos de inter�s*/
    }

    /* Es obligatorio implementar este m�todo.
     * Nunca lo llamaremos nosotros, sino Java */
    @Override
    public void run() {
        /* Poner aqu� el c�digo de la tarea
        	 * que deseemos que permita el paralelismo
        	 * con otras
        	 * �Atenci�n! La tarea puede ser de 3 tipos
        	 * 1.- Una tarea simple que ejecute algunas sentencias
        	 * 2.- Una tarea repetitiva que se ejecute mientras se d� una condicion
        	 * 3.- Una tarea repetitiva que se ejecute siempre (bucle infinito)*/
    }
}
