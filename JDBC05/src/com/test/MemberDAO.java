/*=========================================
 	 MemberDAO.java
 	 - 데이터베이스 액션 처리 전용 클래스
=========================================*/

package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.util.DBConn;

public class MemberDAO
{
	// 주요 속성 구성
	private Connection conn;
	
	// 데이터베이스 연결 담당 메소드
	public Connection connection() throws ClassNotFoundException, SQLException
	{
		conn = DBConn.getConnection();
		return conn;
	}
	
	// 입력
	public int add(MemberDTO dto) throws SQLException
	{
		int result = 0;
		
		Statement stmt = conn.createStatement();
		
		
		
		String sql = String.format("INSERT INTO TBL_EMP (EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_ID, TEL, BUSEO_ID, JIKWI_ID, BASICPAY, SUDANG)"
				+                      "VALUES (EMPSEQ.NEXTVAL, '%s', '%s', TO_DATE('%s', 'YYYY-MM-DD'), (SELECT CITY_ID FROM TBL_CITY WHERE CITY_NAME = '%s'), '%s', (SELECT BUSEO_ID FROM TBL_BUSEO WHERE BUSEO_NAME = '%s'), (SELECT JIKWI_ID FROM TBL_JIKWI WHERE JIKWI_NAME = '%s'), %d, %d)", dto.getEmpname(), dto.getSsn(), dto.getIbsadate(), dto.getCityid(), dto.getTel(), dto.getBuseoid(), dto.getJikwiid(), dto.getBasicpay(), dto.getSudang());
		
		result = stmt.executeUpdate(sql);
		stmt.close();
		
		return result;
	}
	
	// 인원 수
	public int count() throws SQLException
	{
		int result = 0;
		
		Statement stmt = conn.createStatement();
		
		String sql = "SELECT COUNT(*) AS COUNT FROM TBL_EMP";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			result = rs.getInt("COUNT");
		}
		
		rs.close();
		stmt.close();
		
		return result;
	}
	
	// 사번 오름차순 전체 리스트
	public ArrayList<MemberDTO> listsEi() throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		
		Statement stmt = conn.createStatement();
		String sql = "SELECT EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_ID, TEL, BUSEO_ID, JIKWI_ID, BASICPAY, SUDANG, SAL FROM (SELECT EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_ID, TEL, BUSEO_ID, JIKWI_ID, BASICPAY, SUDANG, (BASICPAY + SUDANG) AS SAL FROM TBL_EMP) ORDER BY EMP_ID ASC";
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();
			dto.setEmpid(rs.getString("EMP_ID"));
			dto.setEmpname(rs.getString("EMP_NAME"));
			dto.setSsn(rs.getString("SSN"));
			dto.setIbsadate(rs.getString("IBSADATE"));
			dto.setCityid(rs.getString("CITY_ID"));
			dto.setTel(rs.getString("TEL"));
			dto.setBuseoid(rs.getString("BUSEO_ID"));
			dto.setJikwiid(rs.getString("JIKWI_ID"));
			dto.setBasicpay(rs.getInt("BASICPAY"));
			dto.setSudang(rs.getInt("SUDANG"));
			dto.setSal(rs.getInt("SAL"));
			
			result.add(dto);
		}
		rs.close();
		stmt.close();
		
		return result;
	}
	
	// 이름 오름차순 전체 리스트
	public ArrayList<MemberDTO> listsName() throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		
		Statement stmt = conn.createStatement();
		String sql = "SELECT EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_ID, TEL, BUSEO_ID, JIKWI_ID, BASICPAY, SUDANG, SAL FROM (SELECT EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_ID, TEL, BUSEO_ID, JIKWI_ID, BASICPAY, SUDANG, (BASICPAY + SUDANG) AS SAL FROM TBL_EMP) ORDER BY EMP_NAME ASC";
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();
			dto.setEmpid(rs.getString("EMP_ID"));
			dto.setEmpname(rs.getString("EMP_NAME"));
			dto.setSsn(rs.getString("SSN"));
			dto.setIbsadate(rs.getString("IBSADATE"));
			dto.setCityid(rs.getString("CITY_ID"));
			dto.setTel(rs.getString("TEL"));
			dto.setBuseoid(rs.getString("BUSEO_ID"));
			dto.setJikwiid(rs.getString("JIKWI_ID"));
			dto.setBasicpay(rs.getInt("BASICPAY"));
			dto.setSudang(rs.getInt("SUDANG"));
			dto.setSal(rs.getInt("SAL"));
			
			result.add(dto);
		}
		rs.close();
		stmt.close();
		
		return result;
	}
	
	// 부서 오름차순 전체 리스트
	public ArrayList<MemberDTO> listsBi() throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		
		Statement stmt = conn.createStatement();
		String sql = "SELECT EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_ID, TEL, BUSEO_ID, JIKWI_ID, BASICPAY, SUDANG, SAL FROM (SELECT EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_ID, TEL, BUSEO_ID, JIKWI_ID, BASICPAY, SUDANG, (BASICPAY + SUDANG) AS SAL FROM TBL_EMP) ORDER BY BUSEO_ID ASC";
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();
			dto.setEmpid(rs.getString("EMP_ID"));
			dto.setEmpname(rs.getString("EMP_NAME"));
			dto.setSsn(rs.getString("SSN"));
			dto.setIbsadate(rs.getString("IBSADATE"));
			dto.setCityid(rs.getString("CITY_ID"));
			dto.setTel(rs.getString("TEL"));
			dto.setBuseoid(rs.getString("BUSEO_ID"));
			dto.setJikwiid(rs.getString("JIKWI_ID"));
			dto.setBasicpay(rs.getInt("BASICPAY"));
			dto.setSudang(rs.getInt("SUDANG"));
			dto.setSal(rs.getInt("SAL"));
			
			result.add(dto);
		}
		rs.close();
		stmt.close();
		
		return result;
	}
	
	// 직위 오름차순 전체 리스트
	public ArrayList<MemberDTO> listsJi() throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		
		Statement stmt = conn.createStatement();
		String sql = "SELECT EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_ID, TEL, BUSEO_ID, JIKWI_ID, BASICPAY, SUDANG, SAL FROM (SELECT EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_ID, TEL, BUSEO_ID, JIKWI_ID, BASICPAY, SUDANG, (BASICPAY + SUDANG) AS SAL FROM TBL_EMP) ORDER BY JIKWI_ID ASC";
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();
			dto.setEmpid(rs.getString("EMP_ID"));
			dto.setEmpname(rs.getString("EMP_NAME"));
			dto.setSsn(rs.getString("SSN"));
			dto.setIbsadate(rs.getString("IBSADATE"));
			dto.setCityid(rs.getString("CITY_ID"));
			dto.setTel(rs.getString("TEL"));
			dto.setBuseoid(rs.getString("BUSEO_ID"));
			dto.setJikwiid(rs.getString("JIKWI_ID"));
			dto.setBasicpay(rs.getInt("BASICPAY"));
			dto.setSudang(rs.getInt("SUDANG"));
			dto.setSal(rs.getInt("SAL"));
			
			result.add(dto);
		}
		rs.close();
		stmt.close();
		
		return result;
	}
	
	// 급여 내림차순 전체 리스트
	public ArrayList<MemberDTO> listsPay() throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		
		Statement stmt = conn.createStatement();
		String sql = "SELECT EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_ID, TEL, BUSEO_ID, JIKWI_ID, BASICPAY, SUDANG, SAL FROM (SELECT EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_ID, TEL, BUSEO_ID, JIKWI_ID, BASICPAY, SUDANG, (BASICPAY + SUDANG) AS SAL FROM TBL_EMP) ORDER BY SAL DESC";
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();
			dto.setEmpid(rs.getString("EMP_ID"));
			dto.setEmpname(rs.getString("EMP_NAME"));
			dto.setSsn(rs.getString("SSN"));
			dto.setIbsadate(rs.getString("IBSADATE"));
			dto.setCityid(rs.getString("CITY_ID"));
			dto.setTel(rs.getString("TEL"));
			dto.setBuseoid(rs.getString("BUSEO_ID"));
			dto.setJikwiid(rs.getString("JIKWI_ID"));
			dto.setBasicpay(rs.getInt("BASICPAY"));
			dto.setSudang(rs.getInt("SUDANG"));
			dto.setSal(rs.getInt("SAL"));
			
			result.add(dto);
		}
		rs.close();
		stmt.close();
		
		return result;
	}
	
	// 사번 검색 담당 메소드
	public ArrayList<MemberDTO> listsEi(String empid) throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		
		Statement stmt = conn.createStatement();
		String sql = String.format("SELECT EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_ID, TEL, BUSEO_ID, JIKWI_ID, BASICPAY, SUDANG, SAL FROM (SELECT EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_ID, TEL, BUSEO_ID, JIKWI_ID, BASICPAY, SUDANG, (BASICPAY + SUDANG) AS SAL FROM TBL_EMP) WHERE EMP_ID = %s", empid);
		
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();
			dto.setEmpid(rs.getString("EMP_ID"));
			dto.setEmpname(rs.getString("EMP_NAME"));
			dto.setSsn(rs.getString("SSN"));
			dto.setIbsadate(rs.getString("IBSADATE"));
			dto.setCityid(rs.getString("CITY_ID"));
			dto.setTel(rs.getString("TEL"));
			dto.setBuseoid(rs.getString("BUSEO_ID"));
			dto.setJikwiid(rs.getString("JIKWI_ID"));
			dto.setBasicpay(rs.getInt("BASICPAY"));
			dto.setSudang(rs.getInt("SUDANG"));
			dto.setSal(rs.getInt("SAL"));
			
			result.add(dto);
			
		}
		
		rs.close();
		stmt.close();
		
		return result;
	}
	
	// 이름 검색 담당 메소드
	public ArrayList<MemberDTO> listsName(String empname) throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		
		Statement stmt = conn.createStatement();
		String sql = String.format("SELECT EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_ID, TEL, BUSEO_ID, JIKWI_ID, BASICPAY, SUDANG, SAL FROM (SELECT EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_ID, TEL, BUSEO_ID, JIKWI_ID, BASICPAY, SUDANG, (BASICPAY + SUDANG) AS SAL FROM TBL_EMP) WHERE EMP_NAME = %s", empname);
		
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();
			dto.setEmpid(rs.getString("EMP_ID"));
			dto.setEmpname(rs.getString("EMP_NAME"));
			dto.setSsn(rs.getString("SSN"));
			dto.setIbsadate(rs.getString("IBSADATE"));
			dto.setCityid(rs.getString("CITY_ID"));
			dto.setTel(rs.getString("TEL"));
			dto.setBuseoid(rs.getString("BUSEO_ID"));
			dto.setJikwiid(rs.getString("JIKWI_ID"));
			dto.setBasicpay(rs.getInt("BASICPAY"));
			dto.setSudang(rs.getInt("SUDANG"));
			dto.setSal(rs.getInt("SAL"));
			
			result.add(dto);
			
		}
		
		rs.close();
		stmt.close();
		
		return result;
	}
	
	// 부서 검색 담당 메소드
	public ArrayList<MemberDTO> listsBi(String buseoid) throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		
		Statement stmt = conn.createStatement();
		String sql = String.format("SELECT EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_ID, TEL, BUSEO_ID, JIKWI_ID, BASICPAY, SUDANG, SAL FROM (SELECT EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_ID, TEL, BUSEO_ID, JIKWI_ID, BASICPAY, SUDANG, (BASICPAY + SUDANG) AS SAL FROM TBL_EMP) WHERE BUSEO_ID = %s", buseoid);
		
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();
			dto.setEmpid(rs.getString("EMP_ID"));
			dto.setEmpname(rs.getString("EMP_NAME"));
			dto.setSsn(rs.getString("SSN"));
			dto.setIbsadate(rs.getString("IBSADATE"));
			dto.setCityid(rs.getString("CITY_ID"));
			dto.setTel(rs.getString("TEL"));
			dto.setBuseoid(rs.getString("BUSEO_ID"));
			dto.setJikwiid(rs.getString("JIKWI_ID"));
			dto.setBasicpay(rs.getInt("BASICPAY"));
			dto.setSudang(rs.getInt("SUDANG"));
			dto.setSal(rs.getInt("SAL"));
			
			result.add(dto);
			
		}
		
		rs.close();
		stmt.close();
		
		return result;
	}
	
	// 직위 검색 담당 메소드
	public ArrayList<MemberDTO> listsJi(String jikwiid) throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		
		Statement stmt = conn.createStatement();
		String sql = String.format("SELECT EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_ID, TEL, BUSEO_ID, JIKWI_ID, BASICPAY, SUDANG, SAL FROM (SELECT EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_ID, TEL, BUSEO_ID, JIKWI_ID, BASICPAY, SUDANG, (BASICPAY + SUDANG) AS SAL FROM TBL_EMP) WHERE JIKWI_ID = %s", jikwiid);
		
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();
			dto.setEmpid(rs.getString("EMP_ID"));
			dto.setEmpname(rs.getString("EMP_NAME"));
			dto.setSsn(rs.getString("SSN"));
			dto.setIbsadate(rs.getString("IBSADATE"));
			dto.setCityid(rs.getString("CITY_ID"));
			dto.setTel(rs.getString("TEL"));
			dto.setBuseoid(rs.getString("BUSEO_ID"));
			dto.setJikwiid(rs.getString("JIKWI_ID"));
			dto.setBasicpay(rs.getInt("BASICPAY"));
			dto.setSudang(rs.getInt("SUDANG"));
			dto.setSal(rs.getInt("SAL"));
			
			result.add(dto);
		}
		
		rs.close();
		stmt.close();
		
		return result;
	}
	
	// 수정 메소드
	public int modify(MemberDTO dto) throws SQLException
	{
		int result = 0;
		
		Statement stmt = conn.createStatement();
		String sql = String.format("UPDATE TBL_EMP SET EMP_NAME = '%s', SSN = '%s', IBSADATE = '%s', CITY_ID = %s, TEL = '%s', BUSEO_ID = %s, JIKWI_ID = %s, BASICPAY = %d, SUDANG = %d WHERE EMP_ID = %s", dto.getEmpname(), dto.getSsn(), dto.getIbsadate(), dto.getTel(), dto.getBuseoid(), dto.getJikwiid(), dto.getBasicpay(), dto.getSudang(), dto.getEmpid());
		
		result = stmt.executeUpdate(sql);
		
		stmt.close();
		
		return result;
	}
	
	// 데이터 삭제 담당 메소드
	public int remove(int empid) throws SQLException
	{
		int result = 0;
		
		Statement stmt = conn.createStatement();
		String sql = String.format("DELETE FROM TBL_EMP WHERE EMP_ID = %d",  empid);
		
		result = stmt.executeUpdate(sql);
		
		stmt.close();
		
		return result;
	}
	
	public void close() throws SQLException
	{
		DBConn.close();
	}
	
	// 지역 리스트
	public ArrayList<MemberDTO> listsCity() throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		
		Statement stmt = conn.createStatement();
		String sql = String.format("SELECT CITY_NAME FROM TBL_CITY");
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();
			dto.setCityname(rs.getString("CITY_NAME"));
			
			result.add(dto);
		}
		rs.close();
		stmt.close();
		
		return result;
	}
	
	// 부서 리스트
	public ArrayList<MemberDTO> listsBuseo() throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		
		Statement stmt = conn.createStatement();
		String sql = String.format("SELECT BUSEO_NAME FROM TBL_BUSEO");
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();
			dto.setBuseoname(rs.getString("BUSEO_NAME"));
			
			result.add(dto);
		}
		rs.close();
		stmt.close();
		
		return result;
	}
	
	// 직위 리스트
	public ArrayList<MemberDTO> listsJikwi() throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		
		Statement stmt = conn.createStatement();
		String sql = String.format("SELECT JIKWI_NAME FROM TBL_JIKWI");
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();
			dto.setJikwiname(rs.getString("JIKWI_NAME"));
			
			result.add(dto);
		}
		rs.close();
		stmt.close();
		
		return result;
	}
	
	// 기본급 리스트
	public int listsBasic(String jikwiname) throws SQLException
	{
		int result = 0;
		Statement stmt = conn.createStatement();
		String sql = String.format("SELECT MIN_BASICPAY FROM TBL_JIKWI WHERE JIKWI_NAME = '%s'", jikwiname);
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			result = rs.getInt("MIN_BASICPAY");
		}
		rs.close();
		stmt.close();
		
		return result;
	}
	
}

