package ejemplohilosswing;


import javax.swing.JProgressBar;
import javax.swing.SwingWorker;


public class TareaMuyLarga extends SwingWorker<Integer, Void>{
    JProgressBar barraProgreso;

    public TareaMuyLarga(JProgressBar barraProgreso) {
        this.barraProgreso = barraProgreso;
    }
    @Override
    protected Integer doInBackground() throws Exception {
        int resultado=tareaLarguiiiisima();
        return resultado;
    }
    
    public int tareaLarguiiiisima() throws InterruptedException{
        int MAX=5;
        int incremento=100/(MAX-1);
        int progresoActual=0;
        for (int i=0; i<5; i++){
            System.out.println("Progreso actual:"+progresoActual+"%");
            barraProgreso.setValue(progresoActual);
            Thread.sleep(1000);
            progresoActual+=incremento;
        }
        System.out.println("Progreso completado");
        return 42;
    }
    
}
