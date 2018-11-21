
## items 23. 인터페이스가 간단하면 클래스 대신 함수를 받자.

파이썬의 내장 API의 상당수는 함수를 넘겨서 동작을 사용자화하는 기능이 있다.
API는 이런 후크(hook)를 이용해서 작성한 코드를 실행 중에 호출한다.
예를 들어 list의 sort 메소드에는 key라는 추가적인 인자를 받을 수 있다.

```
names = ['안철수', '안안철수', '안안안철수', '안안안안안철수']
names.sort(key=lambda x: len(x), reverse=True)
# 글자 크기의 내림차순으로 리스트를 정렬한다.

print(names)

>>> ['안안안안안철수', '안안안철수', '안안철수', '안철수']
```
람다 함수를 후크로 받아서 정렬의 방법을 사용자화했다.


다른 언어에서는 이런 후크를 클래스로 정의하지만 파이썬에서는 일반적으로 함수를 사용한다.
이유는 파이썬이 일급함수(first-class function)를 갖췄기 때문이다.
다시 말해, 언어에서 함수와 메서드를 다른 값처럼 전달하고 참조할 수 있다.




이번엔 collections모듈의 defaultdict라는 자료구조를 사용해보자. 이 자료구조는 찾을 수 없는 키에 접근할 때마다 호출될 함수를 첫 번째 인자로 받는다.
"딕셔너리는 키와 그에 매핑되는 값을 가지고 있는데, 찾을 수 없는 키에 접근하려고 할 때
default로 지정된 함수를 호출해주는 자료구조."
또한 이 함수는 찾을 수 없는 키에 대응할 기본값을 반환해야 한다.
다음은 키를 찾을 수 없을 때마다 로그를 남기고, 기본값으로 0을 반환하는 후크를 정의한 코드다.
```
from collections import defaultdict

def log_missing():
    print('Key added.')
    return 0
# 키가 없을 때마다 '키:0'을 반환하는 함수.

current = {'green': 12, 'blue': 3}
increments = [
    ('red', 5),
    ('blue', 17),
    ('white', 11),
]
result = defaultdict(log_missing, current)
print('before: ', dict(result)) #dict 는 딕셔너리 자료형을 의미.
for key, amount in increments: # increment : 증가
    reuslt[key] += amount
print('After: ', dict(result))

>>> before: {'green': 12, 'blue': 3}
>>> Key added.
>>> Key added.
>>> After: {'green': 12, 'red': 5, 'white': 11, 'blue': 20}
```
- log_missing 같은 함수를 넘기면 결정 동작과 부작용을 분리하므로 API를 쉽게 구축하고 테스트할 수 있다.



이번에는 기본값 후크를 defaultdict에 넘겨서 찾을 수 없는 키의 총 개수를 센다고 해보자.
이렇게 만드는 한 가지 방법은 상태 보존 클로저를 사용하는 것이다.
```
def increment_with_report(current, increments):
    added_count = 0

    def missing():
        nonlocal added_count
        added_count += 1
        return 0
    result = defaultdict(missing, current)
    for key, amount in increments:
        result[key] += amount

    return result, added_count

result, count = increment_with_report(current, increments)
# 인자 current와 increments는 위 예제의 것을 사용.

assert count == 2
```

 assert 는 주어진 조건과 맞지 않으면 에러를 내 주는 역할을 한다.
 raise와 비슷하지만, 디버깅모드에만 효과가 있고, 릴리즈모드에서는
 아무런 일도 하지 않음.


`defualtdict`는 `missing`후크가 상태를 유지한다는 사실을 모르지만, `increment_with_report` 함수를 실행하면 튜플의 요소로 기대한 개수인 2를 얻는다.

상태 보존용으로 nonlocal과 같은 클로저를 사용하면 상태가 없는 예제의 함수보다 이해하기 어렵다는 단점이 있다.
또 다른 방법은 보존할 상태를 캡슐화하는 작은 클래스를 정의하는 것이다.
```
class CountMissing:
    def __init__(self):
        self.added = 0

    def missing(self):
        self.added += 0
        return 0
        ```
다른 언어에서라면 이제 CountMissing의 인터페이스를 수용하도록 defaultdict를 수정해야 한다고 생각할 수 있다.
하지만 파이썬에서는 일급함수 덕분에 객체로 CountMissing.missing 메서드를 직접 참조해서 defaultdict의 기본값 후크로 넘길 수 있다.
```
counter = CountMissing()
result = defaultdict(counter.Missing, current)

for key, amount in increments:
    result[key] += amount
assert counter.added == 2
```
위의 예를 조금만 더 업그레이드해보자. 코드를 처음 보는 사람들은 `counter`와 `missing` 두 개 이상을 파악해야 한다.
이떄 `__call__`를 사용해서 가독성을 조금 더 높여보자.
```
class BetterCountMissing:
    def __init__(self):
        self.added = 0


    def __call__(self):
        print('added!')  # 호출되었을 때.
        self.added += 1
        return 0

counter = BetterCountMissing()
result = defaultdict(counter, current)
for key, amount in increments:
    result[key] += amount

# __call__을 정의하면 객체를 함수처럼 사용할 수 있다.
```


### 핵심정리
- 파이썬에서 컴포넌트 사이의 간단한 인터페이스용으로 클래스를 정의하고 인스턴스를 생성하는 대신, 함수만 써도 종종 충분하다.  
- 파이썬에서 함수와 메서드에 대한 참조는 1급이다. 즉, 다른 타입처럼 표현식에서 사용할 수 있다.  
- ```__call__```이라는 특별한 메서드는 클래스의 인스턴스를 일반 파이썬 함수처럼 호출할 수 게 해준다.  
- 상태를 보존하는 함수가 필요할 때 상태 보존 클로저를 정의하는 대신, ```__call__```메서드를 제공하는 클래스를 정의하는 방안을 고려하자.
## items 23. 인터페이스가 간단하면 클래스 대신 함수를 받자.
