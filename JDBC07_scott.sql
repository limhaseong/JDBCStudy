SELECT USER
FROM DUAL;
--==> SCOTT

SELECT *
FROM TBL_MEMBER
ORDER BY SID;
/*
1	이윤수	010-1111-1111
2	임하성	010-2222-2222
3	김민지	010-3333-3333
4	문정환	010-4444-4444
5	정한울	010-5555-5555
6	길현욱	010-6666-6666
*/

TRUNCATE TABLE TBL_MEMBER;
--==> Table TBL_MEMBER이(가) 잘렸습니다.

SELECT *
FROM TBL_MEMBER
ORDER BY SID;
--==> 조회 결과 없음

DROP SEQUENCE MEMBERSEQ;
--==> Sequence MEMBERSEQ이(가) 삭제되었습니다.

--○ CallableStatement 실습을 위한 프로시저 작성
--   프로시저 명 : PRC_MEMBERINSERT
--   프로시저 기능 : TBL_MEMBER 테이블에 데이터를 입력하는 프로시저
CREATE OR REPLACE PROCEDURE PRC_MEMBERINSERT 
( VNAME      IN TBL_MEMBER.NAME%TYPE
, VTEL       IN TBL_MEMBER.TEL%TYPE
)
IS
    -- 주요 변수 선언
    VSID  TBL_MEMBER.SID%TYPE;
BEGIN
    -- 기존 SID 의 최대값 얻어오기
    SELECT NVL(MAX(SID),0) INTO VSID
    FROM TBL_MEMBER;

    -- TBL_MEMBER 테이블에 데이터 입력 -> INSERT 쿼리문 수행
    INSERT INTO TBL_MEMBER(SID, NAME, TEL)
    VALUES(VSID + 1, VNAME, VTEL);
    
    -- 커밋
    COMMIT;
END;
--==> Procedure PRC_MEMBERINSERT이(가) 컴파일되었습니다.

--○ Test001.java 실행 후 확인
SELECT *
FROM TBL_MEMBER;
/*
1	김다슬	010-1111-1111
2	오수경	010-2222-2222
*/

-- 커밋
COMMIT;
--==> 커밋 완료.

--○ CallableStatement 실습을 위한 프로시저 작성
--   프로시저 명 : PRC_MEMBERSELECT
--   프로시저 기능 : TBL_MEMBER 테이블의 데이터를 읽어오는 프로시저
--   ※ 『SYS_REFCURSOR』 자료형을 이용하여 커서 다루기
CREATE OR REPLACE PROCEDURE PRC_MEMBERSELECT 
( VRESULT   OUT SYS_REFCURSOR
)
IS
    -- 주요 변수 선언
    -- -> 커서 정의 -> 출력 매개변술 대체
BEGIN
    OPEN VRESULT FOR
        SELECT SID, NAME, TEL
        FROM TBL_MEMBER
        ORDER BY SID;
        
    -- CLOSE VRESULT;
    --> 참조상태의 커서는 별도로 닫지 않는다.(닫으면 자바에서 사용 불가)
    
    --COMMIT; -> 쓰면 안됨.
END;
--==> Procedure PRC_MEMBERSELECT이(가) 컴파일되었습니다.

