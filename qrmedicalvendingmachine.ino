#include <Wire.h> 
#include <LiquidCrystal_I2C.h>
LiquidCrystal_I2C lcd(0x27,20,4);
#define rack1 4
#define rack2 18
#define rack3 5
#define rack4 19
#define liq 23




void setup() {
 Serial.begin(9600);   
Wire.begin(); 
lcd.begin(); 
lcd.backlight();            
lcd.home(); 
pinMode(rack1,OUTPUT);
digitalWrite(rack1,LOW);
pinMode(rack2,OUTPUT);
digitalWrite(rack2,LOW);
pinMode(rack3,OUTPUT);
digitalWrite(rack3,LOW);
pinMode(rack4,OUTPUT);
digitalWrite(rack4,LOW);
pinMode(liq,OUTPUT);
digitalWrite(liq,LOW);



  lcd.setCursor(7,1);lcd.print("MEDICAL");
  lcd.setCursor(2,2);lcd.print("PRODUCT DISPENSOR");
  lcd.setCursor(10,3);lcd.print("WAITING...");

  delay(3000);
  //lcd.clear();
}

void loop() {

 String str;
  if (Serial.available() > 0 ) 
  {
  lcd.clear();
  lcd.setCursor(6,1);lcd.print("GOT INPUT");
  lcd.setCursor(2,2);lcd.print("STARTING PROCESS......");
  delay(1500);


  str = Serial.readStringUntil('\n'); 
  Serial.print(F("input str:")); Serial.println(str);
  for (int i = 0; i <= 5 ; i++) {
    char separator=',';
    int index=i;
    String data=str;
    int found = 0;
    int cnt=0;
    int strIndex[] = { 0, -1 };
    int maxIndex= data.length() - 1;

   for (int i = 0; i <= maxIndex && found <= index; i++) {
      if (data.charAt(i) == separator || i == maxIndex) {
          found++;
          strIndex[0] = strIndex[1] + 1;
          strIndex[1] = (i == maxIndex) ? i+1 : i;
      }
    }
    cnt=( found > index ? data.substring(strIndex[0], strIndex[1]) : "").toInt();
    Serial.print(index);
    Serial.print("-");
    Serial.println(cnt);
    if(index==0){
      if(cnt!=0){
        for(int k=0;k<cnt;k++){
          lcd.clear();
          lcd.setCursor(2,1);lcd.print("DISPENSING ");
          lcd.setCursor(7,2);lcd.print("PRODUCT 1");
          digitalWrite(rack1,HIGH);
          delay(5000);
          digitalWrite(rack1,LOW);
          delay(2000);
        }
      }
    }
    if(index==1){
      if(cnt!=0){
        for(int k=0;k<cnt;k++){
          lcd.clear();
          lcd.setCursor(2,1);lcd.print("DISPENSING ");
          lcd.setCursor(7,2);lcd.print("PRODUCT 2");
          digitalWrite(rack2,HIGH);
          delay(8500);
          digitalWrite(rack2,LOW);
          delay(500);
        }
      }
    }
    if(index==2){
      if(cnt!=0){
        for(int k=0;k<cnt;k++){
          lcd.clear();
          lcd.setCursor(2,1);lcd.print("DISPENSING ");
          lcd.setCursor(7,2);lcd.print("PRODUCT 3");
          digitalWrite(rack3,HIGH);
          delay(8500);
          digitalWrite(rack3,LOW);
          delay(500);
        }
      }
    }
    if(index==3){
      if(cnt!=0){
        for(int k=0;k<cnt;k++){
          lcd.clear();
          lcd.setCursor(2,1);lcd.print("DISPENSING ");
          lcd.setCursor(7,2);lcd.print("PRODUCT 4");
          digitalWrite(rack4,HIGH);
          delay(1100);
          digitalWrite(rack4,LOW);
          delay(500);
        }
      }
    }
    if(index==4){
      if(cnt!=0){
        for(int k=0;k<cnt;k++){
          lcd.clear();
          lcd.setCursor(2,1);lcd.print("DISPENSING ");
          lcd.setCursor(7,2);lcd.print("LIQUID 1");
          digitalWrite(liq,HIGH);
          delay(10000);
          digitalWrite(liq,HIGH);
          delay(500);
        }
      }
    }

  }

  while (Serial.available()) {
  Serial.read();
  }
  lcd.clear();
  lcd.setCursor(7,1);lcd.print("MEDICAL");
  lcd.setCursor(2,2);lcd.print("PRODUCT DISPENSOR");
  lcd.setCursor(10,3);lcd.print("WAITING...");

  delay(1000);
}







}