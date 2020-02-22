int led = 9; 
int brightness = 0; 
int fadeAmount = 5; 

void setup() { 
    pinMode(led, OUTPUT);
} 
void loop() { 
    analogWrite(led, brightness); 
    brightness = brightness + fadeAmount;
    if (brightness == 0 || brightness == 255) { 	// 일정 값에 다다르면 
        fadeAmount = -fadeAmount ; 	// 감소 또는 증가
    }
    delay(30);
} 
