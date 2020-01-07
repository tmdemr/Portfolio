# Constraint(제약 조건)

## 주키의 제약 조건
- not null
- unique

## 외래키
- 참조 무결성
  - 상대 테이블에서 존재
  - 상대 테이블의 주키

## 제약 조건들
### PRIME KEY
- 주 키
### FORIGN KEY
- 외리키
### NOT NULL
- 해당 조건에서 설정된 값이 NULL이면 거부함
### Check
- 해당 조건에서 설정된 값만 입력을 허용하고, 나머지는 거부함

```sql
CREATE TABLE new_emp2
{
    no NUMBER(4) PRIMARY KEY;
    jumin VARCHAR2(20) NOT NULL;
        CONSTRAINT NOT NULL;
        CONSTRAINT CHECK(condition);
}

ALTER TABLE new_emp2
ADD CONSTRAINT emp2_no_fk FOREIGN KEY(no);
REFERENCES emp2(empno);
--new_emp2 테이블의 no 컬럼이 emp2테이블의 empno 컬럼의 값을 참조하도록 참조 제약조건 추가

ALTER TABLE new_emp2
MODIFY (loc_code constraint emp2_loccode_nn NOT NULL);
-- new_emp2 테이블의 loc_code 컬럼에 NOT NULL 제약 조건 추가하기

ALTER TABLE emp2
ADD CONSTRAINT emp2_name_fk FORIGN KEY(name);
REFERENCES emp2(name);

ALTER ...
DISABLE NONVALIDATE CONSTRAINT SYS_C0014418;
-- 제약조건을 비활성화 시킴으로써 필드를 변경하는 방법

```