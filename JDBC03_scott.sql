SELECT USER
FROM DUAL;
--==> SCOTT

--○ 실습 테이블 생성
CREATE TABLE TBL_SCORE
( SID       NUMBER
, NAME      VARCHAR2(30)
, KOR       NUMBER(3)
, ENG       NUMBER(3)
, MAT       NUMBER(3)
);
--==> Table TBL_SCORE이(가) 생성되었습니다.

--○ 제약조건 추가(SID 컬럼에 PK 제약조건 추가)
ALTER TABLE TBL_SCORE
ADD CONSTRAINT SCORE_SID_PK PRIMARY KEY(SID);
--==> Table TBL_SCORE이(가) 변경되었습니다.

--○ 제약조건 추가(KOR, ENG, MAT 컬럼에 CK 제약조건 추가)
ALTER TABLE TBL_SCORE
ADD ( CONSTRAINT SCORE_KOR_CK CHECK (KOR BETWEEN 0 AND 100)
     , CONSTRAINT SCORE_ENG_CK CHECK (ENG BETWEEN 0 AND 100)
     , CONSTRAINT SCORE_MAT_CK CHECK (MAT BETWEEN 0 AND 100) );
--==> Table TBL_SCORE이(가) 변경되었습니다.

--○ 시퀀스 생성
CREATE SEQUENCE SCORESEQ
NOCACHE;
--==> Sequence SCORESEQ이(가) 생성되었습니다.

--○ 데이터 입력 쿼리문 구성
INSERT INTO TBL_SCORE(SID, NAME, KOR, ENG, MAT) VALUES(SCORESEQ.NEXTVAL, '강혜성', 80, 75, 60)
;

--○ 총점, 평균
SELECT (KOR+ENG+MAT) AS SUM, (KOR+ENG+MAT)/3 AS AVG FROM TBL_SCORE
;

--○ 인원수
SELECT COUNT(*) AS COUNT FROM TBL_SCORE
;

--○ 전체출력
SELECT SID, NAME, KOR, ENG, MAT, (KOR+ENG+MAT) AS SUM, TRUNC((KOR+ENG+MAT)/3, 1) AS AVG FROM TBL_SCORE ORDER BY SID
;
/*
1	강혜성	75	60	80	215	71.6
2	김동민	100	90	80	270	90
3	이주형	80	85	80	245	81.6
*/