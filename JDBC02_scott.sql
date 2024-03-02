SELECT USER
FROM DUAL;
--==> SCOTT

TRUNCATE TABLE TBL_MEMBER;
--==> Table TBL_MEMBER��(��) �߷Ƚ��ϴ�.

--�� ������ ����
CREATE SEQUENCE MEMBERSEQ
NOCACHE;
--==> Sequence MEMBERSEQ��(��) �����Ǿ����ϴ�.

--�� ������ �Է� ������ ����
INSERT INTO TBL_MEMBER(SID, NAME, TEL)
VALUES(MEMBERSEQ.NEXTVAL, '������', '010-1111-1111');
--> ���ٱ���
INSERT INTO TBL_MEMBER(SID, NAME, TEL) VALUES(MEMBERSEQ.NEXTVAL, '������', '010-1111-1111')
;
--==> 1 �� ��(��) ���ԵǾ����ϴ�.

--�� Ȯ��
SELECT *
FROM TBL_MEMBER;
--==> 1	������	010-1111-1111

--�� Ŀ��
COMMIT;
--==> Ŀ�� �Ϸ�.


--�� �ο� �� Ȯ�� ������ ����
SELECT COUNT(*) AS COUNT
FROM TBL_MEMBER;
--> �� �� ����
SELECT COUNT(*) AS COUNT FROM TBL_MEMBER
;
--==> 1

--�� ��ü ����Ʈ ��ȸ ������ ����
SELECT SID, NAME, TEL
FROM TBL_MEMBER
ORDER BY SID;
--> �� �� ����
SELECT SID, NAME, TEL FROM TBL_MEMBER ORDER BY SID
;
--==> 1	������	010-1111-1111

--�� �ڹ� MemberMain.java ���� �� �ٽ� Ȯ��
SELECT SID, NAME, TEL
FROM TBL_MEMBER;
/*
2	���ϼ�	010-2222-2222
3	�����	010-3333-3333
1	������	010-1111-1111
*/

--�� Ŀ��
COMMIT;





