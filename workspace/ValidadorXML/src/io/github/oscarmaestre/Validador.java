package io.github.oscarmaestre;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Validador {
	public DocumentBuilderFactory 	fabrica;
	public DocumentBuilder			constructorDOM;
	
	public  Validador() throws ParserConfigurationException{
		fabrica=DocumentBuilderFactory.newInstance();
		constructorDOM=fabrica.newDocumentBuilder();
	}
	public boolean esUnaDTD(String texto){
		boolean respuesta=false;
		
		return respuesta;
	}
}
