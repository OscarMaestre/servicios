package com.ies.barberia;

import java.util.Random;

public class Cliente implements Runnable{
	GestorSillas gestorSillas;
	public Cliente(GestorSillas gestor){
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
	@Override
	public void run() {
		int sillaLibre;
		sillaLibre=gestorSillas.getSillaLibre();
		if (sillaLibre==-1){
			//System.out.println("Me voy");
			return ;
		}
		boolean terminar;
		terminar=gestorSillas.afeitadoCompleto(sillaLibre);
		while (!terminar){
			//esperarTiempoAlAzar();
			terminar=gestorSillas.afeitadoCompleto(sillaLibre);
		}
		
		
	}
	

}
