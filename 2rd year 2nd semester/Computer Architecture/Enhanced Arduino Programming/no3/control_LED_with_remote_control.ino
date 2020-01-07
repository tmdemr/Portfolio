#include <IRremote.h>

int led = 8;
int RECV_PIN = 11;
IRrecv irrecv(RECV_PIN);
decode_results results;

void setup()
{
    pinMode(led,OUTPUT);
    Serial.begin(9600); //통신속도 조절
    irrecv.enableIRIn(); // Start the receiver
}

void loop() {
    if(irrecv.decode(&results)){
        Serial.println(results.value);
    if (results.value==16753245){ //특정버튼을 눌렀을 때
        digitalWrite(led, HIGH);	//LED가 켜지고
    }
    else{
        digitalWrite(led, LOW);	//그렇지 않다면 LED가 꺼진다
    }
    irrecv.resume();	//다시 다음 값을 입력받는다
}
