/*============================
       Test004.java
============================*/

// 실행 예)
// 번호를 입력하세요(-1 종료) : 3
// 이름을 입력하세요 : 박나영
// 전화번호를 입력하세요 : 010-3333-3333
// >> 데이터베이스 연결 성공~!!!
// >> 회원 정보가 입력되었습니다.

// 번호를 입력하세요(-1 종료) : 4
// 이름을 입력하세요 : 정현욱
// 전화번호를 입력하세요 : 010-4444-4444
// >> 데이터베이스 연결 성공~!!!
// >> 회원 정보가 입력되었습니다.

// 번호를 입력하세요(-1 종료) : 5
// 이름을 입력하세요 : 김민지
// 전화번호를 입력하세요 : 010-5555-5555
// >> 데이터베이스 연결 성공~!!!
// >> 회원 정보가 입력되었습니다.

// 번호를 입력하세요(-1 종료) : -1
// >> 데이터베이스 연결 닫힘~!!!
// >> 프로그램 종료됨~!!!

package com.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.util.DBConn;

public class Test004
{
	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{
		//강사님 방법
		Scanner sc = new Scanner(System.in);
		
		Connection conn = DBConn.getConnection();
		
		do
		{
			System.out.print("번호를 입력하세요 (-1 종료) : ");
			String sid = sc.next();
			
			//반복문의 조건을 무너뜨리는 코드 구성
			if (sid.equals("-1"))
			{
				break;
			}
			
			System.out.print("이름을 입력하세요 : ");
			String name = sc.next();
			
			System.out.print("전화번호를 입력하세요 : ");
			String tel = sc.next();
			
			if (conn!=null)
			{
				System.out.println(">>데이터베이스 연결 성공~!!");
				try
				{
					//작업 객체 준비
					Statement stmt = conn.createStatement();
					
					//쿼리문 준비
					//String sql ="INSERT INTO TBL_MEMBER(SID, NAME, TEL) VALUES("+ sid +", '"+ name +"', '"+ tel +"')";
					//String sql = String.format("INSERT INTO TBL_MEMBER(SID, NAME, TEL) VALUES(①, '②', '③')", sid, name, tel);
					String sql = String.format("INSERT INTO TBL_MEMBER(SID, NAME, TEL) VALUES(%s, '%s', '%s')", sid, name, tel);
					// ① = '' 이 없기 때문에 오라클에서 number
					
					// 데이터베이스로부터 질의 결과를 가져와야 하는 경우
					// -> 『executeQuery()』 메소드 사용
					
					// 특정 내용을 데이터베이스에 적용해야 하는 경우(insert, update, delete)
					// -> 『executeUpdate()』 메소드 사용
					
					int result = stmt.executeUpdate(sql);
					//-- executeUpdate() 메소드는 적용된 행의 갯수 반환
					
					if (result > 0)
					{
						System.out.println(">> 회원 정보가 입력되었습니다.");
					}
					else
					{
						System.out.println(">> 회원 정보가 입력 실패~!!");
					}
				} catch (Exception e)
				{
					System.out.println(e.toString());
				}
			}
			else
			{
				System.out.println(">>데이터베이스 연결 실패~!!");
				break;
			}
		} while (true);
		sc.close();
		//굳이 안해도 됨.
		
		DBConn.close();
		
		System.out.println(">> 데이터베이스 연결 닫힘~!!!");
		System.out.println(">> 프로그램 종료됨~!!!");
	}
}
