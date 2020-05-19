def solution(brown, yellow):
    answer = []
    measure = []
    width = brown + yellow

    for x in range(1, width):
        if width % x == 0:
            measure.append(x)

    for x in range(len(measure)):
        for y in range(len(measure)):
            if (measure[x] * measure[y] == width) and ((measure[x]-2) * (measure[y]-2) == yellow):
                answer = [measure[x], measure[y]]

    answer.sort(reverse=True)
    return answer


# 리팩토링 수행 후
def solution_refactored(brown, yellow):
    width = brown + yellow

    for x in range(1, width+1):
        if width % x != 0:
            continue
            
        y = width // x

        if (x * y == width) and ((x - 2) * (y - 2) == yellow):
            answer = [x, y]
            return sorted(answer, reverse=True)
            
    return answer