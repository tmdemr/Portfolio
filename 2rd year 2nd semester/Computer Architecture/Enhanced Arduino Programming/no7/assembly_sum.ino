#include <Wire.h>
#include <LiquidCrystal_I2C.h>

LiquidCrystal_I2C lcd(0x27, 16, 2); // Set the LCD I2C address

extern "C" {
int sum(int);
}

void setup() {
	 lcd.init();
  lcd.backlight();
	 lcd.setCursor(0,0); 
  lcd.print("The sum is :");
}
void loop() {
  int v;
  v = sum(16);
  lcd.setCursor(0,1); 
  lcd.print(v);
  delay(1000);
}