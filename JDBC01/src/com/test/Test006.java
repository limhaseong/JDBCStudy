/*=================================
       Test006.java
       - 테이블 내의 데이터 읽어오기
=================================*/

// EMP 테이블을 대상으로
// EMPNO, ENAME, JOB, SAL 항목을 확인하는 프로그램 구성

package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.util.DBConn;

public class Test006
{
	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{
		Connection conn = DBConn.getConnection();
		
		if (conn != null)
		{
			System.out.println("데이터베이스 연결 성공~~");
			
			try
			{
				// 작업 객체 생성
				Statement stmt = conn.createStatement();
				
				// 쿼리문 준비(select 쿼리문)
				String sql = "SELECT EMPNO, ENAME, JOB, SAL FROM TBL_EMP ORDER BY EMPNO";
				
				//쿼리문 실행
				ResultSet rs = stmt.executeQuery(sql);
				
				while (rs.next())
				{
					int empno = rs.getInt("EMPNO");
					String ename = rs.getString("ENAME");
					String job = rs.getString("JOB");
					int sal = rs.getInt("SAL");
					
					String str = String.format("%3d %5s %12s %12d", empno, ename, job, sal);
					System.out.println(str);
				}
				
				//Result 리소스 반납
				rs.close();
				
				//Statement 리소스 반납
				stmt.close();
				
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}
		else
		{
			System.out.println("데이터베이스 연결 실패~!!");
		}
		
		DBConn.close();
		
		System.out.println("데이터베이스 연결 닫힘~!!");
		System.out.println(">> 프로그램 종료됨~!!");
	}
}
