#include <Separador.h>

#include <Key.h>
#include <Keypad.h>

#include <DHT.h>
#include <LiquidCrystal.h>

#define RS 2
#define E 3
#define D4 4
#define D5 5
#define D6 6
#define D7 7
#define LDR A0

String sms = "";
String no  = "";
String ms  = "";
float t = 0;
float h = 0;
const long A = 1000;     //Resistencia en oscuridad en KΩ
const int B = 15;        //Resistencia a la luz (10 Lux) en KΩ
const int Rc = 10;       //Resistencia calibracion en KΩ
int V;
int i;
const byte numRows= 4; //número de filas en el teclado
const byte numCols= 4; //
Separador s;
String datos [] = {"Bienvenido"};
int numero=0;


DHT dht(8, DHT11);
LiquidCrystal lcd(RS,E,D4,D5,D6,D7);
char keymap[numRows][numCols]= {
     {'1', '2', '3','A'}, 
     {'4', '5', '6','B'}, 
     {'7', '8', '9','C'},
     {'*', '0', '#','D'}
};

// Pines del teclado y como se conectan al Arduino
byte rowPins[numRows] = {9, 10, 11, 12}; //Rows 0 to 3
byte colPins[numCols]= {A1, A2, A3,A4}; //Columns 0 to 2

// Inicializa una instancia de la clase Keypad
Keypad myKeypad= Keypad(makeKeymap(keymap), rowPins, colPins, numRows, numCols);

boolean medicion = true;
boolean seriales = false;
boolean impresion = false;


void setup() {
 lcd.begin(16,1);
 lcd.setCursor(0,0);
 Serial.begin(9600);
 dht.begin();
}

void loop() {

  delay(500);
        t = dht.readTemperature();
        h = dht.readHumidity();
        V = analogRead(LDR);         
        i = ((long)(1024-V)*A*10)/((long)B*Rc*V);
        String temp = (String)t;
        String hum = (String)h;
        String ilu = (String)i;
        String mensaje = ("Temp " + temp +"°C " + "Humedad: "+ hum +"% "+"Luminosidad: " + ilu);
        datos[1] = mensaje;
        
  if(Serial.available()>0){
    delay(100);
    while(Serial.available()>0){
      char caracter = Serial.read();
       if(caracter != '\n'){
        sms.concat(caracter);
      }
    }   
    no = s.separa(sms, ',' , 0);
    int numero= no.toInt();
    ms = s.separa(sms, ',' , 1);
    sms = "";
    datos[numero] = ms;
    //lcd.print(ms);
  }
  sms = "";
  //no="";
  ms="";
  
     
      
    char keypressed = myKeypad.getKey();
    if (keypressed != NO_KEY) {
       if(keypressed == '0'){
        Serial.print("0"); 
        }
       if(keypressed == '1'){
        Serial.print("0"); 
       }
       if(keypressed == '2'){
        Serial.print("0");        
       }
       if(keypressed == '3'){
        Serial.print("0"); 
       }
       if(keypressed == '4'){
        Serial.print("0");   
       }
       if(keypressed == '5'){
        Serial.print("0");  
       }
       if(keypressed == '6'){
        Serial.print("0");    
       }
       if(keypressed == '7'){
        Serial.print("0");   
       }
       if(keypressed == '8'){
        Serial.print("0");    
       }
       if(keypressed == '9'){
        Serial.print("0");  
       }
       if(keypressed == 'A'){
        Serial.print("0"); 
       }
       if(keypressed == 'B'){
        Serial.print("0"); 
       }
       if(keypressed == 'C'){
        lcd.clear();
        //lcd.print("numero 2");
       }
       if(keypressed == 'D'){
        lcd.clear();
        //lcd.print("numero 4");
       }
       if(keypressed == '*'){
        lcd.clear();
        //impresiones((String)datos[0]);
       }
       if(keypressed == '#'){
        lcd.clear();
        //lcd.print("numero 2");
       }
    }
//}
 
}

void impresiones(String sms){
  lcd.setCursor(0,0);
  lcd.clear();
  lcd.print(sms); 
  
  for (int posicion = 0; posicion < sms.length(); posicion++){
    lcd.scrollDisplayLeft();
    /*if(keypressed = NO_KEY)
    {
    //sms = "";
    lcd.clear();
    break;
    }*/
    
    delay(200);
    
  }

  //Mueve el texto a la derecha tantas veces como su longitud, mas 16, que es el tamaño de la pantalla
  for (int posicion = 0; posicion < (16 + sms.length()); posicion++)
  {
    lcd.scrollDisplayRight();
    /*if(keypressed = NO_KEY){
      //sms = "";
      lcd.clear();
    break;
    }*/
    delay(200);
  }

  //Mueve el texto a la izquierda hasta quedar el su posicion inicial
  for (int posicion = 0; posicion < 16; posicion++)
  {
    lcd.scrollDisplayLeft();
    /*if(keypressed = NO_KEY)
    {
    //sms = "";
    lcd.clear();
    break;
    }*/
    delay(200);
  }
  delay(1000);
  
  
}
