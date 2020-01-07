#include <LiquidCrystal_I2C.h>

LiquidCrystal_I2C lcd(0x27, 16, 2); // Set the LCD I2C 

int analogPin = 0;    // 워터센서 analog port 0 연결 선언
int buzzer = 13;         // 부저. 디지털 13번 포트 연결 선언
int val = 0;          // 전류변화값 변수선언
int tones = 440;
void setup ()
{
  lcd.init();
  lcd.backlight();
  lcd.setCursor(0,0); 
  
  lcd.print("The Water is ..");
  pinMode(buzzer, OUTPUT);            // 디지털 13번 포트를 LED OUTPUT 으로 설정
  Serial.begin (9600);           // 시리얼모니터 설정
}
 
void loop() // 계속 체크해야 하는 부분
{
  val = analogRead(analogPin);   // analogPin 의 변화값(전류값)을 읽음
 
  if (val > 500)                 // val(전류) 값이 500이 넘으면 (센서 중간정도까지 물이 차올랐을 때)
  {                               
      tone(buzzer, tones);   // 부저 작동(굉장히 시끄럽고 위험한 소리)
      lcd.setCursor(0,1); 
      lcd.print("Stop!!!    "); // 물을 채우는 것을 멈춰야 합니다!
  }
  else                           // val 값이 500이하면
  {
      noTone(buzzer);    // 부저 미작동
      lcd.setCursor(0,1); 
      lcd.print("Flowing.."); // 물을 더 채워야 합니다.
  }
  
  Serial.println(val);      // 시리얼모니터에 전류값 표시(로그 찍기 위함)
  delay (500); // 0.5초 대기.
}
