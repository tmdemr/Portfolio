int lightPin = 0; // 센서 핀 정의
int ledPin=11; //  LED 핀 정의

void setup()
{
    Serial.begin(9600); 
    pinMode( ledPin, OUTPUT );
}

void loop()
{
    Serial.println(analogRead(lightPin));
    analogWrite(ledPin, analogRead(lightPin)/2);
    delay(10); // 정확한 반응을 위한 딜레이 부여 
}
