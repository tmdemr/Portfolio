int ON = LOW;
int OFF = HIGH;

int numerals[10][8] = {
{ON, OFF, ON, ON, OFF, ON, ON, ON}, // 0
{OFF, OFF, OFF, OFF, OFF, ON, ON, OFF}, // 1
{ON, OFF, ON, ON, OFF, OFF, ON, ON}, // 2
{ON, OFF, OFF, ON, OFF, ON, ON, ON}, // 3
{ON, ON, OFF, OFF, OFF, ON, ON, OFF}, // 4
{ON, ON, OFF, ON, OFF, ON, OFF, ON}, // 5
{ON, ON, ON, ON, OFF, ON, OFF, ON}, // 6
{OFF, OFF, OFF, OFF, OFF, ON, ON, ON}, // 7
{ON, ON, ON, ON, OFF, ON, ON, ON}, // 8
{ON, ON, OFF, ON, OFF, ON, ON, ON}, // 9
};

int pins[] = {2, 3, 4, 5, 6, 7, 8, 9};

void setup()
{
for (int i=0; i < 8; i++) {
pinMode(pins[i], OUTPUT);
}
}
void loop(){
    for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 8; j++) {
        digitalWrite(pins[j], numerals[i][j]);
        }
    delay(1000);
    }
}
