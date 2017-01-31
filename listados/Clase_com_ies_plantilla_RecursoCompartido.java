public class RecursoCompartido {

    /* Todo objeto compartido tendr� uno o varios valores
     * de importancia. Deber�an declarse aqu� */
    int valorImportante;

    public RecursoCompartido() {
        /* El constructor deber�a inicializar todos los valores
        	 * de importancia y todos los que necesite para
        	 * funcionar
        	 */
    }

    /* Cualquier modificaci�n o consulta
     * de un valor compartido por varios hilos
     * deber�a usar "synchronized" */
    public synchronized void setValor(int
                                      nuevoValor) {
        valorImportante = nuevoValor;
    }

    public synchronized int getValor() {
        return valorImportante;
    }
}
