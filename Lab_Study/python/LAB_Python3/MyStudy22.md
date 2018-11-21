### 선행학습!
##### [일급 함수(First Class Function)](http://schoolofweb.net/blog/posts/%ED%8C%8C%EC%9D%B4%EC%8D%AC-%ED%8D%BC%EC%8A%A4%ED%8A%B8%ED%81%B4%EB%9E%98%EC%8A%A4-%ED%95%A8%EC%88%98-first-class-function/)

##### 상태 보존 클로저(Closure)  

<br><br><br>

## items 22 딕셔너리와 튜플보다는 헬퍼 클래스로 관리히자.

파이썬에 내장되어 있는 딕셔너리 타입은 객체의 수명이 지속되는 동안 동적인 내부 상태를 관리하는 용도로 아주 좋다고 한다.
여기서 '동적'의 의미는 예상하지 못한 식별자를 관리해야 하는 상황을 말한다.<br>
예를 들어 이름을 모르는 학생 집단의 성적을 관리하고 싶다고 해보자. 학생별로 딕셔너리에 이름을 지정하는 클래스를 정의할 수 있다.

```python
class SimpleGradebook(object):
	def __init__(self): # 객체 초기화.
		self._grades = {} # 빈 딕셔너리 생성
	def add_student(self, name): # 이름이 들어갈 빈 튜플 생성
		self._grades[name] = []
	def report_grade(self, name, score): # 이름의 value 할당
		self._grades[name].append(score)
	def average_grade(self, name):
		grades = self._grades[name]
		return sum(grades) / len(grades)
```
클래스 사용하는 방법.
```python
book = SimpleGradebook()
book.add_student('Lee TaeYong')
book.report_grade('Lee TaeYong', 80)

print(book.average_grade('Lee TaeYong')
```
<br><br><br>
딕셔너리는 너무 사용하기 쉬워서, 코드를 취약하게 만들 수 있다.
유지 보수의 취약성을 말하는 듯.
예를 들어, 모든 성적을 한 곳에 관리하지 않고, 과목별로 저장한다고 해보자.<br>
이런 경우 _grades를 변경해서 **학생 이름(키)에 리스트가 아닌 또 다른 딕셔너리를 매핑할 수 있다.**<br>
딕셔너리 안에 딕셔너리가 들어가는 형태로 가장 안쪽 딕셔너리는 과목에 성적을 매핑한다.

```python
class BySubjuectGradebook:
	def __init__(self):
		# 전체 자료를 담을 딕셔너리 정의
		self._grades = {}


	def add_student(self, name):
		# 딕셔너리 안 학생 정보를 담을 딕셔너리를 또 생성
		self._grades[name] = {}


	def report_grade(self, name, subject, grade):
		# 학생 딕셔너리 안에 과목 리스트를 생성하고 점수 입력
		by_subject = self._grades[name]
		grade_list = by_subject.setdefault(subject, [])
		grade_list.append(grade)


	def average_grade(self, name):
		# 한 학생의 모든 과목의 모든 점수를 합산하여 평균을 구함.
		by_subject = self._grades[name]
		total, count = 0, 0
		for grades in by_subject.values():
			total  += sum(grades)
			count += len(grades)
		return total / count
```


해당 코드는 아직까지는 다룰만해 보인다.

```python
book = BySubjectGradebook()
book.add_student('stonehead')
book.report_grade('stonehead', 'math', 75)
book.report_grade('stonehead', 'math', 65)
book.report_grade('stonehead', 'gym', 90)
book.report_grade('stonehead', 'gym', 90)
```

<br><br><br>

그런데, 요구사항이 바껴서 수업의 최종성적에서 각 점수가 차지하는 가중치를 매겨서 중간고사와 기말고사를 쪽지시험보다 중요하게 만들려고 한다.<br>
이 기능을 구현하는 방법 중 하나는 가장 안쪽 딕셔너리를 변경해서 과목(키)을 성적(값)에 매핑하지 않고, 성적과 비중을 담은 튜플(score, weight)에 매핑하는 것이다.

```python
class WeightedGradebook:
	# 생략 ...
	def report_grade(self, name, subject, score, weight):
		by_subject = self._grades[name]
		grade_list = by_subject.setdefault(subject, [])
		grade_list.append(score, list)


	def average_grade(self, name):
		by_subject = self._grades[name]
		score_sum, score_count = 0, 0
		subject, socres in by_subject.items():
			subject_avg, total_weight = 0, 0
			for score, weight in scores:
				# ..
		return score_sum / score_count
```
average_grade의 함수 활용이 반복문의 중첩으로 이해하기 어려워졌을 뿐만 아니라,<br>
`book.report_grade('stonehead', 'math', 80, 0.2)`와 같이 함수 호출에서도 인자가 무엇을 의미하는지 명확하지도 않다.
<br><BR>

**이 정도로 복잡해지면 딕셔너리와 튜플 대신 클래스의 계층 구조를 사용할 때가 된 것이다.**<br>
처음에야 성적에 비중을 적용하게 될지 몰랐으니 복잡하게 헬퍼클래스를 추가할 필요까지는 없었을 것이다.<br>
그렇지만 여러 계층으로 중첩하면 다른 프로그래머들이 코드를 이해하기 힘들고, 유지보수가 극도로 어려워 진다.<br>
**때문에 관리가 복잡하다고 느끼는 즉시 클래스로 옮겨가야 한다. 그러면 데이터를 더 잘 캡슐화한 인터페이스를 제공할 수 있다.**


<br><br><br>


## 클래스 리펙토링
- 클래스 리팩토링이란?
	- 리팩토링은 외부동작을 바꾸지 않으면서 내부 구조를 개선하는 방법으로, 클래스를 변경하는 것.

의존 관계에서 가장 아래에 있는 성적부터 클래스로 옮겨보자. 이렇게 간단한 정보를 담기에 클래스는 너무 무거워 보인다. 성적은 변하지 않으니 튜플을 사용한다.

```python
grades = []
grades.append((95,0.4))
# ...
total = sum(score * weight for score, weight in grades)
total_weight = sum(weight for _, weight in grades)
average_grade = total / total_weight

# 파이썬에서는 반복문 등에서 사용하지 않을 변수를 관례적으로 '_'으로 표현한다.
```

문제는 일반 튜플은 위치에 의존한다는 점이다. 성적에 선생님의 의견 같은 더 많은 정보를 연관지으려면 튜플을 사용하는 곳 모두에 아이템을 두 개가 아니라 세 개를 추가해야 한다.

```python
grades = []
grades.append((94, 0.2, 'GReat!!'))
```

튜플을 점점 더 길게 확장하는 패턴은 딕셔너리의 계층을 깊게 두는 방식과 비슷하다. 튜플의 아이템이 두 개를 넘어가면 다른 방법을 고려해야 한다.<br><BR>

이 때 `collections` 모듈의 [namedtuple](https://docs.python.org/3/library/collections.html#collections.namedtuple)이 정확히 이런 요구에 부합한다.<br> `namedtuple`을 이용하면 작은 불변 데이터 클래스를 쉽게 정의할 수 있다.

```python
import collections
Grade = collections.namedtuple('Grade', ('score', 'weight'))

```
불변 데이터 클래스는 위치 인수나 키워드 인수로 생성할 수 있다.<br>
그리고 일반 튜플과 다르게 이름이 붙은 속성으로 접근할 수 있다. 이름이 붙은 속성이 있으면 나중에 요구 사항이 또 변해서 단순 데이터 컨테이너에 동작을 추가해야 할 때 용이하다.

<br> <br>`namedtuple`을 직접 사용해서 코드를 작성해보자.

```python
class Subject(object):
	def __init__(self):
		self._grades = []

	def report_grade(self, score, weight):
		self._grades.append(Grade(score, weight))

	def average_grade(self):
		total, total_weight = 0, 0
		for grade in self._grades:
			total += grade.score * grade.weight #! 튜플에 키워드로 접근했다.
			total_weight += grade.weight
		return total / total_weight
```

<br>이제 한 학생이 공부한 과목들을 표현하는 클래스를 작성해보자.
```python
class Student:
	def __init__(self):
		self._subjects = []

	def subject(self, name):
		return self._subject.setdefault(name, Subjects())

	def average_grade(self):
		total, count = 0, 0
		for subject in self._subject.values():
			total += subject.average_grade()
			count += 1
		return total / count

```
<br> 마지막으로 학생의 이름을 키로 사용해 동적으로 모든 학생을 담을 컨테이너를 작성한다.
```python
class Gradebook(object):
	def __init__(self):
		return self._students = {}

	def student(self):
		if name not in self._students:
			self._students[name] = Students()
		return self._students[name]
```
<br>이 세 클래스의 코드 줄 수는 이전에 구현한 코드의 두 배에 가깝다.
하지만, 이 코드가 훨씬 이해하기 쉽다. 예제도 더 명확하고 확장하기도 쉽다

```python
book = Gradebook()
albert = book.student('albert')
math = albert.subject('math')
math.report_grade(80, 0.1)

#...
print(albert.average_grade())
```

#### 핵심정리!
> 1. 다른 딕셔너리나 긴 튜플을 값으로 담은 딕셔너리를 생성하지 말자.
> 2. 정식 클래스의 유연성이 없다면 가벼운 불변 데이터 컨테이너에는 `namedtuple`을 사용해보자.
> 3. 내부 상태를 관리하는 딕셔너리가 복잡해지면 여러 헬퍼 클래스를 사용하는 방식으로 코드를 관리하자.

## items 22 딕셔너리와 튜플보다는 헬퍼 클래스로 관리히자.
