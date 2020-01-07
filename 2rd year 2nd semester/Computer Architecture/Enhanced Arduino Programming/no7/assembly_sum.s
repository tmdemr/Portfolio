.text
.global sum

sum: // 단순 합산 반복. R24보다 크면 반복
  ldi r18, 0    // r18에 0 즉시 적재
  ldi r19, 0
loop:
  add r18, r19  // r18 = r18+r19
  inc r19       // r19 = r19 + 1
  cp r24, r19   // r24 - r19 = 0인지(비교)?
  brge loop     // r24가 r19보다 크거나 같으면 loop 반복
  mov r24, r18  // r24에 r18 복사
  clr r25       // r25 클리어
  ret 
