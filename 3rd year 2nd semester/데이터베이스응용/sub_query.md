# Sub Query


## 서브 쿼리란
- 중첩된 질의어를 의미

```sql
select select_list
from table or view
where condition expr 
(select select_list
from table
where condition)
```

```sql
select prof_name, hiredate, dept_name
from professor, department
where hiredate < (
    select hiredate
    from professor
    where prof_name is 'Meg Ryan'
);

select name, weight
from student
where weight > (
    select avg(weight)
    from student
    group by deptno1 = 201
)

select name, position, salary
from emp2
where salary > ANY(
    select avg(salary)
    from emp2
    where position = 'section head'
)

select name, grade, weight
from student
where weight < ALL(
    select min(weight)
    from student
    group by grade
)

select dname, name, salary
from enp2, dept2
where salary < ALL(
    select avg(salary)
    from dept2
    group by deptno
);
```

