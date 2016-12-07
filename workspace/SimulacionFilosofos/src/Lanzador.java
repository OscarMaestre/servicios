
public class Lanzador {

	public static void main(String[] args) throws InterruptedException {
		final int NUM_ELEMENTOS=5;
		GestorPalillos gestorPalillos;
		gestorPalillos=new GestorPalillos(NUM_ELEMENTOS);
		Thread[] hilos=new Thread[NUM_ELEMENTOS];
		for (int i=0; i<NUM_ELEMENTOS; i++){
			Filosofo filosofo;
			filosofo=new Filosofo(
					gestorPalillos);
			hilos[i]=new Thread ( filosofo );
			hilos[i].start();
			
		}
		
		
		/* A partir de aquí innecesario, ¿por qué?*/
		for (int i=0; i<NUM_ELEMENTOS; i++){
			hilos[i].join();
		}
		System.out.println(
				"Fin del programa. Gracias por usarlo");
		
	}

}
