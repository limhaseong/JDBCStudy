/*====================
 	 Test001.java
 	 - 쿼리문 전송 실습
====================*/

package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import com.util.DBConn;

public class Test001
{
	public static void main(String[] args)
	{
		try
		{
			Connection conn = DBConn.getConnection();
			
			if (conn != null)
			{
				System.out.println("데이터베이스 연결 성공");
			}
			
			try
			{
				// Type A - Statement
				/*
				Statement stmt = conn.createStatement();
				
				String sql = "INSERT INTO TBL_MEMBER(SID, NAME, TEL) VALUES(MEMBERSEQ.NEXTVAL, '문정환', '010-4444-4444')";
				
				int result = stmt.executeUpdate(sql);
				
				if (result>0)
				{
					System.out.println("데이터 입력 성공~!!!");
				}
				stmt.close();
				DBConn.close();
				*/
				
				// Type A - PreparedStatement
				/*
				String sql = "INSERT INTO TBL_MEMBER(SID, NAME, TEL) VALUES(MEMBERSEQ.NEXTVAL, '문정환', '010-4444-4444')";
				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				
				int result = pstmt.executeUpdate();
				
				if (result>0)
				{
					System.out.println("데이터 입력 성공~!!!");
				}
				pstmt.close();
				DBConn.close();
				*/
				
				//////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
				// Type B - Statement
				/*
				Statement stmt = conn.createStatement();
				
				String name = "문정환";
				String tel = "010-5555-5555";
				
				
				String sql = String.format("INSERT INTO TBL_MEMBER(SID, NAME, TEL) VALUES(MEMBERSEQ.NEXTVAL, '%s', '%s')", name, tel);
				
				int result = stmt.executeUpdate(sql);
				
				if (result>0)
				{
					System.out.println("데이터 입력 성공~!!!");
				}
				stmt.close();
				DBConn.close();
				*/
				
				// Type B - PreparedStatement
				
				String sql = "INSERT INTO TBL_MEMBER(SID, NAME, TEL) VALUES(MEMBERSEQ.NEXTVAL, ?, ?)";
				//String sql = "INSERT INTO TBL_MEMBER(SID, NAME, TEL) VALUES(MEMBERSEQ.NEXTVAL, '?', '?')"; --> (X)
				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				
				// IN 매개변수 넘겨주기
				pstmt.setString(1, "정한울");
				pstmt.setString(2, "010-5555-5555");
				
				int result = pstmt.executeUpdate();
				
				if (result>0)
				{
					System.out.println("데이터 입력 성공~!!!");
				}
				pstmt.close();
				DBConn.close();
				
				
				
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
			
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
}
