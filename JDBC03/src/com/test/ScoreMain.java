/*=============================
 	ScoreMain.java
=============================*/

/*
○ 성적 처리 프로그램 구현 -> 데이터베이스 연동 -> ScoreDAO, ScoreDTO 클래스 활용

	여러 명의 이름, 국어점수, 영어점수, 수학점수를 입력받아
	총점, 평균을 연산하여 내용을 출력하는 프로그램을 구현한다.
	
실행 예)

1번째 학생 성적 입력(이름 국어 영어 수학) : 강혜성 80 75 60
2번째 학생 성적 입력(이름 국어 영어 수학) : 김동민 100 90 80
3번째 학생 성적 입력(이름 국어 영어 수학) : 이주형 80 85 80
4번째 학생 성적 입력(이름 국어 영어 수학) : .

--------------------------------------------------------------
번호		이름		국어		영어		수학		총점		평균
--------------------------------------------------------------
 1	   강혜성    80      75      60      xxx     xx.x
 2     김동민   100      90      80      xxx     xx.x
 3     이주형    80      85      80      xxx     xx.x
--------------------------------------------------------------
*/

package com.test;

import java.util.Scanner;

import com.util.DBConn;

public class ScoreMain
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		try
		{
			ScoreDAO dao = new ScoreDAO();
			int count = dao.count();
			
			do
			{
				System.out.printf("%d번째 학생 성적 입력(이름 국어 영어 수학) : ", ++count);
				
				String name = sc.next();
				if (name.equals("."))
				{
					break;
				}
				
				int kor = sc.nextInt();
				int eng = sc.nextInt();
				int mat = sc.nextInt();
				
				ScoreDTO dto = new ScoreDTO();
				
				dto.setName(name);
				dto.setKor(kor);
				dto.setEng(eng);
				dto.setMat(mat);
				
				int sum = dto.getKor() + dto.getEng() + dto.getMat();
				// int sum = kor + eng + mat;
				dto.setSum(sum);
				
				double avg = dto.getSum()/3;
				dto.setAvg(avg);
				
				int result = dao.add(dto);
				
				
			} while (true);
			
			System.out.println();
			System.out.println("------------------------------------------------------------------");
			System.out.println("번호	  이름        국어      영어      수학      총점      평균");
			System.out.println("------------------------------------------------------------------");
			
			for (ScoreDTO obj : dao.lists())
			{
				System.out.printf("%3s %8s %9d %9d %9d %9d %9.1f\n", obj.getSid(), obj.getName(), obj.getKor(), obj.getEng(), obj.getMat(), obj.getSum(), obj.getAvg());
			}
			System.out.println("------------------------------------------------------------------");
			
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
/*
실해 
*/
