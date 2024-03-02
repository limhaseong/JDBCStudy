package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.util.DBConn;

public class ScoreDAO
{
	private Connection conn;
	
	public Connection connection()
	{
		conn = DBConn.getConnection();
		return conn;
	}
	
	//데이터 입력 메소드
	public int add(ScoreDTO dto) throws SQLException
	{
		String sql = String.format("INSERT INTO TBL_SCORE(SID, NAME, KOR, ENG, MAT) VALUES(SCORESEQ.NEXTVAL, ?, ?, ?, ?)");
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, dto.getName());
		pstmt.setInt(2, dto.getKor());
		pstmt.setInt(3, dto.getEng());
		pstmt.setInt(4, dto.getMat());
		
		int result = pstmt.executeUpdate();
		if (result > 0)
		{
			System.out.println("회원 데이터 입력 완료");
		}
		pstmt.close();
		
		return result;
	}
	
	// 전체 리스트 출력 담당하는 메소드
	public ArrayList<ScoreDTO> lists() throws SQLException
	{
		ArrayList<ScoreDTO> result = new ArrayList<ScoreDTO>();
		
		String sql = "SELECT SID, NAME, KOR, ENG, MAT, (KOR+ENG+MAT) AS TOT, (KOR+ENG+MAT)/3 AS AVG, RANK() OVER(ORDER BY (KOR+ENG+MAT) DESC) AS RANK FROM TBL_SCORE ORDER BY SID ASC";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next())
		{
			/*
			int sid = rs.getInt("SCORESEQ.NEXTVAL");
			String name = rs.getString("NAME");
			int kor = rs.getInt("KOR");
			int eng = rs.getInt("ENG");
			int mat = rs.getInt("MAT");
			int tot = rs.getInt("TOT");
			double avg = rs.getDouble("AVG");
			int rank = rs.getInt("RANK");
			
			
			
			String str = String.format(" %7s %5d %5d %5d %5d %5f %5d", name, kor, eng, mat, tot, avg, rank);
			System.out.println(str);*/
			
			
			ScoreDTO dto = new ScoreDTO();
			dto.setSid(rs.getString("SID"));
			dto.setName(rs.getString("NAME"));
			dto.setKor(rs.getInt("KOR"));
			dto.setEng(rs.getInt("ENG"));
			dto.setMat(rs.getInt("MAT"));
			dto.setTot(rs.getInt("TOT"));
			dto.setAvg(rs.getDouble("AVG"));
			dto.setRank(rs.getInt("RANK"));
			
			result.add(dto);
			
			
		}
		rs.close();
		pstmt.close();
		
		return result;
	}
	
	// 이름 검색 담당 메소드
	public ArrayList<ScoreDTO> lists(String name) throws SQLException
	{
		ArrayList<ScoreDTO> result = new ArrayList<ScoreDTO>();
		
		String sql = String.format("SELECT SID, NAME, KOR, ENG, MAT, TOT, AVG, RANK FROM (SELECT SID, NAME, KOR, ENG, MAT, (KOR+ENG+MAT) AS TOT, (KOR+ENG+MAT)/3 AS AVG, RANK() OVER(ORDER BY (KOR+ENG+MAT) DESC) AS RANK FROM TBL_SCORE) WHERE NAME = ? ");
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, name);
		
		
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next())
		{
			ScoreDTO dto = new ScoreDTO();
			
			dto.setSid(rs.getString("SID"));
			dto.setName(rs.getString("NAME"));
			dto.setKor(rs.getInt("KOR"));
			dto.setEng(rs.getInt("ENG"));
			dto.setMat(rs.getInt("MAT"));
			dto.setTot(rs.getInt("TOT"));
			dto.setAvg(rs.getDouble("AVG"));
			dto.setRank(rs.getInt("RANK"));
			
			result.add(dto);
			
		}
		
		rs.close();
		pstmt.close();
		
		return result;
	}
	
	// 번호 검색 담당 메소드
	public ArrayList<ScoreDTO> lists(int sid) throws SQLException
	{
		ArrayList<ScoreDTO> result = new ArrayList<ScoreDTO>();
		
		String sql = String.format("SELECT SID, NAME, KOR, ENG, MAT, TOT, AVG, RANK FROM (SELECT SID, NAME, KOR, ENG, MAT, (KOR+ENG+MAT) AS TOT, (KOR+ENG+MAT)/3 AS AVG, RANK() OVER(ORDER BY (KOR+ENG+MAT) DESC) AS RANK FROM TBL_SCORE) WHERE SID = ? ");
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, sid);
		
		
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next())
		{
			ScoreDTO dto = new ScoreDTO();
			
			dto.setSid(rs.getString("SID"));
			dto.setName(rs.getString("NAME"));
			dto.setKor(rs.getInt("KOR"));
			dto.setEng(rs.getInt("ENG"));
			dto.setMat(rs.getInt("MAT"));
			dto.setTot(rs.getInt("TOT"));
			dto.setAvg(rs.getDouble("AVG"));
			dto.setRank(rs.getInt("RANK"));
			
			result.add(dto);
			
		}
		
		rs.close();
		pstmt.close();
		
		return result;
	}
	
	// 인원 수 확인 담당 메소드
	public int count() throws SQLException
	{
		int result = 0;
		
		String sql = "SELECT COUNT(*) AS COUNT FROM TBL_SCORE";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		
		ResultSet rs = pstmt.executeQuery(sql);
		while (rs.next())
		{
			result = rs.getInt("COUNT");
		}
		
		rs.close();
		pstmt.close();
		
		return result;
	}
	
	// 데이터 수정 담당 메소드
	public int modify(ScoreDTO dto) throws SQLException
	{
		String sql = String.format("UPDATE TBL_SCORE SET NAME = ?, KOR = ?, ENG = ?, MAT = ? WHERE SID = ?");
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, dto.getName());
		pstmt.setInt(2, dto.getKor());
		pstmt.setInt(3, dto.getEng());
		pstmt.setInt(4, dto.getMat());
		pstmt.setString(5, dto.getSid());
	
		
		int result = pstmt.executeUpdate();
		if (result > 0)
		{
			System.out.println("회원 데이터 수정 완료");
		}
		
		pstmt.close();
		
		return result;
	}
	
	// 데이터 삭제 담당 메소드
	public int remove(int sid) throws SQLException
	{
		String sql = String.format("DELETE FROM TBL_SCORE WHERE SID = ?");
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, sid);
		
		int result = pstmt.executeUpdate();
		if (result > 0)
		{
			System.out.println("회원 데이터 수정 완료");
		}
		
		pstmt.close();
		
		return result;
	}
	
	// 데이터베이스 연결 종료 담당 메소드
	public void close() throws SQLException
	{
		DBConn.close();
	}
}
