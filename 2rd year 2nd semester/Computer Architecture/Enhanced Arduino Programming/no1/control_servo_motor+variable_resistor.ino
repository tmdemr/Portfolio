#include <Servo.h>

Servo sv;

void setup(){
  sv.attach(13);
}

void loop(){
  int val = analogRead(A0);
  int radian = map(val,0,1023,0,180);
  sv.write(radian);
  delay(20);
} 
