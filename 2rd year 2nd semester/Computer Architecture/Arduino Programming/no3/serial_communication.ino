int led1 = 7;
int led2 = 6;
int led1_status = LOW; 
int led2_status = LOW; 

void setup()
{
  pinMode(led1, OUTPUT);
  pinMode(led2, OUTPUT);
  digitalWrite(led1, LOW); // 각 LED의 출력을 LOW로 초기화
  digitalWrite(led2, LOW);
  Serial.begin(9600);
}

void loop()
  {
  char read_data;
  if (Serial.available())
  {
    read_data = Serial.read();
        if( read_data == '1' && led1_status ==  LOW)  { // 시리얼 모니터에 1을 입력 하였고,
            digitalWrite(led1, HIGH)                       // led1의 상태가 LOW라면 led1을 HIGH로 바꿈
            led1_status = HIGH;
        
        Serial.println("LED1 ON");	// 시리얼 모니터에 “LED1 ON”이라고 출력.

        }
        else if( read_data == '1' && led1_status == HIGH ) { // 시리얼 모니터에 1을 입력 하였고,
                                                        // led1의 상태가 HIGH라면 불을 끔
            digitalWrite(led1, LOW);
            led1_status = LOW;
            Serial.println("LED1 OFF");
        }

        if( read_data == '2' && led2_status == LOW)
        {
            digitalWrite(led2, HIGH);
            led2_status = HIGH;
            Serial.println("LED2 ON");
        }
        
        else if( read_data == '2' && led2_status == HIGH )
        {
            digitalWrite(led2, LOW);
            led2_status = LOW;
            Serial.println("LED1 OFF");
        }
    }
  delay(10);
}
