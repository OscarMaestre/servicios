/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplohilosswing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author participante
 */
public class VentanaConHilos extends Ventana implements ActionListener{

    public VentanaConHilos() {
        super();
        this.btnPulsame.addActionListener(this);
        this.btnCancelar.addActionListener(this);
        this.pbBarraProgreso.setMinimum(0);
        this.pbBarraProgreso.setMaximum(100);
    }
   
    public void ejecutarSinHilos() throws InterruptedException{
        /* Simplemente llamamos a la tarea larga y veremos
        que la venta se "congela" */
        System.out.println("Ejecutando con hilos");
        TareaMuyLarga tarea=new TareaMuyLarga(this.pbBarraProgreso);
        tarea.execute();
        
    }
    public void ejecutarConHilos(){
        System.out.println("Ejecutando con hilos");
        TareaMuyLarga tarea=new TareaMuyLarga(this.pbBarraProgreso);
        tarea.execute();
    }
    
    public void limpiarRadios(){
        this.radioNoUsarHilos.setSelected(false);
        this.radioUsarHilos.setSelected(false);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==this.btnPulsame){
            
            try {
                if (this.radioNoUsarHilos.isSelected()){
                    ejecutarSinHilos();
                }
                if (this.radioUsarHilos.isSelected()){
                    ejecutarConHilos();
                }
            } catch (InterruptedException ex) {
                System.out.println("¡¡Hubo un error en Thread.sleep()!!");
            }
        }
        if (e.getSource()==this.btnCancelar){
            System.out.println("Cancelando...");
        }
        
    }
}
