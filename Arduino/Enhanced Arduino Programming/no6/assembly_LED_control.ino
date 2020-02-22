void setup() {
    DDRB = 1<<5;
    DDRB &= ~(1<<4);
}

void loop() {
    int press = (PINB >> 4) & 0x1;   //  B포트의 4pin로 입력을 받음(디지털 12번 핀)
    if (press != 1)
        PORTB |= 1<<5;            // B포트의 5pin에 High 출력(디지털 13번 핀)
    else
        PORTB &= ~(1<<5);         // B포트의 5pin에 Low 출력
    delay(100);
}
