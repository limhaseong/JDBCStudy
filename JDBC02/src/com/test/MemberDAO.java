/*====================
 	MemberDAO.java
====================*/

// MemberMain.jave 와 MemberDTO.java와 같이 보기~!!

// 데이터베이스에 엑세스 하는 기능
// -> DBConn 활용(전담 계층)

// 데이터를 입력하는 기능 -> insert

// 인원 수 확인하는 기능
// 즉, 대상 테이블(TBL_MEMBER)의 레코드 카운팅 기능 -> select

// 전체 리스트 조회하는 기능
// 즉, 대상 테이블(TBL_MEMBER)의 데이터를 조회하는 기능 -> select

package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.util.DBConn;

public class MemberDAO
{
	// 주요 속성 구성 -> DB 연결 객체
	private Connection conn;
	
	/*
	public Connection getConn()
	{
		return conn;
	}

	public void setConn(Connection conn)
	{
		this.conn = conn;
	}
	*/
	
	// 생성자 정의(사용자 정의 생성자)
	public MemberDAO() throws ClassNotFoundException, SQLException
	{
		conn = DBConn.getConnection();
	}
	
	// 메소드 정의 -> 데이터를 입력하는 기능
	public int add(MemberDTO dto) throws SQLException
	{
		//강사님 방법
		
		// 반환할 결과값을 담아낼 변수(적용된 행의 갯수)
		int result = 0;
		
		// 작업 객체 생성
		Statement stmt = conn.createStatement();
		
		// 쿼리문 준비
		String sql = String.format("INSERT INTO TBL_MEMBER(SID, NAME, TEL)" + "VALUES(MEMBERSEQ.NEXTVAL, '%s', '%s')", dto.getName(), dto.getTel());
		
		// 작업 객체를 활용하여 쿼리문 실행(전달)
		result = stmt.executeUpdate(sql);
		
		// 사용한 리소스 반납
		stmt.close();
		
		// 최종 결과값 반환
		return result;

///////////////////////////////////////////////////////////////////////////////////////////////////////
		
		/*
		Scanner sc = new Scanner(System.in);

		// 반환할 결과값을 담아낼 변수(적용된 행의 갯수)
		int result = 0;
		int i = 2;
		
		do
		{
			System.out.print("이름 전화번호 입력 (" + i + ") : ");
			String name = sc.next();
			String tel = sc.next();
			
			i++;
			if (name.equals("."))
			{
				break;
			}
			
			try
			{
				//작업 객체 준비
				Statement stmt = conn.createStatement();
				
				//쿼리문 준비(insert)
				String sql = String.format("INSERT INTO TBL_MEMBER(NAME, TEL) VALUES(%s %s)", name , tel);
				
				result = stmt.executeUpdate(sql);
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
			
		} while (true);
		*/
		
	}
	
	// 메소드 정의 -> 전체 인원 수 확인하는 기능
	public int count() throws SQLException
	{
		//강사님 방법
				// 결과값으로 반환하게 될 변수 선언 및 초기화(인원수)
				int result = 0;
				
				//작업 객체 생성
				Statement stmt = conn.createStatement();
				
				//쿼리문 준비
				String sql = "SELECT COUNT(*) AS COUNT FROM TBL_MEMBER";
				
				// 생성된 작업 객체를 활용하여 쿼리문 실행
				// -> select -> executeQuery() -> ResultSet 반환 -> 일반적으로 반복문 구성을 통한 ResultSet 처리
				ResultSet rs = stmt.executeQuery(sql);
				
				// ResultSet 처리 -> 반복문 구성 -> 결과값 수신
				while (rs.next())  // if(rs.next()) 가능 -> 이유는 단일 결과값인 전체인원수만 받으면 되기 때문
				{
					result = rs.getInt("COUNT"); //SELECT COUNT(*) AS COUNT" AS 별칭을 부여했으므로 COUNT 입력, COUNT(*) 사용 불가
					//       rs.getInt(1) -> 사용가능 ※ 컬럼 인덱스는 1부터...
				}
				/*
				String sid = rs.getString(1);
				String name = rs.getString(2);
				String tel = rs.getString(3); 으로 얻어내기 가능
				*/
				
				//리소스 반납
				rs.close();
				stmt.close();
				
				// 최종 결과값 반환
				return result;

///////////////////////////////////////////////////////////////////////////////////////////////////////
				
		/*
		// 결과값으로 반환하게 될 변수 선언 및 초기화(인원수)
		int result = 0;
		
		try
		{
			// 작업 객체 생성
			Statement stmt = conn.createStatement();
			
			//쿼리문 준비(select)
			String sql = "SELECT COUNT(*) AS COUNT FROM TBL_MEMBER";
			
			//쿼리문 실행
			ResultSet rs = stmt.executeQuery(sql);
			
			// 반복문 필요 x
			result = rs.getInt("COUNT(*)");
			
			System.out.printf("%d", result);
			
			//Result 리소스 반납
			rs.close();
			
			//Statement 리소스 반납
			stmt.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		*/
		
	}
	
	// 메소드 정의 -> 전체 리스트 조회하는 기능
	public ArrayList<MemberDTO> lists() throws SQLException
	{
		// 결과값으로 반환할 변수 선언 및 초기화
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		
		// 작업 객체 생성
		Statement stmt = conn.createStatement();
		
		//쿼리문 준비(select)
		String sql = "SELECT SID, NAME, TEL FROM TBL_MEMBER ORDER BY SID";
		
		// 생성된 작업 객체를 활용하여 쿼리문 실행
		// -> select -> executeQuery() -> ResultSet 반환 -> 일반적으로 반복문 구성을 통한 ResultSet 처리
		ResultSet rs = stmt.executeQuery(sql);
		
		// check~~!
		// ResultSet 처리
		while (rs.next())
		{
			// 객체 생성
			MemberDTO dto = new MemberDTO();
			
			dto.setSid(rs.getString("SID")); //"SID"의 값을 String으로 얻어와서 Sid에 set하겠다.
			dto.setName(rs.getString("NAME"));
			dto.setTel(rs.getString("TEL"));
			
			result.add(dto);			
		}
		
		// 리소스 반납
		rs.close();
		stmt.close();
		
		//최종 결과값 반환
		return result;
		
///////////////////////////////////////////////////////////////////////////////////////////////////////
		
		/*
		// 결과값으로 반환할 변수 선언 및 초기화
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		try
		{
			// 작업 객체 생성
			Statement stmt = conn.createStatement();
			
			//쿼리문 준비(select)
			String sql = "SELECT SID, NAME, TEL FROM TBL_MEMBER ORDER BY SID";
			
			//쿼리문 실행
			ResultSet rs = stmt.executeQuery(sql);
			int i = 0;
			
			while (rs.next())
			{
				
				String sid = rs.getString("SID");
				String name = rs.getString("NAME");
				String tel = rs.getString("TEL");
				
				result.add(new MemberDTO());
				
				result.get(i).setSid(sid);
				result.get(i).setName(name);
				result.get(i).setTel(tel);
			}
			
			//Result 리소스 반납
			rs.close();
			
			//Statement 리소스 반납
			stmt.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		*/
	}
	
	// 메소드 정의 -> 데이터베이스 연결 종료
	public void close() throws SQLException
	{
		//주의할점
		//conn.close(); -> 이렇게 사용하면 안됨(커넥션의 close를 부른 것임)
		
		DBConn.close();
	}
	
}













