Servicios web
==================


Introducción
--------------------

Un problema que ocurre a menudo es el siguiente: *¿como se puede conseguir
que aplicaciones escritas en distintos lenguajes se intercambien datos
entre sí?*

Supongamos que en un departamento de la empresa se dispone de una
aplicación escrita en C# a la que se le puede suministrar un nombre de mes
y que devuelve los mejores clientes de ese mes. Y supongamos
por otro lado que se dispone de una aplicación Java de escritorio que
permite a los clientes mostrar gráficos de una manera muy cómoda. ¿Como
conseguir que la aplicación Java se integre con la aplicación en C#?

* Una posibilidad sería reescribir la funcionalidad. Tomar la tarea hecha
  en C# y reescribirla en la aplicación Java. Evidentemente, esto llevaría bastante tiempo.

* Otra posibilidad sería inventar algún protocolo que permitiese llevar los
  datos entre distintas aplicaciones, lo cual también puede ser farragoso y
  propenso a errores.

Sin embargo, los servicios web nos ofrecen una mejora sobre la segunda
posibilidad, al proporcionar **un mecanismo estandarizado para intercambiar
datos entre aplicaciones** Los servicios web facilitan la tarea de
construir programas que puedan intercambiarse datos incluso entre distintos
lenguajes así como facilitar dicho intercambio entre aplicaciones que ya se
hubiesen creado sin pensar en esta integración.

Prerrequisitos
---------------

Necesitaremos el siguiente software:

*JDK
*Apache ANT.
*Apache Axis

  
    
    

    

    
