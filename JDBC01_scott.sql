SELECT USER
FROM DUAL;
--==> SCOTT

SELECT *
FROM TAB;


DROP TABLE TBL_MEMBER;
--==> Table TBL_MEMBER��(��) �����Ǿ����ϴ�.

--�� �ǽ� ���̺� ����
CREATE TABLE TBL_MEMBER
( SID       NUMBER
, NAME      VARCHAR2(30)
, TEL       VARCHAR2(60)
, CONSTRAINT MEMBER_SID_PK PRIMARY KEY(SID)
);
--==> Table TBL_MEMBER��(��) �����Ǿ����ϴ�.

--�� ���� ������ �Է�
INSERT INTO TBL_MEMBER(SID, NAME, TEL) VALUES(1, 'ȫ�浿', '010-1111-1111');
--==> 1 �� ��(��) ���ԵǾ����ϴ�.

--�� Ȯ��
SELECT *
FROM TBL_MEMBER;
--==> 1	ȫ�浿	010-1111-1111

--�� Ŀ�� -> Ŀ���� ���ϸ� ��Ŭ������ ����Ŭ �Ѵ� ���� ����Ƿ� �ݵ�� �ϱ�.
COMMIT;
--==> Ŀ�� �Ϸ�.

--�� �ڹٿ��� Test003.java ���� �� �ٽ� Ȯ��
SELECT *
FROM TBL_MEMBER;
--==>
/*
2	���ϼ�	010-2222-2222
1	ȫ�浿	010-1111-1111
*/

--�� �ڹٿ��� Test004.java ���� �� �ٽ� Ȯ��
SELECT *
FROM TBL_MEMBER;
/*
2	���ϼ�	010-2222-2222
3	�ڳ���	010-3333-3333
4	������	010-4444-4444
1	ȫ�浿	010-1111-1111
*/

--�� Ŀ�� -> Ŀ���� ���ϸ� ��Ŭ������ ����Ŭ �Ѵ� ���� ����Ƿ� �ݵ�� �ϱ�.
COMMIT;
--==> Ŀ�� �Ϸ�.


--�� �ڹ� Test005.java ���� ������ ��ȸ ������ �غ�
SELECT SID, NAME, TEL
FROM TBL_MEMBER
ORDER BY SID;
--> �� �� ����
SELECT SID, NAME, TEL FROM TBL_MEMBER ORDER BY SID
;
/*
1	ȫ�浿	010-1111-1111
2	���ϼ�	010-2222-2222
3	�ڳ���	010-3333-3333
4	������	010-4444-4444
*/



