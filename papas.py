#!/usr/bin/env python3
#coding=utf-8

from selenium import webdriver

def crear_driver():
    #driver = webdriver.PhantomJS()
    driver=webdriver.Firefox()
    driver.set_window_size(1120, 550)
    driver.get("https://papas.jccm.es/papas/")
    return driver


def iniciar_sesion(driver):
    elementos=driver.find_elements_by_partial_link_text("de JCCM")
    print(elementos)
    elementos[0].click()
    
def navegar():
    driver=crear_driver()
    iniciar_sesion(driver)
    
if __name__ == '__main__':
    navegar()