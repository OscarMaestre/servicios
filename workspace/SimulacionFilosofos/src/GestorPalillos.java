/* Controla el acceso a los recursos
 * compartidos, en este caso los palillos
 */
public class GestorPalillos {
	/* Un palillo false es uno que
	 * no está cogido*/
	private boolean vectorPalillos[];
	public GestorPalillos(int numPalillos){
		vectorPalillos=new boolean[numPalillos];
		for (int i=0; i<numPalillos; i++){
			vectorPalillos[i]=false;
		}
	}
	
	
	public 
		synchronized ParejaPalillos 
			cogerPalillos()
	{
		ParejaPalillos pareja;
		int pos1, pos2;
		pos1=-1;
		pos2=-1;
		boolean seguirBuscando=true;
		int pos=0;
		while (seguirBuscando) {
			if (vectorPalillos[pos]==false){
				if (pos1==-1){
					pos1=pos;
				} else {
					if (pos2==-1){
						pos2=pos;
					}
				}
			}
			pos=pos+1;
			if (pos>=vectorPalillos.length){
				seguirBuscando=false;
			}
			if ( (pos1!=-1) && (pos2!=-1) ){
				seguirBuscando=false;
			}
		//Fin del while
		}
		if ( (pos1!=-1) && (pos2!=-1) ) {
			vectorPalillos[pos1]=true;
			vectorPalillos[pos2]=true;
			pareja=new ParejaPalillos();
			pareja.setPalilloDer(pos1);
			pareja.setPalilloIzq(pos2);
			return pareja;
		}
		return null;
		
	}
	
	public synchronized void liberarPalillos(
			ParejaPalillos pareja){
		int pos1=pareja.getPalilloDer();
		int pos2=pareja.getPalilloIzq();
		vectorPalillos[pos1]=false;
		vectorPalillos[pos2]=false;
		
	}
}
