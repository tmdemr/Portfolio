#include <DHT11.h>
#include <Wire.h>
#include <LiquidCrystal_I2C.h>

#define DHT11PIN 2
DHT11 dht11(DHT11PIN);

LiquidCrystal_I2C lcd(0x27, 16, 2); // Set the LCD I2C address

void setup() {
lcd.init();
  lcd.backlight();
}
void loop() {
  lcd.setCursor(0,0); 
  lcd.print("Hum : ");
  lcd.print((float)dht11.getHumidity())

  lcd.setCursor(0,1); 
  lcd.print("Tem : ");
  lcd.print((float)dht11.getTemperature())
  delay(1000);
}
