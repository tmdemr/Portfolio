#include <Stepper.h>

const int stepsPerRevolution = 200;  // // 모터 한 바퀴 회전에 필요한 스텝
Stepper myStepper(stepsPerRevolution, 8, 9, 10, 11);

void setup() {
	myStepper.setSpeed(60); //모터 회전 속도
}
void loop() {
	 myStepper.step(stepsPerRevolution);
  delay(500);
	 myStepper.step(-stepsPerRevolution);
  delay(500);
}
