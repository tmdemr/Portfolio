# -*- coding: utf-8 -*-


# 3
def logger(msg):

    def log_message():
        print ('Log: ', msg)

    return log_message # log_message 함수를 반환.

log_hi = logger('Hi')
print (log_hi) # log_message 오브젝트가 출력됨.
log_hi() # "Log: Hi"가 출력됨.


'''
msg와 같은 지역 변수값은 함수가 호출된 이후에 메모리상에서 사라지므로
다시 참조가 불가능한데, msg 변수에 할당됐던 'Hi'값이 logger 함수가 종료된
이후에도 참조가 됐음.

이런 log_message와 같은 함수를 "클로저 (closure)"라고 부르며,
클로저는 다른 함수의 지역변수를 그 함수가 종료된 이후에도 기억을
할 수 있음.'''

def logger(msg):

    def log_message(): #1
        print ('Log: ', msg)

    return log_message

log_hi = logger('Hi')
print (log_hi) # log_message 오브젝트가 출력됨.
log_hi() # "Log: Hi"가 출력됨.

del logger # 글로벌 네임스페이스에서 logger 오브젝트를 지움.

# logger 오브젝트가 지워진 것을 확인.
try:
    print (logger)
except NameError:
    print ('NameError: logger는 존재하지 않습니다.')

log_hi()  # logger가 지워진 뒤에도 Log: Hi"가 출력됨.


'''
logger 함수가 완전히 삭제된 이후에도, log_message 함수는 'Hi'를
기억하고 있다는 것을 확인할 수 있음.
'''
