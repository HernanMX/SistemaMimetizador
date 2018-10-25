Software
--------------------------------------------
* Arduino IDE
* Ubuntu 16.04
* NetBeans IDE

Librerias
----------------------------------------------
* KeyPad
* DHT
* Separador
* LiquidCrystal
* PanamaHitek

Descripcion del proyecto
----------------------------------------------
Realice un sistema para el despliegue de mensajes en una pantalla LCD, el objetivo es que al llegar un individuo pueda ver en un pequeno tablero electronico una serie de mensajes o notas almacenados.

Cada mensaje tiene las siguientes caracteristicas: Tienes que mostrar un mensaje, que se entienda, con sólo 140 caracteres de espacio. Debe de mostrat fecha y hora en la que fue emitido el mensaje. Ademas debe de mostrar Un mensaje del estado del tiempo (Temperatura, Humedad, luminosidad)

El sistema debe contener: Un interfaz de hardware para navegar entre los mensajes. Una interfaz de software para enviar los mensajes desde la computadora via serial. La interfaz de software debe permitir agreagr mensajes y/o borrarlos.

El sistema es controlado mediante el teclado matricial. Al cargar la aplicacion tiene almacenado dos mensajes por default; "Bienvenido"
y las mediciones de temperatura, humedad y luminosidad.
Se tiene una interfaz de java la cual se encarga de gestionar los nuevos mensajes a cargar en arduino. Ademas de agregarlos, se encarga de eliminar el que el usuario decida. Los mensajes se almacenan en un archivo de texto que son los que posteriormente se cargan en arduino.

Materiales
-----------------------------------------------
* 1 Arduino Unp
* 1 Pantalla LCD 16x2
* 2 resistenias 220 Ohms
* 1 Potenciomentro 10 K
* 1 Sensor DHT11
* 1 Fotoresistencia
* 1 Teclado Matricial 4x4
* Jumpers
* 1 Protoboard

Integrantes del equipo
-----------------------------------------------
* González Alcaraz Hernán Arturo
* Nava Torres Juana Cinthia Lizbeth
* Padilla Guerrero Paul Adrian
![imagen](https://github.com/HernanMX/SistemaMimetizador/blob/master/SIstemaMimetizador/src/Imagenes/Arduino.jpg)
![imagen](https://github.com/HernanMX/SistemaMimetizador/blob/master/SIstemaMimetizador/src/Imagenes/Arduino1.jpg)
![imagen](https://github.com/HernanMX/SistemaMimetizador/blob/master/SIstemaMimetizador/src/Imagenes/Arduino2.jpg)
![imagen](https://github.com/HernanMX/SistemaMimetizador/blob/master/SIstemaMimetizador/src/Imagenes/Interfaz%201.png)
![imagen](https://github.com/HernanMX/SistemaMimetizador/blob/master/SIstemaMimetizador/src/Imagenes/Interfaz2.png)
