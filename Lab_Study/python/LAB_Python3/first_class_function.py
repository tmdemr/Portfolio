# -*- coding: utf-8 -*-

'''
일급 함수(First Class Function)란?
    프로그래밍 언어가 함수(function)을 first-class citizen으로
    취급하는 것을 뜻함.
    다시 말해서,
    함수 자체를 인자(argument)로써 다른 함수에 전달하거나,
    다른 함수의 결과값으로 리턴 할 수 있고,
    함수를 변수에 할당하거나 데이터 구조안에 저장할 수 있는 함수.
'''



# 1
'''def square(x):
    return x * x

x = square(5)

print ("\n x = square(5)")
print(x)

y = square

print ("\n square : ")
print (square)
print ("\n y : ")
print (y)


x2 = y(5)

print ("\n x2 = y(5)")
print (x2)



def my_map(func, arg_list): # 첫 번째 인자로 함수를 전달받음.
    result = []
    for i in arg_list:
        result.append(func(i)) # square 함수 호출, func == square
    return result

num_list = [1, 2, 3, 4, 5]

squares = my_map(square, num_list)

print ("\n square(num_list[i])")
print (squares)'''



# 2
'''
# 함수를 인자로 전달이 가능함에 따라, 아래와 같이 활용이 가능하다.
def square(x):
    return x * x

def cube(x):
    return x * x * x

def quad(x):
    return x * x * x * x

def my_map(func, arg_list):
    result = []
    for i in arg_list:
        result.append(func(i)) # square 함수 호출, func == square
    return result

num_list = [1, 2, 3, 4, 5]

# wrapper 클래스를 하나만 정의하여, 기존의 함수를
# 수정 없이 그대로 사용 가능
squares = my_map(square, num_list)
cubes = my_map(cube, num_list)
quads = my_map(quad, num_list)

print (squares)
print (cubes)
print (quads)
'''
