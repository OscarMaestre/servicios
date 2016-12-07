import java.util.Random;

public class Filosofo implements Runnable{
	GestorPalillos gestorPalillos;
	
	public Filosofo(GestorPalillos g){
		gestorPalillos=g;
		
	}
	@Override
	public void run() {
		Random generador=new Random();
		String nombreFilosofo;
		nombreFilosofo=Thread.currentThread().getName();
		ParejaPalillos parejaAsignada;
		while (true){
			try{
				/* Comer */
				parejaAsignada=gestorPalillos.cogerPalillos();
				while(parejaAsignada==null){
					parejaAsignada=gestorPalillos.cogerPalillos();
				}
				System.out.println("Soy "
					+ nombreFilosofo +" y estoy comiendo" );
				int msAzar=generador.nextInt(3000);
				Thread.sleep(msAzar);
				gestorPalillos.liberarPalillos(
						parejaAsignada);
				/* Filosofar */
				System.out.println("Soy "
						+ nombreFilosofo +
						" y estoy pensando" );
				msAzar=generador.nextInt(3000);
				Thread.sleep(msAzar);
			}
			catch (InterruptedException e){
				System.out.println("Se interrumpió "+
					" al filósofo " + nombreFilosofo);
				System.out.println("Saliendo...");
				return ;
			}
						
		}
		
	}

}
