# -*- coding: utf-8 -*-


import collections
Grade = collections.namedtuple('Grade', ('score', 'weight'))

class SimpleGradebook(object):
	def __init__(self):
		self._grades = {}
	def add_student(self, name):
		self._grades[name] = []
	def report_grade(self, name, score):
		self._grades[name].append(score)
	def average_grade(self, name):
		grades = self._grades[name]
		return sum(grades) / len(grades)

book = SimpleGradebook()
book.add_student('이 태용')
book.report_grade('이 태용', 90)

print(book.average_grade('이 태용'))


'''
class Student(object):
	def __init__(self):
		self._subjects = {}
	def subject(self, name):
		if name not in self._subjects:
			self._subjects[name] = Subject()
		return self._subjects[name]
	def average_grade(self):
		total, count = 0, 0
		for subject in self._subjects.values():
			total += subject.average_grade()
			count += 1
		return total / count

class Gradebook(object):
	def __init__(self):
		self._students = {}
	def student(self, name):
		if name not in self._students:
			self._students[name] = Student()
		return self._students[name]

book = Gradebook()
albert = book.student('Albert Einstein')
math = albert.subject('Math')
math.report_grade(80, 0.10)

print(albert.average_grade())
'''
