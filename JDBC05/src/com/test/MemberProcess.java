/*=========================================
 	MemberProcess.java
 	- 콘솔 기반 서브 메뉴 입출력 전용 클래스
=========================================*/

package com.test;

import java.util.Scanner;

public class MemberProcess
{
	private MemberDAO dao;
	
	// 생성자 정의(사용자 정의 생성자)
	public MemberProcess()
	{
		dao = new MemberDAO();
	}
	
	public void MemberInsert()
	{
		try
		{
			dao.connection();
			
			Scanner sc = new Scanner(System.in);
			
			do
			{
				System.out.println();
				System.out.println("---------------------------------------------------- 직원 정보 입력");
				System.out.printf("이름 : ");
				String empname = sc.next();
				
				if (empname.equals("."))
				{
					break;
				}
				
				System.out.print("주민등록번호(yymmdd-nnnnnnn) : ");
				String ssn = sc.next();
				
				System.out.print("입사일(yyyy-mm-dd) : ");
				String ibsadate = sc.next();
				
				System.out.print("지역(");
				for (MemberDTO dto : dao.listsCity())
				{
					System.out.print(dto.getCityname() + "/");
				}
				
				System.out.print(") : ");
				String cityid = sc.next();
				
				System.out.printf("전화번호 : ");
				String tel = sc.next();
				
				System.out.print("부서(");
				for (MemberDTO dto : dao.listsBuseo())
				{
					System.out.print(dto.getBuseoname()+ "/");
				}
				System.out.print(") : ");
				String buseoid = sc.next();
				
				System.out.print("직위(");
				for (MemberDTO dto : dao.listsJikwi())
				{
					System.out.print(dto.getJikwiname()+ "/");
				}
				System.out.print(") : ");
				String jikwiid = sc.next();
				
				int minbasicpay = dao.listsBasic(jikwiid);
				
				System.out.print("기본급(최소 ");
				System.out.print(minbasicpay);
				System.out.print(" 이상) : ");
				int basicpay = sc.nextInt();
				
				System.out.printf("수당 : ");
				int sudang = sc.nextInt();
				
				

				MemberDTO dto = new MemberDTO();
				dto.setEmpname(empname);
				dto.setSsn(ssn);
				dto.setIbsadate(ibsadate);
				dto.setCityid(cityid);
				dto.setTel(tel);
				dto.setBuseoid(buseoid);
				dto.setJikwiid(jikwiid);
				dto.setBasicpay(basicpay);
				dto.setSudang(sudang);
				
				int result = dao.add(dto);
				
				if (result > 0)
				{
					System.out.println("직원 정보 입력 완료~!!");
				}
				
			} while (true);
			
			//데이터 베이스 연결 종료
			dao.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}

	private int listsBasic(String jikwiid)
	{
		// TODO Auto-generated method stub
		return 0;
	}
}
