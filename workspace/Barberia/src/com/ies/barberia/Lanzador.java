package com.ies.barberia;

public class Lanzador {
	public static void main(String[] args) throws InterruptedException
	{
		GestorSillas gestorSillas;
		gestorSillas=new GestorSillas(3);
		/* Creamos barberos*/
		int numBarberos=2;
		Thread barberos[];
		barberos=new Thread[numBarberos];
		for (int i=0; i<numBarberos;i++){
			Barbero b=new Barbero(
					gestorSillas);
			barberos[i]=new Thread(b);
			barberos[i].start();
			barberos[i].setName("Barbero "+i);
		}
		
		while (true){
			Cliente c=new Cliente(
					gestorSillas);
			Thread hilo=new Thread(c);
			hilo.start();
		}
		
		
	}
}
