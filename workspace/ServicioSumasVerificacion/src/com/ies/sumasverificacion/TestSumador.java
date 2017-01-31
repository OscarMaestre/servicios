package com.ies.sumasverificacion;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;

public class TestSumador {

	@Test
	public void test() {
		String cad1="ABC";
		int sumaEsperada=198;
		int suma=Sumador.sumaSimple(cad1);
		assertEquals(sumaEsperada,  suma);
		
		cad1="ZZ";
		sumaEsperada=180;
		suma=Sumador.sumaSimple(cad1);
		assertEquals(sumaEsperada,  suma);
		
	}

}
