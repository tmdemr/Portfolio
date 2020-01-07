int sensorPin = A0; // select the input pin for the potentiometer
int led = 9; // the pin that the LED is attached to

void setup() 
{ 
    pinMode(led, OUTPUT);
    Serial.begin(9600);
} 

void loop() 
{ 
    int sensorValue = 0;
    sensorValue = analogRead(sensorPin); 	//가변저항의 값을 analog로 받음.
    analogWrite(led, sensorValue/4);	//빛이 깜빡이는 것을 방지, 4로 나눔
    Serial.println(sensorValue);
    delay(10); 
}
