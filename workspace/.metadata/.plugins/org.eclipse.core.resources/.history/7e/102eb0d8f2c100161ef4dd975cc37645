package com.ies.barberia;

import java.util.Random;

public class Barbero implements Runnable {

	GestorSillas gestorSillas;
	int sillaQueAtendemos;
	public Barbero(GestorSillas gestor){
		gestorSillas=gestor;
	}
	public void esperarTiempoAlAzar(){
		Random generador=new Random();
		int msAzar=generador.nextInt(3000);
		try{
			Thread.sleep(msAzar);
		} catch (Exception e){
			
		}
	}
	public int esperarSillaConCliente(){
		int sillaSinAtender;
		sillaSinAtender=gestorSillas.getSillaSinAtender();
		while (sillaSinAtender==-1){
			/*Dormimos y lo volvemos 
			 * a intentar*/
			esperarTiempoAlAzar();
			sillaSinAtender=gestorSillas.getSillaSinAtender();
		//Fin del while	
		}
		return sillaSinAtender;
	//Fin de esperarSillaConCliente
	}
	@Override
	public void run() {
		while (true){
			int silla=this.esperarSillaConCliente();
			System.out.println("Atendiendo silla:"+silla);
			esperarTiempoAlAzar();
			gestorSillas.liberarSilla(silla);
		}
	}

}
