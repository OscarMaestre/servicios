package com.ies.examenhilos;

import java.io.IOException;
import java.lang.management.ManagementFactory;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

public class Lanzador {
	public static void registrarBean(Pruebas p) throws InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException, MalformedObjectNameException{
		/*
		 * A la hora de ejecutar esto es importante definir las siguientes opciones
		 * en la máquina virtual(en Run as->Run configurations
		 * -Dcom.sun.management.jmxremote,  
		 * -Dcom.sun.management.jmxremote.ssl=false, 
		 * -Dcom.sun.management.jmxremote.authenticate=false]
		 */
		MBeanServer servidor=ManagementFactory.getPlatformMBeanServer();
		ObjectName nombre=new ObjectName("com.ies.examenhilos:type=Pruebas");
		servidor.registerMBean(p, nombre);
	}
	public static void main(String[] args) 
			throws IOException, InterruptedException, InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException, MalformedObjectNameException{
		/* Si no te deja escribir en este fichero, modifícalo.  */
		String rutaFicheroTrazas="trazas.txt";
		String rutaFicheroTrazasOrdenadas="trazas_ordenadas.txt";
		final int MAX_HILOS = 500;
		Pruebas pruebas=new Pruebas();
		registrarBean(pruebas);
		ProcesadorMensajes p=new ProcesadorMensajes(pruebas, rutaFicheroTrazas);
		Thread[] hilos=new Thread[MAX_HILOS];
		for (int i=0; i<MAX_HILOS; i++){
			hilos[i]=new Thread(new GeneradorMensajes(p));
			hilos[i].setName("Generador "+Utilidades.anadirCeros(i));
			hilos[i].start();
		}
		
		System.out.println("Hilos lanzados, esperando la finalización");
		for (int i=0; i<MAX_HILOS; i++){
			hilos[i].join();
		}
		p.cerrarFicheros();
		System.out.println("Hilos finalizados. Compruebe el fichero");
		Utilidades.ordenarLineasFichero(rutaFicheroTrazas, rutaFicheroTrazasOrdenadas);
		
	}
}
