SELECT USER
FROM DUAL;
--==> SCOTT

SELECT *
FROM TBL_MEMBER
ORDER BY SID;
/*
1	������	010-1111-1111
2	���ϼ�	010-2222-2222
3	�����	010-3333-3333
4	����ȯ	010-4444-4444
5	���ѿ�	010-5555-5555
6	������	010-6666-6666
*/

TRUNCATE TABLE TBL_MEMBER;
--==> Table TBL_MEMBER��(��) �߷Ƚ��ϴ�.

SELECT *
FROM TBL_MEMBER
ORDER BY SID;
--==> ��ȸ ��� ����

DROP SEQUENCE MEMBERSEQ;
--==> Sequence MEMBERSEQ��(��) �����Ǿ����ϴ�.

--�� CallableStatement �ǽ��� ���� ���ν��� �ۼ�
--   ���ν��� �� : PRC_MEMBERINSERT
--   ���ν��� ��� : TBL_MEMBER ���̺� �����͸� �Է��ϴ� ���ν���
CREATE OR REPLACE PROCEDURE PRC_MEMBERINSERT 
( VNAME      IN TBL_MEMBER.NAME%TYPE
, VTEL       IN TBL_MEMBER.TEL%TYPE
)
IS
    -- �ֿ� ���� ����
    VSID  TBL_MEMBER.SID%TYPE;
BEGIN
    -- ���� SID �� �ִ밪 ������
    SELECT NVL(MAX(SID),0) INTO VSID
    FROM TBL_MEMBER;

    -- TBL_MEMBER ���̺� ������ �Է� -> INSERT ������ ����
    INSERT INTO TBL_MEMBER(SID, NAME, TEL)
    VALUES(VSID + 1, VNAME, VTEL);
    
    -- Ŀ��
    COMMIT;
END;
--==> Procedure PRC_MEMBERINSERT��(��) �����ϵǾ����ϴ�.

--�� Test001.java ���� �� Ȯ��
SELECT *
FROM TBL_MEMBER;
/*
1	��ٽ�	010-1111-1111
2	������	010-2222-2222
*/

-- Ŀ��
COMMIT;
--==> Ŀ�� �Ϸ�.

--�� CallableStatement �ǽ��� ���� ���ν��� �ۼ�
--   ���ν��� �� : PRC_MEMBERSELECT
--   ���ν��� ��� : TBL_MEMBER ���̺��� �����͸� �о���� ���ν���
--   �� ��SYS_REFCURSOR�� �ڷ����� �̿��Ͽ� Ŀ�� �ٷ��
CREATE OR REPLACE PROCEDURE PRC_MEMBERSELECT 
( VRESULT   OUT SYS_REFCURSOR
)
IS
    -- �ֿ� ���� ����
    -- -> Ŀ�� ���� -> ��� �Ű����� ��ü
BEGIN
    OPEN VRESULT FOR
        SELECT SID, NAME, TEL
        FROM TBL_MEMBER
        ORDER BY SID;
        
    -- CLOSE VRESULT;
    --> ���������� Ŀ���� ������ ���� �ʴ´�.(������ �ڹٿ��� ��� �Ұ�)
    
    --COMMIT; -> ���� �ȵ�.
END;
--==> Procedure PRC_MEMBERSELECT��(��) �����ϵǾ����ϴ�.

