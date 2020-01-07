int led1=8; //8, 9번 핀으로 발광 다이오드 제어
int led2=9;

void setup() {
  // put your setup code here, to run once:
  pinMode(led1,OUTPUT);
  pinMode(led2,OUTPUT);
}

void loop() {
  // put your main code here, to run repeatedly:
  digitalWrite(led1,HIGH); // 동시에 LED 점등
  digitalWrite(led2,HIGH);
  delay(500)             // 0.5초 지연
  digitalWrite(led1,LOW); // 동시에 LED 소등
  digitalWrite(led2,LOW);
  delay(500);
}
