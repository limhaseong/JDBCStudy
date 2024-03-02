/*====================
 	 DBConn.java
 	 - 예외 처리 : try - catch
====================*/

package com.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn
{
	private static Connection dbconn;
	
	public static Connection getConnection()
	{
		if (dbconn == null)
		{
			try
			{
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				String user = "scott";
				String pwd = "tiger";
				Class.forName("oracle.jdbc.driver.OracleDriver");
				dbconn = DriverManager.getConnection(url, user, pwd);
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}
		return dbconn;
	}
	
	public static Connection getConnection(String url, String user, String pwd)
	{
		if (dbconn == null)
		{
			try
			{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				dbconn = DriverManager.getConnection(url, user, pwd);
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}
		return dbconn;
	}
	
	public static void close()
	{
		if (dbconn != null)
		{
			try
			{
				if (!dbconn.isClosed())
				{
					dbconn.close();
				}
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}
		dbconn = null;
	}
}
