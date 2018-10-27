//Incluimos libreria para separar el numero de mensaje y mensaje que Java envia
#include <Separador.h>
//INcluimos libreria para manejo de teclado amtricial 4x4
#include <Key.h>
#include <Keypad.h>
//Incluimos libreria para uso de sensor de humedad dht11
#include <DHT.h>
//Inlcuimos libreria para uso de lcd
#include <LiquidCrystal.h>

//Asignacion de pines para pantalla lcd
#define RS 2
#define E 3
#define D4 4
#define D5 5
#define D6 6
#define D7 7
#define LDR A0

//Variable que almacena el mensaje recibido de java
String sms = "";
//Variable que almacena el numero de mensaje recibido de java
String no  = "";
//Variable temporar para almacenar mensaje cuando se separa
String ms  = "";
//Variable para guardar la temperatura
float t = 0;
//Variable parfa guardar la humedad
float h = 0;
//Variables para valor de luminosidad
//Resistencia en oscuridad en KΩ
const long A = 1000;
//Resistencia a la luz (10 Lux) en KΩ
const int B = 15;
//Resistencia calibracion en KΩ
const int Rc = 10;
//Variable para almacenar valor de luminosidad
int V;
//Variable para almacenar valor de luminosidad
int i;
//número de filas en el teclado
const byte numRows = 4;
//número de columnas en el teclado
const byte numCols = 4;
//Arreglopara almacenar los mensajes
String datos [16];
int numero = 0;
//Instanciamos la variable para hacer uso de libreria separador
Separador s;

//Tipo de sensor y el pin que se le asigno
DHT dht(8, DHT11);
//Pines que corresponden a pantalla lcd
LiquidCrystal lcd(RS, E, D4, D5, D6, D7);
// Arreglo bidimensional que contiene las teclas del teclado matricial
char keymap[numRows][numCols] = {
  {'1', '2', '3', 'A'},
  {'4', '5', '6', 'B'},
  {'7', '8', '9', 'C'},
  {'*', '0', '#', 'D'}
};

// Pines del teclado y como se conectan al Arduino
byte rowPins[numRows] = {9, 10, 11, 12}; //Rows 0 to 3
byte colPins[numCols] = {A1, A2, A3, A4}; //Columns 0 to 2

// Inicializa una instancia de la clase Keypad
Keypad myKeypad = Keypad(makeKeymap(keymap), rowPins, colPins, numRows, numCols);

void setup() {
  //Definimos el tamaño de la pantalla 16x1
  lcd.begin(16, 1);
  //Definimos las coordenadas donde comenzarfa la impresion del mensaje en pantalla
  lcd.setCursor(0, 0);
  Serial.begin(9600);
  dht.begin();
}

void loop() {

  delay(500);
  //Asigna el valor de la lectura de temperatura
  t = dht.readTemperature();
  //Asigna el valor de la lectura de humedad
  h = dht.readHumidity();
  //Lectura de luminosidad
  V = analogRead(LDR);
  i = ((long)(1024 - V) * A * 10) / ((long)B * Rc * V);
  //Asignamos valores de sensores a variables de tipo String para que puedan mostrarse en pantalla
  String temp = (String)t;
  String hum = (String)h;
  String ilu = (String)i;
  String mensaje = ("Temperatura: " + temp + "C " + "Humedad: " + hum + "% " + "Luminosidad: " + ilu);
  //El mensaje de temperatura se guarda en posicion 1
  datos[1] = mensaje;

  if (Serial.available() > 0) {
    delay(100);
    // Si el puerto serial esta recibiendo informacion concatena los caracteres recibidos en una variable String
    while (Serial.available() > 0) {
      char caracter = Serial.read();
      if (caracter != '\n') {
        sms.concat(caracter);
      }
    }
    //Separa el mensaje recibido, el cual nos retorna dos valores. La primer parte contiene el numero de mensaje
    //que sera la posicion asignada dentro del arreglo y la segunda parte el mensaje.
    no = s.separa(sms, ',' , 0);
    int numero = no.toInt();
    ms = s.separa(sms, ',' , 1);
    sms = "";
    datos[numero] = ms;

  }
  //Comprueba que una tecla este presionada y comprueba el valor de dicha tecla.
  //Una vez comprobado el valor imprime el mensaje almacenado en esa posicion del arreglo.
  //En caso de que la posicion no contenga un mensaje, se le indica al usuario.
  char keypressed = myKeypad.getKey();
  if (keypressed != NO_KEY) {
    if (keypressed == '0') {
      lcd.clear();
      impresiones("Bienvenido");
    }
    if (keypressed == '1') {
      lcd.clear();
      if ((String)datos[1] != "") {
        impresiones((String)datos[1]);
      } else {
        lcd.print("No hay Mensaje");
      }
    }
    if (keypressed == '2') {
      lcd.clear();
      if ((String)datos[2] != "") {
        impresiones((String)datos[2]);
      } else {
        lcd.print("No hay Mensaje");
      }
    }
    if (keypressed == '3') {
      lcd.clear();
      if ((String)datos[3] != "") {
        impresiones((String)datos[3]);
      } else {
        lcd.print("No hay Mensaje");
      }
    }
    if (keypressed == '4') {
      lcd.clear();
      if ((String)datos[4] != "") {
        impresiones((String)datos[4]);
      } else {
        lcd.print("No hay Mensaje");
      }
    }
    if (keypressed == '5') {
      lcd.clear();
      if ((String)datos[5] != "") {
        impresiones((String)datos[5]);
      } else {
        lcd.print("No hay Mensaje");
      }
    }
    if (keypressed == '6') {
      lcd.clear();
      if ((String)datos[6] != "") {
        impresiones((String)datos[6]);
      } else {
        lcd.print("No hay Mensaje");
      }
    }
    if (keypressed == '7') {
      lcd.clear();
      if ((String)datos[7] != "") {
        impresiones((String)datos[7]);
      } else {
        lcd.print("No hay Mensaje");
      }
    }
    if (keypressed == '8') {
      lcd.clear();
      if ((String)datos[8] != "") {
        impresiones((String)datos[8]);
      } else {
        lcd.print("No hay Mensaje");
      }
    }
    if (keypressed == '9') {
      lcd.clear();
      if ((String)datos[8] != "") {
        impresiones((String)datos[8]);
      } else {
        lcd.print("No hay Mensaje");
      }
    }
    if (keypressed == 'A') {
      if ((String)datos[10] != "") {
        impresiones((String)datos[10]);
      } else {
        lcd.print("No hay Mensaje");
      } lcd.clear();

    }
    if (keypressed == 'B') {
      lcd.clear();
      if ((String)datos[11] != "") {
        impresiones((String)datos[11]);
      } else {
        lcd.print("No hay Mensaje");
      }
    }
    if (keypressed == 'C') {
      lcd.clear();
      if ((String)datos[12] != "") {
        impresiones((String)datos[12]);
      } else {
        lcd.print("No hay Mensaje");
      }
    }
    if (keypressed == 'D') {
      lcd.clear();
      if ((String)datos[13] != "") {
        impresiones((String)datos[13]);
      } else {
        lcd.print("No hay Mensaje");
      }
    }
    if (keypressed == '*') {
      lcd.clear();
      if ((String)datos[14] != "") {
        impresiones((String)datos[14]);
      } else {
        lcd.print("No hay Mensaje");
      }
    }
    if (keypressed == '#') {
      lcd.clear();
      if ((String)datos[15] != "") {
        impresiones((String)datos[15]);
      } else {
        lcd.print("No hay Mensaje");
      }
    }
  }
  //}

}
//Metodo para impresion de mensaje en pantalla el cual recibe a mostrar en pantalla
void impresiones(String sms) {
  //Se mueve el cursor a la posicion 0,0 donde comenzara kla impresion del mensaje
  lcd.setCursor(0, 0);
  lcd.clear();
  lcd.print(sms);
  for (int posicion = 0; posicion < sms.length(); posicion++) {
    lcd.scrollDisplayLeft();
    delay(200);
  }

  //Mueve el texto a la derecha tantas veces como su longitud, mas 16, que es el tamaño de la pantalla
  for (int posicion = 0; posicion < (16 + sms.length()); posicion++)
  {
    lcd.scrollDisplayRight();
    delay(200);
  }

  //Mueve el texto a la izquierda hasta quedar el su posicion inicial
  for (int posicion = 0; posicion < 16; posicion++)
  {
    lcd.scrollDisplayLeft();
    delay(200);
  }
  delay(1000);
}
