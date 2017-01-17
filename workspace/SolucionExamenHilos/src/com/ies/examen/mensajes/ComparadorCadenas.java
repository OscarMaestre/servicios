package com.ies.examen.mensajes;


import java.util.Comparator;

/* Puedes ignorar esta clase. Solo se usa para ordenar
 * cadenas automáticamente en un ArrayList
 */
public class ComparadorCadenas implements Comparator<String> {

	public int compare(String cad1, String cad2) {
		return cad1.compareTo(cad2);
	}

}
