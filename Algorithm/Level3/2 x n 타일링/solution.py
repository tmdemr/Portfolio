def fibonacci(n): 
        a, b = 0, 1 
        for i in range(n+1):
            a, b = b, a+b 
        return a

def solution(n):
    return fibonacci(n) % 1000000007
