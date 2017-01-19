#!/usr/bin/env python3
#coding=utf-8

from selenium import webdriver
import datetime, sys
from time import sleep

def crear_driver():
    #driver = webdriver.PhantomJS()
    driver=webdriver.Firefox()
    driver.set_window_size(1120, 550)
    driver.get("https://papas.jccm.es/papas/")
    return driver


def get_instante():
    fecha=datetime.datetime.now()
    return fecha.isoformat(sep=" ")

def iniciar_sesion(driver, usuario, clave):
    
    #Hacemos en click en iniciar sesión mediante
    #el sistema de autenticacion
    elementos=driver.find_elements_by_partial_link_text("de JCCM")
    elementos[0].click()
    sleep(3)
    control_usuario=driver.find_element_by_id("username")
    control_usuario.click()
    control_usuario.send_keys(usuario)
    control_usuario=driver.find_element_by_id("password")
    control_usuario.click()
    control_usuario.send_keys(clave)
    
    boton_submit=driver.find_element_by_name("submit")
    boton_submit.click()
    sleep(3)
    boton_aula_virtual=driver.find_element_by_name("AULA_VIRTUAL_2016")
    boton_aula_virtual.click()
    sleep(2)
    driver.get("https://aulavirtual.castillalamancha.es/Curso_1617/course/view.php?id=2730")
    
    
    
def navegar(usuario, clave):
    fecha=get_instante()
    print(fecha)
    driver=crear_driver()
    iniciar_sesion(driver, usuario, clave)
    
if __name__ == '__main__':
    usuario =sys.argv[1]
    clave   =sys.argv[2]
    navegar(usuario, clave)