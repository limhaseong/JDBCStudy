SELECT USER
FROM DUAL;
--==> SCOTT

--�� �ǽ� ���̺� ����
CREATE TABLE TBL_SCORE
( SID       NUMBER
, NAME      VARCHAR2(30)
, KOR       NUMBER(3)
, ENG       NUMBER(3)
, MAT       NUMBER(3)
);
--==> Table TBL_SCORE��(��) �����Ǿ����ϴ�.

--�� �������� �߰�(SID �÷��� PK �������� �߰�)
ALTER TABLE TBL_SCORE
ADD CONSTRAINT SCORE_SID_PK PRIMARY KEY(SID);
--==> Table TBL_SCORE��(��) ����Ǿ����ϴ�.

--�� �������� �߰�(KOR, ENG, MAT �÷��� CK �������� �߰�)
ALTER TABLE TBL_SCORE
ADD ( CONSTRAINT SCORE_KOR_CK CHECK (KOR BETWEEN 0 AND 100)
     , CONSTRAINT SCORE_ENG_CK CHECK (ENG BETWEEN 0 AND 100)
     , CONSTRAINT SCORE_MAT_CK CHECK (MAT BETWEEN 0 AND 100) );
--==> Table TBL_SCORE��(��) ����Ǿ����ϴ�.

--�� ������ ����
CREATE SEQUENCE SCORESEQ
NOCACHE;
--==> Sequence SCORESEQ��(��) �����Ǿ����ϴ�.

--�� ������ �Է� ������ ����
INSERT INTO TBL_SCORE(SID, NAME, KOR, ENG, MAT) VALUES(SCORESEQ.NEXTVAL, '������', 80, 75, 60)
;

--�� ����, ���
SELECT (KOR+ENG+MAT) AS SUM, (KOR+ENG+MAT)/3 AS AVG FROM TBL_SCORE
;

--�� �ο���
SELECT COUNT(*) AS COUNT FROM TBL_SCORE
;

--�� ��ü���
SELECT SID, NAME, KOR, ENG, MAT, (KOR+ENG+MAT) AS SUM, TRUNC((KOR+ENG+MAT)/3, 1) AS AVG FROM TBL_SCORE ORDER BY SID
;
/*
1	������	75	60	80	215	71.6
2	�赿��	100	90	80	270	90
3	������	80	85	80	245	81.6
*/