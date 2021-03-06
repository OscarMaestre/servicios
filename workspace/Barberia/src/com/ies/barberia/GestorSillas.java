package com.ies.barberia;

public class GestorSillas {
	private int numSillas;
	private boolean sillaOcupada[];
	private boolean sillaAtendida[];
	GestorSillas(int num){
		numSillas=num;
		sillaOcupada=new boolean[num];
		sillaAtendida=new boolean[num];
		for (int i=0;i<numSillas; i++){
			sillaOcupada[i]=false;
			sillaAtendida[i]=false;
		}
	}
	
	 
	public synchronized 
		int getSillaLibre()
	{
		for (int i=0; i<numSillas; i++){
			if (sillaOcupada[i]==false){
				sillaOcupada[i]=true;
				return i;
			}
		}
		return -1;
	}
	public synchronized boolean afeitadoCompleto(int pos){
		if (sillaAtendida[pos]==false){
			return true;
		}
		return false;
		
	}
	public  void liberarSilla(int pos){
		sillaOcupada[pos]=false;
		sillaAtendida[pos]=false;
	}
	
	public synchronized int
		getSillaSinAtender(){
		for (int i=0; i<numSillas;i++){
			if ( (sillaOcupada[i]==true) &&
				(sillaAtendida[i]==false)){
				sillaAtendida[i]=true;
				return i;
			}
		}
		return -1;
	}
	
}
