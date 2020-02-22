#include<DHT.h>     //DHT.h 라이브러리 추가
DHT dht(12, DHT11); //DHT 설정 dht(핀, DHT종류)

void setup() {
  Serial.begin(9600); //시리얼모니터 시작
}

void loop() {
  delay(3000);
  int tem = dht.readTemperature();  //온도 값 정수형 변수 tem에 저장
  int hum = dht.readHumidity();     //습도 값 정수형 변수 hum에 저장
  Serial.print("Temperature :");    //Temperature : 글자 출력
  Serial.print(tem);                //측정된 온도 값 출력
  Serial.print("C ");               //온도C로 표현
  Serial.print("Humidity : ");
  Serial.print(hum);
  Serial.println("% ");             //온도와 동일
}
