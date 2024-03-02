SELECT USER
FROM DUAL;
--==> SCOTT

SELECT *
FROM TBL_MEMBER
ORDER BY 1;
/*
1	������	010-1111-1111
2	���ϼ�	010-2222-2222
3	�����	010-3333-3333
*/

--�� ������ �Է� ������ ����
INSERT INTO TBL_MEMBER(SID, NAME, TEL) VALUES(MEMBERSEQ.NEXTVAL, '����ȯ', '010-4444-4444')
;

--�� Ȯ��
SELECT *
FROM TBL_MEMBER
ORDER BY 1;
/*
1	������	010-1111-1111
2	���ϼ�	010-2222-2222
3	�����	010-3333-3333
4	����ȯ	010-4444-4444
*/

--�� Ŀ��
COMMIT;
--==> Ŀ�� �Ϸ�.

--�� ������ ��ü ��ȸ ������ ����
SELECT SID, NAME, TEL
FROM TBL_MEMBER
ORDER BY SID;
--> �� �� ����
SELECT SID, NAME, TEL FROM TBL_MEMBER ORDER BY SID
;

--�� Test001.java ���� �� Ȯ��
SELECT SID, NAME, TEL FROM TBL_MEMBER ORDER BY SID
;
/*
1	������	010-1111-1111
2	���ϼ�	010-2222-2222
3	�����	010-3333-3333
4	����ȯ	010-4444-4444
5	���ѿ�	010-5555-5555
*/

--�� Ŀ��
COMMIT;
--==> Ŀ�� �Ϸ�.

--�� Test002.java ���� �� Ȯ��
SELECT SID, NAME, TEL FROM TBL_MEMBER ORDER BY SID
;
/*
1	������	010-1111-1111
2	���ϼ�	010-2222-2222
3	�����	010-3333-3333
4	����ȯ	010-4444-4444
5	���ѿ�	010-5555-5555
6	������	010-6666-6666
*/

--�� Ŀ��
COMMIT;
--==> Ŀ�� �Ϸ�.

SELECT *
FROM TBL_SCORE;