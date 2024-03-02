/*====================
 	MemberMain.java
====================*/

// // MemberDTO.jave 와 MemberDAO.java와 같이 보기~!!

/*
○ TBL_MEMBER 테이블을 활용하여
   이름과 전화번호를 여러 건 입력받고, 전체 출력하는 프로그램을 구현한다.
   단, 데이터베이스 연동이 이루어져야 하고
   

, MemberDTO 클래스를 활용해야 한다.
   
실행 예)

이름 전화번호 입력(2) : 임하성 010-2222-2222
>> 회원 정보 입력 완료~!!!
이름 전화번호 입력(3) : 김민지 010-3333-3333
>> 회원 정보 입력 완료~!!!
이름 전화번호 입력(4) : .

------------------------------------
전체 회원 수 : 3명
------------------------------------
번호			이름			전화번호
1	       이윤수	  010-1111-1111
2	       임하성	  010-2222-2222
3	       김민지	  010-3333-3333
------------------------------------
*/

package com.test;

import java.util.Scanner;

import com.util.DBConn;

//DBConn은 DAO에서 다 했으므로 필요 X
//DTO -> DAO에서 편하게 하라고 만들어 놨으나 DAO에서 add에 MemberDTO가 있으므로 여기서도 필요

public class MemberMain
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		try
		{
			MemberDAO dao = new MemberDAO();
			int count = dao.count();
			
			do
			{
				System.out.printf("이름 전화번호 입력(%d) : ", ++count); // 임하성 010-2222-2222
				String name = sc.next();							 // 임하성
				
				//반복의 조건을 무너뜨리는 코드 구성
				
				if (name.equals("."))
				{
					break;
				}
				String tel = sc.next();								 // 010-2222-2222
				
				// ※ 여기까지의 과정을 통해 이름과 전화번호를 사용자로부터 입력받은 이유는
				//    입력받은 데이터를 데이터베이스에 입력하기 위함.
				//    데이터 입력을 위해서는 dao 의 add() 메소드 호출 필요.
				//    add() 메소드를 호출하기 위해서는 MemberDTO 를 넘겨주는 과정이 필요.
				//    MemberDTO 를 넘겨주기 위해서는 객체의 속성값을 구성하는 과정이 필요.
				
				// MemberDTO 객체 생성
				MemberDTO dto = new MemberDTO();
				
				// 속성값 구성
				dto.setName(name);
				dto.setTel(tel);
				
				// 데이터베이스에 데이터 입력하는 메소드 호출 -> add()
				int result = dao.add(dto);
				
				if (result > 0)
				{
					System.out.println(">> 회원 정보 입력 완료~!!!");
				}
				
			} while (true);
			
			System.out.println();
			System.out.println("------------------------------------");
			//System.out.println("전체 회원 수 : " + dao.count() + "명");
			System.out.printf("전체 회원 수 : %d명\n", dao.count());
			System.out.println("------------------------------------");
			System.out.println("번호		이름		전화번호");
			
			//전체 회원 목록 가져와 출력
			//dao.lists(); --> 이렇게 쓰는 거 x
			for (MemberDTO obj : dao.lists())
			{
				System.out.printf("%3s %7s %12s\n", obj.getSid(), obj.getName(), obj.getTel());
			}
			System.out.println("------------------------------------");
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		finally
		{
			try
			{
				DBConn.close();
				System.out.println(">> 데이터베이스 연결 닫힘~!!!");
				System.out.println(">> 프로그램 종료됨~!!");
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}
	}
}

// 실행결과
/*
이름 전화번호 입력(2) : 임하성 010-2222-2222
>> 회원 정보 입력 완료~!!!
이름 전화번호 입력(3) : 김민지 010-3333-3333
>> 회원 정보 입력 완료~!!!
이름 전화번호 입력(4) : .

------------------------------------
전체 회원 수 : 3명
------------------------------------
번호		이름		전화번호
  1     이윤수 010-1111-1111
  2     임하성 010-2222-2222
  3     김민지 010-3333-3333
------------------------------------
>> 데이터베이스 연결 닫힘~!!!
>> 프로그램 종료됨~!!
*/
