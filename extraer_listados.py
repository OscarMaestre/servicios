#!/usr/bin/python3

from subprocess import call
import os, sys
	
DIRECTORIO_EXTRACCION_LISTADOS="codigo_extraido"
DIRECTORIO_WORKSPACE="workspace"
DIRECTORIO_CODIGO_FUENTE="src"
EXTRAER_CLASE = "class"
EXTRAER_METODO= "method"
lista_extractor=["java", "-jar", "Extractor.jar"]


class Extraccion(object):
	def __init__(self, clase_cualificada, dir_proyecto):
		self.clase			=	clase_cualificada
		self.nombre_clase	=	self.clase.split(".")[-1]
		self.nombre_paquete = 	".".join ( self.clase.split(".")[:-1] )
		print (self.nombre_paquete)
		self.archivo_salida_clase = self.clase.replace(".", "_")+".java"
		print(self.nombre_clase)
		self.dir_proyecto	=	dir_proyecto
		self.archivo_clase=self.clase.replace(".", os.sep) + ".java"
	def get_ruta(self):
		lista=[DIRECTORIO_WORKSPACE, self.dir_proyecto,
			   DIRECTORIO_CODIGO_FUENTE, self.archivo_clase]
		return os.sep.join(lista)
	def ejecutar_lista(self, lista, fichero_salida=None):
		if fichero_salida==None:
			fichero_salida = DIRECTORIO_EXTRACCION_LISTADOS + os.sep + "Clase_"+self.archivo_salida_clase
		with open(fichero_salida, "w", encoding="utf-8"	) as descriptor_salida:
			print ("Ejecutando "+ " ".join(lista)+ " > " + fichero_salida)
			call (lista, stdout=descriptor_salida)
			
			
	def extraer_clase(self):
		ruta_clase=self.get_ruta()
		lista_lanzamiento = lista_extractor + [ruta_clase, self.nombre_clase, EXTRAER_CLASE ]
		fich_salida = DIRECTORIO_EXTRACCION_LISTADOS + os.sep + "Clase_"+self.archivo_salida_clase
		self.ejecutar_lista(lista_lanzamiento, fichero_salida=fich_salida)
		return 
		
	def extraer_clase_con_nombre(self, nombre):
		ruta_clase=self.get_ruta()
		lista_lanzamiento = lista_extractor + [ruta_clase, nombre, EXTRAER_CLASE ]
		nombre_cualificado=self.nombre_paquete+"."+nombre
		print (nombre_cualificado)
		nuevo_archivo_salida=nombre_cualificado.replace(".","_")
		fich_salida = DIRECTORIO_EXTRACCION_LISTADOS + os.sep + "Clase_"+nuevo_archivo_salida+".java"
		self.ejecutar_lista(lista_lanzamiento, fichero_salida=fich_salida)
		return 
		
	def extraer_metodo(self, nombre_metodo):
		ruta_clase=self.get_ruta()
		lista_lanzamiento = lista_extractor + [ruta_clase, nombre_metodo, EXTRAER_METODO ]
		fich_salida = DIRECTORIO_EXTRACCION_LISTADOS + os.sep + "Metodo_"+nombre_metodo+"_"+self.archivo_salida_clase
		self.ejecutar_lista(lista_lanzamiento, fichero_salida=fich_salida)
		return 
		
		


e6=Extraccion(clase_cualificada="com.ies.AreasEnParalelo",
							dir_proyecto="EjemplosHilos")
e6.extraer_clase()
e6.extraer_clase_con_nombre("CalculadorAreas")
e6.extraer_metodo("main")


sys.exit(0)		
		
e1=Extraccion(clase_cualificada="com.utilidades.UtilidadesFicheros",
							dir_proyecto="Utilidades")
e1.extraer_clase()
e1.extraer_metodo	("getBufferedReader")
e1.extraer_metodo	("getPrintWriter")
e1.extraer_metodo	("getLineasFichero")
e1.extraer_metodo	("getSuma")

e2=Extraccion(clase_cualificada="com.ies.ProcesadorFichero",
							dir_proyecto="Multiproceso_Vocales")
e2.extraer_clase()
e2.extraer_metodo("main")
e2.extraer_metodo("hacerRecuento")

		
e3=Extraccion(clase_cualificada="com.ies.Lanzador",
							dir_proyecto="Multiproceso_Vocales")
e3.extraer_clase()
e3.extraer_metodo("main")

e4=Extraccion(clase_cualificada="com.ies.ProcesadorContabilidad",
							dir_proyecto="SumaContabilidades")
e4.extraer_metodo("main")

e5=Extraccion(clase_cualificada="com.ies.LanzadorContabilidades",
							dir_proyecto="SumaContabilidades")
e5.extraer_clase()
e5.extraer_metodo("main")

