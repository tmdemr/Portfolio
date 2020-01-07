# PL/SQL
## 구성 요소
- 선언문
- 조건문
- 예외 처리문
  
## 명시적 커서
- 사용자가 선언하여 생기는 커서
- 여러 개의 행을 처리할 때 사용
## 묵시적 커서
- 오라클에서 자동으로 선언해주는 SQL 커서
- 사용자는 생성유무를 알 수 없음.
- 묵시적 커서에 저장되는 데이터는 1행만 가능함

## MERGY
```sql
BEGIN
    MERGE INTO p1_merge2 m2
    USING pl_merge1 m1
    ON(m1.no = m2.no)

DECLARE
    TYPE emp_record_type IS RECORD
        emp_id employees.employee_id%TYPE,
        f_name employees.first_name%TYPE,
        e_sal employees.salary%TYPE;
    v_recl emp_record_type ;
    BEGIN
```

### **Cursor For Loop  (중요 !)**

- Open, Fetch 등의 절차를 수행하
### explicit Cursor 와 FOR UPDATE 문장

```sql
DECLARE
  CURSOR emp_cur IS
    SELECT EMPNO, ENAME, SAL
    FROM EMP
    WHERE DEPTNO = 20
    FOR UPDATE
    NO WAIT;

BEGIN
...
  OPEN emp_cur;
...
  UPDATE EMP;
  SET sal = sal * 2
  WHERE CURRENT OF emp_cur

  CLOSE emp_cur
...
END;
```


### PREGMA
- C언어에서는 또 다른 링크를 걸거나 라이브러리를 만들 때 사용
- PL/SQL에서는 오류번호를 자동으로 넣어줌

## ORACLE SUBPROGRAM
### PROCEDURE
- 프로시저는 지정된 특정 처리를 실행하는 서브 프로그램의 한 유형
- 단독으로 실행되거나 다른 프로시져나 다른 툴 또는 다른 환경 등에서 호출되어 실행됨.


### TRIGGER
- 특정 사건이 발생되었을 때 자동으로 블럭이 실행
- TRIGGER 자체는 사용자가 지정해서 실해을 할 수 없으며, 오직 TRIGGER 생성시 정의한 특정 사건에 의해서만 묵시적인 자동실행이 이루어짐.



### DDL & Dictionary
#### Data Dictionary
- 오라클 데이터베이스 내부에서 사용하는 구조물
- History이기 때문에 보고 활용하는 것만 가능함

#### DDL

```sql
BEGIN
    FOR i IN 1...1000 LOOP
      INSERT INTO st_table VALUES (i);
    END LOOP;
  COMMIT;
END;
/
```
### DML
#### MERGE
- MERGE를 시킬 경우, 테이블의 구조가 동일해야 함
- 쿼기의 특징상 부하가 많이 걸리는 경우가 많음
```sql
MERGE INTO Table1
USING Table2
ON(병합 조건절)
WHEN MATCHED THEN
UPDATE SET 업데이트 내용
DELETE WHERE 조건
WHEN NOT MATCHED THEN
INSERT VALUES(컬럼이름)
```

#### Transaction
- 논리적인 작업의 발생 단위
- 