/* Controla el acceso a los recursos
 * compartidos, en este caso los palillos
 */
public class GestorPalillos {
	/* Un palillo false es uno que
	 * no est� cogido*/
	private boolean vectorPalillos[];
	public GestorPalillos(int numPalillos){
		vectorPalillos=new boolean[numPalillos];
		for (int i=0; i<numPalillos; i++){
			vectorPalillos[i]=false;
		}
	}
	public synchronized void cogerPalillos(int n1, int n2){
		boolean palilloIzq;
		palilloIzq=vectorPalillos[n1];
		boolean palilloDer;
		palilloDer=vectorPalillos[n2];
		
		/* Si no est� cogido el izquierdo
		 * y adem�s no est� cogido el derecho...
		 */
		if ( (!palilloIzq) && (!palilloDer) ){
			vectorPalillos[n1]=true;
			vectorPalillos[n2]=true;
			System.out.println(
				"Cogidos palillos " + 
				n1 +" y " + n2);
		}
	// Fin de cogerPalillos
	}
	
	public synchronized void liberarPalillos(int n1, int n2){
		vectorPalillos[n1]=false;
		vectorPalillos[n2]=false;
	}
}
